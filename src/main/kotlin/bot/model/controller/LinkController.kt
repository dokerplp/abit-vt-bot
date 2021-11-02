package bot.model.controller

import bot.model.entity.LanguageEntity
import bot.model.entity.LinkEntity
import bot.model.repository.LinkRepository
import bot.model.service.LinkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LinkController(@Autowired val repository: LinkRepository) : LinkService{
    override fun findAll(): MutableIterable<LinkEntity> {
        return repository.findAll()
    }
}