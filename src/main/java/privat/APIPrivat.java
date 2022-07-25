package privat;

import serviceClasses.Bank;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class APIPrivat {
    private static final String PRIVAT_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    private static final String PRIVAT_PLZ_URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=12";

    public static Bank getPrivatAPI() throws IOException, InterruptedException {
        final List<Privat> date = HttpUtil.sendGetBank(URI.create(PRIVAT_URL));
        final List<Privat> datePlz = HttpUtil.sendGetBank(URI.create(PRIVAT_PLZ_URL));
        for (Privat currency : datePlz) {
            if (currency.getCcy().equals("PLZ")) {
                date.add(currency);
                break;
            }
        }
        return HttpUtil.getPrivat(date);
    }
}
