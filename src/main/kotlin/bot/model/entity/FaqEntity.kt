package bot.model.entity


import javax.persistence.*

@Entity
@Table(name = "faq")
class FaqEntity() {

    @Id
    @Column(name = "id")
    var id: Long = 0

    @OneToOne
    @PrimaryKeyJoinColumn
    lateinit var question: QuestionEntity

    @OneToOne
    @PrimaryKeyJoinColumn
    lateinit var answer: AnswerEntity

    @OneToMany
    @PrimaryKeyJoinColumn
    var links: List<LinkEntity>? = null


}