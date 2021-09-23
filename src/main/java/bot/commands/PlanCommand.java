package bot.commands;

import bot.run.AbitVTBot;
import com.google.common.io.Resources;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PlanCommand implements Command{

    private final AbitVTBot bot;

    public PlanCommand(AbitVTBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

        String chatId;

        Message message = update.getMessage();
        SendDocument sendDocument = new SendDocument();
        if (message != null) chatId = message.getChatId().toString();
        else chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        sendDocument.setChatId(chatId);

        InputFile plans = new InputFile();
        plans.setMedia("https://eduold-prod.itmo.dev/file/subspec/4285/09.03.01_kompyuternye_sistemy_i_tehnologii_14549.pdf");
        sendDocument.setDocument(plans);

        sendDocument.setAllowSendingWithoutReply(true);
        bot.execute(sendDocument);

        plans.setMedia("https://eduold-prod.itmo.dev/file/subspec/4290/09.03.04_44.03.04_kompyuternye_tehnologii_v_dizayne_14546_14547.pdf");

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Custom syllabus");
        button.setUrl("https://docs.google.com/spreadsheets/d/1NlrnPsPksHXzEHFnSHtUtbJg5AENXx6pVFBevHIg-4k/edit#gid=1824976275");

        List<InlineKeyboardButton> bl = new ArrayList<>();
        bl.add(button);
        buttons.add(bl);

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(buttons);

        sendDocument.setReplyMarkup(keyboard);

        bot.execute(sendDocument);
    }

    @Override
    public String help() {
        return "/plan - get syllabus of vt";
    }

    @Override
    public String name() {
        return "/plan";
    }
}
