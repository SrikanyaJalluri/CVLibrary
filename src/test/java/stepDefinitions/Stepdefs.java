package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

public class Stepdefs {

    WebDriver driver;
    final String URL = "https://www.cv-library.co.uk/";
    HomePage pages;

    @Before
    public void openBrowser(){

        if(driver==null){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
        pages = new HomePage(driver);
    }

    @After
    public void closeBrowser(){
        driver.quit();
        driver = null;
    }


    @Given("^JobSeeker is on landing page and open advance search$")
    public void jobseeker_is_on_landing_page() throws Throwable {

        pages.clickMoreLink();
    }

    // | JobTitle         | Location | Distance       | SalaryMin | SalaryMax | Salarytype | Jobtype |
    @When("^He search for a job with following details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void he_search_for_a_job_with_following_details(String jobTitle, String location, String distance, String salarymin, String salarymax, String salarytype, String jobtype) throws Throwable {
        pages.setAdvancedSearchData(jobTitle,location,distance,salarymin,salarymax,salarytype,jobtype);
    }

    @When("^Search for the jobs$")
    public void search_for_the_jobs() throws Throwable {
        pages.clickFindJobs();
    }

    @Then("^He should receive the matching jobs$")
    public void he_should_receive_the_matching_jobs() throws Throwable {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Jobs in London"));
    }



}
