package bot.model.repository

import bot.model.entity.LanguageEntity
import bot.model.entity.LinkEntity
import bot.model.service.LanguageService
import bot.model.service.LinkService
import org.springframework.data.repository.CrudRepository

interface LinkRepository : CrudRepository<LinkEntity, Int>, LinkService