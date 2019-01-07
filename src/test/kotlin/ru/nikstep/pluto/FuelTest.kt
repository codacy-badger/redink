package ru.nikstep.pluto

import com.github.kittinunf.fuel.httpGet
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.nikstep.pluto.service.PullRequestSavingService

@RunWith(MockitoJUnitRunner::class)
class FuelTest {

    @Test
    fun test() {
        val responseObject = "https://api.github.com/repos/nikita715/plagiarism_test/pulls/2/commits"
            .httpGet()
            .header("Authorization" to "Bearer 6607b3c34fefa69c7b6f0d375eb9af0085e37dc0")
            .responseObject(
                PullRequestSavingService.JsonArrayDeserializer()
            )
        println(responseObject.third.get())
    }

}