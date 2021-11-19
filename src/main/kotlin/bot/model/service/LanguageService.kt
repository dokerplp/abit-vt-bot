package bot.model.service

import bot.model.entity.LanguageEntity
import org.springframework.stereotype.Service

@Service
interface LanguageService {
    fun getById(id: Long): LanguageEntity
    fun save(entity: LanguageEntity)
}