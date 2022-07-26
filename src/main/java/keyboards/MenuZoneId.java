package keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import settings.Buttons;
import settings.Setting;
import settings.Settings;
import settings.ZoneId;

import java.util.ArrayList;
import java.util.List;

public class MenuZoneId {
    public static InlineKeyboardMarkup keyboard(Long chatId) {
        Setting userSetting = Settings.settings.get(chatId);
        ZoneId selectedZoneID = userSetting.getZoneId();
        List<List<InlineKeyboardButton>> keyboardMZoneId = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMZoneIdRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMZoneIdRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMZoneIdRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMZoneIdRow4 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMZoneIdRow5 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMZoneIdRow6 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMZoneIdRow7 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMZoneIdRow8 = new ArrayList<>();
        InlineKeyboardButton buttonZoneIdOne = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_ONE.getNameZone() + getButtonStatus(ZoneId.UTC_ONE, selectedZoneID))
                .callbackData(ZoneId.UTC_ONE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdTwo = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_TWO.getNameZone() + getButtonStatus(ZoneId.UTC_TWO, selectedZoneID))
                .callbackData(ZoneId.UTC_TWO.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdThree = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_THREE.getNameZone() + getButtonStatus(ZoneId.UTC_THREE, selectedZoneID))
                .callbackData(ZoneId.UTC_THREE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdFour = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_FOUR.getNameZone() + getButtonStatus(ZoneId.UTC_FOUR, selectedZoneID))
                .callbackData(ZoneId.UTC_FOUR.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdFive = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_FIVE.getNameZone() + getButtonStatus(ZoneId.UTC_FIVE, selectedZoneID))
                .callbackData(ZoneId.UTC_FIVE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdSix = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_SIX.getNameZone() + getButtonStatus(ZoneId.UTC_SIX, selectedZoneID))
                .callbackData(ZoneId.UTC_SIX.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdSeven = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_SEVEN.getNameZone() + getButtonStatus(ZoneId.UTC_SEVEN, selectedZoneID))
                .callbackData(ZoneId.UTC_SEVEN.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdEight = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_EIGHT.getNameZone() + getButtonStatus(ZoneId.UTC_EIGHT, selectedZoneID))
                .callbackData(ZoneId.UTC_EIGHT.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdNine = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_NINE.getNameZone() + getButtonStatus(ZoneId.UTC_NINE, selectedZoneID))
                .callbackData(ZoneId.UTC_NINE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdTen = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_TEN.getNameZone() + getButtonStatus(ZoneId.UTC_TEN, selectedZoneID))
                .callbackData(ZoneId.UTC_TEN.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdEleven = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_ELEVEN.getNameZone() + getButtonStatus(ZoneId.UTC_ELEVEN, selectedZoneID))
                .callbackData(ZoneId.UTC_ELEVEN.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdTwelve = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_TWELVE.getNameZone() + getButtonStatus(ZoneId.UTC_TWELVE, selectedZoneID))
                .callbackData(ZoneId.UTC_TWELVE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMOne = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_MINUS_ONE.getNameZone() + getButtonStatus(ZoneId.UTC_MINUS_ONE, selectedZoneID))
                .callbackData(ZoneId.UTC_MINUS_ONE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMTwo = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_MINUS_TWO.getNameZone() + getButtonStatus(ZoneId.UTC_MINUS_TWO, selectedZoneID))
                .callbackData(ZoneId.UTC_MINUS_TWO.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMThree = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_MINUS_THREE.getNameZone() + getButtonStatus(ZoneId.UTC_MINUS_THREE, selectedZoneID))
                .callbackData(ZoneId.UTC_MINUS_THREE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMFour = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_MINUS_FOUR.getNameZone() + getButtonStatus(ZoneId.UTC_MINUS_FOUR, selectedZoneID))
                .callbackData(ZoneId.UTC_MINUS_FOUR.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMFive = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_MINUS_FIVE.getNameZone() + getButtonStatus(ZoneId.UTC_MINUS_FIVE, selectedZoneID))
                .callbackData(ZoneId.UTC_MINUS_FIVE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMSix = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_MINUS_SIX.getNameZone() + getButtonStatus(ZoneId.UTC_MINUS_SIX, selectedZoneID))
                .callbackData(ZoneId.UTC_MINUS_SIX.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMSeven = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_MINUS_SEVEN.getNameZone() + getButtonStatus(ZoneId.UTC_MINUS_SEVEN, selectedZoneID))
                .callbackData(ZoneId.UTC_MINUS_SEVEN.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdOMEight = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_MINUS_EIGHT.getNameZone() + getButtonStatus(ZoneId.UTC_MINUS_EIGHT, selectedZoneID))
                .callbackData(ZoneId.UTC_MINUS_EIGHT.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMNine = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_MINUS_NINE.getNameZone() + getButtonStatus(ZoneId.UTC_MINUS_NINE, selectedZoneID))
                .callbackData(ZoneId.UTC_MINUS_NINE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMTen = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_MINUS_TEN.getNameZone() + getButtonStatus(ZoneId.UTC_MINUS_TEN, selectedZoneID))
                .callbackData(ZoneId.UTC_MINUS_TEN.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMEleven = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_MINUS_ELEVEN.getNameZone() + getButtonStatus(ZoneId.UTC_MINUS_ELEVEN, selectedZoneID))
                .callbackData(ZoneId.UTC_MINUS_ELEVEN.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMTwelve = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_MINUS_TWELVE.getNameZone() + getButtonStatus(ZoneId.UTC_MINUS_TWELVE, selectedZoneID))
                .callbackData(ZoneId.UTC_MINUS_TWELVE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdZero = InlineKeyboardButton.builder()
                .text(ZoneId.UTC_ZERO.getNameZone() + getButtonStatus(ZoneId.UTC_ZERO, selectedZoneID))
                .callbackData(ZoneId.UTC_ZERO.getNameZone())
                .build();
        InlineKeyboardButton buttonHome = InlineKeyboardButton.builder()
                .text(Buttons.BACK_TO_START.getName())
                .callbackData(Buttons.BACK_TO_START.getNameEN())
                .build();
        InlineKeyboardButton buttonBackToSetting = InlineKeyboardButton.builder()
                .text(Buttons.BACK_TO_SETTINGS.getName())
                .callbackData(Buttons.BACK_TO_SETTINGS.getNameEN())
                .build();

        keyboardMZoneIdRow1.add(buttonZoneIdOne);
        keyboardMZoneIdRow1.add(buttonZoneIdTwo);
        keyboardMZoneIdRow1.add(buttonZoneIdThree);
        keyboardMZoneIdRow1.add(buttonZoneIdFour);

        keyboardMZoneIdRow2.add(buttonZoneIdFive);
        keyboardMZoneIdRow2.add(buttonZoneIdSix);
        keyboardMZoneIdRow2.add(buttonZoneIdSeven);
        keyboardMZoneIdRow2.add(buttonZoneIdEight);

        keyboardMZoneIdRow3.add(buttonZoneIdNine);
        keyboardMZoneIdRow3.add(buttonZoneIdTen);
        keyboardMZoneIdRow3.add(buttonZoneIdEleven);
        keyboardMZoneIdRow3.add(buttonZoneIdTwelve);

        keyboardMZoneIdRow4.add(buttonZoneIdMOne);
        keyboardMZoneIdRow4.add(buttonZoneIdMTwo);
        keyboardMZoneIdRow4.add(buttonZoneIdMThree);
        keyboardMZoneIdRow4.add(buttonZoneIdMFour);

        keyboardMZoneIdRow5.add(buttonZoneIdMFive);
        keyboardMZoneIdRow5.add(buttonZoneIdMSix);
        keyboardMZoneIdRow5.add(buttonZoneIdMSeven);
        keyboardMZoneIdRow5.add(buttonZoneIdOMEight);

        keyboardMZoneIdRow6.add(buttonZoneIdMNine);
        keyboardMZoneIdRow6.add(buttonZoneIdMTen);
        keyboardMZoneIdRow6.add(buttonZoneIdMEleven);
        keyboardMZoneIdRow6.add(buttonZoneIdMTwelve);

        keyboardMZoneIdRow7.add(buttonZoneIdZero);

        keyboardMZoneIdRow8.add(buttonHome);
        keyboardMZoneIdRow8.add(buttonBackToSetting);


        keyboardMZoneId.add(keyboardMZoneIdRow1);
        keyboardMZoneId.add(keyboardMZoneIdRow2);
        keyboardMZoneId.add(keyboardMZoneIdRow3);
        keyboardMZoneId.add(keyboardMZoneIdRow4);
        keyboardMZoneId.add(keyboardMZoneIdRow5);
        keyboardMZoneId.add(keyboardMZoneIdRow6);
        keyboardMZoneId.add(keyboardMZoneIdRow7);
        keyboardMZoneId.add(keyboardMZoneIdRow8);



        return InlineKeyboardMarkup.builder().keyboard(keyboardMZoneId).build();
    }

    public static String getButtonStatus(ZoneId currentZoneId, ZoneId selectedZoneId) {
        if (currentZoneId == selectedZoneId) {
            return "✅";
        }
        return "";
    }
}