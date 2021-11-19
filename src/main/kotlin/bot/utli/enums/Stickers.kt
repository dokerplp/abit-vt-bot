package bot.utli.enums

import java.io.Serializable

enum class Stickers(private val id: String) : Serializable {
    WELCOME_TO_THE_CLUB("CAACAgIAAxkBAAOdYUo0m_syBRShgkKxCTjJqPWLC14AAh8AA_HzcxPzDm9d0wYLCiEE"),
    VOICE("CAACAgIAAxkBAAIBMWFKc0fe3iVLjOG5iBrXuYb1e6EjAAIXAAOr26Y545BSbBihquIhBA"),
    KOTIKI("CAACAgIAAxkBAAICp2FQwwXFjFrrzyHbDBrTQprgQWiQAAIrAAP2zkAb8nPTZBjynH8hBA");

    override fun toString(): String {
        return id
    }
}