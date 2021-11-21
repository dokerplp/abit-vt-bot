package dokerplp.bot.model.controller

import dokerplp.bot.model.entity.SubjectEntity
import dokerplp.bot.model.repository.SubjectRepository
import dokerplp.bot.model.service.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SubjectController(
    @Autowired val repository: SubjectRepository
) : SubjectService {
    override fun findAll(): MutableIterable<SubjectEntity> = repository.findAll()
    override fun getById(id: Long): SubjectEntity? = repository.getById(id)
    override fun save(sub: SubjectEntity): SubjectEntity = repository.save(sub)
    override fun deleteAll() = repository.deleteAll()
}