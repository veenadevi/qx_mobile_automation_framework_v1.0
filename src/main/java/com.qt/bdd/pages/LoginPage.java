package com.qt.bdd.pages;

import com.qt.commonutils.QXClient;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.cucumber.java.an.E;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This Class has all the Objects related to Login Page.
 *
 * @author
 */
public class LoginPage {

    /**
     * This  @FindAll annotation locates the all element adress using the specified XPath
     *
     */

    @FindAll({
            @FindBy(xpath = "//android.widget.Button[@content-desc=\"Add Contact\"]"),})
    public MobileElement add_contact;
    @FindAll({
            @FindBy(xpath = "//android.widget.Spinner[@resource-id=\"com.example.android.contactmanager:id/accountSpinner\"]"),})
    public MobileElement target_Account;
    @FindAll({
            @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.example.android.contactmanager:id/contactNameEditText\"]"),})
    public MobileElement contact_Name;
    @FindAll({
            @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.example.android.contactmanager:id/contactPhoneEditText\"]"),})
    public MobileElement contactPhone;
    @FindAll({
            @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.example.android.contactmanager:id/contactEmailEditText\"]"),})
    public MobileElement contactEmail;
    @FindAll({
            @FindBy(xpath = "//android.widget.Button[@content-desc=\"Save\"]"),})
    public MobileElement save;
    @FindAll({
            @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/continue_button\"]"),})
    public MobileElement continueButton;
    @FindAll({
            @FindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]"),})
    public MobileElement okButton;
    @FindAll({
            @FindBy(xpath = "(//android.widget.Switch[@resource-id=\"android:id/switch_widget\"])[1]"),})
    public MobileElement switchContactOne;
    @FindAll({
            @FindBy(xpath = "(//android.widget.Switch[@resource-id=\"android:id/switch_widget\"])[2]"),})
    public MobileElement switchContactTwo;
    @FindAll({
            @FindBy(xpath = "(//android.widget.Switch[@resource-id=\"android:id/switch_widget\"])[3]"),})
    public MobileElement switchContactThree;

    @FindAll({
            @FindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]"),})
    public MobileElement dontAllow;


    /**
     * Constructor for LoginPage class.
     * Initializes page elements using PageFactory and AppiumFieldDecorator.
     */

    public LoginPage() {

        PageFactory.initElements(new AppiumFieldDecorator(QXClient.get().driver()), this);
    }


     /**
     * Enters the provided name into the name field.
     *
     * @param name The name to enter into the name field.
     * @throws Exception If any error occurs while entering the name.
     */



    public void enterName(String name) throws Exception {
        try {
            QXClient.get().gestures().waitForElementToVisible(contact_Name);
            contact_Name.sendKeys(name);
            QXClient.get().report().info("Enter the name");
            QXClient.get().logger().info("Enter the name");
        } catch (Exception e) {
            QXClient.get().report().info("TestCase failed due to : Enter the name not displayed");
            QXClient.get().logger().info("TestCase failed due to :Enter the name is not displayed");
        }

    }

    /**
     * Enters the provided phone number into the contact phone field.
     *
     * @param phone The phone number to enter into the contact phone field.
     * @throws Exception If any error occurs while entering the phone number.
     */

    public void enterContactPhone(String phone) throws Exception {
        try {
            QXClient.get().gestures().waitForElementToVisible(contactPhone);
            contactPhone.sendKeys(phone);
            QXClient.get().report().info("Enter the mobileNumber");
            QXClient.get().logger().info("Enter the mobileNumber");
        } catch (Exception e) {
            QXClient.get().report().info("TestCase failed due to : Enter the mobileNumber not displayed");
            QXClient.get().logger().info("TestCase failed due to :Enter the mobileNumber is not displayed");
        }

    }

    /**
     * Enters the provided email address into the email field.
     *
     * @param email The email address to enter into the email field.
     * @throws Exception If any error occurs while entering the email address.
     */


    public void enterEmail(String email) throws Exception {
        try {
            QXClient.get().gestures().waitForElementToVisible(contactEmail);
            contactEmail.sendKeys(email);
            QXClient.get().report().info("Enter the email");
            QXClient.get().logger().info("Enter the email");
        } catch (Exception e) {
            QXClient.get().report().info("TestCase failed due to : Enter the email not displayed");
            QXClient.get().logger().info("TestCase failed due to :Enter the email is not displayed");
        }

    }

    /**
     * Clicks on the save button to save the entered information.
     *
     * @throws Exception If any error occurs while clicking on the save button.
     */


    public void clickOnSave() throws Exception {
        try {
            Thread.sleep(1000);
            QXClient.get().gestures().waitAndClickElementisVisible(save);
            QXClient.get().report().info("Tap on save");
            QXClient.get().logger().info("Tap on save");
        }catch (Exception e){
            QXClient.get().report().info("TestCase failed due to : Tap on save is not displayed");
            QXClient.get().logger().info("TestCase failed due to : Tap on save is not displayed");
        }
    }

    /**
     * Clicks on the "Add Contact" button to initiate the contact addition process.
     *
     * @throws Exception If any error occurs while clicking on the "Add Contact" button.
     */


    public void clickOnAddContact() throws Exception {
        try {
            Thread.sleep(1000);
            QXClient.get().gestures().waitAndClickElementisVisible(add_contact);
            QXClient.get().report().info("Tap on add contact");
            QXClient.get().logger().info("Tap on add contact");
        }catch (Exception e){
            QXClient.get().report().info("TestCase failed due to : Tap on add contact is not displayed");
            QXClient.get().logger().info("TestCase failed due to : Tap on add contact is not displayed");
        }
    }

    /**
     * Checks if the "Add Contact" button is displayed on the page.
     *
     * @return True if the "Add Contact" button is displayed, false otherwise.
     * @throws Exception If any error occurs while checking the visibility of the "Add Contact" button.
     */

    public void isAddContactDisplayed() throws Exception {
        try {
            Thread.sleep(1000);
            QXClient.get().gestures().waitAndClickElementisVisible(switchContactOne);
            Thread.sleep(1000);
            if(QXClient.get().gestures().isElementPresent(dontAllow))
            {
                Thread.sleep(1000);
                QXClient.get().gestures().waitAndClickElementisVisible(dontAllow);
            }
            QXClient.get().gestures().waitAndClickElementisVisible(switchContactTwo);
            Thread.sleep(1000);
            QXClient.get().gestures().waitAndClickElementisVisible(switchContactThree);
            Thread.sleep(1000);
            QXClient.get().gestures().waitAndClickElementisVisible(continueButton);
            Thread.sleep(1000);
           if(QXClient.get().gestures().isElementPresent(okButton))
           {
               Thread.sleep(1000);
               QXClient.get().gestures().waitAndClickElementisVisible(okButton);
           }
           Thread.sleep(1000);
            QXClient.get().gestures().isElementPresent(add_contact);
            QXClient.get().report().info("add contact displayed");
            QXClient.get().logger().info("add contact displayed");
        }catch (Exception e){
            QXClient.get().report().info("TestCase failed due to : add contact is not displayed");
            QXClient.get().logger().info("TestCase failed due to : add contact is not displayed");
        }
    }



}



