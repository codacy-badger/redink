package ru.nikstep.pluto.service

import ru.nikstep.pluto.repo.PullRequestRepository

class PullRequestLoadingService(
    val pullRequestRepository: PullRequestRepository,
    val plagiarismService: PlagiarismService
) {

    fun processPullRequests() {
        val pullRequests = pullRequestRepository.findAll()
        pullRequests.forEach {
            plagiarismService.analyze(it)
            pullRequestRepository.delete(it)
        }
    }

}
