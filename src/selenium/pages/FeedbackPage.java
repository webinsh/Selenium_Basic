package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class FeedbackPage extends GenericSamplePage {
    public String emptyAssertError = "Field was not empty";
    public String name = "Raitis", age = "21", language1 = "English", language2 = "French", gender = "male",
            howDoYouLikeUsVal = "Ok, i guess", comment = "Cool task, let's see, if app will handle this comment!",
            yesColor = "rgba(76, 175, 80, 1)", noColor = "rgba(244, 67, 54, 1)";
    @FindBy(how = How.ID, using = "fb_name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "fb_age")
    private WebElement ageInput;
    @FindBy(how = How.CLASS_NAME, using = "w3-check")
    private List<WebElement> languages;
    @FindBy(how = How.CLASS_NAME, using = "w3-radio")
    private List<WebElement> genders;
    @FindBy(how = How.CLASS_NAME, using = "w3-select")
    private WebElement howDoYouLikeUs;
    @FindBy(how = How.NAME, using = "comment")
    private WebElement commentInput;
    @FindBy(how = How.CLASS_NAME, using = "w3-btn-block")
    private WebElement submitButton;

    public FeedbackPage() {
    }

    public void submitForm() {
        submitButton.click();
    }

    public String getNameInput() {
        return nameInput.getAttribute("value");
    }

    public void setNameInput(String nameInput) {
        this.nameInput.sendKeys(nameInput);
    }

    public String getAgeInput() {
        return ageInput.getAttribute("value");
    }

    public void setAgeInput(String ageInput) {
        this.ageInput.sendKeys(ageInput);
    }

    public List<String> getLanguages() {
        List<String> retVal = new ArrayList<String>();
        for (WebElement element : this.languages) {
            if (element.isSelected()) {
                retVal.add(element.getAttribute("value"));
            }
        }
        return retVal;
    }

    public void setLanguages(String languageName) {
        for (WebElement element : this.languages) {
            if (element.getAttribute("value").equals(languageName)) {
                element.click();
            }
        }
    }

    public String getGenders() {
        for (WebElement element : this.genders) {
            if (element.isSelected()) {
                return element.getAttribute("value");
            }
        }
        return "";
    }

    public void setGenders(String gender) {
        for (WebElement element : this.genders) {
            if (element.getAttribute("value").equals(gender)) {
                element.click();
            }
        }
    }

    public String getHowDoYouLikeUs() {
        Select howDoYouLikeUs = new Select(this.howDoYouLikeUs);
        return howDoYouLikeUs.getFirstSelectedOption().getAttribute("value");
    }

    public void setHowDoYouLikeUs(String value) {
        Select howDoYouLikeUs = new Select(this.howDoYouLikeUs);
        howDoYouLikeUs.selectByVisibleText(value);
    }

    public String getCommentInput() {
        return commentInput.getAttribute("value");
    }

    public void setCommentInput(String commentInput) {
        this.commentInput.sendKeys(commentInput);
    }

    public void fillFormWithGenericData() {
        setNameInput(name);
        setAgeInput(age);
        setLanguages(language1);
        setLanguages(language2);
        setGenders(gender);
        setHowDoYouLikeUs(howDoYouLikeUsVal);
        setCommentInput(comment);
    }

}
