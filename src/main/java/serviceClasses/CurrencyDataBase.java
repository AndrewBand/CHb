package serviceClasses;

import monobank.APIMonobank;
import nbu.APINbu;
import privat.APIPrivat;
import settings.Banks;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class CurrencyDataBase {
    public static HashMap<Banks, Bank> currentInfo = new HashMap<>();
    private static final Object monitor = new Object();

    public static Bank getCurrentInfo(Banks bankName) {
        synchronized (monitor) {
            if (currentInfo.get(bankName) == null) {
                try {
                    setCurrentInfo(bankName);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
            Bank bank = currentInfo.get(bankName);
            long timeDiff = Duration.between(bank.getTime(), LocalDateTime.now()).toMinutes();
            if (timeDiff > 30) {
                try {
                    setCurrentInfo(bankName);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return currentInfo.get(bankName);
    }

    public static void setCurrentInfo(Banks bankName) throws IOException, InterruptedException {
        switch (bankName) {
            case PRIVAT:
                Bank bankPrivat = APIPrivat.getPrivatAPI();
                bankPrivat.setTime(LocalDateTime.now());
                currentInfo.put(bankName, bankPrivat);
                break;
            case MONO:
                Bank bankMono = APIMonobank.getMonoAPI();
                bankMono.setTime(LocalDateTime.now());
                currentInfo.put(bankName, bankMono);
                break;
            case NBU:
                Bank bankNBU = APINbu.getNBUAPI();
                bankNBU.setTime(LocalDateTime.now());
                currentInfo.put(bankName, bankNBU);
                break;
        }
    }
}
