package nbu;

import serviceClasses.Bank;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class APINbu {
    private static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?json";

    public static Bank getNBUAPI() throws IOException, InterruptedException {
        final List<NbuBank> dateNBU = HttpUtil.sendGetBank(URI.create(NBU_URL));
        return HttpUtil.getNbu(dateNBU);
    }
}
