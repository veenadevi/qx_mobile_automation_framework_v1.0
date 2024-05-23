package com.qt.commonutils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

/**
 * This Class has all the Objects related to WaitForUtils.
 *
 * @author
 */

public class WaitForUtils {
  public AppiumDriver driver;
  WebDriverWait wait = null;

    /**
     * Constructs a WaitForUtils object with the given AppiumDriver.
     *
     * @param driver The AppiumDriver instance to be used for waiting operations.
     */
  public WaitForUtils(AppiumDriver driver) {
    this.driver = driver;
  }


    /**
     * Waits until the given mobile element is visible and clickable.
     *
     * @param element The mobile element to wait for visibility and clickability.
     */
  public void waitTillTheElementIsVisibleAndClickable(MobileElement element) {
    WebDriverWait wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.visibilityOf(element));
    wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  /**
     * This will Wait maximum 20 secs for the list to have atleast one element.
     *
     * @author :
     * @since :
     */
  public void waitTillListHasElements(List<MobileElement> list) {
    for (byte i = 1; list.size() == 0 && i <= 20; i++) {
      waitInSec(1);
    }
  }

    /**
     * Waits until the given mobile element is invisible.
     *
     * @param element The mobile element to wait for invisibility.
     */

  public void waitTillTheElementInVisible(MobileElement element) {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.invisibilityOf(element));
  }

    /**
     * Waits until the page is loaded by waiting for the specified element to be clickable.
     *
     * @param element The web element used to determine page loading.
     */

  public void waitForPageToLoad(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    /**
     * Waits until the specified element becomes stale and then waits for it to be clickable again.
     *
     * @param element The web element to wait for its state.
     */

  public void waitForElementState(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.stalenessOf(element));
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until all elements in the specified list are visible on the page.
     *
     * @param elementList The list of web elements to wait for their visibility.
     */

  public void waitForPageToLoad(List<WebElement> elementList) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
    }
    /**
     * Waits until all elements in the specified list are not visible on the page.
     *
     * @param elementList The list of web elements to wait for their invisibility.
     * @param timeout     The maximum time to wait in seconds.
     */

  public void waitForElementToDisAppear(List<WebElement> elementList, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOfAllElements(elementList));
    }

    /**
     * Waits until the specified mobile element is visible on the page.
     *
     * @param element The mobile element to wait for its visibility.
     */

  public void waitForElementToAppear(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the specified web element is visible on the page.
     *
     * @param element The web element to wait for its visibility.
     * @param timeout The maximum time to wait for the element to become visible, in seconds.
     */

  public void waitForElementToAppear(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     * Waits for the specified web element to be available on the page and returns it.
     *
     * @param arg The web element to wait for.
     * @return The web element after it becomes available.
     */

  public WebElement waitForElement(WebElement arg) {
        waitForPageToLoad(arg);
        WebElement el = arg;
        return el;
    }
    /**
     * Waits for the frame with the specified id to be available and switches to it.
     *
     * @param id The id of the frame to wait for and switch to.
     */

  public void WaitForFrameAndSwitchToIt(String id) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(id));
    }

    /**
     * Waits for the frame with the specified index to be available and switches to it.
     *
     * @param id The index of the frame to wait for and switch to.
     */

  public void WaitForFrameAndSwitchToIt(int id) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(id));
    }

    /**
     * Scrolls to the specified web element.
     *
     * @param element The web element to scroll to.
     */

  public void ScrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Waits for the elements in the list to be visible on the screen.
     *
     * @param elementList The list of web elements to wait for.
     */

  public void waitForElements(List<WebElement> elementList) {
        waitForPageToLoad(elementList);
    }

    /**
     * Waits for the mobile element to appear on the screen.
     *
     * @param arg The mobile element to wait for.
     */
  public void waitForElementToAppearOnScreen(MobileElement arg) {
        waitForElementToAppear(arg);
    }
    /**
     * Clicks on the element until the specified element exists.
     *
     * @param element The element to click on.
     * @param by      The locator of the element to check for existence.
     */

  public void clickUntilElementExists(WebElement element, By by) {
        boolean elementOnScreen;
        int i = 0;
        do {
            if (i == 25) {
                break;
            }
            try {
                driver.findElement(by);
                break;
            } catch (NoSuchElementException e) {
                element.click();
                elementOnScreen = false;
                System.out.println(i);
            }
            i++;
        } while (!elementOnScreen);
    }


  public String getCurrentMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

  /**
     * This Function is to check the element is present or not
     *
     * @author
     * @param:
     */
  public boolean isElementDisplayed(MobileElement element) {
        try {
            if (element.isDisplayed())
                System.out.println("Element present on screen ***********" + element);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element not present on screen **************" + element);
            return false;
        }
    }

  /**
     * This Function will pause the execution for given secs.
     *
     * @param secs : No of seconds to be paused.
     * @author
     */
  public void waitInSec(int secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  /**
     * This Function is to Wait till element visible
     *
     * @author
     * @param: Mobile Element & String
     */
  public void waitTillTheElementIsVisible(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
