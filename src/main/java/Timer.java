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
        ZonedDateTime Greenwich = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime startTime = LocalDateTime.from(Greenwich);
        LocalDateTime startDays = LocalDateTime.from(Greenwich).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime timeSendMessage = LocalDateTime.from(Greenwich).withMinute(0).withSecond(0);
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
            int userZoneId = Settings.settings.get(key).getZoneId().getZone();
            if (userNotificationTime == (int) hour.toHours()+userZoneId) {
                CurrencyInfoBot timer = CurrencyInfoBot.getInstance("timer");
                timer.printMessage(chatId, Settings.getInfo(chatId));
            }
        }
    }
}

