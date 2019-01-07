package ru.nikstep.pluto.service

import com.github.kittinunf.fuel.httpGet
import ru.nikstep.pluto.entity.User
import java.io.File

class SimpleGithubAppService : GithubAppService {
    override fun getAccessToken(installationId: Int): String {
        return "Bearer " + "https://api.github.com/app/installations/$installationId/access_tokens".httpGet()
            .header(
                "Authorization" to getToken(),
                "Accept" to "application/vnd.github.machine-man-preview+json"
            )
            .responseObject(PullRequestSavingService.JsonObjectDeserializer()).third.get().getString("token")
    }

    override fun getAccessToken(user: User): String {
        TODO("not implemented")
    }

    override fun getToken(): String {
        val file = File("src/main/resources/keygen.rb").absolutePath
        val process = Runtime.getRuntime().exec("ruby $file")
        process.waitFor()
        return "Bearer " + process.inputStream.bufferedReader().readText()
    }

}