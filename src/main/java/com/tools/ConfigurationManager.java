package com.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author tian 读取properties 配置文件
 */
public class ConfigurationManager {
    private Properties properties;
    private FileOutputStream outputFile;
    private static final String TAG = ConfigurationManager.class
            .getSimpleName();

    public ConfigurationManager(String app) throws Exception {
        String configPath = "";
        String appFile = app + ".properties";
        String enviPath = System.getenv("SKA_CONFIG_PATH");
        if (enviPath == null || enviPath.length() == 0)
            configPath = System.getProperty("user.dir") + File.separator
                    + appFile;
        else {
            configPath = enviPath + File.separator + appFile;
        }
        this.properties = new Properties();
        if (new File(configPath).exists()) {
            FileInputStream inputStream = new FileInputStream(configPath);
            this.properties.load(inputStream);
        } else {
            InputStream inputStream = ConfigurationManager.class
                    .getClassLoader().getResourceAsStream(appFile);
            this.properties.load(inputStream);
        }
    }

    public ConfigurationManager() {
        String configPath = "";
        String appFile = "config.properties";
        String enviPath = System.getenv("SKA_CONFIG_PATH");
        if (enviPath == null || enviPath.length() == 0)
            configPath = System.getProperty("user.dir") + File.separator
                    + appFile;
        else {
            configPath = enviPath + File.separator + appFile;
        }
        this.properties = new Properties();
        try {

            if (new File(configPath).exists()) {
                FileInputStream inputStream = null;
                inputStream = new FileInputStream(configPath);

                this.properties.load(inputStream);
            } else {
                InputStream inputStream = ConfigurationManager.class
                        .getClassLoader().getResourceAsStream(appFile);
                this.properties.load(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationManager getManager(String app) throws Exception {
        return new ConfigurationManager(app);
    }

    public Properties getProperties() {
        return this.properties;
    }

    public void clearProperties() {
        this.properties.clear();
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    public Object setProperty(String key, String value) {
        return this.properties.setProperty(key, value);
    }

    public void saveFile(String fileName, String description) throws Exception {
        try {
            this.outputFile = new FileOutputStream(fileName);
            this.properties.store(this.outputFile, description);
            this.outputFile.close();
        } catch (FileNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new Exception(TAG, ex);
        } finally {
            if (this.outputFile != null)
                this.outputFile.close();
        }
    }
}