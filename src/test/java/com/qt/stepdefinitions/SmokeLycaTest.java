package com.qt.stepdefinitions;

import com.qt.bdd.pages.*;
import com.qt.commonutils.QXClient;
import gherkin.lexer.Th;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;



/**
 * This Class has all the Objects related to SmokeTestCase.
 *
 * @author
 */
public class SmokeLycaTest {


    LoginPage loginPage;
    /**
     * Constructor for SmokeLycaTest class.
     * Initializes the LoginPage object.
     */

    public SmokeLycaTest() {

        loginPage = new LoginPage();

    }

    /**
     * Precondition step: I have installed the application
     * @throws Exception if there is an interruption while sleeping
     */

    @Given("I have installed the application")
    public void iHaveInstalledTheApplication() throws Exception {
        Thread.sleep(3000);
        loginPage.isAddContactDisplayed();
        Thread.sleep(1000);
       loginPage.clickOnAddContact();
    }

    /**
     * Step: I have to do the operation {name}, {email}, {number}
     * @param name The name to enter
     * @param email The email to enter
     * @param number The contact phone number to enter
     * @throws Exception if there is an interruption while sleeping
     */

    @When("I have to do the operation{string},{string},{string}")
    public void iHaveToDoTheOperation(String name,String email,String number) throws Exception {
        Thread.sleep(1000);
        loginPage.enterName(name);
        Thread.sleep(1000);
        loginPage.enterEmail(email);
        Thread.sleep(1000);
        loginPage.enterContactPhone(number);
    }

    @Then("I have to see the result")
    public void iHaveToSeeTheResult()throws Exception {
        Thread.sleep(1000);
        loginPage.clickOnSave();
    }

}


