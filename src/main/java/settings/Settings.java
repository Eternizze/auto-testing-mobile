package settings;

import java.io.IOException;

/**
 * Settings class reads global configurations and use them during test execution.
 */
public class Settings {
    private static Settings instance;
    private static final Object lock = new Object();

    /**
     * Create a Singleton instance of settings (we need only one instance).
     *
     * @return Instance of settings.
     * @throws IOException when fails to read config file passed by `appConfig` property.
     */
    public static Settings getInstance() throws IOException{
        if(instance == null){
            synchronized (lock){
                instance = new Settings();
                instance.loadData();
            }
        }
        return instance;
    }

    private void loadData(){

    }

}
