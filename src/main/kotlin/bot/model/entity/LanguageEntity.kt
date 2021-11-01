package bot.model.entity

import bot.utli.enums.Language
import lombok.Getter
import lombok.Setter
import org.hibernate.annotations.Type
import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "settings")
class LanguageEntity() {

    @Id
    @Column(name = "id")
    var id: Int = 0
        private set

    @Column(name = "language")
    var language: String = "EN"
        private set
}

