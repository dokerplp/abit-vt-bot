package bot

import bot.model.controller.FaqController
import bot.model.controller.LinkController
import bot.model.entity.AnswerEntity
import bot.model.entity.FaqEntity
import bot.model.entity.LinkEntity
import bot.model.entity.QuestionEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList
import kotlin.streams.toList

@Component
class AdminPanel(
    @Autowired val linkController: LinkController,
    @Autowired val faqController: FaqController
) : Runnable {

    private val scanner = Scanner(System.`in`)

    override fun run() {
        while (true) {
            when (scanner.nextLine()) {
                "faq" -> faq()
                "sub" -> sub()
                "link" -> link()
            }
        }
    }

    private fun longCastable(str: String): Long {
        return try {
            str.toLong()
        } catch (e: NumberFormatException) {
            -1L
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
        print("Link ids: ")
        val links: List<Long> = scanner.nextLine().split(" ").stream().map { longCastable(it) }.filter { it != -1L }.toList()

        val list: ArrayList<LinkEntity> = ArrayList()
        for (link in links) {
            val li = linkController.getById(link)
            if (li != null) list.add(li)
        }

        val faq = faqController.save(FaqEntity())
        faq.answer = AnswerEntity(en = aen, ru = aru, faq = faq)
        faq.question = QuestionEntity(en = qen, ru = qru, faq = faq)
        faq.links = list
        faqController.save(faq)
    }

    private fun sub() {

    }

    private fun link() {

    }
}