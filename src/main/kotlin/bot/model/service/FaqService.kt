package bot.model.service

import bot.model.entity.FaqEntity
import org.springframework.stereotype.Service

@Service
interface FaqService {
    fun findAll(): MutableIterable<FaqEntity>
    fun save(entity: FaqEntity): FaqEntity
    fun getById(id: Long): FaqEntity
}