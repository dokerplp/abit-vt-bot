package bot.model.entity

import javax.persistence.*

@Entity
@Table(name = "links")
class LinkEntity() {
    @Id
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "text")
    var text: String = ""

    @Column(name = "value")
    var value: String = ""

    @ManyToOne
    @MapsId
    @JoinColumn(name = "faq_id")
    var faq: FaqEntity? = null


}