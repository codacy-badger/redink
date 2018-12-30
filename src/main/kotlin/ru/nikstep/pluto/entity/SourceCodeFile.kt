package ru.nikstep.pluto.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//@Entity
class SourceCodeFile(val user: User, val repo: Repository, val text: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}