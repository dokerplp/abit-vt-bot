package bot.model.entity

import javax.persistence.*

@Entity
@Table(name = "links")
class LinkEntity() {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "show")
    var show: Boolean = false

    @Column(name = "text")
    lateinit var text: String

    @Column(name = "value")
    lateinit var value: String

    constructor(text: String, value: String, show: Boolean) : this() {
        this.text = text
        this.value = value
        this.show = show
    }
}