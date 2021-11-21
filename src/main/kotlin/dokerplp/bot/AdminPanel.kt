package dokerplp.bot

import dokerplp.bot.model.controller.FaqController
import dokerplp.bot.model.controller.LinkController
import dokerplp.bot.model.controller.SubjectController
import dokerplp.bot.model.entity.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList
import kotlin.streams.toList

@Component
class AdminPanel(
    @Autowired val linkController: LinkController,
    @Autowired val faqController: FaqController,
    @Autowired val subjectController: SubjectController
) : Runnable {

    private val scanner = Scanner(System.`in`)

    override fun run() {
        try {
            while (true) {
                when (scanner.nextLine()) {
                    "faq" -> faq()
                    "sub" -> sub()
                    "link" -> link()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun faq() {
        println("Question:")
        print("\tRussian: ")
        val qru = scanner.nextLine()
        print("\tEnglish: ")
        val qen = scanner.nextLine()
        println("Answer:")
        print("\tRussian: ")
        val aru = scanner.nextLine()
        print("\tEnglish: ")
        val aen = scanner.nextLine()

        val links = links()

        val faq = faqController.save(FaqEntity())
        faq.answer = AnswerEntity(en = aen, ru = aru, faq = faq)
        faq.question = QuestionEntity(en = qen, ru = qru, faq = faq)
        faq.links = links
        faqController.save(faq)
    }

    private fun sub() {
        println("Name:")
        print("\tRussian: ")
        val nru = scanner.nextLine()
        print("\tEnglish: ")
        val nen = scanner.nextLine()
        println("Description:")
        print("\tRussian: ")
        val dru = scanner.nextLine()
        print("\tEnglish: ")
        val den = scanner.nextLine()

        val links = links()

        val sub = subjectController.save(SubjectEntity())
        sub.name = SubjectNameEntity(ru = nru, en = nen, subject = sub)
        sub.description = SubjectDescriptionEntity(ru = dru, en = den, subject = sub)
        sub.links = links
        subjectController.save(sub)
    }

    private fun link() {
        println("Link:")
        print("\tText: ")
        val text = scanner.nextLine()
        print("\tValue: ")
        val value = scanner.nextLine()
        print("\tShow: ")
        val show = scanner.nextLine()

        linkController.save(LinkEntity(text = text, value = value, show = show.toBoolean()))
    }

    private fun longCastable(str: String): Long {
        return try {
            str.toLong()
        } catch (e: NumberFormatException) {
            -1L
        }
    }

    private fun links(): ArrayList<LinkEntity> {
        print("Link ids: ")
        val links: List<Long> = scanner.nextLine().split(" ").stream().map { longCastable(it) }.filter { it != -1L }.toList()

        val list: ArrayList<LinkEntity> = ArrayList()
        for (link in links) {
            val li = linkController.getById(link)
            if (li != null) list.add(li)
        }
        return list
    }
}