package bot.model.service

import bot.model.entity.LanguageEntity

interface LanguageService {
    fun getById(id: Int): LanguageEntity
}