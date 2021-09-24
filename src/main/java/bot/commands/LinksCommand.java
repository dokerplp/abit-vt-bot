package bot.commands;

import bot.enums.Language;
import bot.run.AbitVTBot;
import bot.util.Translatable;
import com.google.common.io.Resources;
import com.sun.research.ws.wadl.Resource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import sun.misc.ClassLoaderUtil;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.*;

public class LinksCommand implements Command, Translatable {

    private String HELP;
    private String TEXT;

    private final AbitVTBot bot;

    public LinksCommand(AbitVTBot bot) {
        this.bot = bot;
        bot.getTranslator().add(this);
        translate(Language.EN);
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

        String chatId;

        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        if (message != null) chatId = message.getChatId().toString();
        else chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        sendMessage.setChatId(chatId);
        sendMessage.setText(TEXT);

        JSONParser parser = new JSONParser();

        URL file = Resources.getResource("links.json");
        try (
                Reader reader = new FileReader(file.getFile()))
        {
            JSONArray links = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> iterator = links.iterator();

            List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

            while (iterator.hasNext()){
                JSONObject job = iterator.next();
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText((String) job.get("text"));
                button.setUrl((String) job.get("link"));
                List<InlineKeyboardButton> bl = new ArrayList<>();
                bl.add(button);
                buttons.add(bl);
            }

            InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(buttons);

            sendMessage.setReplyMarkup(keyboard);

            bot.execute(sendMessage);

        } catch (IOException | ParseException e) {
            System.out.println("error");
        }

    }

    @Override
    public String help() {
        return HELP;
    }

    @Override
    public String name() {
        return "/links";
    }

    @Override
    public void translate(Language language) {
        ResourceBundle helpBundle = ResourceBundle.getBundle("help", language.getLocale());
        ResourceBundle otherBundle = ResourceBundle.getBundle("other", language.getLocale());

        HELP = helpBundle.getString("links");
        TEXT = otherBundle.getString("links.text");
    }
}
