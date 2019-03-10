package core.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class TestBaseApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestBaseApp.class);
    private WebDriver driver = null;
    private String url = "";
    private String browser = "";
    private static TestBaseApp testBaseApp = null;

    private TestBaseApp() {
        Properties properties = getConfigurationData("config_file_name");
        url = properties.getProperty("url");
        browser = properties.getProperty("browser");
    }

    public static TestBaseApp getTestBaseAppInstance(){
        if(testBaseApp==null){
            testBaseApp = new TestBaseApp();
        }
        return  testBaseApp;
    }

    public static WebDriver getDriver(){
        return  getTestBaseAppInstance().driver;
    }

    private void setDriver(WebDriver driver){
        driver = this.driver;
    }

    public void setBrowser(){
        if(browser.equals("Fire Fox")){
            setDriver(new FirefoxDriver());
        }
    }

    private Properties getConfigurationData(String fileName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            FileInputStream fileInput = new FileInputStream(new File(classLoader.getResource(fileName).getFile()));
            Properties properties = new Properties();
            properties.load(fileInput);
            return properties;
        } catch (Exception e) {
            LOGGER.warn("configuration file is not found " + e.getLocalizedMessage(), e);
            Assert.fail("Failed to load the configuration file " + e.getLocalizedMessage(), e);
        }
        return null;
    }

    public void navigateToUrl(){
        driver.navigate().to(url);
    }

}
