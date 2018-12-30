package ru.nikstep.pluto.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.nikstep.pluto.repo.PullRequestRepository
import ru.nikstep.pluto.service.PullRequestService

@Configuration
class BeanConfig {

    @Bean
    fun pullRequestService(pullRequestRepository: PullRequestRepository): PullRequestService {
        return PullRequestService(pullRequestRepository)
    }

}