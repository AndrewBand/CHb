package settings;

import settings.Settings;

public class SaveSettings implements Runnable{

    @Override
    public void run() {
        Settings.save();
    }
}
