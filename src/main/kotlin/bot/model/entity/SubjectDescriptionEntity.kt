package bot.model.entity

import javax.persistence.*

@Entity
@Table(name = "subject_descriptions")
class SubjectDescriptionEntity {
    @Id
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "ru")
    lateinit var ru: String

    @Column(name = "en")
    lateinit var en: String

    @OneToOne
    @MapsId
    @JoinColumn(name = "sub_id")
    lateinit var subject: SubjectEntity
}