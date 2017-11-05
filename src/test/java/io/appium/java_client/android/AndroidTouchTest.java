package io.appium.java_client.android;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.PositionOffsetOption.positionOffsetOption;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.WebElementOption.elementOption;
import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AndroidTouchTest extends BaseAndroidTest {

    @Before
    public void setUp() throws Exception {
        driver.resetApp();
    }

    @Test public void dragNDropByElementTest() throws Exception  {
        Activity activity = new Activity("io.appium.android.apis", ".view.DragAndDropDemo");
        driver.startActivity(activity);
        WebElement dragDot1 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        WebElement dragDot3 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_3"));

        WebElement dragText = driver.findElement(By.id("io.appium.android.apis:id/drag_text"));
        assertEquals("Drag text not empty", "", dragText.getText());

        TouchAction dragNDrop = new TouchAction(driver)
                        .longPress(elementOption(dragDot1))
                        .moveTo(elementOption(dragDot3))
                        .release();
        dragNDrop.perform();
        assertNotEquals("Drag text empty", "", dragText.getText());
    }

    @Test public void dragNDropByElementAndDurationTest() throws Exception  {
        Activity activity = new Activity("io.appium.android.apis", ".view.DragAndDropDemo");
        driver.startActivity(activity);
        WebElement dragDot1 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        WebElement dragDot3 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_3"));

        WebElement dragText = driver.findElement(By.id("io.appium.android.apis:id/drag_text"));
        assertEquals("Drag text not empty", "", dragText.getText());

        TouchAction dragNDrop = new TouchAction(driver)
                .longPress(longPressOptions()
                        .withOffset(elementOption(dragDot1))
                        .withDuration(ofSeconds(2)))
                .moveTo(elementOption(dragDot3))
                .release();
        dragNDrop.perform();
        assertNotEquals("Drag text empty", "", dragText.getText());
    }

    @Test public void dragNDropByCoordinatesTest() throws Exception  {
        Activity activity = new Activity("io.appium.android.apis", ".view.DragAndDropDemo");
        driver.startActivity(activity);
        AndroidElement dragDot1 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        AndroidElement dragDot3 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_3"));

        WebElement dragText = driver.findElement(By.id("io.appium.android.apis:id/drag_text"));
        assertEquals("Drag text not empty", "", dragText.getText());

        Point center1 = dragDot1.getCenter();
        Point center2 = dragDot3.getCenter();

        TouchAction dragNDrop = new TouchAction(driver)
                .longPress(positionOffsetOption(center1.x, center1.y))
                .moveTo(positionOffsetOption(center2.x, center2.y))
                .release();
        dragNDrop.perform();
        assertNotEquals("Drag text empty", "", dragText.getText());
    }

    @Test public void dragNDropByCoordinatesAndDurationTest() throws Exception  {
        Activity activity = new Activity("io.appium.android.apis", ".view.DragAndDropDemo");
        driver.startActivity(activity);
        AndroidElement dragDot1 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        AndroidElement dragDot3 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_3"));

        WebElement dragText = driver.findElement(By.id("io.appium.android.apis:id/drag_text"));
        assertEquals("Drag text not empty", "", dragText.getText());

        Point center1 = dragDot1.getCenter();
        Point center2 = dragDot3.getCenter();

        TouchAction dragNDrop = new TouchAction(driver)
                .longPress(longPressOptions()
                        .withOffset(positionOffsetOption(center1.x, center1.y))
                        .withDuration(ofSeconds(2)))
                .moveTo(positionOffsetOption(center2.x, center2.y))
                .release();
        dragNDrop.perform();
        assertNotEquals("Drag text empty", "", dragText.getText());
    }

    @Test public void pressByCoordinatesTest() throws Exception {
        Activity activity = new Activity("io.appium.android.apis", ".view.Buttons1");
        driver.startActivity(activity);
        Point point =
                driver.findElementById("io.appium.android.apis:id/button_toggle").getLocation();
        new TouchAction(driver)
                .press(positionOffsetOption(point.x + 20, point.y + 30))
                .waitAction(waitOptions(ofSeconds(1)))
                .release()
                .perform();
        assertEquals("ON" ,driver
                .findElementById("io.appium.android.apis:id/button_toggle").getText());
    }

    @Test public void pressByElementTest() throws Exception {
        Activity activity = new Activity("io.appium.android.apis", ".view.Buttons1");
        driver.startActivity(activity);
        new TouchAction(driver)
                .press(elementOption(driver.findElementById("io.appium.android.apis:id/button_toggle")))
                .waitAction(waitOptions(ofSeconds(1)))
                .release()
                .perform();
        assertEquals("ON" ,driver
                .findElementById("io.appium.android.apis:id/button_toggle").getText());
    }

    @Test public void tapActionTestByElement() throws Exception {
        Activity activity = new Activity("io.appium.android.apis", ".view.ChronometerDemo");
        driver.startActivity(activity);
        AndroidElement chronometer =
                driver.findElementById("io.appium.android.apis:id/chronometer");

        TouchAction startStop = new TouchAction(driver)
                .tap(elementOption(driver.findElementById("io.appium.android.apis:id/start")))
                .waitAction(waitOptions(ofSeconds(2)))
                .tap(elementOption(driver.findElementById("io.appium.android.apis:id/stop")));

        startStop.perform();

        String time = chronometer.getText();
        assertNotEquals(time, "Initial format: 00:00");
        Thread.sleep(2500);
        assertEquals(time, chronometer.getText());
    }

    @Test public void tapActionTestByCoordinates() throws Exception {
        Activity activity = new Activity("io.appium.android.apis", ".view.ChronometerDemo");
        driver.startActivity(activity);
        AndroidElement chronometer =
                driver.findElementById("io.appium.android.apis:id/chronometer");

        Point center1 = driver.findElementById("io.appium.android.apis:id/start").getCenter();

        TouchAction startStop = new TouchAction(driver)
                .tap(positionOffsetOption(center1.x, center1.y))
                .tap(elementOption(driver.findElementById("io.appium.android.apis:id/stop"), 5, 5));
        startStop.perform();

        String time = chronometer.getText();
        assertNotEquals(time, "Initial format: 00:00");
        Thread.sleep(2500);
        assertEquals(time, chronometer.getText());
    }

    @Test public void horizontalSwipingTest() throws Exception {
        Activity activity = new Activity("io.appium.android.apis", ".view.Gallery1");
        driver.startActivity(activity);

        AndroidElement gallery = driver.findElementById("io.appium.android.apis:id/gallery");
        List<MobileElement> images = gallery
                .findElementsByClassName("android.widget.ImageView");
        int originalImageCount = images.size();
        Point location = gallery.getLocation();
        Point center = gallery.getCenter();

        TouchAction swipe = new TouchAction(driver)
                .press(elementOption(images.get(2),-10, center.y - location.y))
                .waitAction(waitOptions(ofSeconds(2)))
                .moveTo(elementOption(gallery,10,center.y - location.y))
                .release();
        swipe.perform();
        assertNotEquals(originalImageCount, gallery
                .findElementsByClassName("android.widget.ImageView").size());
    }

    @Test public void multiTouchTest() throws Exception {
        Activity activity = new Activity("io.appium.android.apis", ".view.Buttons1");
        driver.startActivity(activity);
        TouchAction press = new TouchAction(driver)
                .press(elementOption(driver.findElementById("io.appium.android.apis:id/button_toggle")))
                .waitAction(waitOptions(ofSeconds(1)))
                .release();
        new MultiTouchAction(driver)
                .add(press)
                .perform();
        assertEquals("ON" ,driver
                .findElementById("io.appium.android.apis:id/button_toggle").getText());
    }

}
