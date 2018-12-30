package ru.nikstep.pluto.rest

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.nikstep.pluto.service.PullRequestSavingService

@RestController
class PullRequestController(val pullRequestSavingService: PullRequestSavingService) {

    @PostMapping(name = "/pull", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@RequestBody payload: String) {
        pullRequestSavingService.storePullRequest(payload)
    }
}
