package monobank;

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

    public static List<Monobank> sendGetBank(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<Monobank> monobankList = GSON.fromJson(response.body(), new TypeToken<List<Monobank>>() {
        }.getType());
        return monobankList;
    }

    public static Bank getMonobank(List<Monobank> monobankList) {
        Bank bank = new Bank();
        for (Monobank currency : monobankList) {
            if (currency.getCurrencyCodeA() == 840 && currency.getCurrencyCodeB() == 980) {
                bank.setUSD_buy(currency.getRateBuy());
                bank.setUSD_sell(currency.getRateSell());
            }else if(currency.getCurrencyCodeA() == 978 && currency.getCurrencyCodeB() == 980) {
                bank.setEUR_buy(currency.getRateBuy());
                bank.setEUR_sell(currency.getRateSell());
            }else if(currency.getCurrencyCodeA() == 985) {
                bank.setPLN_buy(currency.getRateCross());
                bank.setPLN_sell(currency.getRateCross());
            }
        }
        return bank;
    }
}
