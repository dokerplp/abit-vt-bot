package bot.enums;

import java.io.Serializable;

public enum Stickers implements Serializable {

    WELCOME_TO_THE_CLUB("CAACAgIAAxkBAAOdYUo0m_syBRShgkKxCTjJqPWLC14AAh8AA_HzcxPzDm9d0wYLCiEE");

    private final String id;

    Stickers(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return id;
    }
}
