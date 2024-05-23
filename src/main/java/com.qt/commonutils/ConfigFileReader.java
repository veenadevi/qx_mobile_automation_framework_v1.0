package com.qt.commonutils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * This Class has all the Objects related to ConfigFileReader.
 *
 * @author
 */
public class ConfigFileReader {

    private final String propertyFilePath = "src/main/java/com.qt/config/Configuration.properties";
    private final Properties properties;

    /**
     * Constructs a new ConfigFileReader object.
     */

    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    /**
     * Retrieves the implicit wait time from the configuration properties.
     *
     * @return The implicit wait time in milliseconds.
     * @throws RuntimeException If implicitWaitTime is not specified in the Configuration.properties file.
     */

    public long getImplicitWaitTime() {
        String implicitWaitTime = properties.getProperty("implicitWaitTime");
        if (implicitWaitTime != null) return Long.parseLong(implicitWaitTime);
        else throw new RuntimeException("implicitWaitTime not specified in the Configuration.properties file.");
    }


    /**
     * Retrieves the home page URL from the configuration properties.
     *
     * @return The home page URL.
     * @throws RuntimeException If homePageURL is not specified in the Configuration.properties file.
     */
    public String getHomePageUrl() {
        String homePageURL = properties.getProperty("homePageURL");
        if (homePageURL != null) return homePageURL;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    /**
     * Retrieves the browser name from the configuration properties.
     *
     * @return The browser name.
     * @throws RuntimeException If the browser name is not specified or not supported in the Configuration.properties file.
     */

    public String getBrowserName() {
        String browserName = properties.getProperty("browser");
        if (browserName == null || browserName.equals("chrome")) return "chrome";
        else if (browserName.equalsIgnoreCase("firefox")) return browserName;
        else if (browserName.equals("ie")) return browserName;
        else if (browserName.equals("edge")) return browserName;
        else
            throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
    }

    /**
     * Retrieves the browser window size preference from the configuration properties.
     *
     * @return True if the window should be maximized, otherwise false.
     */


    public Boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("windowMaximize");
        if (windowSize != null) return Boolean.valueOf(windowSize);
        return true;
    }

    public String getPersonalDetailsJsonPath() {
        String personalDetailsJsonPath = properties.getProperty("personalDetailsJsonPath");
        if (personalDetailsJsonPath != null) return personalDetailsJsonPath;
        else
            throw new RuntimeException("Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");
    }

    /**
     * Retrieves the "About Us" text from the configuration properties.
     *
     * @return The "About Us" text.
     * @throws RuntimeException If the "About Us" text is not specified in the Configuration.properties file.
     */


    public String getAboutUSTextFromProperty() {
        String aboutUS = properties.getProperty("AboutUS");
        if (aboutUS != null) return aboutUS;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    /**
     * Retrieves the "Terms and Conditions" text from the configuration properties.
     *
     * @return The "Terms and Conditions" text.
     * @throws RuntimeException If the "Terms and Conditions" text is not specified in the Configuration.properties file.
     */

    public String getTermsAndConditionsTextFromProperty() {
        String termsAndConditions = properties.getProperty("TermsAndConditions");
        if (termsAndConditions != null) return termsAndConditions;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    /**
     * Retrieves the "Privacy Policy" text from the configuration properties.
     *
     * @return The "Privacy Policy" text.
     * @throws RuntimeException If the "Privacy Policy" text is not specified in the Configuration.properties file.
     */

    public String getPrivacyPolicyTextFromProperty() {
        String privacyPolicy = properties.getProperty("PrivacyPolicy");
        if (privacyPolicy != null) return privacyPolicy;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    /**
     * Retrieves the "Disclaimer" text from the configuration properties.
     *
     * @return The "Disclaimer" text.
     * @throws RuntimeException If the "Disclaimer" text is not specified in the Configuration.properties file.
     */

    public String getDisclaimerTextFromProperty() {
        String disclaimer = properties.getProperty("Disclaimer");
        if (disclaimer != null) return disclaimer;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    /**
     * Retrieves the "Contact Us" text from the configuration properties.
     *
     * @return The "Contact Us" text.
     * @throws RuntimeException If the "Contact Us" text is not specified in the Configuration.properties file.
     */

    public String getContactUsTextFromProperty() {
        String contactUs = properties.getProperty("ContactUs");
        if (contactUs != null) return contactUs;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    /**
     * Retrieves the "Facebook" text from the configuration properties.
     *
     * @return The "Facebook" text.
     * @throws RuntimeException If the "Facebook" text is not specified in the Configuration.properties file.
     */

    public String getFaceBookTextFromProperty() {
        String faceBook = properties.getProperty("FaceBook");
        if (faceBook != null) return faceBook;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    /**
     * Retrieves the "LinkedIn" text from the configuration properties.
     *
     * @return The "LinkedIn" text.
     * @throws RuntimeException If the "LinkedIn" text is not specified in the Configuration.properties file.
     */

    public String getLinkedInTextFromProperty() {
        String linkedIn = properties.getProperty("LinkedIn");
        if (linkedIn != null) return linkedIn;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    /**
     * Retrieves the "Twitter" text from the configuration properties.
     *
     * @return The "Twitter" text.
     * @throws RuntimeException If the "Twitter" text is not specified in the Configuration.properties file.
     */


    public String getTwitterTextFromProperty() {
        String twitter = properties.getProperty("Twitter");
        if (twitter != null) return twitter;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    /**
     * Retrieves the "YouTube" text from the configuration properties.
     *
     * @return The "YouTube" text.
     * @throws RuntimeException If the "YouTube" text is not specified in the Configuration.properties file.
     */

    public String getYouTubeTextFromProperty() {
        String youTube = properties.getProperty("YouTube");
        if (youTube != null) return youTube;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

}
//===========================================================================================================
    /*

    public String getPMKVY4SchemeTextFromProperty() {
        String PMKVY4Scheme = properties.getProperty("PMKVY4Scheme");
        if (PMKVY4Scheme != null) return PMKVY4Scheme;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getSectorsTextFromProperty() {
        String sectors = properties.getProperty("Sectors");
        if (sectors != null) return sectors;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getJobRolesTextFromProperty() {
        String jobRoles = properties.getProperty("JobRoles");
        if (jobRoles != null) return jobRoles;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getSkillIndiaMapTextFromProperty() {
        String skillIndiaMap = properties.getProperty("SkillIndiaMap");
        if (skillIndiaMap != null) return skillIndiaMap;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getMinisrtySkillDevTextFromProperty() {
        String minisrtySkillDev = properties.getProperty("MinisrtySkillDev");
        if (minisrtySkillDev != null) return minisrtySkillDev;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getNSDCTextFromProperty() {
        String NSDC = properties.getProperty("NSDC");
        if (NSDC != null) return NSDC;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getAmritmahotsavTextFromProperty() {
        String amritmahotsav = properties.getProperty("Amritmahotsav");
        if (amritmahotsav != null) return amritmahotsav;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getRecommendationInKannada() {
        String recommendation = properties.getProperty("RecommendationInKannada");
        if (recommendation != null) return recommendation;
        else throw new RuntimeException("Recommendation not specified in the Config property file");
    }

    public String getSkillCoursesInKannada() {
        String skillCourses = properties.getProperty("SkillCoursesInKannada");
        if (skillCourses != null) return skillCourses;
        else throw new RuntimeException("Recommendation not specified in the Config property file");
    }

    public String getJobExchangeInKannada() {
        String jobExchange = properties.getProperty("JobExchangeInKannada");
        if (jobExchange != null) return jobExchange;
        else throw new RuntimeException("Recommendation not specified in the Config property file");
    }

    public String getSkillCentresInKannada() {
        String skillCentre = properties.getProperty("SkillCentresInKannada");
        if (skillCentre != null) return skillCentre;
        else throw new RuntimeException("Skill Centres not specified in the Config property file");
    }

    public String getSkillIndiaMapInKannada() {
        String skillIndiaMap = properties.getProperty("SkillIndiaMapInKannada");
        if (skillIndiaMap != null) return skillIndiaMap;
        else throw new RuntimeException("Skill India Map not specified in the Config property file");
    }

    public String getMobileNumberForEditBtnVerify() {
        String mobNumberForEdit = properties.getProperty("MobileNumberForEdit");
        if (mobNumberForEdit != null) return mobNumberForEdit;
        else throw new RuntimeException("Mobile number not specified in the config property file");
    }

    public String getMobileNumberForLoginWithLearner() {
        String mobNumberForLearner = properties.getProperty("LearnerMobileNumber");
        if (mobNumberForLearner != null) return mobNumberForLearner;
        else throw new RuntimeException("Learner Mobile number not specified in the config property file");
    }

    public String getPinZero() {
        String pinZero = properties.getProperty("PinZero");
        if (pinZero != null) return pinZero;
        else throw new RuntimeException("Pin zero is not specified in the config property file");
    }

    public String getPinOne() {
        String pinOne = properties.getProperty("PinOne");
        if (pinOne != null) return pinOne;
        else throw new RuntimeException("Pin one is not specified in the config property file");
    }

    public String getPinTwo() {
        String pinTwo = properties.getProperty("PinTwo");
        if (pinTwo != null) return pinTwo;
        else throw new RuntimeException("pinTwo is not specified in the config property file");
    }

    public String getPinThree() {
        String pinThree = properties.getProperty("PinThree");
        if (pinThree != null) return pinThree;
        else throw new RuntimeException("pinThree is not specified in the config property file");
    }

    public String getBengaluruCity() {
        String bengaluru = properties.getProperty("BangaloreCity");
        if (bengaluru != null) return bengaluru;
        else throw new RuntimeException("Bengaluru city is not specified in the config property file");
    }

    public String getIndustry() {
        String industry = properties.getProperty("Industry");
        if (industry != null) return industry;
        else throw new RuntimeException("Industry is not specified in the config property file");
    }

    public String getLocation() {
        String location = properties.getProperty("LocationCity");
        if (location != null) return location;
        else throw new RuntimeException("Location is not specified in the config property file");
    }

    public String getState() {
        String state = properties.getProperty("StateFilter");
        if (state != null) return state;
        else throw new RuntimeException("State is not specified in the config property file");
    }

    public String getCourse() {
        String course = properties.getProperty("CourseFilter");
        if (course != null) return course;
        else throw new RuntimeException("Course is not specified in the config property file");
    }

    public String getJobExchangeName() {
        String jobExchangeName = properties.getProperty("JobExchangeName");

        if (jobExchangeName != null) return jobExchangeName;
        else throw new RuntimeException("JobExchangeName is not specified in the config property file");
    }

    public String getJobExchangeRadioButtonSearch() {
        String jobExchangeRadioButtonSearch = properties.getProperty("JobExchangeRadioButtonSearch");

        if (jobExchangeRadioButtonSearch != null) return jobExchangeRadioButtonSearch;

        else throw new RuntimeException("jobExchangeRadioButtonSearch is not specified in the config property file");
    }


    public String getSkillMapDigitalCity() {
        String skillMapDigitalCity = properties.getProperty("SkillMapDigitalCity");
        if (skillMapDigitalCity != null) return skillMapDigitalCity;
        else throw new RuntimeException("skillMapDigitalCity is not specified int he config property file");
    }

    public String getJobExchangeSearchValue() {
        String jobExchangeSearchValue = properties.getProperty("JobExchangeSearchValue");
        if (jobExchangeSearchValue != null) return jobExchangeSearchValue;
        else throw new RuntimeException("jobExchangeSearchValue is not specified int he config property file");

    }

    public long getExplicitWaitTime() {
        String explicitWaitTime = properties.getProperty("explicitWaitTime");
        if (explicitWaitTime != null) return Long.parseLong(explicitWaitTime);
        else throw new RuntimeException("implicitWaitTime not specified in the Configuration.properties file.");
    }

    public String getWelcomePopupTermsAndConditions() {
        String welcomeTermsAndConditions = properties.getProperty("WelcomePopupTermsAndConditions");
        if (welcomeTermsAndConditions != null) return welcomeTermsAndConditions;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getWelcomePopupPrivacyPolicy() {
        String welcomePrivacyPolicy = properties.getProperty("WelcomePopupPrivacyPolicy");
        if (welcomePrivacyPolicy != null) return welcomePrivacyPolicy;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getHomePageUrlToVerify() {
        String homePageUrl = properties.getProperty("Home");
        if (homePageUrl != null) return homePageUrl;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getGovernmentOfIndiaTextFromProperty() {
        String governmentOfIndia = properties.getProperty("GovernmentOfIndia");
        if (governmentOfIndia != null) return governmentOfIndia;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    //------------------------------------   CreditCard Data   -----------------------------------------//
    public String getTheNameOfCreditCardHolder() {
        String creditCardName = properties.getProperty("creditCardName");
        if (creditCardName != null) return creditCardName;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String getVerficationCode() {
        String verificationCode = properties.getProperty("verificationCode");
        if (verificationCode != null) return verificationCode;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }


    public String getTheCreditCardNumber() {
        String creditCardNumber = properties.getProperty("creditCardNumber");
        if (creditCardNumber != null) return creditCardNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getTheCreditCardExpiryDate() {
        String creditCardExpiryDate = properties.getProperty("creditCardExpiryDate");
        if (creditCardExpiryDate != null) return creditCardExpiryDate;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getCreditCardCVV() {
        String creditCardCVV = properties.getProperty("creditCardCVV");
        if (creditCardCVV != null) return creditCardCVV;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getCreditCardEmailId() {
        String creditCardEmailId = properties.getProperty("creditCardEmailId");
        if (creditCardEmailId != null) return creditCardEmailId;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getCreditCardHouseNumber() {
        String creditCardHouseNo = properties.getProperty("creditCardHouseNo");
        if (creditCardHouseNo != null) return creditCardHouseNo;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getCreditCardStreetName() {
        String creditCardStreetName = properties.getProperty("creditCardStreetName");
        if (creditCardStreetName != null) return creditCardStreetName;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getCreditCardCity() {
        String creditCardCity = properties.getProperty("creditCardCity");
        if (creditCardCity != null) return creditCardCity;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getCreditCardCounty() {
        String creditCardCounty = properties.getProperty("creditCardCounty");
        if (creditCardCounty != null) return creditCardCounty;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getCreditCardCountry() {
        String creditCardCountry = properties.getProperty("creditCardCountry");
        if (creditCardCountry != null) return creditCardCountry;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getCreditCardPostCode() {
        String creditCardpostCode = properties.getProperty("creditCardpostCode");
        if (creditCardpostCode != null) return creditCardpostCode;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getfirstName() {
        String payfirstName = properties.getProperty("payfirstName");
        if (payfirstName != null) return payfirstName;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String getlastName() {
        String PaylastName = properties.getProperty("PaylastName");
        if (PaylastName != null) return PaylastName;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getPayEmailAddress() {
        String PayEnterEmailAddress = properties.getProperty("PayEnterEmailAddress");
        if (PayEnterEmailAddress != null) return PayEnterEmailAddress;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getPayHouseNumber() {
        String PayEnterHouseNumber = properties.getProperty("PayEnterHouseNumber");
        if (PayEnterHouseNumber != null) return PayEnterHouseNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getPayStreetName() {
        String PayEnterStreetName = properties.getProperty("PayEnterStreetName");
        if (PayEnterStreetName != null) return PayEnterStreetName;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String getPayApartmentNumber() {
        String PayEnterApartmentNumber = properties.getProperty("PayEnterApartmentNumber");
        if (PayEnterApartmentNumber != null) return PayEnterApartmentNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String getPayCityName() {
        String PayEnterCityName = properties.getProperty("PayEnterCityName");
        if (PayEnterCityName != null) return PayEnterCityName;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getPayCountyName() {
        String PayEnterCountyName = properties.getProperty("PayEnterCountyName");
        if (PayEnterCountyName != null) return PayEnterCountyName;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getPayCountryName() {
        String PayEnterCountryName = properties.getProperty("PayEnterCountryName");
        if (PayEnterCountryName != null) return PayEnterCountryName;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getPayPostCode() {
        String PayEnterPostCode = properties.getProperty("PayEnterPostCode");
        if (PayEnterPostCode != null) return PayEnterPostCode;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterMobileNumberForFamilyPlan() {
        String familyPlanMobileNumber = properties.getProperty("familyPlanMobileNumber");
        if (familyPlanMobileNumber != null) return familyPlanMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherMobileNumberForRecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileNumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherMobileNumberForATRecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileATNumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherMobileNumberForAURecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileAUNumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherMobileNumberForCHRecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileCHNumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherMobileNumberForBERecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileBENumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherMobileNumberForDERecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileDENumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherMobileNumberForDKRecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileDKNumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherMobileNumberForFRRecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileFRNumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherMobileNumberForIRRecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileIRNumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherMobileNumberForITRecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileITNumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherPersonNumberForNLRecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileNLNumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherPersonNumberForNORecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileNONumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherPersonNumberForPLRecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobilePLNumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherPersonNumberForPTRecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobilePTNumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherPersonNumberForSERecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileSENumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherPersonNumberForUKRecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileUKNumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String enterOtherPersonNumberForUSARecharge() {
        String enterOtherMobileNumber = properties.getProperty("enterOtherMobileUSANumber");
        if (enterOtherMobileNumber != null) return enterOtherMobileNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String getTheNameOfInsufficientCreditCardHolder() {
        String insufficientCreditCardName = properties.getProperty("insufficientCreditCardName");
        if (insufficientCreditCardName != null) return insufficientCreditCardName;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }
    public String getTheInsufficientCreditCardNumber() {
        String insufficientCreditCardNumber = properties.getProperty("insufficientCreditCardNumber");
        if (insufficientCreditCardNumber != null) return insufficientCreditCardNumber;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getTheInsuffcientCreditCardExpiryDate() {
        String insufficientCreditCardExpiryDate = properties.getProperty("insufficientCreditCardExpiryDate");
        if (insufficientCreditCardExpiryDate != null) return insufficientCreditCardExpiryDate;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getInsuffcientCreditCardCVV() {
        String insufficientCreditCardCVV = properties.getProperty("insufficientCreditCardCVV");
        if (insufficientCreditCardCVV != null) return insufficientCreditCardCVV;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

    public String getInsuffcientCreditCardEmailId() {
        String insufficientCreditCardEmailId = properties.getProperty("insufficientCreditCardEmailId");
        if (insufficientCreditCardEmailId != null) return insufficientCreditCardEmailId;
        else throw new RuntimeException("Url not specified in the Configuration.properties file.");
    }

}


     */