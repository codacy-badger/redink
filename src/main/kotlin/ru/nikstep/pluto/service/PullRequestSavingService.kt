package ru.nikstep.pluto.service

import org.springframework.boot.configurationprocessor.json.JSONObject
import ru.nikstep.pluto.entity.PullRequest
import ru.nikstep.pluto.repo.PullRequestRepository

class PullRequestSavingService(var pullRequestRepository: PullRequestRepository) {

    fun storePullRequest(payload: String) {
        val pullRequest = JSONObject(payload).getJSONObject("pull_request")
        val link = pullRequest.getString("commits_url")
        val userLogin = pullRequest.getJSONObject("user").getString("login")
        pullRequestRepository.save(PullRequest(userLogin, link))
    }

}