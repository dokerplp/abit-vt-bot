package bot.model.controller

import bot.model.entity.LanguageEntity
import bot.model.repository.LanguageRepository
import bot.model.service.LanguageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LanguageController(@Autowired val repository: LanguageRepository) : LanguageService {
    override fun getById(id: Long): LanguageEntity {
        return repository.getById(id)
    }

    override fun save(entity: LanguageEntity) {
        return repository.save(entity)
    }

}