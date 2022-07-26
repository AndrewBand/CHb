package settings;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import serviceClasses.Bank;
import serviceClasses.CurrencyDataBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.String.format;

public class Settings {
    public static Map<Long, Setting> settings = new HashMap<>();
    private static final Gson settingGson = new Gson();
    private static final String SETTING_GSON_PATH = "src/main/resources/settings.json";

    private static final Object monitor = new Object();


    public static String getInfo(Long chatId) {
        StringBuilder messageToUser = new StringBuilder();
        Setting userSetting = settings.get(chatId);
        String bankName = userSetting.getSelectedBank().getBankNameUA();
        messageToUser.append(bankName).append("\n");
        int numberDecPlaces = userSetting.getNumberOfDecimalPlaces();
        List<Currency> currencies = userSetting.getSelectedCurrency();
        Bank bankInfo = CurrencyDataBase.getCurrentInfo(userSetting.getSelectedBank());
        for (Currency currency : currencies) {
            messageToUser.append("Курс купівлі ")
                    .append(currency.getCurrencyName())
                    .append(" - ")
                    .append(bankInfo.getBuyRate(currency) == 0 ? "не купує" : format("%." + numberDecPlaces + "f", bankInfo.getBuyRate(currency)))
                    .append("\n");
            messageToUser.append("Курс продажу ")
                    .append(currency.getCurrencyName())
                    .append(" - ")
                    .append(bankInfo.getSellRate(currency) == 0 ? "не продає" : format("%." + numberDecPlaces + "f", bankInfo.getSellRate(currency)))
                    .append("\n");
        }
        return messageToUser.toString();
    }

    public static File fileSettingsGsonCheck() {
        File settingGsonFile = new File(SETTING_GSON_PATH);
        if (!settingGsonFile.exists()) {
            System.out.println("Create Path for Gson file Settings - " + settingGsonFile.getParentFile().mkdirs());
            try {
                System.out.println("Create new Gson file Settings - " + settingGsonFile.createNewFile());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return settingGsonFile;
    }


//    public static void load() {
//        synchronized (monitor) {
//            try {
//                IntermediateSettings.intermediateSettings = new ObjectMapper().readValue(fileSettingsGsonCheck(),
//                        IntermediateSettings.intermediateSettings.getClass());
//                System.out.println(IntermediateSettings.intermediateSettings);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    public static void load() {
        synchronized (monitor) {
            try {
                IntermediateSettings.intermediateSettings = new ObjectMapper().readValue(fileSettingsGsonCheck(),
                        new TypeReference<List<IntermediateSetting>>() {
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void save() {
        List<Setting> saveList = new ArrayList<>();

        Settings.settings.forEach((k, v) -> saveList.add(v));

        synchronized (monitor) {
            String s = settingGson.toJson(saveList);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileSettingsGsonCheck()))) {
                writer.write(settingGson.toJson(saveList));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    public static void save() {
//        synchronized (monitor) {
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileSettingsGsonCheck()))) {
//                writer.write(settingGson.toJson(settings));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void converter() {
        List<IntermediateSetting> inputList = IntermediateSettings.intermediateSettings;
        Map<Long, Setting> outputMap = Settings.settings;
        for (IntermediateSetting oneSetting : inputList) {
            Setting outputSetting = new Setting();
            outputSetting.setChatId(oneSetting.getChatId());
            outputSetting.setNumberOfDecimalPlaces(parseNumOfDecPlaces(oneSetting.getNumberOfDecimalPlaces()));
            outputSetting.setSelectedBank(parseSelectedBank(oneSetting.getSelectedBank()));
            outputSetting.setSelectedCurrency(parseCurrency(oneSetting.getSelectedCurrency()));
            outputSetting.setNotificationTime(parseNotificationTime(oneSetting.getNotificationTime()));
            outputSetting.setZoneId(parseZoneId(oneSetting.getZoneId()));
            outputMap.put(oneSetting.getChatId(), outputSetting);
        }
        System.out.println(Settings.settings);

    }

//        for (Long key : inputList.keySet()) {
//        for (Map.Entry pair : inputList.entrySet()) {
//
//            Setting outputSetting = new Setting();
////        for (Map.Entry pair : IntermediateSettings.intermediateSettings.entrySet()) {
//            System.out.println(pair.getValue());
//            IntermediateSetting inputSetting = (IntermediateSetting) pair.getValue();
//            outputSetting.setChatId(inputSetting.getChatId());
//            outputSetting.setNumberOfDecimalPlaces(parseNumOfDecPlaces(inputSetting.getNumberOfDecimalPlaces()));
//            outputSetting.setSelectedBank(parseSelectedBank(inputSetting.getSelectedBank()));
//            outputSetting.setSelectedCurrency(parseCurrency(inputSetting.getSelectedCurrency()));
//            outputSetting.setNotificationTime(parseNotificationTime(inputSetting.getNotificationTime()));
//            outputSetting.setZoneId(parseZoneId(inputSetting.getZoneId()));
//            outputMap.put(inputSetting.getChatId(), outputSetting);
//        }

    private static NumberOfDecimalPlaces parseNumOfDecPlaces(String inputStrNumOfDec) {
        for (NumberOfDecimalPlaces value : NumberOfDecimalPlaces.values()) {
            if (inputStrNumOfDec.equals(value.name())) {
                return value;
            }
        }
        return null;
    }

    private static Banks parseSelectedBank(String inputStrBank) {
        for (Banks value : Banks.values()) {
            if (inputStrBank.equals(value.name())) {
                return value;
            }
        }
        return null;
    }

    private static List<Currency> parseCurrency(List<String> inputListStrCurrency) {
        List<Currency> result = new ArrayList<>();
        for (Currency value : Currency.values()) {
            for (String oneCurrency : inputListStrCurrency) {
                if (oneCurrency.equals(value.name())) {
                    result.add(value);
                }
            }

        }
        return result;
    }

    private static NotificationTime parseNotificationTime(String inputStrNotificationTime) {
        for (NotificationTime value : NotificationTime.values()) {
            if (inputStrNotificationTime.equals(value.name())) {
                return value;
            }
        }
        return null;
    }

    private static ZoneId parseZoneId(String inputStrZoneId) {
        for (ZoneId value : ZoneId.values()) {
            if (inputStrZoneId.equals(value.name())) {
                return value;
            }
        }
        return null;
    }


}
