package bot.model.controller

import bot.model.entity.LanguageEntity
import bot.model.repository.LanguageRepository
import bot.model.service.LanguageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LanguageController(
    @Autowired val repository: LanguageRepository
) : LanguageService {
    override fun getById(id: Long): LanguageEntity?= repository.getById(id)
    override fun save(entity: LanguageEntity) = repository.save(entity)
}