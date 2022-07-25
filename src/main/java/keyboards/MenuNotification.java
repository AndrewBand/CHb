package keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import settings.*;

import java.util.ArrayList;
import java.util.List;

public class MenuNotification {
    public static InlineKeyboardMarkup keyboard(long chatId) {
        Setting userSetting = Settings.settings.get(chatId);
        NotificationTime selectedNotificationTime = userSetting.getNotificationTime();
        List<List<InlineKeyboardButton>> keyboardMenuNotification = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow4 = new ArrayList<>();
        InlineKeyboardButton buttonNotificationTime9 = InlineKeyboardButton.builder()
                .text(NotificationTime.NINE.getTime() + getButtonStatus(NotificationTime.NINE, selectedNotificationTime))
                .callbackData(String.valueOf(NotificationTime.NINE.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime10 = InlineKeyboardButton.builder()
                .text(NotificationTime.TEN.getTime() + getButtonStatus(NotificationTime.TEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotificationTime.TEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime11 = InlineKeyboardButton.builder()
                .text(NotificationTime.ELEVEN.getTime() + getButtonStatus(NotificationTime.ELEVEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotificationTime.ELEVEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime12 = InlineKeyboardButton.builder()
                .text(NotificationTime.TWELVE.getTime() + getButtonStatus(NotificationTime.TWELVE, selectedNotificationTime))
                .callbackData(String.valueOf(NotificationTime.TWELVE.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime13 = InlineKeyboardButton.builder()
                .text(NotificationTime.THIRTEEN.getTime() + getButtonStatus(NotificationTime.THIRTEEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotificationTime.THIRTEEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime14 = InlineKeyboardButton.builder()
                .text(NotificationTime.FOURTEEN.getTime() + getButtonStatus(NotificationTime.FOURTEEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotificationTime.FOURTEEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime15 = InlineKeyboardButton.builder()
                .text(NotificationTime.FIFTEEN.getTime() + getButtonStatus(NotificationTime.FIFTEEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotificationTime.FIFTEEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime16 = InlineKeyboardButton.builder()
                .text(NotificationTime.SIXTEEN.getTime() + getButtonStatus(NotificationTime.SIXTEEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotificationTime.SIXTEEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime17 = InlineKeyboardButton.builder()
                .text(NotificationTime.SEVENTEEN.getTime() + getButtonStatus(NotificationTime.SEVENTEEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotificationTime.SEVENTEEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime18 = InlineKeyboardButton.builder()
                .text(NotificationTime.EIGHTEEN.getTime() + getButtonStatus(NotificationTime.EIGHTEEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotificationTime.EIGHTEEN.getTime()))
                .build();
        InlineKeyboardButton buttonTurnOfNotification = InlineKeyboardButton.builder()
                .text("OFF" + getButtonStatus(NotificationTime.SWICH_OFF, selectedNotificationTime))
                .callbackData(String.valueOf(NotificationTime.SWICH_OFF.getTime()))
                .build();
        InlineKeyboardButton buttonHome = InlineKeyboardButton.builder()
                .text(Buttons.BACK_HOME.getName())
                .callbackData(Buttons.BACK_HOME.getNameEN())
                .build();
        InlineKeyboardButton buttonBack = InlineKeyboardButton.builder()
                .text(Buttons.BACK_TO_SETTINGS.getName())
                .callbackData(Buttons.BACK_TO_SETTINGS.getNameEN())
                .build();
        keyboardMSetRow1.add(buttonNotificationTime9);
        keyboardMSetRow1.add(buttonNotificationTime10);
        keyboardMSetRow1.add(buttonNotificationTime11);
        keyboardMSetRow1.add(buttonNotificationTime12);
        keyboardMSetRow1.add(buttonNotificationTime13);
        keyboardMSetRow2.add(buttonNotificationTime14);
        keyboardMSetRow2.add(buttonNotificationTime15);
        keyboardMSetRow2.add(buttonNotificationTime16);
        keyboardMSetRow2.add(buttonNotificationTime17);
        keyboardMSetRow2.add(buttonNotificationTime18);
        keyboardMSetRow3.add(buttonTurnOfNotification);
        keyboardMSetRow4.add(buttonHome);
        keyboardMSetRow4.add(buttonBack);
        keyboardMenuNotification.add(keyboardMSetRow1);
        keyboardMenuNotification.add(keyboardMSetRow2);
        keyboardMenuNotification.add(keyboardMSetRow3);
        keyboardMenuNotification.add(keyboardMSetRow4);

        return InlineKeyboardMarkup.builder().keyboard(keyboardMenuNotification).build();
    }

    private static String getButtonStatus(NotificationTime currentNotificationTime, NotificationTime selectedNotificationTime) {
        if (currentNotificationTime == selectedNotificationTime) {
            return "✅";
        }
        return "";
    }
}
