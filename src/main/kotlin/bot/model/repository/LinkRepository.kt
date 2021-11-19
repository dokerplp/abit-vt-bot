package bot.model.repository

import bot.model.entity.LinkEntity
import bot.model.service.LinkService
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
interface LinkRepository : CrudRepository<LinkEntity, Int>, LinkService