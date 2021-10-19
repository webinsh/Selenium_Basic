package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample6Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void findElementByXPath() throws Exception {
//         TODO:
//        2 ways to find text: "Heading 2 text":
        driver.findElement(By.xpath("//h2[@id=\"heading_2\"]"));
        driver.findElement(By.xpath("//*[text()=\"Heading 2 text\"]"));
//        1-2 ways to find text: "Test Text 1"
        driver.findElement(By.xpath("//div[contains(@class,test1)]/p[contains(text(),\"Test Text 1\")]"));
        driver.findElement(By.xpath("//*[@id=\"test1\"]/p[@class=\"test\"]"));
//        1-2 ways to find text: "Test Text 2"
        driver.findElement(By.xpath("//p[@class=\"twoTest\"]"));
        driver.findElement(By.xpath("//p[text()=\"Test Text 2\"]"));
//        1-2 ways to find text: "Test Text 3"
        driver.findElement(By.xpath("//p[text()=\"Test Text 3\"]"));
        driver.findElement(By.xpath("//div[@id=\"test3\"]/p"));
//        1-2 ways to find text: "Test Text 4"
        driver.findElement(By.xpath("//div[@id=\"test3\"]/p[2]"));
        driver.findElement(By.xpath("//p[text()=\"Test Text 4\"]"));
//        1-2 ways to find text: "Test Text 5"
        driver.findElement(By.xpath("//p[text()=\"Test Text 5\"]"));
        driver.findElement(By.xpath("//div[@id=\"test2\"]/p"));
//        1-2 ways to find text: "This is also a button"
        driver.findElement(By.xpath("//input[2]"));
        driver.findElement(By.xpath("//input[contains(@value,\"also\")]"));

    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        driver.findElement(By.cssSelector("#heading_2"));
        driver.findElement(By.cssSelector("h2:last-of-type"));
//        1-2 ways to find text: "Test Text 1"
        driver.findElement(By.cssSelector("#test1 p.test"));
        driver.findElement(By.cssSelector("#test1 > p.test"));
//        1-2 ways to find text: "Test Text 2"
        driver.findElement(By.cssSelector("#test1 > p:last-child"));
        driver.findElement(By.cssSelector("p.twoTest"));
//        1-2 ways to find text: "Test Text 3"
        driver.findElement(By.cssSelector("#test3 > p:nth-child(1)"));
        driver.findElement(By.cssSelector("div#test3 > .test"));
//        1-2 ways to find text: "This is also a button"
        driver.findElement(By.cssSelector("input[id=buttonId]"));
        driver.findElement(By.cssSelector("input[value=\"This is also a button\"]"));
    }
}
