package ru.nikstep.redink

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import ru.nikstep.redink.repo.RepositoryRepository

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RepoRepoTest {

    @Autowired
    lateinit var repositoryRepository: RepositoryRepository

    @Test
    fun test() {
        repositoryRepository.findAll().get(0).filePatterns
    }

}
