package ru.nikstep.pluto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlutoApplication

fun main(args: Array<String>) {
    runApplication<PlutoApplication>(*args)
}
