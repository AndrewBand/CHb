package settings;

public enum Buttons {
    START ("Старт","/start"),
    GET_INFO("Отримати інфо","GET_INFO"),
    SETTINGS("Налаштування","SETTINGS"),
    NUM_DECIMAL_PLACES ("Кількість знаків після коми","NumDecimalPlaces"),
    BANK("Банк","Bank"),
    CURRENCY ("Валюта","Currency"),
    NOTIFICATION ("Час сповіщення","Notification"),
    ZONEID("Часовий пояс","ZoneId"),
    BACK_TO_SETTINGS("↩️","SETTINGS"),
    BACK_TO_START ("🏠️","BACK_TO_START");

    private String buttonsName;
    private String buttonsNameEN;

    Buttons(String buttonsName, String buttonsNameEN) {
        this.buttonsName = buttonsName;
        this.buttonsNameEN = buttonsNameEN;
    }

    public String getName() {
        return buttonsName;
    }

    public String getNameEN() {
        return buttonsNameEN;
    }

    public static Buttons convertToEnum (String text){
        for (Buttons button: Buttons.values()) {
            if (button.getNameEN().equals(text)) {
                return button;
            }
        }
        return null;
    }
}
