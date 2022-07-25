package privat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import serviceClasses.Bank;
import settings.Banks;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static List<Privat> sendGetBank(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), new TypeToken<List<Privat>>() {
        }.getType());
    }

    public static Bank getPrivat(List<Privat> date) {
        Bank bank = new Bank();
        for (Privat currency : date) {
            switch (currency.getCcy()) {
                case "USD":
                    bank.setUSD_buy(currency.getBuy());
                    bank.setUSD_sell(currency.getSale());
                    break;
                case "EUR":
                    bank.setEUR_buy(currency.getBuy());
                    bank.setEUR_sell(currency.getSale());
                    break;
                case "PLZ":
                    bank.setPLN_buy(currency.getBuy());
                    bank.setPLN_sell(currency.getSale());
                    break;
                case "BTC":
                    bank.setBTC_buy(currency.getBuy());
                    bank.setBTC_sell(currency.getSale());
                    break;
            }
        }
        return bank;
    }
}
