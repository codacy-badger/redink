package ru.nikstep.pluto.repo

import org.springframework.data.jpa.repository.JpaRepository
import ru.nikstep.pluto.entity.Repository
import ru.nikstep.pluto.entity.SourceCode
import ru.nikstep.pluto.entity.User

interface SourceCodeRepository : JpaRepository<SourceCode, Long> {
    fun findByUserAndRepoAndFileName(user: User, repo: Repository, fileName: String): SourceCode?
}