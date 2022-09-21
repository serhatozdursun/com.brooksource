package steps;

import configuration.Configuration;
import driver.DriverManager;
import enums.FindWorkMenu;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import page.HomePage;
import page.JobDescriptionPage;
import page.JobSearchPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Steps {
    private final Logger log = LogManager.getLogger(Steps.class);

    @Given("Open {string} and get {string}")
    public void lunchBrowser(String browser, String url) {
        System.setProperty("browser", browser);
        DriverManager.getInstance().startDriverService();
        DriverManager.getInstance().lunchBrowser();
        log.info("{} is lunched", DriverManager.getInstance().getBrowser());
        var waitTime = Configuration.getInstance().getWaitTime();
        DriverManager.getInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
        DriverManager.getInstance().getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
        DriverManager.getInstance().getDriver().get(url);
        log.info("{} is loaded", url);
    }

    @And("Hover mouse over FIND WORK")
    public void clickOnFindWorkDropdown() {
        var homePage = new HomePage();
        homePage.hoverToFindWork();

    }

    @And("Select {menu} from FIND WORK")
    public void selectMenuFromFINDWORK(FindWorkMenu menu) {
        var homePage = new HomePage();
        switch (menu) {
            case OUR_PROCESS -> homePage.clickFindWorkProcess();
            case SEEK_A_POSITION -> homePage.clickSeekingPosition();
            case WHY_BROOKSOURCE -> homePage.clickWhatWeOffer();
        }
    }

    @And("Input {string} in to the job search field")
    public void inputInToTheJobSearchField(String text) {
        var searchJob = new JobSearchPage();
        searchJob.setSearch(text);
    }

    @And("Click on the magnifying glass to execute the search")
    public void clickOnTheMagnifyingGlassToExecuteTheSearch() {
        var searchJob = new JobSearchPage();
        searchJob.clickMagnifyingGlass();
    }

    @And("Store {int}. job title to compare")
    public void storeIntJobTitleToCompare(int index) {
        var searchJob = new JobSearchPage();
        searchJob.storeJobTitle(index - 1);
    }

    @When("Click on the {int}. available position")
    public void clickOnTheFirstAvailablePosition(int index) {
        var searchJob = new JobSearchPage();
        searchJob.clickListedJob(index - 1);
    }

    @Then("Assert that the stored job title and the displayed job title are the same")
    public void assertPositionDetailsDisplayed() {
        var jobDescription = new JobDescriptionPage();
        var descriptionPageJobTitle = jobDescription.getJobTitle();
        var searchResultPageJobTitle = JobSearchPage.getJobTitlesText();
        assertEquals(searchResultPageJobTitle, descriptionPageJobTitle,
                "The job titles displayed with the selected job are different.");
    }


}
