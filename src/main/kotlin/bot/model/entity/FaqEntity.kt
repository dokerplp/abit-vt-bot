package bot.model.entity

import javax.persistence.*

@Entity
@Table(name = "faq")
class FaqEntity() {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @OneToOne
    @PrimaryKeyJoinColumn
    lateinit var question: QuestionEntity

    @OneToOne
    @PrimaryKeyJoinColumn
    lateinit var answer: AnswerEntity

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "faq_links",
        joinColumns = [JoinColumn(name = "faq_id")],
        inverseJoinColumns = [JoinColumn(name = "link_id")]
    )
    var links: List<LinkEntity>? = null


}