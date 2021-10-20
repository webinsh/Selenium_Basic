package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddPersonPage extends GenericSamplePage {
    @FindBy(how = How.ID, using = "addPersonBtn")
    private WebElement addPersonButton;
    @FindBy(how = How.CLASS_NAME, using = "name")
    private List<WebElement> personNames;
    @FindBy(how = How.CLASS_NAME, using = "surname")
    private List<WebElement> personSurnames;
    @FindBy(how = How.CLASS_NAME, using = "job")
    private List<WebElement> personJobs;
    @FindBy(how = How.CLASS_NAME, using = "dob")
    private List<WebElement> personDates;
    @FindBy(how = How.CLASS_NAME, using = "language")
    private List<WebElement> personLanguages;
    @FindBy(how = How.CLASS_NAME, using = "gender")
    private List<WebElement> personGenders;
    @FindBy(how = How.CLASS_NAME, using = "status")
    private List<WebElement> personEmploymentStatuses;
    @FindBy(how = How.CLASS_NAME, using = "fa-pencil")
    private List<WebElement> editButtons;
    @FindBy(how = How.XPATH, using = "//span[contains(@onclick,\"delete\")]")
    private List<WebElement> deleteButtons;
    @FindBy(how = How.ID, using = "status")
    private WebElement statusSelect;


    private String strpersonName = "";
    private String strpersonSurname = "";
    private String strpersonJob = "";
    private String strpersonDateOfBirth = "";
    private String strpersonLanguages = "";
    private String strpersonGender = "";
    private String strpersonEmploymentStatus = "";
    private int indexToUpdate = -2;
    private List<List<Object>> persons;

    public void setIndexToUpdate(int index) {
        indexToUpdate = index;
    }

    public String getPersonName(int index) {
        return personNames.get(index).getText();
    }

    public void setPersonNames(String value) {
        strpersonName = value;
    }

    public String getPersonSurname(int index) {
        return personSurnames.get(index).getText();
    }

    public void setPersonSurname(String value) {
        strpersonSurname = value;
    }

    public String getPersonJob(int index) {
        return personJobs.get(index).getText();
    }

    public void setPersonJob(String value) {
        strpersonJob = value;
    }

    public String getPersonDate(int index) {
        return personDates.get(index).getText();
    }

    public void setPersonDates(String value) {
        strpersonDateOfBirth = value;
    }

    public String getPersonLanguage(int index) {
        return personLanguages.get(index).getText();
    }

    public void setPersonLanguage(String language) {
        strpersonLanguages = language;
    }

    public String getPersonGender(int index) {
        return personGenders.get(index).getText();
    }

    public void setPersonGender(String gender) {
        strpersonGender = gender;
    }

    public String getPersonEmploymentStatuses(int index) {
        return personEmploymentStatuses.get(index).getText();
    }

    public void setPersonEmploymentStatuses(String value) {
        strpersonEmploymentStatus = value;
    }


    public void addPersonOpenView() {
        addPersonButton.click();
    }

    public int getLastIndex() {
        return personNames.size() - 1;
    }

    public void openPersonEdit(int index) {
        editButtons.get(index).click();
    }

    public void deletePerson(int index) {
        deleteButtons.get(index).click();
    }
}
