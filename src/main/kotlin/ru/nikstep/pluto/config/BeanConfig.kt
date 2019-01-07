package ru.nikstep.pluto.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.nikstep.pluto.repo.PullRequestRepository
import ru.nikstep.pluto.repo.RepositoryRepository
import ru.nikstep.pluto.repo.SourceCodeRepository
import ru.nikstep.pluto.repo.UserRepository
import ru.nikstep.pluto.service.*

@Configuration
class BeanConfig {

    @Bean
    fun sourceCodeService(
        sourceCodeRepository: SourceCodeRepository,
        userRepository: UserRepository,
        repositoryRepository: RepositoryRepository
    ): SourceCodeService {
        return SourceCodeService(sourceCodeRepository, userRepository, repositoryRepository)
    }

    @Bean
    fun pullRequestService(
        pullRequestRepository: PullRequestRepository,
        repositoryRepository: RepositoryRepository,
        sourceCodeService: SourceCodeService
    ): PullRequestSavingService {
        return PullRequestSavingService(pullRequestRepository, repositoryRepository, sourceCodeService)
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