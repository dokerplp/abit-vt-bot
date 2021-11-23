package dokerplp.bot.model.entity

import dokerplp.bot.utli.enums.Language
import javax.persistence.*


@Entity
@Table(name = "settings")
class LanguageEntity() {

    @Id
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    var language: Language = Language.EN

    constructor(id: Long, language: Language) : this(){
        this.id = id
        this.language = language
    }

}

