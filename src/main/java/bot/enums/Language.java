package bot.enums;

import java.util.Locale;

public enum Language {
    RU(new Locale("ru", "RU")),
    EN(new Locale("en", "EN"));

    private final Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}
