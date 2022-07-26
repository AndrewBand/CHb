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
    public String getBotUsername() {
        return "@CurrencyInfoProjectGroup1Bot";
    }

//    public String getBotUsername() {
//        return "TestKabaBOT";
//    }


    @Override
    public String getBotToken() {
        return "5416117406:AAE1XHQxbn8TIY2perQrAAiQsNcxlcth9Wo";
    }

//    public String getBotToken() {
//        return "5110494726:AAHvvtZ2yxM8dnzpR730WBz4eeG7haGp9Kw";
//    }

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
                        Currency.getSelectedCurrencyList(), NotificationTime.NINE, ZoneId.UTCTHREE);
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
                        Currency.getSelectedCurrencyList(), NotificationTime.NINE, ZoneId.UTCTHREE);
            } else {
                userSettings = Settings.settings.get(chatId);
            }
        }
        checkMainMenu(buttonQuery);
        checkBanksMenu(buttonQuery);
        checkDecimalPlacesMenu(buttonQuery);
        checkNotificationMenu(buttonQuery);
        checkCurrencyMenu(buttonQuery);
        checkZoneIdMenu(buttonQuery);
    }

    private void saveSelectCurrency(CallbackQuery buttonQuery, Currency enumData) throws TelegramApiException {
        List<Currency> currentCurrencies = userSettings.getSelectedCurrency();
        if (currentCurrencies.contains(enumData)) {
            currentCurrencies.remove(enumData);
        } else {
            currentCurrencies.add(enumData);
        }
        updateMessage(buttonQuery, MenuCurrency.keyboard(buttonQuery.getMessage().getChatId()));
    }

    private void saveSelectZoneId(CallbackQuery buttonQuery, ZoneId enumData) throws TelegramApiException {
        userSettings.setZoneId(enumData);
        updateMessage(buttonQuery, MenuZoneId.keyboard(buttonQuery.getMessage().getChatId()));
    }

    private void saveSelectNumDecPlaces(CallbackQuery buttonQuery, NumberOfDecimalPlaces enumData)
            throws TelegramApiException {
        userSettings.setNumberOfDecimalPlaces(enumData);
        updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard(buttonQuery.getMessage().getChatId()));
    }

    private void saveSelectNotificationTime(CallbackQuery buttonQuery, NotificationTime enumData)
            throws TelegramApiException {
        userSettings.setNotificationTime(enumData);
        updateMessage(buttonQuery, MenuNotification.keyboard(buttonQuery.getMessage().getChatId()));
    }

    private void saveSelectBanks(CallbackQuery buttonQuery, Banks enumData) throws TelegramApiException {
        userSettings.setSelectedBank(enumData);
        updateMessage(buttonQuery, MenuBanks.keyboard(buttonQuery.getMessage().getChatId()));
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
        public void checkMainMenu(CallbackQuery buttonQuery) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (Buttons.convertToEnum(dataButtonQuery) != null){
            switch (Buttons.convertToEnum(dataButtonQuery)) {
                case GET_INFO:
                    printMessage(chatId, Settings.getInfo(chatId));
                    printMessage(chatId, MenuStart.keyboard(), "Щоб отримати інфо натисність кнопку");
                    break;
                case SETTINGS:
                    printMessage(chatId, MenuSettings.keyboard(Settings.settings.get(chatId)), "Виберіть налаштування");
                    break;
                case BACK_TO_START:
                    printMessage(chatId, MenuStart.keyboard(), "Щоб отримати інфо натисність кнопку");
                    break;
                case NUM_DECIMAL_PLACES:
                    updateMessage(buttonQuery, MenuNumDecimalPlaces.keyboard(chatId));
                    break;
                case BANK:
                    updateMessage(buttonQuery, MenuBanks.keyboard(chatId));
                    break;
                case CURRENCY:
                    updateMessage(buttonQuery, MenuCurrency.keyboard(chatId));
                    break;
                case NOTIFICATION:
                    updateMessage(buttonQuery, MenuNotification.keyboard(chatId));
                    break;
                case ZONEID:
                    updateMessage(buttonQuery, MenuZoneId.keyboard(chatId));
                    break;
            }
        }
    }
    public void checkBanksMenu(CallbackQuery buttonQuery) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (Banks.convertToEnum(dataButtonQuery) != null){
            switch (Banks.convertToEnum(dataButtonQuery)) {
                case PRIVAT:
                    if (!userSettings.getSelectedBank().equals(Banks.PRIVAT)) {
                        saveSelectBanks(buttonQuery, Banks.PRIVAT);
                    }
                    break;
                case NBU:
                    if (!userSettings.getSelectedBank().equals(Banks.NBU)) {
                        saveSelectBanks(buttonQuery, Banks.NBU);
                    }
                    break;
                case MONO:
                    if (!userSettings.getSelectedBank().equals(Banks.MONO)) {
                        saveSelectBanks(buttonQuery, Banks.MONO);
                    }
                    break;
            }
        }
    }
    public void checkDecimalPlacesMenu(CallbackQuery buttonQuery) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (NumberOfDecimalPlaces.convertToEnum(dataButtonQuery) != null){
            switch (NumberOfDecimalPlaces.convertToEnum(dataButtonQuery)) {
                case TWO:
                    if (userSettings.getNumberOfDecimalPlaces() != NumberOfDecimalPlaces.TWO.getIntNumber()) {
                        saveSelectNumDecPlaces(buttonQuery, NumberOfDecimalPlaces.TWO);
                    }
                    break;
                case THREE:
                    if (userSettings.getNumberOfDecimalPlaces() != NumberOfDecimalPlaces.THREE.getIntNumber()) {
                        saveSelectNumDecPlaces(buttonQuery, NumberOfDecimalPlaces.THREE);
                    }
                    break;
                case FOUR:
                    if (userSettings.getNumberOfDecimalPlaces() != NumberOfDecimalPlaces.FOUR.getIntNumber()) {
                        saveSelectNumDecPlaces(buttonQuery, NumberOfDecimalPlaces.FOUR);
                    }
                    break;
            }
        }
    }
    public void checkNotificationMenu(CallbackQuery buttonQuery) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (NotificationTime.convertToEnum(dataButtonQuery) != null){
            switch (NotificationTime.convertToEnum(dataButtonQuery)) {
                case NINE:
                    if (userSettings.getNotificationTime().getTime() != NotificationTime.NINE.getTime()) {
                        saveSelectNotificationTime(buttonQuery, NotificationTime.NINE);
                    }
                    break;
                case TEN:
                    if (userSettings.getNotificationTime().getTime() != NotificationTime.TEN.getTime()) {
                        saveSelectNotificationTime(buttonQuery, NotificationTime.TEN);
                    }
                    break;
                case ELEVEN:
                    if (userSettings.getNotificationTime().getTime() != NotificationTime.ELEVEN.getTime()) {
                        saveSelectNotificationTime(buttonQuery, NotificationTime.ELEVEN);
                    }
                    break;
                case TWELVE:
                    if (userSettings.getNotificationTime().getTime() != NotificationTime.TWELVE.getTime()) {
                        saveSelectNotificationTime(buttonQuery, NotificationTime.TWELVE);
                    }
                    break;
                case THIRTEEN:
                    if (userSettings.getNotificationTime().getTime() != NotificationTime.THIRTEEN.getTime()) {
                        saveSelectNotificationTime(buttonQuery, NotificationTime.THIRTEEN);
                    }
                    break;
                case FOURTEEN:
                    if (userSettings.getNotificationTime().getTime() != NotificationTime.FOURTEEN.getTime()) {
                        saveSelectNotificationTime(buttonQuery, NotificationTime.FOURTEEN);
                    }
                    break;
                case FIFTEEN:
                    if (userSettings.getNotificationTime().getTime() != NotificationTime.FIFTEEN.getTime()) {
                        saveSelectNotificationTime(buttonQuery, NotificationTime.FIFTEEN);
                    }
                    break;
                case SIXTEEN:
                    if (userSettings.getNotificationTime().getTime() != NotificationTime.SIXTEEN.getTime()) {
                        saveSelectNotificationTime(buttonQuery, NotificationTime.SIXTEEN);
                    }
                    break;
                case SEVENTEEN:
                    if (userSettings.getNotificationTime().getTime() != NotificationTime.SEVENTEEN.getTime()) {
                        saveSelectNotificationTime(buttonQuery, NotificationTime.SEVENTEEN);
                    }
                    break;
                case EIGHTEEN:
                    if (userSettings.getNotificationTime().getTime() != NotificationTime.EIGHTEEN.getTime()) {
                        saveSelectNotificationTime(buttonQuery, NotificationTime.EIGHTEEN);
                    }
                    break;
                case SWICH_OFF:
                    if (userSettings.getNotificationTime().getTime() != NotificationTime.SWICH_OFF.getTime()) {
                        saveSelectNotificationTime(buttonQuery, NotificationTime.SWICH_OFF);
                    }
                    break;
            }
        }
    }
    public void checkCurrencyMenu(CallbackQuery buttonQuery) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (Currency.convertToEnum(dataButtonQuery) != null){
            switch (Currency.convertToEnum(dataButtonQuery)) {
                case USD:
                    saveSelectCurrency(buttonQuery, Currency.USD);
                    break;
                case EUR:
                    saveSelectCurrency(buttonQuery, Currency.EUR);
                    break;
                case PLN:
                    saveSelectCurrency(buttonQuery, Currency.PLN);
                    break;
                case BTC:
                    saveSelectCurrency(buttonQuery, Currency.BTC);
                    break;
            }
        }
    }

    public void checkZoneIdMenu(CallbackQuery buttonQuery) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (ZoneId.convertToEnum(dataButtonQuery) != null){
            switch (ZoneId.convertToEnum(dataButtonQuery)) {
                case UTCONE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCONE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCONE);
                    }
                    break;
                case UTCTWO:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCTWO.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCTWO);
                    }
                    break;
                case UTCTHREE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCTHREE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCTHREE);
                    }
                    break;
                case UTCFOUR:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCFOUR.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCFOUR);
                    }
                    break;
                case UTCFIVE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCFIVE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCFIVE);
                    }
                    break;
                case UTCSIX:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCSIX.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCSIX);
                    }
                    break;
                case UTCSEVEN:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCSEVEN.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCSEVEN);
                    }
                    break;
                case UTCEIGHT:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCEIGHT.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCEIGHT);
                    }
                    break;
                case UTCNINE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCNINE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCNINE);
                    }
                    break;
                case UTCTEN:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCTEN.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCTEN);
                    }
                    break;
                case UTCELEVEN:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCELEVEN.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCELEVEN);
                    }
                    break;
                case UTCTWELVE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCTWELVE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCTWELVE);
                    }
                    break;
                case UTCMINUSONE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCMINUSONE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCMINUSONE);
                    }
                    break;
                case UTCMINUSTWO:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCMINUSTWO.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCMINUSTWO);
                    }
                    break;
                case UTCMINUSTHREE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCMINUSTHREE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCMINUSTHREE);
                    }
                    break;
                case UTCMINUSFOUR:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCMINUSFOUR.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCMINUSFOUR);
                    }
                    break;
                case UTCMINUSFIVE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCMINUSFIVE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCMINUSFIVE);
                    }
                    break;
                case UTCMINUSSIX:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCMINUSSIX.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCMINUSSIX);
                    }
                    break;
                case UTCMINUSSEVEN:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCMINUSSEVEN.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCMINUSSEVEN);
                    }
                    break;
                case UTCMINUSEIGHT:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCMINUSEIGHT.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCMINUSEIGHT);
                    }
                    break;
                case UTCMINUSNINE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCMINUSNINE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCMINUSNINE);
                    }
                    break;
                case UTCMINUSTEN:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCMINUSTEN.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCMINUSTEN);
                    }
                    break;
                case UTCMINUSELEVEN:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCMINUSELEVEN.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCMINUSELEVEN);
                    }
                    break;
                case UTCMINUSTWELVE:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCMINUSTWELVE.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCMINUSTWELVE);
                    }
                    break;
                case UTCZERO:
                    if (!userSettings.getZoneId().getNameZone().equals(ZoneId.UTCZERO.getNameZone())) {
                        saveSelectZoneId(buttonQuery, ZoneId.UTCZERO);
                    }
                    break;
            }
        }
    }
}