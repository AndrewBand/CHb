package settings;

import java.util.List;

public class IntermediateSetting {
    private Long chatId;
    private String numberOfDecimalPlaces;
    private String selectedBank;
    private List<String> selectedCurrency;
    private String notificationTime;
    private String zoneId;

    public Long getChatId() {
        return chatId;
    }

    public String getNumberOfDecimalPlaces() {
        return numberOfDecimalPlaces;
    }

    public String getSelectedBank() {
        return selectedBank;
    }

    public List<String> getSelectedCurrency() {
        return selectedCurrency;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public String getZoneId() {
        return zoneId;
    }
}
