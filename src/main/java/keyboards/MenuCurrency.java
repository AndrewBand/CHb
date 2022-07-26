package keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import settings.*;

import java.util.ArrayList;
import java.util.List;

public class MenuCurrency {
    public static InlineKeyboardMarkup keyboard(long chatId) {
        Setting userSetting = Settings.settings.get(chatId);
        List<Currency> selectedCurrencies = userSetting.getSelectedCurrency();
        List<List<InlineKeyboardButton>> keyboardMenuCurrency = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMenuCurrency1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMenuCurrency2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMenuCurrency3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMenuCurrency4 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMenuCurrency5 = new ArrayList<>();
        InlineKeyboardButton buttonUsd = InlineKeyboardButton.builder()
                .text(Currency.USD.getCurrencyName() + getButtonStatus(Currency.USD, selectedCurrencies))
                .callbackData(Currency.USD.getCurrencyName())
                .build();
        InlineKeyboardButton buttonEur = InlineKeyboardButton.builder()
                .text(Currency.EUR.getCurrencyName() + getButtonStatus(Currency.EUR, selectedCurrencies))
                .callbackData(Currency.EUR.getCurrencyName())
                .build();
        InlineKeyboardButton buttonPln = InlineKeyboardButton.builder()
                .text(Currency.PLN.getCurrencyName() + getButtonStatus(Currency.PLN, selectedCurrencies))
                .callbackData(Currency.PLN.getCurrencyName())
                .build();
        InlineKeyboardButton buttonBtc = InlineKeyboardButton.builder()
                .text(Currency.BTC.getCurrencyName() + getButtonStatus(Currency.BTC, selectedCurrencies))
                .callbackData(Currency.BTC.getCurrencyName())
                .build();
        InlineKeyboardButton buttonHome = InlineKeyboardButton.builder()
                .text(Buttons.BACK_TO_START.getName())
                .callbackData(Buttons.BACK_TO_START.getNameEN())
                .build();
        InlineKeyboardButton buttonBack = InlineKeyboardButton.builder()
                .text(Buttons.BACK_TO_SETTINGS.getName())
                .callbackData(Buttons.BACK_TO_SETTINGS.getNameEN())
                .build();
        keyboardMenuCurrency1.add(buttonUsd);
        keyboardMenuCurrency2.add(buttonEur);
        keyboardMenuCurrency3.add(buttonPln);
        keyboardMenuCurrency4.add(buttonBtc);
        keyboardMenuCurrency5.add(buttonHome);
        keyboardMenuCurrency5.add(buttonBack);
        keyboardMenuCurrency.add(keyboardMenuCurrency1);
        keyboardMenuCurrency.add(keyboardMenuCurrency2);
        keyboardMenuCurrency.add(keyboardMenuCurrency3);
        keyboardMenuCurrency.add(keyboardMenuCurrency4);
        keyboardMenuCurrency.add(keyboardMenuCurrency5);

        return InlineKeyboardMarkup.builder().keyboard(keyboardMenuCurrency).build();
    }

    private static String getButtonStatus(Currency currency, List<Currency> selectedCurrencies) {
        if (selectedCurrencies.contains(currency)) {
            return "✅";
        }
        return "";
    }
}
