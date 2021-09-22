package bot.test;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;

public class StickerTest {
    public static void getStickerID(Update update){
        if (update.hasMessage()){
            Message message = update.getMessage();
            if (message.hasSticker()){
                Sticker sticker = message.getSticker();
                System.out.println(sticker.getFileId());
            }
        }
    }
}
