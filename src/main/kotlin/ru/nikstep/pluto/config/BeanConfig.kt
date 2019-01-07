package ru.nikstep.pluto.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.nikstep.pluto.repo.PullRequestRepository
import ru.nikstep.pluto.repo.RepositoryRepository
import ru.nikstep.pluto.service.EmptyPlagiarismService
import ru.nikstep.pluto.service.PlagiarismService
import ru.nikstep.pluto.service.PullRequestLoadingService
import ru.nikstep.pluto.service.PullRequestSavingService

@Configuration
class BeanConfig {

    @Bean
    fun pullRequestService(
        pullRequestRepository: PullRequestRepository,
        repositoryRepository: RepositoryRepository
    ): PullRequestSavingService {
        return PullRequestSavingService(pullRequestRepository, repositoryRepository)
    }

    @Bean
    fun plagiarismService(): PlagiarismService {
        return EmptyPlagiarismService()
    }

    @Bean
    fun pullRequestLoadingService(
        pullRequestRepository: PullRequestRepository,
        plagiarismService: PlagiarismService
    ): PullRequestLoadingService {
        return PullRequestLoadingService(pullRequestRepository, plagiarismService)
    }

}