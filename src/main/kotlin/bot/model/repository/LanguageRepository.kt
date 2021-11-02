package bot.model.repository

import bot.model.entity.LanguageEntity
import bot.utli.enums.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional(readOnly = true)
interface LanguageRepository : CrudRepository<LanguageEntity, Int>{
    fun getById(id: Long): LanguageEntity
    fun save(entity: LanguageEntity)
}