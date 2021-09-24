package bot.util.settings;

import bot.enums.Language;
import bot.util.Translatable;

import java.util.ArrayList;
import java.util.List;

public class Translator {
    private List<Translatable> list = new ArrayList<>();

    public void add(Translatable translatable){
        list.add(translatable);
    }

    public void translate(Language language){
        list.forEach(translatable -> translatable.translate(language));
    }
}
