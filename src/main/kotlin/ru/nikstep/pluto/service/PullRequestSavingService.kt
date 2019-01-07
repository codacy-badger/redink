package ru.nikstep.pluto.service

import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import org.apache.commons.codec.binary.Base64
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.ResponseHandler
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.springframework.boot.configurationprocessor.json.JSONArray
import org.springframework.boot.configurationprocessor.json.JSONObject
import ru.nikstep.pluto.repo.PullRequestRepository
import ru.nikstep.pluto.repo.RepositoryRepository
import java.io.InputStream
import java.nio.charset.Charset


class PullRequestSavingService(
    val pullRequestRepository: PullRequestRepository,
    val repositoryRepository: RepositoryRepository
) {

    val httpclient = HttpClients.createDefault()

    fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
        return this.bufferedReader(charset).use { it.readText() }
    }

    fun storePullRequest(payload: String) {
        val jsonPayload = JSONObject(payload)
        val pullRequest = jsonPayload.getJSONObject("pull_request")
        val link = pullRequest.getString("commits_url")
        val userLogin = pullRequest.getJSONObject("user").getString("login")

        val ref = pullRequest.getJSONObject("head").getString("ref")
        val repo = jsonPayload.getJSONObject("repository").getString("full_name")

        val fileNames = repositoryRepository.findByName(repo).filePatterns

        for (fileName in fileNames!!) {
            val linkk = "https://api.github.com/repos/$repo/contents/$fileName?ref=$ref"
            val responseMessageCommit =
                getObjectResponse(linkk)
            val res = responseMessageCommit.getString("content")

            val data = String(Base64.decodeBase64(res))

            print(data)
        }

        //get file
//        val file = (responseMessageCommit.getJSONArray("files").get(0) as JSONObject).getString("raw_url")
//        val responseFile = getStringResponse(file)

//        pullRequestRepository.save(PullRequest(userLogin, link))
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

    var responseHandler: ResponseHandler<String> = ResponseHandler<String> { response ->
        val status = response.getStatusLine().getStatusCode()
        if (status in 200..299) {
            val entity = response.getEntity()
            if (entity != null) EntityUtils.toString(entity) else null
        } else {
            throw ClientProtocolException("Unexpected response status: $status")
        }
    }


    class JsonObjectDeserializer(private val charset: Charset = Charsets.UTF_8) : ResponseDeserializable<JSONObject> {
        override fun deserialize(response: Response): JSONObject = JSONObject(String(response.data, charset))
    }

    class JsonArrayDeserializer(private val charset: Charset = Charsets.UTF_8) : ResponseDeserializable<JSONArray> {
        override fun deserialize(response: Response): JSONArray = JSONArray(String(response.data, charset))
    }

}