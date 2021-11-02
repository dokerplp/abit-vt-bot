package bot.model.controller

import bot.model.entity.LanguageEntity
import bot.model.service.DefaultLanguageService
import org.springframework.stereotype.Component
import javax.annotation.Resource

@Component
class LanguageController(@Resource(name = "languageService") val service: DefaultLanguageService) {
    fun getById(id: Long): LanguageEntity {
        return service.getById(id)
    }
    fun save(entity: LanguageEntity){
        service.save(entity)
    }
}