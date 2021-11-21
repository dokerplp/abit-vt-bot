package bot.model.service

import bot.model.entity.LinkEntity
import org.springframework.stereotype.Service

@Service
interface LinkService {
    fun findAll(): MutableIterable<LinkEntity>
    fun findAllByShow(show: Boolean): MutableIterable<LinkEntity>
    fun getById(id: Long): LinkEntity?
    fun save(entity: LinkEntity): LinkEntity
    fun deleteAll()
}