package ru.nikstep.pluto.service

import ru.nikstep.pluto.entity.User

interface GithubAppService {
    fun getToken(): String
    fun getAccessToken(installationId: Int): String
    fun getAccessToken(user: User): String
}