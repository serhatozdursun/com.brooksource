package page;

import configuration.Configuration;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utilities.BaseTest;

import java.util.List;


public class JobSearchPage extends BaseTest {
    private static String jobTitlesText;
    public JobSearchPage() {
        var waitTime = Configuration.getInstance().getWaitTime();
        var ajax = new AjaxElementLocatorFactory(DriverManager.getInstance().getDriver(), waitTime);
        PageFactory.initElements(ajax, this);
        waitUntilPageLoad();
    }

    @FindBy(css = ".facetwp-search")
    private WebElement search;
    @FindBy(css = ".facetwp-icon")
    private WebElement magnifyingGlass;
    @FindBy(css = ".job_listings ul")
    private List<WebElement> listedJobs;
    @FindBy(tagName = "h3")
    private List<WebElement> jobTitles;


    public void setSearch(String searchText) {
        sendKeys(search, searchText);
    }

    public void clickMagnifyingGlass() {
        var beforeListedJob = listedJobs.size();
        click(magnifyingGlass);
        do {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (beforeListedJob == listedJobs.size());
    }

    public void clickListedJob(int index) {
        click(listedJobs.get(index));
    }

    public void storeJobTitle(int index) {
        var jobTitle = getText(jobTitles.get(index));
        setJobTitlesText(jobTitle);
    }

    public static String getJobTitlesText() {
        return jobTitlesText;
    }

    private static void setJobTitlesText(String text) {
        jobTitlesText = text;
    }
}
