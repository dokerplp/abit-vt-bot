package bot.model.entity

import javax.persistence.*

@Entity
@Table(name = "subject_descriptions")
class SubjectDescriptionEntity() {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "ru")
    lateinit var ru: String

    @Column(name = "en")
    lateinit var en: String

    @OneToOne(cascade= [CascadeType.ALL])
    @MapsId
    @JoinColumn(name = "sub_id")
    lateinit var subject: SubjectEntity

    constructor(ru: String, en: String, subject: SubjectEntity) : this() {
        this.ru = ru
        this.en = en
        this.subject = subject
    }
}
