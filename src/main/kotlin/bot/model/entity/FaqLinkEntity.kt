package bot.model.entity

import javax.persistence.*

@Entity
@Table(name = "faq_links")
class FaqLinkEntity {
    @Id
    @Column(name = "id")
    var id: Long = 0

    @OneToOne
    @MapsId
    @JoinColumn(name = "link_id")
    lateinit var link: LinkEntity

    @OneToOne
    @MapsId
    @JoinColumn(name = "faq_id")
    lateinit var faq: FaqEntity

}