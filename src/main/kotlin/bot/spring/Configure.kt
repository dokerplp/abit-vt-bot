package bot.spring

import bot.run.Bot
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configure {

    @Bean
    fun getBot(): Bot {
        return Bot()
    }
}