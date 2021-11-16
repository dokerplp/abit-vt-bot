package bot.model.repository

import bot.model.entity.FaqEntity
import bot.model.service.FaqService
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
interface FaqRepository : CrudRepository<FaqEntity, Int>, FaqService