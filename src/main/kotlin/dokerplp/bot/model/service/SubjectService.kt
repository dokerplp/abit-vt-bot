package dokerplp.bot.model.service

import dokerplp.bot.model.entity.SubjectEntity
import org.springframework.stereotype.Service

@Service
interface SubjectService {
    fun findAll(): MutableIterable<SubjectEntity>
    fun getById(id: Long): SubjectEntity?
    fun save(sub: SubjectEntity): SubjectEntity
    fun deleteAll()
}