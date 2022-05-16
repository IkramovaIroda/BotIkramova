package MYBot;

import javassist.bytecode.StackMap;

import java.io.*;


public class FileWriter {

    public static void write(String chatid, String name, String phone, String date, String time) {
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("user.txt",true));
            String ready = String.join("#", chatid, name, phone, date, time);
            writer.write(ready);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
