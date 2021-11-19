package bot.model.entity

import javax.persistence.*

@Entity
@Table(name = "subjects")
class SubjectEntity {

    @Id
    var id: Long = 0

    @OneToOne(cascade= [CascadeType.ALL])
    @PrimaryKeyJoinColumn
    lateinit var name: SubjectNameEntity

    @OneToOne(cascade= [CascadeType.ALL])
    @PrimaryKeyJoinColumn
    lateinit var description: SubjectDescriptionEntity

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(name = "subject_links",
        joinColumns = [JoinColumn(name = "sub_id")],
        inverseJoinColumns = [JoinColumn(name = "link_id")]
    )
    var links: List<LinkEntity>? = null

}