package com.qt.commonutils;

import com.github.javafaker.Faker;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
/**
 * This Class has all the Objects related to Gestures.
 *
 * @author
 */
public class Gestures {

    public static AppiumDriver driver;

    /**
     * Constructs a Gestures object with the specified AppiumDriver.
     *
     * @param driver the AppiumDriver instance
     */

    public Gestures(AppiumDriver driver) {
        Gestures.driver = driver;
    }

    /**
     * Checks if the specified WebElement is disabled.
     *
     * @param buttonLocator The locator of the button WebElement.
     * @return True if the button is disabled, false otherwise.
     */

    public static boolean isDisabled(WebElement buttonLocator) {
        MobileElement button = (MobileElement) driver.findElement((By) buttonLocator);
        return !button.isEnabled();
    }

    /**
     * Asserts that the specified WebElement is not displayed.
     *
     * @param element the WebElement to be checked
     * @throws AssertionError if the WebElement is displayed when it should not be
     */

    public static void assertElementNotDisplayed(WebElement element) {
        if (!element.isDisplayed()) {
            System.out.println("Element is not displayed as expected.");
        } else {
            throw new AssertionError("Element is displayed, but it should not be.");
        }
    }

    /**
     * Simulates a click on the device's back button.
     */

    public static void clkBackButton() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        System.out.println("Clicked on back buttton");
    }

    /**
     * Performs a horizontal scroll on the upper part of the screen.
     */

    public static void horizontalScrollUpperScreen() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.2);
        int endX = (int) (size.width * 0.8);
        int y = (int) (size.height * 0.3);

        performHorizontalScroll(startX, y, endX, y);
    }

    /**
     * Performs a horizontal scroll on the lower part of the screen.
     */


    public static void horizontalScrollLowerScreen() {
        Dimension size = driver.manage().window().getSize();
//        int startX = (int) (size.width * 0.2);
//        int endX = (int) (size.width * 0.8);
//        int y = (int) (size.height * 0.7);
        int startX = (int) (size.width * 0.8); // Start from the right side (80%)
        int endX = (int) (size.width * 0.2);   // Scroll to the left side (20%)
        int y = size.height / 2;

        performHorizontalScroll(startX, y, endX, y);
    }

    /**
     * Performs a horizontal scroll from the specified starting point to the specified ending point.
     *
     * @param startX the x-coordinate of the starting point
     * @param startY the y-coordinate of the starting point
     * @param endX the x-coordinate of the ending point
     * @param endY the y-coordinate of the ending point
     */

    public static void performHorizontalScroll(int startX, int startY, int endX, int endY) {
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(endX, endY)).release().perform();
    }

    /**
     * Toggles the WiFi state on the device.
     *
     * @param enable true to enable WiFi, false to disable
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted while sleeping
     */

    public static void toggleWiFi(boolean enable) throws IOException, Exception {
        if (enable) {
            executeCommand("adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
            Thread.sleep(2000);
            executeCommand("adb shell input keyevent 23");
            executeCommand("adb shell input keyevent 23");
            // Press Enter to turn WiFi on
            executeCommand("adb shell input keyevent 4");
        } else {
            executeCommand("adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
            Thread.sleep(2000);
            executeCommand("adb shell input keyevent 20"); // Press Down to navigate to "Turn off"
            executeCommand("adb shell input keyevent 23");
            executeCommand("adb shell input keyevent 23");
            executeCommand("adb shell input keyevent 4");// Press Enter to turn WiFi off
        }
    }


    /**
     * Sends all apps to the background by simulating the corresponding keyevent.
     *
     * @throws IOException if an I/O error occurs
     */
    public static void sendAppsToBackground() throws IOException {
        String command = "adb shell input keyevent 187";
        executeCommand(command);
    }

    /**
     * Opens the previous application by simulating the back button press.
     *
     * @throws IOException if an I/O error occurs
     */
    public static void openPeviousApp() throws IOException {
        clkBackButton();
    }

    /**
     * Executes the provided command using Runtime.getRuntime().exec().
     *
     * @param command the command to execute
     * @throws IOException if an I/O error occurs
     */
    private static void executeCommand(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        try {
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the specified activity with the given package name and activity name.
     *
     * @param appPackage  the package name of the app
     * @param appActivity the activity name to start
     */
    public void startActivity(String appPackage, String appActivity) {
        ((AndroidDriver) driver).startActivity(new Activity(appPackage, appActivity));
    }


    /**
     * Performs a horizontal swipe on the specified element.
     *
     * @param element the WebElement to swipe
     * @throws Exception if any error occurs during the swipe operation
     */
    public void horizontalSwipingTest(WebElement element) throws Exception {

        MobileElement slider = (MobileElement) element;
        Point source = slider.getLocation();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.x, source.y));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        dragNDrop.addAction(new Pause(finger, Duration.ofMillis(600)));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), source.x + 400, source.y));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(Collections.singletonList(dragNDrop));
    }

    /**
     * Performs a vertical swipe on the specified element.
     *
     * @param element the WebElement to swipe
     * @throws Exception if any error occurs during the swipe operation
     */

    public void verticalSwipeTest(WebElement element) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        wait.until(ExpectedConditions.visibilityOf(element));
        verticalSwipe(element);
    }

    /**
     * Performs a drag-and-drop operation on the specified element.
     *
     * @param element the WebElement to drag and drop
     * @throws Exception if any error occurs during the drag-and-drop operation
     */

    public void dragAndDrop(WebElement element) throws Exception {
        Thread.sleep(5000);
        element.click();
        MobileElement dragMe = (MobileElement) new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
        Point source = dragMe.getCenter();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), source.x, source.y));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        dragNDrop.addAction(new Pause(finger, ofMillis(600)));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(Collections.singletonList(dragNDrop));
    }

    /**
     * Performs a pinch or zoom gesture on the specified element.
     *
     * @param element the WebElement to perform the gesture on
     * @throws Exception if any error occurs during the pinch or zoom gesture
     */

    public void pinchAndZoom(WebElement element) throws Exception {
        Thread.sleep(5000);
        element.click();
        Thread.sleep(3000);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Dimension size = driver.manage().window().getSize();
        Point source = new Point(size.getWidth(), size.getHeight());

        Sequence pinchAndZoom1 = new Sequence(finger, 0);
        pinchAndZoom1.addAction(finger.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), source.x / 2, source.y / 2));
        pinchAndZoom1.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        pinchAndZoom1.addAction(new Pause(finger, ofMillis(100)));
        pinchAndZoom1.addAction(finger.createPointerMove(ofMillis(600), PointerInput.Origin.viewport(), source.x / 3, source.y / 3));
        pinchAndZoom1.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


        Sequence pinchAndZoom2 = new Sequence(finger2, 0);
        pinchAndZoom2.addAction(finger2.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), source.x / 2, source.y / 2));
        pinchAndZoom2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        pinchAndZoom2.addAction(new Pause(finger, ofMillis(100)));
        pinchAndZoom2.addAction(finger2.createPointerMove(ofMillis(600), PointerInput.Origin.viewport(), source.x * 3 / 4, source.y * 3 / 4));
        pinchAndZoom2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(pinchAndZoom1, pinchAndZoom2));
    }

    /**
     * Performs a long press gesture on the specified element.
     *
     * @param element the WebElement to perform the long press gesture on
     * @throws Exception if any error occurs during the long press gesture
     */

    public void longPress(WebElement element) throws Exception {

        Thread.sleep(5000);
        element.click();
        MobileElement longpress = (MobileElement) new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
        new Actions(driver).clickAndHold(longpress).perform();
        Thread.sleep(5000);
    }

    /**
     * Performs a double tap gesture on the specified mobile element.
     *
     * @param mobileElement the mobile element to perform the double tap gesture on
     */

    public void doubleTap(MobileElement mobileElement) {
        TouchActions action = new TouchActions(driver);
        action.doubleTap(mobileElement);
        action.perform();
    }

    /**
     * Performs a double tap gesture on the specified web element.
     *
     * @param element the web element to perform the double tap gesture on
     * @throws Exception if an error occurs while performing the double tap gesture
     */

    public void doubleTap(WebElement element) throws Exception {

        Thread.sleep(5000);
        driver.findElementByAccessibilityId("doubleTap").click();
        MobileElement mobileElement = (MobileElement) new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
        Thread.sleep(1000);
        Point source = mobileElement.getCenter();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), source.x, source.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, ofMillis(200)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, ofMillis(40)));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, ofMillis(200)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(tap));
        Thread.sleep(4000);
    }

    /**
     * Performs a vertical swipe gesture starting from the specified web element.
     *
     * @param element the web element to start the vertical swipe gesture from
     * @throws Exception if an error occurs while performing the vertical swipe gesture
     */
    private void verticalSwipe(WebElement element) throws Exception {
        Thread.sleep(5000);
        MobileElement slider = (MobileElement) element;
        Point source = slider.getCenter();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), source.x / 2, source.y + 400));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        dragNDrop.addAction(finger.createPointerMove(ofMillis(600), PointerInput.Origin.viewport(), source.getX() / 2, source.y / 2));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(Collections.singletonList(dragNDrop));
    }
    /**
     * Performs a multi-touch gesture on the specified web element.
     *
     * @param element the web element to perform the multi-touch gesture on
     * @throws Exception if an error occurs while performing the multi-touch gesture
     */


    public void multiTouchTest(WebElement element) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        Thread.sleep(3000);
        MobileElement slider = (MobileElement) element;
        MobileElement slider1 = (MobileElement) element;

        Dimension sizeSlider = slider.getSize();
        Dimension sizeSlider1 = slider1.getSize();
        TouchAction touchAction1 = new TouchAction(driver).press(ElementOption.element(slider, 0, sizeSlider.height / 2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(ElementOption.element(slider, sizeSlider.width / 2, sizeSlider.height / 2));
        TouchAction touchAction2 = new TouchAction(driver).press(ElementOption.element(slider1, 0, sizeSlider1.height / 2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(ElementOption.element(slider1, sizeSlider1.width / 2, sizeSlider1.height / 2));
        new MultiTouchAction(driver).add(touchAction1).add(touchAction2).perform();
        Thread.sleep(2000);
    }

    /**
     * Closes the current application.
     */
    public void closeApp() {
        driver.closeApp();
    }

    /**
     * clik on the current element.
     */
    public void clickOnElement(WebElement element) {
        element.click();
    }

    /**
     * Waits for the element to be visible and then clicks it.
     * Throws a TimeoutException if the element is not visible within the timeout period.
     *
     * @param element The WebElement to wait for and click.
     * @throws TimeoutException If the element is not visible within the timeout period.
     */
    public void waitAndClickElementisVisible(WebElement element) throws TimeoutException {
        ExceptionUtils.handleException(
                () -> {
                    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

                    try {
                        WebDriverWait wait = new WebDriverWait(driver, 25);
                        wait.until(ExpectedConditions.visibilityOf(element));

                        element.click();
                    } finally {
                        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    }
                },
                "Failed to wait for visibility and click on element: " + element.toString()
        );
    }

    /**
     * Checks if the element is present on the page.
     *
     * @param locator The WebElement to check for presence.
     * @return True if the element is present, false otherwise.
     * @throws Exception If an exception occurs while checking for the element.
     */

    public boolean isElementPresent(WebElement locator) throws Exception {
        try {
            return ExceptionUtils.handleException(
                    () -> {
                        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

                        waitForElementToAppear(locator);

                        if (locator.isDisplayed()) {
                            String elementText = locator.getText().trim();
                            if (!elementText.isEmpty()) {
                                System.out.println("Element present on screen: " + elementText);
                            } else {
                                System.out.println("Element present on screen, but no text available for element: " + locator);
                            }
                            return true;
                        } else {
                            System.out.println("Element not displayed on screen: " + locator);
                            return false;
                        }
                    },
                    "This element is not available: " + locator.toString()
            );
        } catch (Exception e) {
            System.out.println("Exception while checking element presence: " + e.getMessage());
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
    }



    /**
     * Waits for the element to appear on the page and returns it.
     *
     * @param id The WebElement to wait for.
     * @return The WebElement once it appears.
     */
    public WebElement waitForElementToAppear(WebElement id) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(id));
        return id;
    }
    /**
     * Checks if the specified element is present on the screen within the given timeout.
     *
     * @param locator  The WebElement to be checked for presence.
     * @param timeOut  The maximum time to wait for the element to appear, in seconds.
     * @return true if the element is present and displayed within the timeout, otherwise false.
     */

    public boolean isElementPresent(WebElement locator, int timeOut) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        try {
            waitForElementToAppear(locator, timeOut);
            if (locator.isDisplayed()) {
                System.out.println("Element present on screen ***********" + locator);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Element not present on screen **************" + locator);
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }

        return false;
    }
    /**
     * Waits for the specified WebElement to appear on the screen within the given timeout.
     *
     * @param id       The WebElement to wait for.
     * @param timeOut  The maximum time to wait for the element to appear, in seconds.
     * @return The WebElement if it appears within the timeout, otherwise null.
     */
    public WebElement waitForElementToAppear(WebElement id, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.visibilityOf(id));
        return id;
    }
    /**
     * Waits for the specified WebElement to become visible on the screen.
     *
     * @param locator The WebElement to wait for.
     */

    public void waitForElementToVisible(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(locator));

    }
    /**
     * Clicks on the specified WebElement using JavaScript.
     *
     * @param ele The WebElement to click on.
     */
    public void clickWithJavaScript(WebElement ele) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void scrollFromElementToElementVertical(MobileElement startElement, MobileElement endElement, String scrollCount) {
        Dimension size = driver.manage().window().getSize();
        int starty = startElement.getLocation().getY();
        int endy = startElement.getLocation().getY() - 400;
        int constx = (int) (size.width * 0.5);
        System.out.println("Co-ordinates = startY:" + starty + " endY:" + endy + " constX:" + constx);

        try {
            int count = Integer.parseInt(scrollCount);
            for (int i = 0; i < count; i++) {
                if (isElementPresent(endElement, 5)) {
                    break;
                } else {
                    swipeCoordinates(constx, starty, constx, endy);
                }
            }
        } catch (Exception e) {
            System.out.println("Scroll to mobile element failed");
        }
    }

    public void scrollFromElementToElementHorizontal(MobileElement startElement, MobileElement endElement, String scrollCount) {
        Dimension size = driver.manage().window().getSize();
        int startx = startElement.getLocation().getX();
        int endx = startElement.getLocation().getX() - 300;
        int consty = startElement.getLocation().getY();
        System.out.println("Co-ordinates = startX:" + startx + " endX:" + endx + " constY:" + consty);

        try {
            int count = Integer.parseInt(scrollCount);
            for (int i = 0; i < count; i++) {
                if (isElementPresent(endElement, 5)) {
                    break;
                } else {
                    swipeCoordinates(startx, consty, endx, consty);
                }
            }
        } catch (Exception e) {
            System.out.println("Scroll to mobile element failed");
        }
    }

    public void scrollFromElementToElementHorizontalMax(MobileElement startElement, MobileElement endElement, String scrollCount) {
        Dimension size = driver.manage().window().getSize();
        int startx = startElement.getLocation().getX();
        int endx = startElement.getLocation().getX() - 600;
        int consty = startElement.getLocation().getY();
        System.out.println("Co-ordinates = startX:" + startx + " endX:" + endx + " constY:" + consty);

        try {
            int count = Integer.parseInt(scrollCount);
            for (int i = 0; i < count; i++) {
                if (isElementPresent(endElement, 5)) {
                    break;
                } else {
                    swipeCoordinates(startx, consty, endx, consty);
                }
            }
        } catch (Exception e) {
            System.out.println("Scroll to mobile element failed");
        }
    }

    public void scrollToMobileElement(MobileElement element, String scrollCount) {
        try {
            int count = Integer.parseInt(scrollCount);
            for (int i = 0; i < count; i++) {
                if (isElementPresent(element)) {
                    break;
                } else {
                    swipeUp();
                }
            }
        } catch (Exception e) {
            System.out.println("Scroll to mobile element failed");
        }
    }

    public void swipeUp() {
        Dimension size = driver.manage().window().getSize();
        int starty = (int) (size.height * 0.5);
        int endy = (int) (size.height * 0.2);
        int startx = (int) (size.width * 0.5);
        try {
            System.out.println("Trying to swipe up from x:" + startx + " y:" + starty + ", to x:" + startx + " y:" + endy);
            new TouchAction(driver).press(point(startx, starty)).waitAction(waitOptions(ofSeconds(2))).moveTo(point(startx, endy)).release().perform();

        } catch (Exception e) {
            System.out.println("Swipe action was not successful.");
        }
    }

    public void swipeCoordinates(int startX, int startY, int endX, int endY) {
        try {
            if (driver == null) {
                throw new IllegalStateException("Driver is not set. Call setDriver method first.");
            }

            System.out.println("Trying to swipe from x:" + startX + " y:" + startY + ", to x:" + endX + " y:" + endY);

            TouchAction<?> touchAction = new TouchAction<>(driver);
            touchAction.press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                    .moveTo(PointOption.point(endX, endY))
                    .release()
                    .perform();

            System.out.println("Swipe action was successful.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Swipe action was not successful.");
        }
    }

    public boolean generateTextXpathIsElementPresent(String value) {
        boolean flag = false;
        List<MobileElement> elements = driver.findElements(By.xpath("//*[contains(@text,'" + value + "')]"));
        if (elements.size() > 0) {
            flag = true;
        }
        Assert.assertTrue(flag, "Element is not present");
        return flag;
    }

    public void generateXpathAndClickElement(String value) throws Exception {
        BlindWait(3000);
        try {
            MobileElement element = (AndroidElement) driver.findElement(By.xpath("//*[contains(@text,'" + value + "')]"));
            element.click();
        } catch (Exception e) {
            System.out.println("Scroll to mobile element failed");
        }
    }

    public void generateXpathUsingTextAndClickElement(String value) {
        try {
            WebElement element = driver.findElement(By.xpath("//*[@text='" + value + "']"));
            element.click();
        } catch (Exception e) {
            System.out.println("Scroll to mobile element failed");
        }
    }

    public void swipeDown() {
        Dimension size = driver.manage().window().getSize();
        int starty = (int) (size.height * 0.45);
        int endy = (int) (size.height * 0.90);
        int startx = (int) (size.width / 2.2);
        try {
            System.out.println("Trying to swipe up from x:" + startx + " y:" + starty + ", to x:" + startx + " y:" + endy);
            new TouchAction(driver).press(point(startx, starty)).waitAction(waitOptions(ofSeconds(3))).moveTo(point(startx, endy)).release().perform();
        } catch (Exception e) {
            System.out.println("Swipe did not complete successfully.");
        }
    }

    public String getText(WebElement element) {
        String elementText = element.getText();
        return elementText;
    }

    public void openNotification() {
        ((AndroidDriver) driver).openNotifications();
    }

    public String generateRandomName() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        return name;
    }

    public void scrollTilltextVisible(String visibleText) {
        ((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\").instance(0))");
    }

    public void scrollAndClick(String visibleText) {
        ((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\").instance(0))").click();
    }

    public void alternativeMethodForSendKeys(String textData) {
        Actions a = new Actions(driver);
        a.sendKeys(textData);
        a.perform();
        a.sendKeys(Keys.ENTER).build().perform();

    }

    public void BlindWait(int wait) throws Exception {
        Thread.sleep(wait);

    }

    public void scrollUpToMobileElement(MobileElement element, String scrollCount) {
        try {
            int count = Integer.parseInt(scrollCount);
            for (int i = 0; i < count; i++) {
//                if (isElementDisplayed(element)) {
                if (isElementPresent(element)) {
                    break;
                } else {
                    swipeUp();
                }

            }
        } catch (Exception e) {
            System.out.println("Scroll to mobile element failed");
        }
    }

    public void tocuh() throws Exception {
        /*
         * Dimension dims = driver.manage().window().getSize(); int edgeBorder = 10;
         * PointOption pointOptionStart, pointOptionEnd; // init start point = center of
         * screen pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
         * pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
         */
        new TouchAction(driver).tap(point(299, 150)).perform();
    }

    public void generateXpathUsingClassAndTextAndClickElement(String value) throws Exception {

        BlindWait(6000);


        MobileElement element = (AndroidElement) driver.findElement(By.xpath("//android.view.View[@text='" + value + "']"));
        element.click();
        BlindWait(5000);

    }

    public void airplaneMode() {

        ((AndroidDriver) driver).toggleAirplaneMode();

    }

    public void enableAllNetwork() {

        NetworkConnection mobileNetwork = (NetworkConnection) driver;
        mobileNetwork.setNetworkConnection(NetworkConnection.ConnectionType.ALL);
    }

    public void scrollTestUsingFinger() {
//        Dimension dimension = driver.manage().window().getSize();
//        int scrollStart = (int) (dimension.getHeight() * 0.5);
//        int scrollEnd = (int) (dimension.getHeight() * 0.2);
//        int widthHalf = (int) (dimension.getWidth() * 0.5);
//
//        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//        Sequence dragNDrop = new Sequence(finger, 1);
//
//        dragNDrop.addAction(finger.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), widthHalf, scrollStart));
//
//        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//
//        dragNDrop.addAction(new Pause(finger, ofMillis(600)));
//
//        dragNDrop.addAction(finger.createPointerMove(ofMillis(700), PointerInput.Origin.viewport(), widthHalf, scrollEnd));
//
//        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//
//        driver.perform(Collections.singletonList(dragNDrop));
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.5);
        int scrollEnd = (int) (dimension.getHeight() * 0.2);
        int widthHalf = (int) (dimension.getWidth() * 0.5);

        TouchAction touchAction = new TouchAction(driver);

        touchAction.press(PointOption.point(widthHalf, scrollStart)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(600))).moveTo(PointOption.point(widthHalf, scrollEnd)).release().perform();

    }

    public void scrollFromElementToElementVertically(MobileElement startElement, MobileElement endElement, String scrollCount) {
        Dimension size = driver.manage().window().getSize();
        int starty = startElement.getLocation().getY();
        int endy = startElement.getLocation().getY() - 300;
        int constx = startElement.getLocation().getX();
        System.out.println("Co-ordinates = startY:" + starty + " endY:" + endy + " constX:" + constx);

        try {
            int count = Integer.parseInt(scrollCount);
            for (int i = 0; i < count; i++) {
                if (isElementPresent(endElement, 5)) {
                    break;
                } else {
                    swipeCoordinates(constx, starty, constx, endy);
                }
            }
        } catch (Exception e) {
            System.out.println("Scroll to mobile element failed");
        }
    }

    public void horizontalScrollUsingFinger() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStartX = (int) (dimension.getWidth() * 0.8); // Start from the right side
        int scrollEndX = (int) (dimension.getWidth() * 0.2);   // End on the left side
        int heightHalf = (int) (dimension.getHeight() * 0.5);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);

        dragNDrop.addAction(finger.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), scrollStartX, heightHalf));

        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        dragNDrop.addAction(new Pause(finger, ofMillis(600)));

        dragNDrop.addAction(finger.createPointerMove(ofMillis(700), PointerInput.Origin.viewport(), scrollEndX, heightHalf));

        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(dragNDrop));
    }

    public void hideKeyBoard() {

        try {
            Process process = Runtime.getRuntime().exec("adb shell dumpsys input_method");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            boolean isKeyboardShowing = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains("mInputShown=true")) {
                    isKeyboardShowing = true;
                    break;
                }
            }

            reader.close();

            if (isKeyboardShowing) {
                Process hideProcess = Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_BACK");
                hideProcess.waitFor();
                System.out.println("Keyboard hidden");
            } else {
                System.out.println("Keyboard is not showing");
            }
//            if (isKeyboardShowing) {
//                // If the keyboard is showing, send a key event to hide it
//                driver.hideKeyboard();
//                System.out.println("Keyboard hidden");
//            } else {
//                System.out.println("Keyboard is not showing");
//            }
//            if (isKeyboardShowing) {
//                // If the keyboard is showing, send a key event to hide it
//                new TouchAction(driver).tap(PointOption.point(10, 10)).perform();
//                System.out.println("Keyboard hidden");
//            } else {
//                System.out.println("Keyboard is not showing");
//            }
//
//            if (isKeyboardShowing) {
//                // If the keyboard is showing, use JavaScript to hide it
//                JavascriptExecutor js = (JavascriptExecutor) driver;
//                js.executeScript("mobile: hideKeyboard");
//                System.out.println("Keyboard hidden");
//            } else {
//                System.out.println("Keyboard is not showing");
//            }
//
//            if (isKeyboardShowing) {
//                // If the keyboard is showing, use "mobile: performEditorAction" to hide it
//                driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "done"));
//                System.out.println("Keyboard hidden");
//            } else {
//                System.out.println("Keyboard is not showing");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Scrolls vertically by swiping multiple times from the given start element.
     *
     * @param startElement      The element from which scrolling starts.
     * @param defaultScrollCount The number of times to perform the scroll action.
     */
    public void scrollVertically(MobileElement startElement, int defaultScrollCount) {
        Dimension size = driver.manage().window().getSize();
        int starty = startElement.getLocation().getY();
        int endy = startElement.getLocation().getY() - 300;
        int constx = startElement.getLocation().getX();
        System.out.println("Co-ordinates = startY:" + starty + " endY:" + endy + " constX:" + constx);

        try {
            int count = defaultScrollCount;
            for (int i = 0; i < count; i++) {
                swipeCoordinates(constx, starty, constx, endy);
            }
        } catch (Exception e) {
            System.out.println("Scroll to mobile element failed");
        }
    }
    public static void BackNavigate() throws IOException {
        driver.hideKeyboard();

    }
    /**
     * Scrolls vertically by swiping multiple times.
     *
     * @param defaultScrollCount The number of times to perform the scroll action.
     */

    public void scrollVertically(int defaultScrollCount) {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.5);
        int scrollEnd = (int) (dimension.getHeight() * 0.2);
        int widthHalf = (int) (dimension.getWidth() * 0.5);

        try {
            int count = defaultScrollCount;
            for (int i = 0; i < count; i++) {
                swipeCoordinates(widthHalf, scrollStart, widthHalf, scrollEnd);
            }
        } catch (Exception e) {
            System.out.println("Scroll to mobile element failed");
        }


    }


    /**
     * Scrolls vertically upwards by swiping multiple times.
     *
     * @param defaultScrollCount The number of times to perform the scroll action.
     */
    public void scrollVerticallyUpwards(int defaultScrollCount) {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.2);   // Start from 20% of the height
        int scrollEnd = (int) (dimension.getHeight() * 0.8);     // End at 80% of the height
        int widthHalf = (int) (dimension.getWidth() * 0.5);

        try {
            int count = defaultScrollCount;
            for (int i = 0; i < count; i++) {
                swipeCoordinatesup(widthHalf, scrollStart, widthHalf, scrollEnd);
            }
        } catch (Exception e) {
            System.out.println("Scroll downwards to mobile element failed");
        }
    }


    /**
     * Swipes from the specified starting coordinates to the ending coordinates in an upward direction.
     *
     * @param startx The x-coordinate of the starting point.
     * @param starty The y-coordinate of the starting point.
     * @param endx   The x-coordinate of the ending point.
     * @param endy   The y-coordinate of the ending point.
     */

    private void swipeCoordinatesup(int startx, int starty, int endx, int endy) {
        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(endx, endy))
                .release()
                .perform();
    }

}
