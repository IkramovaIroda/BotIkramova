package MYBot;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CurencyMenu {
    public SendMessage CurrencyStart(String Valuta,String text1, long chatId) {
        SendMessage sendMessage = new SendMessage();
        Double result=0.0;
        if(Valuta.equals("USD")){
            result=Double.parseDouble(text1)*10815.0;
        }else if(Valuta.equals("EUR")){
            result=Double.parseDouble(text1)*12267.2;
        }else if(Valuta.equals("RUB")){
        result=Double.parseDouble(text1)*139.323;
        }else if(Valuta.equals("ARS")){
            result=Double.parseDouble(text1)*103.643;
        }
        sendMessage.setText(String.valueOf(result));
        sendMessage.setChatId(String.valueOf(chatId));
        return sendMessage;
    }
    public String converter(String Valuta,String text1, long chatId) {
        Double result=0.0;
        if(Valuta.equals("USD")){
            result=Double.parseDouble(text1)*10815.0;
        }else if(Valuta.equals("EUR")){
            result=Double.parseDouble(text1)*12267.2;
        }else if(Valuta.equals("RUB")){
            result=Double.parseDouble(text1)*139.323;
        }else if(Valuta.equals("ARS")){
            result=Double.parseDouble(text1)*103.643;
        }
        return String.valueOf(result);
    }

    public ReplyKeyboard menuKeyboard() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        KeyboardRow FirstRow = new KeyboardRow();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        FirstRow.add(new KeyboardButton("USD"));
        FirstRow.add(new KeyboardButton("EUR"));
        FirstRow.add(new KeyboardButton("AUD"));
        FirstRow.add(new KeyboardButton("ARS"));

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("AMD"));
        secondRow.add(new KeyboardButton("GBP"));
        secondRow.add(new KeyboardButton("BDT"));
        secondRow.add(new KeyboardButton("BHD"));


        KeyboardRow Thirt = new KeyboardRow();
Thirt.add(new KeyboardButton("BYN"));
        Thirt.add(new KeyboardButton("BGN"));
        Thirt.add(new KeyboardButton("BND"));
        Thirt.add(new KeyboardButton("HUF"));


        KeyboardRow Fourth = new KeyboardRow();
        Fourth.add(new KeyboardButton("VES"));
        Fourth.add(new KeyboardButton("VND"));
        Fourth.add(new KeyboardButton("GEL"));
        Fourth.add(new KeyboardButton("DKK"));

        KeyboardRow Five = new KeyboardRow();
        Five.add(new KeyboardButton("DZD"));
        Five.add(new KeyboardButton("ZAR"));
        Five.add(new KeyboardButton("IDR"));
        Five.add(new KeyboardButton("JOD"));

        KeyboardRow Six = new KeyboardRow();
        Six.add(new KeyboardButton("IQD"));
        Six.add(new KeyboardButton("ISK"));
        Six.add(new KeyboardButton("ILS"));
        Six.add(new KeyboardButton("KHR"));

        KeyboardRow Seven = new KeyboardRow();
        Seven.add(new KeyboardButton("CAD"));
        Seven.add(new KeyboardButton("AED"));
        Seven.add(new KeyboardButton("GBP"));
        Seven.add(new KeyboardButton("DZD"));

        KeyboardRow Eight = new KeyboardRow();
        Eight.add(new KeyboardButton("Назад"));

        keyboardRowList.add(FirstRow);
        keyboardRowList.add(secondRow);
        keyboardRowList.add(Thirt);
        keyboardRowList.add(Fourth);
        keyboardRowList.add(Five);
        keyboardRowList.add(Six);
        keyboardRowList.add(Seven);
        keyboardRowList.add(Eight);

        keyboard.setKeyboard(keyboardRowList);
        return keyboard;
    }
    public ReplyKeyboard menuKeyboard1() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        KeyboardRow FirstRow = new KeyboardRow();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        FirstRow.add(new KeyboardButton("USD"));
        FirstRow.add(new KeyboardButton("EUR"));
        FirstRow.add(new KeyboardButton("RUB"));
        FirstRow.add(new KeyboardButton("ARS"));

        KeyboardRow Eight = new KeyboardRow();
        Eight.add(new KeyboardButton("Назад"));

        keyboardRowList.add(FirstRow);
        keyboardRowList.add(Eight);

        keyboard.setKeyboard(keyboardRowList);
        return keyboard;
    }

    public  String Curency(String CCy) {
        String str = "";
        try {
            URL url = new URL("https://cbu.uz/oz/services/open_data/rates/json/");
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
            Gson gson = new Gson();
            Json[] userArray = gson.fromJson(String.valueOf(stringBuilder), Json[].class);

            for (Json currensyDTO : userArray) {
                if (currensyDTO.getG1().equals(CCy)) {
                    str= "\nКурс валюты: " + currensyDTO.getG1()
                            + "\nRU:  " + currensyDTO.getG6()
                            + "\nUZ:  " + currensyDTO.getG7()
                            + "\nUZC:  " + currensyDTO.getG8()
                            + "\nEn:  " + currensyDTO.getG9()
                            + "\nСумма:  " + currensyDTO.getG4()
                            + "\nДата:  " + currensyDTO.getG5();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return str;
    }
    public  Double Curency1(String CCy) {
        Double str=0.0;
        try {
            URL url = new URL("https://cbu.uz/oz/services/open_data/rates/json/");
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
            Gson gson = new Gson();
            Json[] userArray = gson.fromJson(String.valueOf(stringBuilder), Json[].class);

            for (Json currensyDTO : userArray) {
                if (currensyDTO.getG1().equals(CCy)) {
                    str= Double.valueOf(currensyDTO.getG4());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return str;
    }
}

