package bot.model.service

import bot.model.entity.FaqEntity
import bot.model.entity.LinkEntity
import bot.model.entity.SubjectEntity

interface SubjectService {
    fun findAll(): MutableIterable<SubjectEntity>
    fun getById(id: Long): SubjectEntity
}