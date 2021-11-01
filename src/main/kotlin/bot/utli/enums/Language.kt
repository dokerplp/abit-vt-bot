package bot.utli.enums



import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.type.EnumType
import java.sql.PreparedStatement
import java.sql.SQLException
import java.util.*


enum class Language(val locale: Locale, val lan: String){
    RU(Locale("ru", "RU"), "RU"),
    EN(Locale.US, "EN");

    override fun toString(): String {
        return lan
    }
}



