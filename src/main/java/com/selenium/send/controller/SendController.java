package com.selenium.send.controller;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.selenium.send.controller.Controller.CtrlEnter;
import static com.selenium.send.controller.Controller.waitForElement;

public class SendController {



    static WebDriver driver = null;
    static String pathChromeDriver = null;
    static String profileChrome = null;
    static String userPC = System.getProperty("user.name");
    static String pathTemplate = null;
    static Integer k = 1;
    static Boolean SendTrue = null;
    public static Boolean Send(String mailList, String mailSubject, Integer i) throws InterruptedException {
        System.out.println(userPC);
        //Windows: "C:\\Users\\" + userPC + "\\Documents\\GmailAutoSend\\chromedriver.exe"
        //Linux: "/home/" + userPC + "/Documents/GmailAutoSend/chromedriver"
        pathChromeDriver = "C:\\Users\\" + userPC + "\\Documents\\GmailAutoSend\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathChromeDriver);
        ChromeOptions options = new ChromeOptions();
        //Windows: "user-data-dir=C:\\Users\\"+main.username+"\\AppData\\Local\\Google\\Chrome\\User Data\\Profile "
        //Linux: "user-data-dir=/home/" + userPC + "/.config/google-chrome/Profile "
        profileChrome = "user-data-dir=C:\\Users\\" + userPC + "\\AppData\\Local\\Google\\Chrome\\User Data\\Profile " + i.toString();
        options.addArguments(profileChrome);
        driver = new ChromeDriver(options);
        Controller controller = new Controller();
        PageFactory.initElements(driver, controller);
        Actions action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        Random random = new Random();
        k = random.nextInt(6) + 1;
        pathTemplate = "C:\\Users\\dat\\Documents\\GmailAutoSend\\template\\" + k + ".html";
        driver.get(pathTemplate);
        //Thread.sleep(500000);
        System.out.println(pathTemplate);

        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        Thread.sleep(500);
        action.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        Thread.sleep(500);
        driver.get("https://mail.google.com/mail/u/0/?view=cm&fs=1&to=" + mailList + "&su=" + mailSubject + "&tf=1");
        if (waitForElement(CtrlEnter) == 1) {
            Thread.sleep(500);
            action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
            Thread.sleep(500);
            CtrlEnter.click();
            Thread.sleep(5000);
            driver.quit();
            SendTrue = true;
        } else {
            driver.quit();
            SendTrue = false;
        }
        return SendTrue;
    }


}
