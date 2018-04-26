package com.selenium.send.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.selenium.send.controller.SendController.userPC;

public class AutoSend {
    static Integer i = 1;
    static String line1 = null;
    static String line2 = null;
    static List<String> line2s = new ArrayList<String>();
    public static void main(String[] args) {
        try {
            FileReader fileReader1 = new FileReader("C:\\Users\\" + userPC + "\\Documents\\GmailAutoSend\\Datamail.txt");
            FileReader fileReader2 = new FileReader("C:\\Users\\" + userPC + "\\Documents\\GmailAutoSend\\Subject.txt");
            BufferedReader br1 = new BufferedReader(fileReader1);
            BufferedReader br2 = new BufferedReader(fileReader2);
            line1 = br1.readLine();
            line2 = br2.readLine();
            while(line2 != null) {
                line2s.add(line2);
                line2 = br2.readLine();
            }
            br2.close();
            fileReader2.close();
            Random rd = new Random();
            while(line1 != null) {
                SendController.Send(line1, line2s.get(rd.nextInt(4)), i);
                System.out.println("Profile" + i);
                i++;
                line1 = br1.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
