package settings;

import java.util.List;

public class Setting {
    private final long chatId;
    public Setting(long chatId) {
        this.chatId = chatId;
    }
    private NumberOfDecimalPlaces numberOfDecimalPlaces = NumberOfDecimalPlaces.TWO;
    private Banks selectedBank = Banks.PRIVATE;
    private List<Currency> selectedCurrency = Currency.getSelectedCurrencyList();
    private NotificationTime notificationTime = NotificationTime.NINE;

    private ZoneId zoneId = ZoneId.UTCZERO;

    public long getChatId() {
        return chatId;
    }

    public int getNumberOfDecimalPlaces() {
        return numberOfDecimalPlaces.getIntNumber();
    }

    public void setNumberOfDecimalPlaces(NumberOfDecimalPlaces numberOfDecimalPlaces) {
        this.numberOfDecimalPlaces = numberOfDecimalPlaces;
    }

    public Banks getSelectedBank() {
        return selectedBank;
    }

    public void setSelectedBank(Banks selectedBank) {
        this.selectedBank = selectedBank;
    }

    public List<Currency> getSelectedCurrency() {
        return selectedCurrency;
    }

    public void setSelectedCurrency(List<Currency> selectedCurrency) {
        this.selectedCurrency = selectedCurrency;
    }

    public NotificationTime getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(NotificationTime notificationTime) {
        this.notificationTime = notificationTime;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }
    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public String toString() {
        return "chatId=" + chatId +
                ", numberOfDecimalPlaces=" + numberOfDecimalPlaces +
                ", selectedBank=" + selectedBank +
                ", selectedCurrency=" + selectedCurrency +
                ", notificationTime=" + notificationTime +
                ", zoneId=" + zoneId +
                '}';
    }
}
