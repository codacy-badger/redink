package ru.nikstep.pluto.service

import ru.nikstep.pluto.entity.User

interface GithubAppService {
    fun getToken(): String
    fun getAccessToken(installationId: Int = 447213): String
    fun getAccessToken(user: User): String
}