package bot.model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "links")
class LinkEntity {
    @Id
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "text")
    var text: String = ""

    @Column(name = "value")
    var value: String = ""
}