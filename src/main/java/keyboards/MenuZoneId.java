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
                .text(ZoneId.UTCONE.getNameZone() + getButtonStatus(ZoneId.UTCONE, selectedZoneID))
                .callbackData(ZoneId.UTCONE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdTwo = InlineKeyboardButton.builder()
                .text(ZoneId.UTCTWO.getNameZone() + getButtonStatus(ZoneId.UTCTWO, selectedZoneID))
                .callbackData(ZoneId.UTCTWO.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdThree = InlineKeyboardButton.builder()
                .text(ZoneId.UTCTHREE.getNameZone() + getButtonStatus(ZoneId.UTCTHREE, selectedZoneID))
                .callbackData(ZoneId.UTCTHREE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdFour = InlineKeyboardButton.builder()
                .text(ZoneId.UTCFOUR.getNameZone() + getButtonStatus(ZoneId.UTCFOUR, selectedZoneID))
                .callbackData(ZoneId.UTCFOUR.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdFive = InlineKeyboardButton.builder()
                .text(ZoneId.UTCFIVE.getNameZone() + getButtonStatus(ZoneId.UTCFIVE, selectedZoneID))
                .callbackData(ZoneId.UTCFIVE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdSix = InlineKeyboardButton.builder()
                .text(ZoneId.UTCSIX.getNameZone() + getButtonStatus(ZoneId.UTCSIX, selectedZoneID))
                .callbackData(ZoneId.UTCSIX.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdSeven = InlineKeyboardButton.builder()
                .text(ZoneId.UTCSEVEN.getNameZone() + getButtonStatus(ZoneId.UTCSEVEN, selectedZoneID))
                .callbackData(ZoneId.UTCSEVEN.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdEight = InlineKeyboardButton.builder()
                .text(ZoneId.UTCEIGHT.getNameZone() + getButtonStatus(ZoneId.UTCEIGHT, selectedZoneID))
                .callbackData(ZoneId.UTCEIGHT.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdNine = InlineKeyboardButton.builder()
                .text(ZoneId.UTCNINE.getNameZone() + getButtonStatus(ZoneId.UTCNINE, selectedZoneID))
                .callbackData(ZoneId.UTCNINE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdTen = InlineKeyboardButton.builder()
                .text(ZoneId.UTCTEN.getNameZone() + getButtonStatus(ZoneId.UTCTEN, selectedZoneID))
                .callbackData(ZoneId.UTCTEN.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdEleven = InlineKeyboardButton.builder()
                .text(ZoneId.UTCELEVEN.getNameZone() + getButtonStatus(ZoneId.UTCELEVEN, selectedZoneID))
                .callbackData(ZoneId.UTCELEVEN.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdTwelve = InlineKeyboardButton.builder()
                .text(ZoneId.UTCTWELVE.getNameZone() + getButtonStatus(ZoneId.UTCTWELVE, selectedZoneID))
                .callbackData(ZoneId.UTCTWELVE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMOne = InlineKeyboardButton.builder()
                .text(ZoneId.UTCMINUSONE.getNameZone() + getButtonStatus(ZoneId.UTCMINUSONE, selectedZoneID))
                .callbackData(ZoneId.UTCMINUSONE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMTwo = InlineKeyboardButton.builder()
                .text(ZoneId.UTCMINUSTWO.getNameZone() + getButtonStatus(ZoneId.UTCMINUSTWO, selectedZoneID))
                .callbackData(ZoneId.UTCMINUSTWO.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMThree = InlineKeyboardButton.builder()
                .text(ZoneId.UTCMINUSTHREE.getNameZone() + getButtonStatus(ZoneId.UTCMINUSTHREE, selectedZoneID))
                .callbackData(ZoneId.UTCMINUSTHREE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMFour = InlineKeyboardButton.builder()
                .text(ZoneId.UTCMINUSFOUR.getNameZone() + getButtonStatus(ZoneId.UTCMINUSFOUR, selectedZoneID))
                .callbackData(ZoneId.UTCMINUSFOUR.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMFive = InlineKeyboardButton.builder()
                .text(ZoneId.UTCMINUSFIVE.getNameZone() + getButtonStatus(ZoneId.UTCMINUSFIVE, selectedZoneID))
                .callbackData(ZoneId.UTCMINUSFIVE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMSix = InlineKeyboardButton.builder()
                .text(ZoneId.UTCMINUSSIX.getNameZone() + getButtonStatus(ZoneId.UTCMINUSSIX, selectedZoneID))
                .callbackData(ZoneId.UTCMINUSSIX.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMSeven = InlineKeyboardButton.builder()
                .text(ZoneId.UTCMINUSSEVEN.getNameZone() + getButtonStatus(ZoneId.UTCMINUSSEVEN, selectedZoneID))
                .callbackData(ZoneId.UTCMINUSSEVEN.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdOMEight = InlineKeyboardButton.builder()
                .text(ZoneId.UTCMINUSEIGHT.getNameZone() + getButtonStatus(ZoneId.UTCMINUSEIGHT, selectedZoneID))
                .callbackData(ZoneId.UTCMINUSEIGHT.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMNine = InlineKeyboardButton.builder()
                .text(ZoneId.UTCMINUSNINE.getNameZone() + getButtonStatus(ZoneId.UTCMINUSNINE, selectedZoneID))
                .callbackData(ZoneId.UTCMINUSNINE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMTen = InlineKeyboardButton.builder()
                .text(ZoneId.UTCMINUSTEN.getNameZone() + getButtonStatus(ZoneId.UTCMINUSTEN, selectedZoneID))
                .callbackData(ZoneId.UTCMINUSTEN.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMEleven = InlineKeyboardButton.builder()
                .text(ZoneId.UTCMINUSELEVEN.getNameZone() + getButtonStatus(ZoneId.UTCMINUSELEVEN, selectedZoneID))
                .callbackData(ZoneId.UTCMINUSELEVEN.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdMTwelve = InlineKeyboardButton.builder()
                .text(ZoneId.UTCMINUSTWELVE.getNameZone() + getButtonStatus(ZoneId.UTCMINUSTWELVE, selectedZoneID))
                .callbackData(ZoneId.UTCMINUSTWELVE.getNameZone())
                .build();
        InlineKeyboardButton buttonZoneIdZero = InlineKeyboardButton.builder()
                .text(ZoneId.UTCZERO.getNameZone() + getButtonStatus(ZoneId.UTCZERO, selectedZoneID))
                .callbackData(ZoneId.UTCZERO.getNameZone())
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