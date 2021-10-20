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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class Sample7Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";


    public boolean retryingFindClick(By by) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                driver.findElement(by).click();
                result = true;
                break;
            } catch(Exception e) {
            }
            attempts++;
        }
        return result;
    }

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void selectCheckBox() throws Exception {
//         TODO:
//        check that none of the checkboxes are ticked
//        tick  "Option 2"
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//        tick  "Option 3"
//        click result
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        String expectedText = "You selected value(s): Option 2, Option 3";
        String resultSelector = "#result_button_checkbox";
        String resultMessageSelector = "#result_checkbox";

        String checkboxesSelector = "//input[@type=\"checkbox\"]";
        List<WebElement> checkboxes = driver.findElements(By.xpath(checkboxesSelector));
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                fail("Unexpected checkboxes are selected");
            }
        }
        checkboxes.get(1).click();

        for(int i=0;i<checkboxes.size();i++){
            if(checkboxes.get(i).isSelected() && i !=1){
                fail();
            }
        }

        checkboxes.get(2).click();
        driver.findElement(By.cssSelector(resultSelector)).click();
        assertEquals("Result message was different",expectedText,driver.findElement(By.cssSelector(resultMessageSelector)).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
//        select  "Option 3"
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
//        select  "Option 1"
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
//        click result
//        check that 'You selected option: Option 1' text is being displayed
        String buttonSelector = "[type=radio]";
        String resultSelector = "#result_button_ratio";
        String resultMessageSelector = "#result_radio";
        String resultMessage = "You selected option: Option 1";

        List<WebElement> radios = driver.findElements(By.cssSelector(buttonSelector));
        for (WebElement element : radios){
            if(element.isSelected()){
                fail("Radio button was selected");
            }
        }
        radios.get(2).click();
        for(int i=0;i<radios.size();i++){
            if(radios.get(i).isSelected() && i != 2){
                fail("Other radio button was selected, instead of expected one");
            }
        }
        radios.get(0).click();

        for(int i=0;i<radios.size();i++){
            if(radios.get(i).isSelected() && i != 0){
                fail("Other radio button was selected, instead of expected one");
            }
        }

        driver.findElement(By.cssSelector(resultSelector)).click();
        assertEquals("Result message was different",resultMessage,driver.findElement(By.cssSelector(resultMessageSelector)).getText());
    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
//        check that selected option is "Option 3"
//        select "Option 2" in Select
//        check that selected option is "Option 2"
//        click result
//        check that 'You selected option: Option 2' text is being displayed
        String selectSelector = "#vfb-12";
        String option3 = "value3";
        String option2 = "value2";
        String resultSelector = "#result_button_select";
        String resultMessage = "You selected option: Option 2";
        String resultMessageSelector = "#result_select";

        Select select = new Select(driver.findElement(By.cssSelector(selectSelector)));
        select.selectByValue(option3);
        assertEquals("Different value was selected","Option 3",select.getFirstSelectedOption().getText());

        select.selectByValue(option2);
        assertEquals("Different value was selected","Option 2",select.getFirstSelectedOption().getText());
        driver.findElement(By.cssSelector(resultSelector)).click();
        assertEquals("Result message was different",resultMessage,driver.findElement(By.cssSelector(resultMessageSelector)).getText());

    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception{
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.clear();
        assertEquals("", dateBox.getAttribute("value"));
        dateBox.click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
//    go back 10 month in calendar on page
        for (int i = 0; i < 171; i++) {
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }

        dateWidget.findElement(By.xpath("//a[text()='4']")).click();
        driver.findElement(By.id("result_button_date")).click(); //click on result button
        Thread.sleep(2000);
        WebElement displayedTxt = driver.findElement(By.id("result_date"));
        String expectedTxt = "You entered date: 07/04/2007";
        try {
            assertEquals(expectedTxt, displayedTxt.getText());
        }catch (AssertionError e) {
            System.out.println("Result should be : \"You entered date: 07/04/2007\"");
            e.printStackTrace();
        }

    }
/*    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
        String calendarFieldSelector = "input#vfb-8";
        String calendarDatePrevSelector = "a.ui-datepicker-prev";
        String calendarYearSelector = "span.ui-datepicker-year";
        String calendarDaysSelector = "a.ui-state-default";
        String calendarWidgetSelector = "#ui-datepicker-div";

        WebDriverWait wait = new WebDriverWait(driver,10);

        WebElement calendarField = driver.findElement(By.cssSelector(calendarFieldSelector));

        calendarField.click();
        WebElement calendarWidget = driver.findElement(By.cssSelector(calendarWidgetSelector));
        WebElement calendarDatePrev = calendarWidget.findElement(By.cssSelector(calendarDatePrevSelector));
        WebElement calendarYear = calendarWidget.findElement(By.cssSelector(calendarYearSelector));

        // get year
        //wait.until(ExpectedConditions.stalenessOf(calendarYear));
        //while(!Objects.equals(calendarYear.getText(), "2007")){
            //wait.until(ExpectedConditions.stalenessOf(calendarDatePrev));
            //retryingFindClick(By.cssSelector(calendarDatePrevSelector));
        //    if(calendarDatePrev.isDisplayed())
        //        calendarDatePrev.click();
        //}
        //Thread.sleep(2000);

        wait.until(ExpectedConditions.stalenessOf(calendarDatePrev));
        for(int i=0;i<171;i++){
            calendarDatePrev.click();
        }
        Thread.sleep(2000);

    }*/

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added
    }
}
