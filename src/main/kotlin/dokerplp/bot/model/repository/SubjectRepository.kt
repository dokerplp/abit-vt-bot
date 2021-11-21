package dokerplp.bot.model.repository

import dokerplp.bot.model.entity.SubjectEntity
import dokerplp.bot.model.service.SubjectService
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
interface SubjectRepository : CrudRepository<SubjectEntity, Long>, SubjectService