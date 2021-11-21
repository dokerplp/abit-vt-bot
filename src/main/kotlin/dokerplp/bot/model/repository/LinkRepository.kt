package dokerplp.bot.model.repository

import dokerplp.bot.model.entity.LinkEntity
import dokerplp.bot.model.service.LinkService
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
interface LinkRepository : CrudRepository<LinkEntity, Int>, LinkService