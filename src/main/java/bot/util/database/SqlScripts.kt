package bot.util.database

object SqlScripts {
    const val SELECT_LANGUAGE = "SELECT language FROM settings where id = ?;"

    const val INSERT_LANGUAGE = "INSERT INTO settings VALUES (?, ?::\"lang\");"

    const val UPDATE_LANGUAGE = "UPDATE settings SET language=?::\"lang\" WHERE id=?;"
}