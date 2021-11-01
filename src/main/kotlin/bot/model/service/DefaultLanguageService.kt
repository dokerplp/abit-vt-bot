package bot.model.service

import bot.model.entity.LanguageEntity
import bot.model.repository.LanguageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("languageService")
class DefaultLanguageService(@Autowired val repository: LanguageRepository) : LanguageService{
    override fun getById(id: Int): LanguageEntity {
        return repository.getById(id)
    }
}