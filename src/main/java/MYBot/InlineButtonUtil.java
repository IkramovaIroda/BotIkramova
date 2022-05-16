package MYBot;


import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InlineButtonUtil {

    public static InlineKeyboardButton button(String text, String callbackData) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(text);
        inlineKeyboardButton.setCallbackData(callbackData);
        return inlineKeyboardButton;

    }


    public static InlineKeyboardButton button(String text, String callbackData, String emoji) {

        String emojiText = EmojiParser.parseToUnicode(emoji + " " + text);

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(emojiText);
        inlineKeyboardButton.setCallbackData(callbackData);
        return inlineKeyboardButton;
    }


    public static List<InlineKeyboardButton> row(InlineKeyboardButton... inlineKeyboardButtons) {

        List<InlineKeyboardButton> row = new LinkedList<>();

        row.addAll(Arrays.asList(inlineKeyboardButtons));


        return row;

    }


    public static List<List<InlineKeyboardButton>> collection(List<InlineKeyboardButton>... rows) {

        List<List<InlineKeyboardButton>> collection = new LinkedList<>();

        collection.addAll(Arrays.asList(rows));

        return collection;

    }


    public static InlineKeyboardMarkup keyboard(List<List<InlineKeyboardButton>> collection) {

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        keyboardMarkup.setKeyboard(collection);

        return keyboardMarkup;

    }


}
