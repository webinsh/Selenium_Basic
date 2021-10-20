package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.AddPersonData;
import selenium.pages.AddPersonPage;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;


//import pages.FormPage;
//import pages.ListPage;

public class Task3Bonus {
    WebDriver driver;
//	ListPage listPage = PageFactory.initElements(driver, ListPage.class);
//     should contain what you see when you just open the page (the table with names/jobs)
//	FormPage formPage = PageFactory.initElements(driver, FormPage.class);
//     should be what you see if you click "Add" or "Edit" (2 input field and a button (Add/Edit) and (Cancel)

//    Bonus:
//    try storing people via an Object/separate class

    static AddPersonData addPersonData;
    static AddPersonPage addPersonPage;
    String assertFailMessage = "Data in fields was different from expected";

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/list_of_people");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    public boolean checkIfOtherPersonsChanged(List<List<Object>> l1, List<List<Object>> l2, boolean sizeEqual, int excludedValues) {
        int deduct = 0;
        if (!sizeEqual) {
            deduct = -1;
        }

        if (l1.size() + deduct == l2.size()) {
            for (int i = 0; i < l1.size() - 1; i++) {
                if (!new HashSet(l1.get(i)).equals(new HashSet(l2.get(i))) && i != excludedValues) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public List<List<Object>> getPersonList() {
        addPersonPage = PageFactory.initElements(driver, AddPersonPage.class);
        List<List<Object>> retVal = new ArrayList<>();
        for (int i = 0; i < addPersonPage.getLastIndex() + 1; i++) {
            List<Object> child = new ArrayList<>();
            child.add(addPersonPage.getPersonName(i));
            child.add(addPersonPage.getPersonSurname(i));
            child.add(addPersonPage.getPersonJob(i));
            child.add(addPersonPage.getPersonDate(i));
            child.add(addPersonPage.getPersonLanguage(i));
            child.add(addPersonPage.getPersonGender(i));
            child.add(addPersonPage.getPersonEmploymentStatuses(i));

            retVal.add(child);
        }
        return retVal;
    }

    @Test
    public void addPerson() throws Exception {
        /* TODO:
         * implement adding new person using page object
         *
         * in order: store the list of people and jobs currently on page
         * add a person via "Add person button"
         * check the list again, that non of the people where changes, but an additional one with correct name/job was added
         */

        addPersonPage = PageFactory.initElements(driver, AddPersonPage.class);
        Thread.sleep(2000);
        List<List<Object>> persons = getPersonList();
        addPersonPage.addPersonOpenView();
        addPersonData = PageFactory.initElements(driver, AddPersonData.class);
        // Add new person
        addPersonData.fillFormWithGenericData();
        addPersonData.submitForm();

        addPersonPage = PageFactory.initElements(driver, AddPersonPage.class);
        int lastIndex = addPersonPage.getLastIndex();
        String name = addPersonPage.getPersonName(lastIndex);
        String surname = addPersonPage.getPersonSurname(lastIndex);
        String job = addPersonPage.getPersonJob(lastIndex);
        String date = addPersonPage.getPersonDate(lastIndex);
        String languages = addPersonPage.getPersonGender(lastIndex);
        String employmentStatus = addPersonPage.getPersonEmploymentStatuses(lastIndex);

        // Check added person
        assertEquals(assertFailMessage, addPersonData.name, addPersonPage.getPersonName(lastIndex));
        assertEquals(assertFailMessage, addPersonData.surname, addPersonPage.getPersonSurname(lastIndex));
        assertEquals(assertFailMessage, addPersonData.job, addPersonPage.getPersonJob(lastIndex));
        assertEquals(assertFailMessage, addPersonData.date, addPersonPage.getPersonDate(lastIndex));
        assertEquals(assertFailMessage, addPersonData.expectedLanguage, addPersonPage.getPersonLanguage(lastIndex));
        assertEquals(assertFailMessage, addPersonData.employmentStatus, addPersonPage.getPersonEmploymentStatuses(lastIndex));

        // Check, if other persons are not modified

        List<List<Object>> personsNew = getPersonList();
        if (!checkIfOtherPersonsChanged(personsNew, persons, false, -2)) {
            fail("Other persons have been modified");
        }
    }

    @Test
    public void editPerson() throws InterruptedException {
        /* TODO:
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link
         * check the list again and that 2 people stayed the same and the one used was changed
         */
        addPersonPage = PageFactory.initElements(driver, AddPersonPage.class);
        Thread.sleep(2000);
        List<List<Object>> persons = getPersonList();
        addPersonPage.openPersonEdit(0);

        addPersonData = PageFactory.initElements(driver, AddPersonData.class);
        addPersonData.updatePersonWithGenericData(true);

        List<List<Object>> newPersons = getPersonList();
        if (!checkIfOtherPersonsChanged(newPersons, persons, true, 0)) {
            fail("Other persons were modified");
        }

    }

    @Test
    public void editPersonCancel() throws InterruptedException {
        /* TODO:
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link then click "Cancel" on edit form instead of "Edit"
         * check the list again and that no changes where made
         */

        addPersonPage = PageFactory.initElements(driver, AddPersonPage.class);
        Thread.sleep(2000);
        List<List<Object>> persons = getPersonList();
        addPersonPage.openPersonEdit(0);

        addPersonData = PageFactory.initElements(driver, AddPersonData.class);
        addPersonData.updatePersonWithGenericData(false);

        List<List<Object>> newPersons = getPersonList();
        if (!checkIfOtherPersonsChanged(newPersons, persons, true, -2)) {
            fail("Persons was modified");
        }
    }


    @Test
    public void deletePerson() throws InterruptedException {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete 1 person see that there are now 2 people in the table with correct data
         */
        addPersonPage = PageFactory.initElements(driver, AddPersonPage.class);
        Thread.sleep(2000);
        List<List<Object>> persons = getPersonList();
        addPersonPage.deletePerson(0);
        Thread.sleep(2000);
        List<List<Object>> newPersons = getPersonList();

        if (newPersons.size() != 2) {
            fail("Person was not deleted");
        }
        if (!persons.containsAll(newPersons)) {
            fail("Person was not deleted");
        }

    }


    @Test
    public void deletePersonAll() throws InterruptedException {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete all people and check that there is no no table on page, but the button Add is still present and working
         */
        addPersonPage = PageFactory.initElements(driver, AddPersonPage.class);
        Thread.sleep(2000);
        List<List<Object>> persons = getPersonList();
        if (persons.get(0).size() == 0) {
            fail("There are no initial persons");
        }
        for (int i = 0; i < persons.size(); i++) {
            addPersonPage.deletePerson(0);
        }
        Thread.sleep(2000);
        List<List<Object>> newPersons = getPersonList();
        if (newPersons.size() != 0) {
            fail("Not all persons was deleted");
        }
    }
}
