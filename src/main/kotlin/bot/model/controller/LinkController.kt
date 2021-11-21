package bot.model.controller

import bot.model.entity.LinkEntity
import bot.model.repository.LinkRepository
import bot.model.service.LinkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LinkController(
    @Autowired val repository: LinkRepository
) : LinkService {
    override fun findAll(): MutableIterable<LinkEntity> = repository.findAll()
    override fun findAllByShow(show: Boolean): MutableIterable<LinkEntity> = repository.findAllByShow(show)
    override fun getById(id: Long): LinkEntity? = repository.getById(id)
    override fun save(entity: LinkEntity): LinkEntity = repository.save(entity)
    override fun deleteAll() = repository.deleteAll()
}