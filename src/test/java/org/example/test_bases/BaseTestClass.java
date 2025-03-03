package org.example.test_bases;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.example.utils.WebDriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

@Log4j2
public class BaseTestClass {
    public WebDriver driver;
    public WebDriverWait wait;
    public Properties properties;
    public FileReader reader;
    public String browserName;

    @SneakyThrows
    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        driver = WebDriverFactory.getWebDriver(browser.toLowerCase());
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        properties = new Properties();
        reader = new FileReader("./src/test/resources/config.properties");
        properties.load(reader);
        browserName = browser;
        log.info("setUp completed for {}", this.getClass().getName());
    }

    public void openPage(String URL) {
        driver.get(URL);
        log.info("{} url is opened", URL);
    }

    public String captureScreenshot(String name) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + name + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        //noinspection ResultOfMethodCallIgnored
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        log.info("{} test Completed Successfully", this.getClass().getName());
    }
}
