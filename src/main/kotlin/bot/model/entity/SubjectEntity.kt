package bot.model.entity

import javax.persistence.*

@Entity
@Table(name = "subjects")
class SubjectEntity {

    @Id
    var id: Long = 0

    @OneToOne
    @PrimaryKeyJoinColumn
    lateinit var name: SubjectNameEntity

    @OneToOne
    @PrimaryKeyJoinColumn
    lateinit var description: SubjectDescriptionEntity

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "subject_links",
        joinColumns = [JoinColumn(name = "sub_id")],
        inverseJoinColumns = [JoinColumn(name = "link_id")]
    )
    var links: List<LinkEntity>? = null

}