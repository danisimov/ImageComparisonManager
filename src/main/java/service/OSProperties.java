package service;

import java.util.Properties;

/**
 * Created by danisimov on 21.10.2016.
 */
class OSProperties {

    private static Properties properties = System.getProperties();

    static String getOSName() {
        return properties.getProperty("os.name");
    }

    static String getAccountName() {
        return properties.getProperty("user.name");
    }

    static String getHomeFolder() {
        return properties.getProperty("user.home");
    }
}
