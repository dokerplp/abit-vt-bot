package dokerplp.bot

import lombok.extern.slf4j.Slf4j
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
@Slf4j
class App

fun main(args: Array<String>) {
    val context = runApplication<App>(*args)
    Thread(context.getBean(AdminPanel::class.java)).start()
}
