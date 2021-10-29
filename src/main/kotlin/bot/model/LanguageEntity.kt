package bot.model

import lombok.Getter
import lombok.Setter
import org.intellij.lang.annotations.Language
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "settings")
@Setter
@Getter
class LanguageEntity {

    @Id
    private var id: Long = TODO("initialize me")

    @Column(name = "language")
    private var language: Lan
}