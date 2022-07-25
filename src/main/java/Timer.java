import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import settings.Settings;

import java.time.*;
import java.util.Map;

public class Timer implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                timer();
                Thread.sleep(1500);
            } catch (InterruptedException | TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void timer() throws InterruptedException, TelegramApiException {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        System.out.println(now);
        System.out.println(now.withZoneSameInstant(ZoneOffset.UTC));
        System.out.println(now.withZoneSameInstant(ZoneId.of("UTC")));
//        ZoneId Grinv = ZoneId.of()
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime startDays = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime timeSendMessage = LocalDateTime.now().withMinute(0).withSecond(0);
        if (timeSendMessage.isBefore(startTime)) {
            timeSendMessage = timeSendMessage.plusHours(1);
        }
        Duration timeToSendMess = Duration.between(startTime, timeSendMessage);
        Thread.sleep(timeToSendMess.toMillis());
        Duration hour = Duration.between(startDays, timeSendMessage);
        for (Map.Entry userSet : Settings.settings.entrySet()) {
            Long key = (Long) userSet.getKey();
            Long chatId = Settings.settings.get(key).getChatId();
            int userNotificationTime = Settings.settings.get(key).getNotificationTime().getTime();

            if (userNotificationTime == (int) hour.toHours()) {
                CurrencyInfoBot timer = CurrencyInfoBot.getInstance("timer");
                timer.printMessage(chatId, Settings.getInfo(chatId));
            }
        }
    }
}

