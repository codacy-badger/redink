package ru.nikstep.pluto.entity

import javax.persistence.*

@Entity
class SourceCode(
    @ManyToOne
    @JoinColumn(name = "p_user")
    val user: User,
    @ManyToOne
    @JoinColumn(name = "repo")
    val repo: Repository,
    val fileName: String,
    val fileText: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}