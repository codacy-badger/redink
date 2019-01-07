package ru.nikstep.pluto.service

import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import org.apache.commons.codec.binary.Base64
import org.apache.http.impl.client.HttpClients
import org.springframework.boot.configurationprocessor.json.JSONArray
import org.springframework.boot.configurationprocessor.json.JSONObject
import ru.nikstep.pluto.repo.PullRequestRepository
import ru.nikstep.pluto.repo.RepositoryRepository
import java.lang.String.format
import java.nio.charset.Charset


class PullRequestSavingService(
    val pullRequestRepository: PullRequestRepository,
    val repositoryRepository: RepositoryRepository,
    val sourceCodeService: SourceCodeService
) {

    val fileLinkPattern = "https://api.github.com/repos/%s/contents/%s?ref=%s"
    val httpclient = HttpClients.createDefault()

    fun storePullRequest(payload: String) {
        val jsonPayload = JSONObject(payload)
        val pullRequest = jsonPayload.getJSONObject("pull_request")
        val creatorName = pullRequest.getJSONObject("user").getString("login")

        val branchName = pullRequest.getJSONObject("head").getString("ref")
        val repoName = jsonPayload.getJSONObject("repository").getString("full_name")

        val filePatterns = repositoryRepository.findByName(repoName).filePatterns

        for (fileName in filePatterns) {
            val fileResponse = getObjectResponse(format(fileLinkPattern, repoName, fileName, branchName))
            val fileData = String(Base64.decodeBase64(fileResponse.getString("content")))
            sourceCodeService.save(creatorName, repoName, fileName, fileData)
        }
    }

    fun getArrayResponse(link: String): JSONArray {
        val responseObject = link.httpGet()
            .header("Authorization" to "Bearer 6607b3c34fefa69c7b6f0d375eb9af0085e37dc0")
            .responseObject(JsonArrayDeserializer())
        return responseObject.third.get()
    }

    fun getObjectResponse(link: String): JSONObject {
        val responseObject = link.httpGet()
            .header("Authorization" to "Bearer 6607b3c34fefa69c7b6f0d375eb9af0085e37dc0")
            .responseObject(JsonObjectDeserializer())
        return responseObject.third.get()
    }

    class JsonObjectDeserializer(private val charset: Charset = Charsets.UTF_8) : ResponseDeserializable<JSONObject> {
        override fun deserialize(response: Response): JSONObject = JSONObject(String(response.data, charset))
    }

    class JsonArrayDeserializer(private val charset: Charset = Charsets.UTF_8) : ResponseDeserializable<JSONArray> {
        override fun deserialize(response: Response): JSONArray = JSONArray(String(response.data, charset))
    }

}