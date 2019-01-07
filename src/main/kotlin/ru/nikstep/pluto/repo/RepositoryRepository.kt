package ru.nikstep.pluto.repo

import org.springframework.data.jpa.repository.JpaRepository
import ru.nikstep.pluto.entity.Repository

interface RepositoryRepository : JpaRepository<Repository, Long> {
    fun findByName(name: String): Repository
}
