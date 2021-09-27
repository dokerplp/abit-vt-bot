package bot.commands;

import bot.run.AbitVTBot;
import bot.util.CommandsUtil;
import bot.util.Log;
import com.google.common.io.Resources;
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

import java.io.*;
import java.net.URL;
import java.util.*;

public class LinksCommand implements Command{

    private Log log = new Log();

    private String getTEXT(String chatId){
        ResourceBundle otherBundle = ResourceBundle.getBundle("other", bot.getSql().selectLanguage(chatId).getLocale());
        return otherBundle.getString("links.text");
    }

    private final AbitVTBot bot;

    public LinksCommand(AbitVTBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

        String chatId = CommandsUtil.getChatId(update);


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(getTEXT(chatId));

        JSONParser parser = new JSONParser();

        InputStream in = getClass().getResourceAsStream("/links.json");
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        )
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
            log.userException(e, update);
        }

    }

    @Override
    public String help(String chatId) {
        ResourceBundle helpBundle = ResourceBundle.getBundle("help", bot.getSql().selectLanguage(chatId).getLocale());
        return helpBundle.getString("links");
    }

    @Override
    public String name() {
        return "/links";
    }

}
