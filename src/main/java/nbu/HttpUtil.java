package nbu;

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

    public static List<NbuBank> sendGetBank(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<NbuBank> date = GSON.fromJson(response.body(), new TypeToken<List<NbuBank>>() {
        }.getType());
        return date;
    }

    public static Bank getNbu(List<NbuBank> listCurr) {
        Bank bank = new Bank();
        for (NbuBank currency : listCurr) {
            switch (currency.getCc()) {
                case "USD":
                    bank.setUSD_buy(currency.getRate());
                    bank.setUSD_sell(currency.getRate());
                    break;
                case "EUR":
                    bank.setEUR_buy(currency.getRate());
                    bank.setEUR_sell(currency.getRate());
                    break;
                case "PLN":
                    bank.setPLN_buy(currency.getRate());
                    bank.setPLN_sell(currency.getRate());
                    break;
                case "BTC":
                    bank.setBTC_buy(currency.getRate());
                    bank.setBTC_sell(currency.getRate());
                    break;
            }
        }
        return bank;
    }
}
