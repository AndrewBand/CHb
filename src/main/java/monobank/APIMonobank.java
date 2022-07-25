package monobank;

import serviceClasses.Bank;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class APIMonobank {

    private static final String MONOBANK_URL = "https://api.monobank.ua/bank/currency";

    public static Bank getMonoAPI() throws IOException, InterruptedException {
        final List<Monobank> monobankList = HttpUtil.sendGetBank(URI.create(MONOBANK_URL));
        return HttpUtil.getMonobank(monobankList);
    }
}
