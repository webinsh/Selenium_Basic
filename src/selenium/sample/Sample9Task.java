package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Sample9Task {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        String greenButtonSelector = "#start_green";
        String loadingGreenSelector = "#loading_green";
        String finishGreenSelector = "#finish_green";

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver,10).ignoring(StaleElementReferenceException.class);

        Thread.sleep(4000);
        WebElement greenButton = driver.findElement(By.cssSelector(greenButtonSelector));
        greenButton.click();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(greenButtonSelector)));
        Thread.sleep(4000);
        assertFalse("Button was visible",greenButton.isDisplayed());
        WebElement loadingGreen = driver.findElement(By.cssSelector(loadingGreenSelector));
        assertTrue("Loading green was not visible",loadingGreen.isDisplayed());
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish_green")));
        Thread.sleep(10000);
        assertFalse("Loading screen was visible",loadingGreen.isDisplayed());

        WebElement finishGreen = driver.findElement(By.cssSelector(finishGreenSelector));
        assertTrue("Finishing green loading screen was not present",finishGreen.isDisplayed());
        assertEquals("Message was different","Green Loaded",finishGreen.getText());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"

        String greenButtonSelector = "#start_green";
        String loadingGreenSelector = "#loading_green";
        String finishGreenSelector = "#finish_green";

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver,10).ignoring(StaleElementReferenceException.class);

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement greenButton = driver.findElement(By.cssSelector(greenButtonSelector));
        greenButton.click();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(greenButtonSelector)));
        //Thread.sleep(1000);
        WebElement loadingGreen = driver.findElement(By.cssSelector(loadingGreenSelector));
        assertFalse("Button was visible",greenButton.isDisplayed());

        assertTrue("Loading green was not visible",loadingGreen.isDisplayed());
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish_green")));
        //Thread.sleep(10000);
        WebElement finishGreen = driver.findElement(By.cssSelector(finishGreenSelector));
        assertFalse("Loading screen was visible",loadingGreen.isDisplayed());


        assertTrue("Finishing green loading screen was not present",finishGreen.isDisplayed());
        assertEquals("Message was different","Green Loaded",finishGreen.getText());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"

        String greenButtonSelector = "#start_green";
        String loadingGreenSelector = "#loading_green";
        String finishGreenSelector = "#finish_green";

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver,10).ignoring(StaleElementReferenceException.class);
        WebElement greenButton = driver.findElement(By.cssSelector(greenButtonSelector));
        greenButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(greenButtonSelector)));
        //Thread.sleep(1000);
        assertFalse("Button was visible",greenButton.isDisplayed());
        WebElement loadingGreen = driver.findElement(By.cssSelector(loadingGreenSelector));
        assertTrue("Loading green was not visible",loadingGreen.isDisplayed());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish_green")));
        //Thread.sleep(10000);
        assertFalse("Loading screen was visible",loadingGreen.isDisplayed());

        WebElement finishGreen = driver.findElement(By.cssSelector(finishGreenSelector));
        assertTrue("Finishing green loading screen was not present",finishGreen.isDisplayed());
        assertEquals("Message was different","Green Loaded",finishGreen.getText());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */
    }

}