package ru.nikstep.pluto.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//@Entity
class Repository(val owner: User, val name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}