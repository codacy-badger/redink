package ru.nikstep.pluto.service

import com.github.kittinunf.fuel.httpPost

class AnalysisResultService(val githubAppService: GithubAppService) {

    fun send(repo: String) {
        val jwt = githubAppService.getToken()
        "https://api.github.com/repos/$repo/check-runs".httpPost()
            .header("Authorization" to jwt)
            .body("")
            .response()
    }

}