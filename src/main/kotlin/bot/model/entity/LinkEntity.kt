package bot.model.entity

import javax.persistence.*

@Entity
@Table(name = "links")
open class LinkEntity() {
    @Id
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "text")
    var text: String = ""

    @Column(name = "value")
    var value: String = ""

    @OneToOne
    @PrimaryKeyJoinColumn
    lateinit var subject: SubjectLinkEntity

    @OneToOne
    @PrimaryKeyJoinColumn
    lateinit var faq: FaqLinkEntity

    constructor(text: String) : this(){
        this.text = text
    }

    constructor(subject: SubjectLinkEntity, text: String) : this(text) {
        this.subject = subject
    }

    constructor(faq: FaqLinkEntity, text: String) : this(text) {
        this.faq = faq
    }

}