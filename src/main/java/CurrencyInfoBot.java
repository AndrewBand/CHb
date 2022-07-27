import keyboards.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import settings.*;
import settings.Currency;

import java.util.*;

public class CurrencyInfoBot extends TelegramLongPollingBot {
    private static CurrencyInfoBot instance;
    public String value;

    private Setting userSettings;

    private final static Object monitor = new Object();

    private CurrencyInfoBot(String value) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static CurrencyInfoBot getInstance(String value) {
        if (instance == null) {
            instance = new CurrencyInfoBot(value);
        }
        return instance;
    }

    @Override
//    public String getBotUsername() {
//        return "@CurrencyInfoProjectGroup1Bot";
//    }

    public String getBotUsername() {
        return "TestKabaBOT";
    }


    @Override
//    public String getBotToken() {
//        return "5416117406:AAE1XHQxbn8TIY2perQrAAiQsNcxlcth9Wo";
//    }

    public String getBotToken() {
        return "5110494726:AAHvvtZ2yxM8dnzpR730WBz4eeG7haGp9Kw";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if (update.hasCallbackQuery()) {
            try {
                handleQuery(update.getCallbackQuery());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleMessage(Message message) throws TelegramApiException {
        long chatId = message.getChatId();
        synchronized (monitor) {
            if (Settings.settings.get(chatId) == null) {
                userSettings = new Setting(chatId, NumberOfDecimalPlaces.TWO, Banks.PRIVAT,
                        Currency.getSelectedCurrencyList(), NotificationTime.NINE, ZoneId.UTC_THREE);
            } else {
                userSettings = Settings.settings.get(chatId);
            }
        }
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity;
            commandEntity = message.getEntities().stream()
                    .filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText()
                        .substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                if (command.equals(Buttons.START.getNameEN())) {
                    printMessage(chatId, MenuStart.keyboard(),
                            "Ласкаво просимо.Цей бот дозволить відслідкувати актуальні курси валют.");
                    synchronized (monitor) {
                        Settings.settings.put(chatId, userSettings);
                    }
                }
            }
        } else {
            printMessage(chatId, "Будь ласка впишіть /start або натисніть кнопку.");
        }
    }

    private void handleQuery(CallbackQuery buttonQuery) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        synchronized (monitor) {
            if (Settings.settings.get(chatId) == null) {
                userSettings = new Setting(chatId, NumberOfDecimalPlaces.TWO, Banks.PRIVAT,
                        Currency.getSelectedCurrencyList(), NotificationTime.NINE, ZoneId.UTC_THREE);
            } else {
                userSettings = Settings.settings.get(chatId);
            }
        }
        String dataButtonQuery = buttonQuery.getData();
        switch (dataButtonQuery) {
            case "GET_INFO":
                printMessage(chatId, Settings.getInfo(chatId));
                printMessage(chatId, MenuStart.keyboard(), "Щоб отримати інфо натисність кнопку");
                break;
            case "SETTINGS":
                printMessage(chatId, MenuSettings.keyboard(Settings.settings.get(chatId)), "Виберіть налаштування");
                break;
            case "BACK_HOME":
            case "BACK_TO_START":
                printMessage(chatId, MenuStart.keyboard(), "Щоб отримати інфо натисність кнопку");
                break;
            case "NumDecimalPlaces":
                updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard(chatId));
                break;
            case "Bank":
                updateMessage(buttonQuery, MenuBanks.keyboard(chatId));
                break;
            case "Currency":
                updateMessage(buttonQuery, MenuCurrency.keyboard(chatId));
                break;
            case "Notification":
                updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                break;
            case "ZoneId":
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "Privat":
                if (!userSettings.getSelectedBank().getBankNameEN().equals("Private")) {
                    saveSelectBanks(Banks.PRIVAT);
                    updateMessage(buttonQuery, MenuBanks.keyboard(chatId));
                }
                break;
            case "NBU":
                if (!userSettings.getSelectedBank().getBankNameEN().equals("NBU")) {
                    saveSelectBanks(Banks.NBU);
                    updateMessage(buttonQuery, MenuBanks.keyboard(chatId));
                }
                break;
            case "Monobank":
                if (!userSettings.getSelectedBank().getBankNameEN().equals("Monobank")) {
                    saveSelectBanks(Banks.MONO);
                    updateMessage(buttonQuery, MenuBanks.keyboard(chatId));
                }
                break;
            case "twoPlaces":
                if (userSettings.getNumberOfDecimalPlaces() != 2) {
                    saveSelectNumDecPlaces(NumberOfDecimalPlaces.TWO);
                    updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard(chatId));
                }
                break;
            case "threePlaces":
                if (userSettings.getNumberOfDecimalPlaces() != 3) {
                    saveSelectNumDecPlaces(NumberOfDecimalPlaces.THREE);
                    updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard(chatId));
                }
                break;
            case "fourPlaces":
                if (userSettings.getNumberOfDecimalPlaces() != 4) {
                    saveSelectNumDecPlaces(NumberOfDecimalPlaces.FOUR);
                    updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard(chatId));
                }
                break;
            case "9":
                if (userSettings.getNotificationTime().getTime() != 9) {
                    saveSelectNotificationTime(NotificationTime.NINE);
                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                }
                break;
            case "10":
                if (userSettings.getNotificationTime().getTime() != 10) {
                    saveSelectNotificationTime(NotificationTime.TEN);
                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                }
                break;
            case "11":
                if (userSettings.getNotificationTime().getTime() != 11) {
                    saveSelectNotificationTime(NotificationTime.ELEVEN);
                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                }
                break;
            case "12":
                if (userSettings.getNotificationTime().getTime() != 12) {
                    saveSelectNotificationTime(NotificationTime.TWELVE);
                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                }
                break;
            case "13":
                if (userSettings.getNotificationTime().getTime() != 13) {
                    saveSelectNotificationTime(NotificationTime.THIRTEEN);
                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                }
                break;
            case "14":
                if (userSettings.getNotificationTime().getTime() != 14) {
                    saveSelectNotificationTime(NotificationTime.FOURTEEN);
                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                }
                break;
            case "15":
                if (userSettings.getNotificationTime().getTime() != 15) {
                    saveSelectNotificationTime(NotificationTime.FIFTEEN);
                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                }
                break;
            case "16":
                if (userSettings.getNotificationTime().getTime() != 16) {
                    saveSelectNotificationTime(NotificationTime.SIXTEEN);
                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                }
                break;
            case "17":
                if (userSettings.getNotificationTime().getTime() != 17) {
                    saveSelectNotificationTime(NotificationTime.SEVENTEEN);
                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                }
                break;
            case "18":
                if (userSettings.getNotificationTime().getTime() != 18) {
                    saveSelectNotificationTime(NotificationTime.EIGHTEEN);
                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                }
                break;
            case "0":
                if (userSettings.getNotificationTime().getTime() != 0) {
                    saveSelectNotificationTime(NotificationTime.SWICH_OFF);
                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                }
                break;
            case "USD":
                saveSelectCurrency(Currency.USD);
                updateMessage(buttonQuery, MenuCurrency.keyboard(chatId));
                break;
            case "EUR":
                saveSelectCurrency(Currency.EUR);
                updateMessage(buttonQuery, MenuCurrency.keyboard(chatId));
                break;
            case "PLN":
                saveSelectCurrency(Currency.PLN);
                updateMessage(buttonQuery, MenuCurrency.keyboard(chatId));
                break;
            case "BTC":
                saveSelectCurrency(Currency.BTC);
                updateMessage(buttonQuery, MenuCurrency.keyboard(chatId));
                break;
            case "UTC +1":
                saveSelectZoneId(ZoneId.UTC_ONE);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC +2":
                saveSelectZoneId(ZoneId.UTC_TWO);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC +3":
                saveSelectZoneId(ZoneId.UTC_THREE);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC +4":
                saveSelectZoneId(ZoneId.UTC_FOUR);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC +5":
                saveSelectZoneId(ZoneId.UTC_FIVE);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC +6":
                saveSelectZoneId(ZoneId.UTC_SIX);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC +7":
                saveSelectZoneId(ZoneId.UTC_SEVEN);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC +8":
                saveSelectZoneId(ZoneId.UTC_EIGHT);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC +9":
                saveSelectZoneId(ZoneId.UTC_NINE);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC +10":
                saveSelectZoneId(ZoneId.UTC_TEN);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC +11":
                saveSelectZoneId(ZoneId.UTC_ELEVEN);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC +12":
                saveSelectZoneId(ZoneId.UTC_TWELVE);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC -1":
                saveSelectZoneId(ZoneId.UTC_MINUS_ONE);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC -2":
                saveSelectZoneId(ZoneId.UTC_MINUS_TWO);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC -3":
                saveSelectZoneId(ZoneId.UTC_MINUS_THREE);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC -4":
                saveSelectZoneId(ZoneId.UTC_MINUS_FOUR);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC -5":
                saveSelectZoneId(ZoneId.UTC_MINUS_FIVE);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC -6":
                saveSelectZoneId(ZoneId.UTC_MINUS_SIX);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC -7":
                saveSelectZoneId(ZoneId.UTC_MINUS_SEVEN);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC -8":
                saveSelectZoneId(ZoneId.UTC_MINUS_EIGHT);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC -9":
                saveSelectZoneId(ZoneId.UTC_MINUS_NINE);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC -10":
                saveSelectZoneId(ZoneId.UTC_MINUS_TEN);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC -11":
                saveSelectZoneId(ZoneId.UTC_MINUS_ELEVEN);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC -12":
                saveSelectZoneId(ZoneId.UTC_MINUS_TWELVE);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
            case "UTC 0":
                saveSelectZoneId(ZoneId.UTC_ZERO);
                updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                break;
        }
    }

    private void saveSelectCurrency(Currency currency) {
        List<Currency> currentCurrencies = userSettings.getSelectedCurrency();
        if (currentCurrencies.contains(currency)) {
            currentCurrencies.remove(currency);
        } else {
            currentCurrencies.add(currency);
        }
    }

    private void saveSelectZoneId(ZoneId enumDate) {
        userSettings.setZoneId(enumDate);
    }

    private void saveSelectNumDecPlaces(NumberOfDecimalPlaces enumDate) {
        userSettings.setNumberOfDecimalPlaces(enumDate);
    }

    private void saveSelectNotificationTime(NotificationTime enumDate) {
        userSettings.setNotificationTime(enumDate);
    }

    private void saveSelectBanks(Banks enumDate) {
        userSettings.setSelectedBank(enumDate);
    }

    private void printMessage(Long chatID, InlineKeyboardMarkup keyboard, String text)
            throws TelegramApiException {
        execute(SendMessage.builder()
                .text(text)
                .chatId(chatID)
                .replyMarkup(keyboard)
                .build());
    }

    public void printMessage(Long chatID, String messageText) throws TelegramApiException {
        execute(SendMessage.builder()
                .text(messageText)
                .chatId(chatID)
                .build());
    }

    private void updateMessage(CallbackQuery buttonQuery, InlineKeyboardMarkup keyboard)
            throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        int messageId = buttonQuery.getMessage().getMessageId();
        execute(EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(keyboard)
                .build());
    }

}


//    private void handleQuery(CallbackQuery buttonQuery) throws TelegramApiException {
//        long chatId = buttonQuery.getMessage().getChatId();
//        synchronized (monitor) {
//            if (Settings.settings.get(chatId) == null) {
//                userSettings = new Setting(chatId);
//            } else {
//                userSettings = Settings.settings.get(chatId);
//            }
//        }
//        checkMainMenu(buttonQuery);
//        checkBanksMenu(buttonQuery);
//        checkDecimalPlacesMenu(buttonQuery);
//        checkNotificationMenu(buttonQuery);
//        checkCurrencyMenu(buttonQuery);
//    }
//
//    private void saveSelectCurrency(Currency currency) {
//        List<Currency> currentCurrencies = userSettings.getSelectedCurrency();
//        if (currentCurrencies.contains(currency)){
//            currentCurrencies.remove(currency);
//        } else {
//            currentCurrencies.add(currency);
//        }
//    }
//
//    private void saveSelectNumDecPlaces(NumberOfDecimalPlaces enumDate) {
//        userSettings.setNumberOfDecimalPlaces(enumDate);
//    }
//
//    private void saveSelectNotificationTime(NotificationTime enumDate) {
//        userSettings.setNotificationTime(enumDate);
//    }
//
//    private void saveSelectBanks(Banks enumDate) {
//        userSettings.setSelectedBank(enumDate);
//    }
//
//    private void printMessage(Long chatID, InlineKeyboardMarkup keyboard, String text)
//            throws TelegramApiException {
//        execute(SendMessage.builder()
//                .text(text)
//                .chatId(chatID)
//                .replyMarkup(keyboard)
//                .build());
//    }
//
//    public void printMessage(Long chatID, String messageText) throws TelegramApiException {
//        execute(SendMessage.builder()
//                .text(messageText)
//                .chatId(chatID)
//                .build());
//    }
//
//    private void updateMessage(CallbackQuery buttonQuery, InlineKeyboardMarkup keyboard)
//            throws TelegramApiException {
//        long chatId = buttonQuery.getMessage().getChatId();
//        int messageId = buttonQuery.getMessage().getMessageId();
//        execute(EditMessageReplyMarkup.builder()
//                .chatId(chatId)
//                .messageId(messageId)
//                .replyMarkup(keyboard)
//                .build());
//    }
//    public void checkMainMenu(CallbackQuery buttonQuery) throws TelegramApiException {
//        long chatId = buttonQuery.getMessage().getChatId();
//        String dataButtonQuery = buttonQuery.getData();
//        if (Buttons.convertToEnum(dataButtonQuery) != null){
//            switch (Buttons.convertToEnum(dataButtonQuery)) {
//                case GET_INFO:
//                    printMessage(chatId, Settings.getInfo(chatId));
//                    printMessage(chatId, MenuStart.keyboard(), "Щоб отримати інфо натисність кнопку");
//                    break;
//                case SETTINGS:
//                    printMessage(chatId, MenuSettings.keyboard(Settings.settings.get(chatId)), "Виберіть налаштування");
//                    break;
//                case BACK_TO_START:
//                    printMessage(chatId, MenuStart.keyboard(), "Щоб отримати інфо натисність кнопку");
//                    break;
//                case NUM_DECIMAL_PLACES:
//                    updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard(chatId));
//                    break;
//                case BANK:
//                    updateMessage(buttonQuery, MenuBanks.keyboard(chatId));
//                    break;
//                case CURRENCY:
//                    updateMessage(buttonQuery, MenuCurrency.keyboard(chatId));
//                    break;
//                case NOTIFICATION:
//                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
//                    break;
//            }
//        }
//    }
//    public void checkBanksMenu(CallbackQuery buttonQuery) throws TelegramApiException {
//        long chatId = buttonQuery.getMessage().getChatId();
//        String dataButtonQuery = buttonQuery.getData();
//        if (Banks.convertToEnum(dataButtonQuery) != null){
//            switch (Banks.convertToEnum(dataButtonQuery)) {
//                case PRIVATE:
//                    if (!userSettings.getSelectedBank().equals(Banks.PRIVATE)) {
//                        saveSelectBanks(Banks.PRIVATE);
//                        updateMessage(buttonQuery, MenuBanks.keyboard(chatId));
//                    }
//                    break;
//                case NBU:
//                    if (!userSettings.getSelectedBank().equals(Banks.NBU)) {
//                        saveSelectBanks(Banks.NBU);
//                        updateMessage(buttonQuery, MenuBanks.keyboard(chatId));
//                    }
//                    break;
//                case MONO:
//                    if (!userSettings.getSelectedBank().equals(Banks.MONO)) {
//                        saveSelectBanks(Banks.MONO);
//                        updateMessage(buttonQuery, MenuBanks.keyboard(chatId));
//                    }
//                    break;
//            }
//        }
//    }
//    public void checkDecimalPlacesMenu(CallbackQuery buttonQuery) throws TelegramApiException {
//        long chatId = buttonQuery.getMessage().getChatId();
//        String dataButtonQuery = buttonQuery.getData();
//        if (NumberOfDecimalPlaces.convertToEnum(dataButtonQuery) != null){
//            switch (NumberOfDecimalPlaces.convertToEnum(dataButtonQuery)) {
//                case TWO:
//                    if (userSettings.getNumberOfDecimalPlaces() != NumberOfDecimalPlaces.TWO.getIntNumber()) {
//                        saveSelectNumDecPlaces(NumberOfDecimalPlaces.TWO);
//                        updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard(chatId));
//                    }
//                    break;
//                case THREE:
//                    if (userSettings.getNumberOfDecimalPlaces() != NumberOfDecimalPlaces.THREE.getIntNumber()) {
//                        saveSelectNumDecPlaces(NumberOfDecimalPlaces.THREE);
//                        updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard(chatId));
//                    }
//                    break;
//                case FOUR:
//                    if (userSettings.getNumberOfDecimalPlaces() != NumberOfDecimalPlaces.FOUR.getIntNumber()) {
//                        saveSelectNumDecPlaces(NumberOfDecimalPlaces.FOUR);
//                        updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard(chatId));
//                    }
//                    break;
//            }
//        }
//    }
//    public void checkNotificationMenu(CallbackQuery buttonQuery) throws TelegramApiException {
//        long chatId = buttonQuery.getMessage().getChatId();
//        String dataButtonQuery = buttonQuery.getData();
//        if (NotificationTime.convertToEnum(dataButtonQuery) != null){
//            switch (NotificationTime.convertToEnum(dataButtonQuery)) {
//                case NINE:
//                    if (userSettings.getNotificationTime().getTime() != NotificationTime.NINE.getTime()) {
//                        saveSelectNotificationTime(NotificationTime.NINE);
//                        updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
//                    }
//                    break;
//                case TEN:
//                    if (userSettings.getNotificationTime().getTime() != NotificationTime.TEN.getTime()) {
//                        saveSelectNotificationTime(NotificationTime.TEN);
//                        updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
//                    }
//                    break;
//                case ELEVEN:
//                    if (userSettings.getNotificationTime().getTime() != NotificationTime.ELEVEN.getTime()) {
//                        saveSelectNotificationTime(NotificationTime.ELEVEN);
//                        updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
//                    }
//                    break;
//                case TWELVE:
//                    if (userSettings.getNotificationTime().getTime() != NotificationTime.TWELVE.getTime()) {
//                        saveSelectNotificationTime(NotificationTime.TWELVE);
//                        updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
//                    }
//                    break;
//                case THIRTEEN:
//                    if (userSettings.getNotificationTime().getTime() != NotificationTime.THIRTEEN.getTime()) {
//                        saveSelectNotificationTime(NotificationTime.THIRTEEN);
//                        updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
//                    }
//                    break;
//                case FOURTEEN:
//                    if (userSettings.getNotificationTime().getTime() != NotificationTime.FOURTEEN.getTime()) {
//                        saveSelectNotificationTime(NotificationTime.FOURTEEN);
//                        updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
//                    }
//                    break;
//                case FIFTEEN:
//                    if (userSettings.getNotificationTime().getTime() != NotificationTime.FIFTEEN.getTime()) {
//                        saveSelectNotificationTime(NotificationTime.FIFTEEN);
//                        updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
//                    }
//                    break;
//                case SIXTEEN:
//                    if (userSettings.getNotificationTime().getTime() != NotificationTime.SIXTEEN.getTime()) {
//                        saveSelectNotificationTime(NotificationTime.SIXTEEN);
//                        updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
//                    }
//                    break;
//                case SEVENTEEN:
//                    if (userSettings.getNotificationTime().getTime() != NotificationTime.SEVENTEEN.getTime()) {
//                        saveSelectNotificationTime(NotificationTime.SEVENTEEN);
//                        updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
//                    }
//                    break;
//                case EIGHTEEN:
//                    if (userSettings.getNotificationTime().getTime() != NotificationTime.EIGHTEEN.getTime()) {
//                        saveSelectNotificationTime(NotificationTime.EIGHTEEN);
//                        updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
//                    }
//                    break;
//                case SWICH_OFF:
//                    if (userSettings.getNotificationTime().getTime() != NotificationTime.SWICH_OFF.getTime()) {
//                        saveSelectNotificationTime(NotificationTime.SWICH_OFF);
//                        updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
//                    }
//                    break;
//            }
//        }
//    }
//    public void checkCurrencyMenu(CallbackQuery buttonQuery) throws TelegramApiException {
//        long chatId = buttonQuery.getMessage().getChatId();
//        String dataButtonQuery = buttonQuery.getData();
//        if (Currency.convertToEnum(dataButtonQuery) != null){
//            switch (Currency.convertToEnum(dataButtonQuery)) {
//                case USD:
//                    saveSelectCurrency(Currency.USD);
//                    updateMessage(buttonQuery, MenuCurrency.keyboard(chatId));
//                    break;
//                case EUR:
//                    saveSelectCurrency(Currency.EUR);
//                    updateMessage(buttonQuery, MenuCurrency.keyboard(chatId));
//                    break;
//                case PLN:
//                    saveSelectCurrency(Currency.PLN);
//                    updateMessage(buttonQuery, MenuCurrency.keyboard(chatId));
//                    break;
//                case BTC:
//                    saveSelectCurrency(Currency.BTC);
//                    updateMessage(buttonQuery, MenuCurrency.keyboard(chatId));
//                    break;
//            }
//        }
//    }
//}


