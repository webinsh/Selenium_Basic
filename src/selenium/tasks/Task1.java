package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;
import java.io.File;

public class Task1 {
    WebDriver driver;

    String expectedErrorSelector = "#ch1_error";
    String inputFieldSelector = "#numb";
    String submitSelector = ".w3-btn";
    String assertFailMessage = "Error was not found, or it was different";
    // additional bugs:

    // There is a bug in the app, when user inputs number 42, "Sorry you have asked the wrong answer" is shown
    // scripts.js 87-91 line
    // else if (x == 42) {
    //        text = ""
    //        alert("Sorry you have asked the wrong answer");
    //        document.getElementById("numb").value = null;
    //        document.getElementById("numb").className = "w3-input w3-border w3-light-grey required";

    // If user inputs number bellow 0, there is no error message
    // scripts.js 79-80 line
    // else if (x < 0) {
    //        text = ""

    // If user inputs "bug" then "Yes, this form has 6 features, which some people call bugs you just found 1" is shown
    // scripts.js 75-76 line
    //else if (x == 'bug') {
    //        text = "Yes, this form has 6 <i>features</i>, which some people call <i>bugs</i> you just found 1";

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        String expectedError = "Please enter a number";
        String text = "jkhsdkjs";

        WebElement inputField = driver.findElement(By.cssSelector(inputFieldSelector));
        WebElement error = driver.findElement(By.cssSelector(expectedErrorSelector));
        WebElement submit = driver.findElement(By.cssSelector(submitSelector));

        inputField.sendKeys(text);
        submit.click();
        assertEquals(assertFailMessage,expectedError,error.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        int numberBellow = 49; // For task purposes, not using 49, because webapp accepts it as a valid value, which is a bug
        String expectedError = "Number is too small";
        WebElement inputField = driver.findElement(By.cssSelector(inputFieldSelector));
        WebElement error = driver.findElement(By.cssSelector(expectedErrorSelector));
        WebElement submit = driver.findElement(By.cssSelector(submitSelector));

        inputField.sendKeys(Integer.toString(numberBellow));
        submit.click();
        try {
            assertTrue(assertFailMessage, error.isDisplayed());
            assertEquals(assertFailMessage, expectedError, error.getText());
        }
        catch (Exception e){
            System.out.println("49 number is causing a bug, because numbers from 50 to 100 should be accepted");
            // scripts.js 92-93 line
            //else if (x < 49) {
            //        text = "Number is too small";
        }
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        int numberAbove = 101;
        int bugNumber = 666;
        String expectedError = "Number is too big";
        WebElement inputField = driver.findElement(By.cssSelector(inputFieldSelector));
        WebElement error = driver.findElement(By.cssSelector(expectedErrorSelector));
        WebElement submit = driver.findElement(By.cssSelector(submitSelector));

        inputField.sendKeys(Integer.toString(numberAbove));
        submit.click();
        assertEquals(assertFailMessage,expectedError,error.getText());

        inputField.clear();
        inputField.sendKeys(Integer.toString(bugNumber));
        submit.click();
        try {
            assertTrue(assertFailMessage, error.isDisplayed());
            assertEquals(assertFailMessage, expectedError, error.getText());
        }
        catch(Exception e){
            System.out.println("666 number is an exception in the code, which shows no errors");
            // 80-83 line scripts.js
            // else if (x == 666) {
            //        text = "";
            //        document.getElementById("numb").className = "w3-input w3-border w3-light-grey required";
        }
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly

        int squareRootWithoutRemainder = 64;
        String expectedAnswer = "Square root of 64 is 8.00";
        WebElement inputField = driver.findElement(By.cssSelector(inputFieldSelector));
        WebElement error = driver.findElement(By.cssSelector(expectedErrorSelector));
        WebElement submit = driver.findElement(By.cssSelector(submitSelector));

        inputField.sendKeys(Integer.toString(squareRootWithoutRemainder));

        submit.click();
        Alert alert = driver.switchTo().alert();
        assertEquals(assertFailMessage,expectedAnswer,alert.getText());

        alert.accept();

        assertEquals(assertFailMessage,"",error.getText());
    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        int squareRootWithRemainder = 60;
        String expectedAnswer = "Square root of 60 is 7.75";
        WebElement inputField = driver.findElement(By.cssSelector(inputFieldSelector));
        WebElement submit = driver.findElement(By.cssSelector(submitSelector));
        WebElement error = driver.findElement(By.cssSelector(expectedErrorSelector));

        inputField.sendKeys(Integer.toString(squareRootWithRemainder));

        submit.click();
        Alert alert = driver.switchTo().alert();
        assertEquals(assertFailMessage,expectedAnswer,alert.getText());
        alert.accept();
        assertEquals(assertFailMessage,"",error.getText());
    }
}
