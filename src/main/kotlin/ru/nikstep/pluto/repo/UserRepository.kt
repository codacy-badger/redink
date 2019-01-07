package ru.nikstep.pluto.repo

import org.springframework.data.jpa.repository.JpaRepository
import ru.nikstep.pluto.entity.User

interface UserRepository : JpaRepository<User, Long> {

    fun findByName(name: String): User

}