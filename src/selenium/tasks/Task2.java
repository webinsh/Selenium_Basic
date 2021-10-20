package selenium.tasks;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.AfterFeedbackPage;
import selenium.pages.FeedbackPage;

import java.io.File;
import java.util.*;

public class Task2 {
    static WebDriver driver;
    static FeedbackPage feedbackPage;
    static AfterFeedbackPage afterFeedbackPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
        assertEquals(feedbackPage.emptyAssertError, "", feedbackPage.getNameInput());
        assertEquals(feedbackPage.emptyAssertError, "", feedbackPage.getAgeInput());
        assertEquals(feedbackPage.emptyAssertError, 0, feedbackPage.getLanguages().size());
        assertEquals(feedbackPage.emptyAssertError, "", feedbackPage.getGenders()); // Don't know is only gender, which has no value
        assertEquals(feedbackPage.emptyAssertError, "Choose your option", feedbackPage.getHowDoYouLikeUs());
        assertEquals(feedbackPage.emptyAssertError, "", feedbackPage.getCommentInput());
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
        feedbackPage.submitForm();
        afterFeedbackPage = PageFactory.initElements(driver, AfterFeedbackPage.class);
        List<String> values = Arrays.asList(
                afterFeedbackPage.getName(),
                afterFeedbackPage.getAge(),
                afterFeedbackPage.getLanguage(),
                afterFeedbackPage.getGender(),
                afterFeedbackPage.getOptionOfUs(),
                afterFeedbackPage.getComment()
        );
        for (String value : values) {
            if (!Objects.equals(value, "") && !Objects.equals(value, "null")) {
                fail("Value was not null or nothing");
            }
        }
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)

        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
        feedbackPage.fillFormWithGenericData();
        feedbackPage.submitForm();

        afterFeedbackPage = PageFactory.initElements(driver, AfterFeedbackPage.class);
        afterFeedbackPage.verifyGenericInputFields();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
        feedbackPage.setNameInput("Raitis");
        feedbackPage.submitForm();
        afterFeedbackPage = PageFactory.initElements(driver, AfterFeedbackPage.class);
        afterFeedbackPage.SubmitYes();
        WebElement message = driver.findElement(By.cssSelector("#message"));
        assertEquals(afterFeedbackPage.fieldValueError,"Thank you, Raitis, for your feedback!",message.getText());
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
        feedbackPage.submitForm();
        afterFeedbackPage = PageFactory.initElements(driver, AfterFeedbackPage.class);
        afterFeedbackPage.SubmitYes();
        WebElement message = driver.findElement(By.cssSelector("#message"));
        assertEquals(afterFeedbackPage.fieldValueError,"Thank you for your feedback!",message.getText());
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
        feedbackPage.fillFormWithGenericData();
        Thread.sleep(2000);
        feedbackPage.submitForm();
        afterFeedbackPage = PageFactory.initElements(driver, AfterFeedbackPage.class);
        Thread.sleep(2000);
        afterFeedbackPage.SubmitNo();
        Thread.sleep(2000);
        //feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);

        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);

        assertEquals(afterFeedbackPage.fieldValueError,feedbackPage.name,feedbackPage.getNameInput());
        assertEquals(afterFeedbackPage.fieldValueError,feedbackPage.age,feedbackPage.getAgeInput());
        assertEquals(afterFeedbackPage.fieldValueError,feedbackPage.language1,feedbackPage.getLanguages().get(0));
        assertEquals(afterFeedbackPage.fieldValueError,feedbackPage.language2,feedbackPage.getLanguages().get(1));
        assertEquals(afterFeedbackPage.fieldValueError,feedbackPage.gender,feedbackPage.getGenders());
        assertEquals(afterFeedbackPage.fieldValueError,feedbackPage.howDoYouLikeUsVal,feedbackPage.getHowDoYouLikeUs());
        assertEquals(afterFeedbackPage.fieldValueError,feedbackPage.comment,feedbackPage.getCommentInput());
    }
}
