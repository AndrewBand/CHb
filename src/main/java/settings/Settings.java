package settings;

import serviceClasses.Bank;
import serviceClasses.CurrencyDataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class Settings {
    public static Map<Long, Setting> settings = new HashMap<>();



    public static String getInfo (Long chatId) {
        StringBuilder messageToUser = new StringBuilder();
        Setting userSetting = settings.get(chatId);
        String bankName = userSetting.getSelectedBank().getBankNameUA();
        messageToUser.append(bankName).append("\n");
        int numberDecPlaces = userSetting.getNumberOfDecimalPlaces();
        List<Currency> currencies = userSetting.getSelectedCurrency();
        Bank bankInfo = CurrencyDataBase.getCurrentInfo(userSetting.getSelectedBank());
        for (Currency currency: currencies){
            messageToUser.append("Курс купівлі ")
                    .append(currency.getCurrencyName())
                    .append(" - ")
                    .append(bankInfo.getBuyRate(currency) == 0 ? "не має купівлі" :
                            format("%." + numberDecPlaces + "f" , bankInfo.getBuyRate(currency)))
                    .append("\n");
            messageToUser.append("Курс продажу ")
                    .append(currency.getCurrencyName())
                    .append(" - ")
                    .append(bankInfo.getSellRate(currency) == 0 ? "не має продажу" :
                            format("%." + numberDecPlaces + "f" , bankInfo.getSellRate(currency)))
                    .append("\n");
        }

        return messageToUser.toString();
    }



}
