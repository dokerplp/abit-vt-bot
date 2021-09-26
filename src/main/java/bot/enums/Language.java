package bot.enums;

import java.util.Locale;

public enum Language {
    RU(new Locale("ru", "RU"), "RU"),
    EN(Locale.US, "EN");

    private final Locale locale;
    private final String name;

    Language(Locale locale, String name) {
        this.locale = locale;
        this.name = name;
    }

    public Locale getLocale() {
        return locale;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Language convert(String lang){
        switch (lang){
            case "EN":
                return Language.EN;
            case "RU":
                return Language.RU;
            default:
                return null;
        }
    }
}
