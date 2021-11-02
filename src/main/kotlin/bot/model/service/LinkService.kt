package bot.model.service

import bot.model.entity.LanguageEntity
import bot.model.entity.LinkEntity

interface LinkService {
    fun findAll(): MutableIterable<LinkEntity>
}