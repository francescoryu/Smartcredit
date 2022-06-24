package ch.bzz.smartcredit.service;

import ch.bzz.smartcredit.service.KKarteService;
import ch.bzz.smartcredit.service.TestService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @autor : Francesco Ryu
 * @date : 23.05.2022
 * @Version : 5.0
 */

@ApplicationPath("/resource")

public class Config extends Application {
    private static final String PROPERTIES_PATH = "/home/bzz/webapp/smartcredit.properties";
    private static Properties properties = null;

    /**
     * definiert alle providers
     *
     * @return set von Classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet providers = new HashSet<Class<?>>();
        providers.add(KKarteService.class);
        providers.add(TestService.class);
        providers.add(KundeService.class);
        providers.add(UserService.class);
        return providers;
    }

    /**
     * bekommt den Wert vom propertiesFile
     * @return Wert vom properties
     */
    public static String getProperty(String property) {
        if (Config.properties == null) {
            setProperties(new Properties());
            readProperties();
        }
        String value = Config.properties.getProperty(property);
        if (value == null) return "";
        return value;
    }

    /**
     * liest das propertiesFile
     */
    private static void readProperties() {

        InputStream inputStream;
        try {
            inputStream = new FileInputStream(PROPERTIES_PATH);
            properties.load(inputStream);
            if (inputStream != null) inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * setter vom propertiesFile
     * @param properties
     */
    private static void setProperties(Properties properties) {
        Config.properties = properties;
    }
}