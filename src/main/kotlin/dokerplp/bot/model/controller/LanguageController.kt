package dokerplp.bot.model.controller

import dokerplp.bot.model.entity.LanguageEntity
import dokerplp.bot.model.repository.LanguageRepository
import dokerplp.bot.model.service.LanguageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LanguageController(
    @Autowired val repository: LanguageRepository
) : LanguageService {
    override fun getById(id: Long): LanguageEntity?= repository.getById(id)
    override fun save(entity: LanguageEntity) = repository.save(entity)
}