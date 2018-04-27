package com.selenium.send.controller;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Controller {

    @FindBy(how = How.CSS, using = "#\\3a p1")
    static WebElement CtrlEnter;

    static int waitForElement(WebElement obj) {
        int counter = 0;
        while(counter!=5) {
            try {
                if (obj.isDisplayed()&&obj.isEnabled()) {
                    System.out.println("Object is displayed");
                    return 1;
                }
            } catch (Exception e) {
                counter++;
                try {
                    System.out.println("Object isn't display");
                    Thread.sleep(1000L);
                } catch(InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return 0;
    }
}
