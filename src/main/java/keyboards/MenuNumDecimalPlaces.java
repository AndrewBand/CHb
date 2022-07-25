package keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import settings.*;

import java.util.ArrayList;
import java.util.List;

public class MenuNumDecimalPlaces {
    public static InlineKeyboardMarkup keyboard(long chatId) {
        Setting userSetting = Settings.settings.get(chatId);
        int selectedNumDecPlaces = userSetting.getNumberOfDecimalPlaces();

        List<List<InlineKeyboardButton>> keyboard =  new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow4 = new ArrayList<>();
        InlineKeyboardButton buttonNumberOfDecimalPlaces2 = InlineKeyboardButton.builder()
                .text(NumberOfDecimalPlaces.TWO.getIntNumber()+ getButtonStatus(NumberOfDecimalPlaces.TWO.getIntNumber(), selectedNumDecPlaces))
                .callbackData(NumberOfDecimalPlaces.TWO.getNameDecPlaces())
                .build();
        InlineKeyboardButton buttonNumberOfDecimalPlaces3 = InlineKeyboardButton.builder()
                .text(NumberOfDecimalPlaces.THREE.getIntNumber()+getButtonStatus(NumberOfDecimalPlaces.THREE.getIntNumber(), selectedNumDecPlaces))
                .callbackData(NumberOfDecimalPlaces.THREE.getNameDecPlaces())
                .build();
        InlineKeyboardButton buttonNumberOfDecimalPlaces4 = InlineKeyboardButton.builder()
                .text(NumberOfDecimalPlaces.FOUR.getIntNumber()+getButtonStatus(NumberOfDecimalPlaces.FOUR.getIntNumber(), selectedNumDecPlaces))
                .callbackData(NumberOfDecimalPlaces.FOUR.getNameDecPlaces())
                .build();
        InlineKeyboardButton buttonHome = InlineKeyboardButton.builder()
                .text(Buttons.BACK_HOME.getName())
                .callbackData(Buttons.BACK_HOME.getNameEN())
                .build();
        InlineKeyboardButton buttonBackToSetting = InlineKeyboardButton.builder()
                .text(Buttons.BACK_TO_SETTINGS.getName())
                .callbackData(Buttons.BACK_TO_SETTINGS.getNameEN())
                .build();
        keyboardMSetRow1.add(buttonNumberOfDecimalPlaces2);
        keyboardMSetRow2.add(buttonNumberOfDecimalPlaces3);
        keyboardMSetRow3.add(buttonNumberOfDecimalPlaces4);
        keyboardMSetRow4.add(buttonHome);
        keyboardMSetRow4.add(buttonBackToSetting);
        keyboard.add(keyboardMSetRow1);
        keyboard.add(keyboardMSetRow2);
        keyboard.add(keyboardMSetRow3);
        keyboard.add(keyboardMSetRow4);

        return InlineKeyboardMarkup.builder().keyboard(keyboard).build();
    }
    private static String getButtonStatus(int currentNumDecPlaces, int selectedNumDecPlaces) {
        if (currentNumDecPlaces == selectedNumDecPlaces) {
            return "✅";
        }
        return "";
    }
}

