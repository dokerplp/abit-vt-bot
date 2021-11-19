package bot.model.service

import bot.model.entity.FaqEntity
import bot.model.entity.LanguageEntity


interface FaqService {
    fun findAll(): MutableIterable<FaqEntity>
    fun save(entity: FaqEntity)
    fun getById(id: Long): FaqEntity
}