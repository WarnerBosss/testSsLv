package Helpers;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by DeBeers on 18.11.2016.
 */
public class GeneralHelpers {

    public static void scrollPageUp() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);
        robot.keyPress(KeyEvent.VK_PAGE_UP);
        robot.keyRelease(KeyEvent.VK_PAGE_UP);
    }

}
