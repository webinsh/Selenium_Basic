package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;

public class AfterFeedbackPage extends GenericSamplePage {
    public String fieldValueError = "Field value was different from submitted one";
    @FindBy(how = How.ID, using = "name")
    private WebElement name;
    @FindBy(how = How.ID, using = "age")
    private WebElement age;
    @FindBy(how = How.ID, using = "language")
    private WebElement language;
    @FindBy(how = How.ID, using = "gender")
    private WebElement gender;
    @FindBy(how = How.ID, using = "option")
    private WebElement optionOfUs;
    @FindBy(how = How.ID, using = "comment")
    private WebElement comment;
    @FindBy(how = How.XPATH, using = "//button[text()=\"Yes\"]")
    private WebElement yesButton;
    @FindBy(how = How.XPATH, using = "//button[text()=\"No\"]")
    private WebElement noButton;

    public String getName() {
        return name.getText();
    }

    public String getAge() {
        return age.getText();
    }

    public String getLanguage() {
        return language.getText();
    }

    public String getGender() {
        return gender.getText();
    }

    public String getOptionOfUs() {
        return optionOfUs.getText();
    }

    public String getComment() {
        return comment.getText();
    }

    public void SubmitYes() {
        yesButton.click();
    }

    public void SubmitNo() {
        noButton.click();
    }

    public String getYesButtonColor() {
        return yesButton.getCssValue("background-color");
    }

    public String getNoButtonColor() {
        return noButton.getCssValue("background-color");
    }

    public void verifyGenericInputFields() {
        String name = "Raitis", age = "21", language1 = "English", language2 = "French", gender = "male",
                howDoYouLikeUsVal = "Ok, i guess", comment = "Cool task, let's see, if app will handle this comment!",
                yesColor = "rgba(76, 175, 80, 1)", noColor = "rgba(244, 67, 54, 1)";

        assertEquals(fieldValueError, name, getName());
        assertEquals(fieldValueError, age, getAge());
        assertEquals(fieldValueError, language1 + "," + language2, getLanguage());
        assertEquals(fieldValueError, gender, getGender());
        assertEquals(fieldValueError, howDoYouLikeUsVal, getOptionOfUs());
        assertEquals(fieldValueError, comment, getComment());
        assertEquals(fieldValueError, yesColor, getYesButtonColor());
        assertEquals(fieldValueError, noColor, getNoButtonColor());
    }
}
