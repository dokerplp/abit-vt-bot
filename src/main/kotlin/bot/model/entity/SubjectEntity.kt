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

    @OneToMany
    @PrimaryKeyJoinColumn
    lateinit var links: List<SubjectLinkEntity>

}