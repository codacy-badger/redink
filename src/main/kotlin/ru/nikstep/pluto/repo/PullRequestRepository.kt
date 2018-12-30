package ru.nikstep.pluto.repo

import org.springframework.data.jpa.repository.JpaRepository
import ru.nikstep.pluto.entity.PullRequest

interface PullRequestRepository : JpaRepository<PullRequest, Long>