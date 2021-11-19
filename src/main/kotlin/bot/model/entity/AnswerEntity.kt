package bot.model.entity

import javax.persistence.*

@Entity
@Table(name = "answers")
class AnswerEntity() {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "ru")
    lateinit var ru: String

    @Column(name = "en")
    lateinit var en: String

    @OneToOne
    @MapsId
    @JoinColumn(name = "faq_id")
    lateinit var faq: FaqEntity

}