package dokerplp.bot.model.repository

import dokerplp.bot.model.entity.LanguageEntity
import dokerplp.bot.model.service.LanguageService
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
interface LanguageRepository : CrudRepository<LanguageEntity, Int>, LanguageService