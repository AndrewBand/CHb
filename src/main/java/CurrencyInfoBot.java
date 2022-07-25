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
                userSettings = new Setting(chatId);
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
                userSettings = new Setting(chatId);
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
            case "Privat":
                if (!userSettings.getSelectedBank().getBankNameEN().equals("Privat")) {
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
        }
    }

    private void saveSelectCurrency(Currency currency) {
        List<Currency> currentCurrencies = userSettings.getSelectedCurrency();
        if (currentCurrencies.contains(currency)){
            currentCurrencies.remove(currency);
        } else {
            currentCurrencies.add(currency);
        }
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

//    private Buttons convertToButtons(String buttonQuery) {
//        for (Buttons button : Buttons.values()) {
//            if (button.getNameEN().equals(buttonQuery)) {
//                return button;
//            }
//        }
//        return null;
//    }
//
//    public void checkMainButtons(CallbackQuery buttonQuery) throws TelegramApiException {
//        long chatId = buttonQuery.getMessage().getChatId();
//        String dataButtonQuery = buttonQuery.getData();
//        switch (Objects.requireNonNull(convertToButtons(dataButtonQuery))) {
//            case GET_INFO:
//                printMessage(chatId, Settings.getInfo(chatId));
//                break;
//            case SETTINGS:
//                printMessage(chatId, MenuSettings.keyboard(Settings.settings.get(chatId)), "Виберіть налаштування");
//                break;
//            case BACK_TO_START:
//                printMessage(chatId, MenuStart.keyboard(), "Щоб отримати інфо натисність кнопку");
//                break;
//            case NUM_DECIMAL_PLACES:
//                updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard());
//                break;
//            case BANK:
//                updateMessage(buttonQuery, MenuBanks.keyboard(chatId));
//                break;
//            case CURRENCY:
//                updateMessage(buttonQuery, MenuCurrency.keyboard());
//                break;
//            case NOTIFICATION:
//                updateMessage(buttonQuery, MenuNotification.keyboard());
//                break;
//        }
//    }
}



