package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class AddPersonData extends GenericSamplePage {
    @FindBy(how = How.ID, using = "addPersonBtn")
    private WebElement clearFields;
    @FindBy(how = How.ID, using = "name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "surname")
    private WebElement surnameInput;
    @FindBy(how = How.ID, using = "job")
    private WebElement jobInput;
    @FindBy(how = How.ID, using = "dob")
    private WebElement dateofbirthInput;
    @FindBy(how = How.CLASS_NAME, using = "w3-check")
    private List<WebElement> languagesInput;
    @FindBy(how = How.CLASS_NAME, using = "w3-radio")
    private List<WebElement> genderInput;
    @FindBy(how = How.ID, using = "status")
    private WebElement statusSelect;
    @FindBy(how = How.XPATH, using = "//button[text()=\"Add\"]")
    private WebElement addButton;
    @FindBy(how = How.XPATH, using = "//button[text()=\"Cancel\"]")
    private WebElement cancelButton;
    @FindBy(how = How.XPATH, using = "//h2")
    private WebElement clickAway;
    @FindBy(how = How.XPATH, using = "//button[text()=\"Edit\"]")
    private WebElement edit;

    public String name = "Raitis";
    public String surname = "Barkans";
    public String job = "Test engineer";
    public String date = "10/01/2021";
    public String language = "english";
    public String expectedLanguage = "English,";
    public String employmentStatus = "intern";
    public String gender = "male";

    public void ClearFields() {
        clearFields.click();
    }

    public String getName() {
        return nameInput.getText();
    }

    public void setName(String nameInput) {
        this.nameInput.clear();
        this.nameInput.sendKeys(nameInput);
    }

    public String getSurname() {
        return surnameInput.getText();
    }

    public void setSurname(String surnameInput) {
        this.surnameInput.clear();
        this.surnameInput.sendKeys(surnameInput);
    }

    public String getJob() {
        return jobInput.getText();
    }

    public void setJob(String jobInput) {
        this.jobInput.clear();
        this.jobInput.sendKeys(jobInput);
    }

    public String getDateofbirth() {
        return dateofbirthInput.getText();
    }

    public void setDateofbirth(String dateofbirthInput) {
        this.dateofbirthInput.clear();
        this.dateofbirthInput.sendKeys(dateofbirthInput);
        clickAway.click();
    }

    public List<String> getLanguages() {
        List<String> retVal = new ArrayList<String>();
        for (WebElement language : languagesInput) {
            if (language.isSelected()) {
                retVal.add(language.getAttribute("id"));
            }
        }
        return retVal;
    }

    public void setLanguage(String languagesInput) {
        for (WebElement language : this.languagesInput) {
            if (language.getAttribute("id") == languagesInput) {
                language.click();
            }
        }
    }

    public String getGender() {
        for (WebElement gender : genderInput) {
            if (gender.isSelected()) {
                return gender.getText();
            }
        }
        return "";
    }

    public void setGender(String genderInput) {
        for (WebElement gender : this.genderInput) {
            if (gender.getAttribute("id").equals(genderInput)) {
                gender.click();
            }
        }
    }

    public WebElement getStatusSelect() {
        Select temp = new Select(statusSelect);
        return temp.getFirstSelectedOption();
    }

    public void setStatusSelect(String statusSelect) {
        Select temp = new Select(this.statusSelect);
        temp.selectByValue(statusSelect);
    }

    public void cancelForm() {
        cancelButton.click();
    }

    public void submitForm() {
        addButton.click();
    }

    public void fillFormWithGenericData() {
        setName(name);
        setSurname(surname);
        setJob(job);
        setLanguage(language);
        setStatusSelect(employmentStatus);
        setGender(gender);
        setDateofbirth(date);
    }

    public void updatePersonWithGenericData(boolean editOrCancel) throws InterruptedException {
        fillFormWithGenericData();
        if (editOrCancel) {
            edit.click();
        } else {
            cancelButton.click();
        }
    }
}
