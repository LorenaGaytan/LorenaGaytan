package net.tecgurus.schoolmanager.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesReader {
    private static final Logger LOGGER = Logger.getLogger(PropertiesReader.class.getName());

    public static Properties readPropertiesFile(String fileName) {
        FileInputStream fis = null;
        Properties prop = null;
        String rootPath = Objects.requireNonNull(
            Thread.currentThread().getContextClassLoader().getResource("")
        ).getPath();
        try {
            fis = new FileInputStream(rootPath + fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (IOException ioe) {
            LOGGER.warning(ioe.getMessage());
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                LOGGER.warning(e.getMessage());
            }
        }
        return prop;
    }
}
