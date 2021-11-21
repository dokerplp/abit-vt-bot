package dokerplp.bot.model.controller

import dokerplp.bot.model.entity.FaqEntity
import dokerplp.bot.model.repository.FaqRepository
import dokerplp.bot.model.service.FaqService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FaqController(
    @Autowired val repository: FaqRepository
) : FaqService {
    override fun findAll(): MutableIterable<FaqEntity> = repository.findAll()
    override fun save(entity: FaqEntity) = repository.save(entity)
    override fun getById(id: Long): FaqEntity? = repository.getById(id)
    override fun deleteAll() = repository.deleteAll()
}