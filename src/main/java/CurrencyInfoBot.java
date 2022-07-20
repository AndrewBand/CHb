import keyboards.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class CurrencyInfoBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
//        return "@CurrencyInfoProjectGroup1Bot";
        return "@CurrencyInfoProjectGroup1TestBot";
    }

    @Override
    public String getBotToken() {
//        return "5416117406:AAE1XHQxbn8TIY2perQrAAiQsNcxlcth9Wo";
        return "5553351040:AAHugdZyMWm_u8av-bQqsEaP6Et7WXPsOtk";
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
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity;
            commandEntity = message.getEntities().stream()
                    .filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText()
                        .substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                if (command.equals("/start")) {
                    printMessage(chatId, MenuStart.keyboard(),
                            "Ласкаво просимо.Цей бот дозволить відслідкувати актуальні курси валют.");
                }
            }
        } else {
            printMessage(chatId, "Будь ласка впишіть /start або натисніть кнопку.");
        }
    }

    private void handleQuery(CallbackQuery buttonQuery) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        switch (dataButtonQuery) {
            case "GET_INFO":
                printMessage(chatId, "Bank \n currency buy: \n currency sell:");
                break;
            case "SETTINGS":
                printMessage(chatId, MenuSettings.keyboard(), "Налаштування:");
                break;
            case "BackToStart":
                printMessage(chatId, MenuStart.keyboard(), "Щоб отримати інфо натисність кнопку");
                break;
            case "NumDecimalPlaces":
                printMessage(chatId, MenuNumDecimalPlaces.keyboard(), "Вибиріть кількість знаків після коми:");
                break;
            case "Bank":
                printMessage(chatId, MenuBanks.keyboard(), "Вибиріть банк:");
                break;
            case "Currency":
                printMessage(chatId, MenuCurrency.keyboard(), "Вибиріть валюту:");
                break;
            case "Notification":
                printMessage(chatId, MenuNotification.keyboard(), "Вибиріть час повідомлення:");
                break;
            case "BackToSettings":
                printMessage(chatId, MenuSettings.keyboard(), "Щоб отримати інфо натисність кнопку");
                break;
            case "Privat":
                printMessage(chatId, "Приват Банк");
                break;
            case "NBU":
                printMessage(chatId, "Національний Банк України");
                break;
            case "Monobank":
                printMessage(chatId, "Монобанк");
                break;
        }
    }

    private void printMessage(Long chatID, InlineKeyboardMarkup keyboard, String text)
            throws TelegramApiException {
        execute(SendMessage.builder()
                .text(text)
                .chatId(chatID)
                .replyMarkup(keyboard)
                .build());
    }

    private void printMessage(Long chatID, String messageText) throws TelegramApiException {
        execute(SendMessage.builder()
                .text(messageText)
                .chatId(chatID)
                .build());
    }
}



