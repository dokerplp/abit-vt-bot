package bot.utli.enums

import org.telegram.telegrambots.meta.api.methods.send.SendSticker
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import java.io.Serializable

enum class Links(private val id: String) : Serializable {
    PLAN_IVT("https://eduold-prod.itmo.dev/file/subspec/4285/09.03.01_kompyuternye_sistemy_i_tehnologii_14549.pdf"),
    PLAN_SPPO("https://eduold-prod.itmo.dev/file/subspec/4290/09.03.04_44.03.04_kompyuternye_tehnologii_v_dizayne_14546_14547.pdf"),
    PLAN_CUSTOM("https://docs.google.com/spreadsheets/d/1NlrnPsPksHXzEHFnSHtUtbJg5AENXx6pVFBevHIg-4k/edit#gid=1824976275");

    override fun toString(): String {
        return id
    }

}