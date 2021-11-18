package bot.model.entity

import javax.persistence.*

@Entity
@Table(name = "subject_links")
class SubjectLinkEntity {
    @Id
    @Column(name = "id")
    var id: Long = 0

    @OneToOne
    @MapsId
    @JoinColumn(name = "link_id")
    lateinit var link: LinkEntity

    @OneToOne
    @MapsId
    @JoinColumn(name = "sub_id")
    lateinit var faq: SubjectEntity
}