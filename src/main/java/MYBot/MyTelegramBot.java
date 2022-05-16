package MYBot;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileOutputStream;
import java.util.*;


public class MyTelegramBot extends TelegramLongPollingBot {
    private boolean reclama = false;
    private String step = "";
    int count = 1;
    private String Valuta = "";
    private Integer random = 0;
    private boolean enter = false;
    private boolean sender = false;
    List list = new ArrayList();
    List<Converter> converterList = new ArrayList();

    @Override
    public String getBotUsername() {
        return "test_botlar_bot";
    }

    @Override
    public String getBotToken() {
        return "5286798553:AAF59MFiiXCaFxJ1gJ_wdLzRtI6zEUmdqrE";
    }

    @SneakyThrows
    @Override

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();

        String responceText = "";
        String responceText1 = "";
        System.out.println(update.getMessage().getFrom().getFirstName() + " : " + message.getText());
        String chatId1 = message.getChatId().toString();
        boolean listik = true;
        boolean isEquals = true;
        if (message.getContact() != null) {
            if (message.getContact().getPhoneNumber() != null) {
                for (users u : ConnectDB.select()) {
                    if (u.getChatid().equals(chatId1)) {
                        System.out.println("yozilmadi");
                        listik = false;
                        isEquals=false;
                    }
                }
                if (isEquals) {
                    ConnectDB.insert(chatId1, update.getMessage().getFrom().getFirstName(), message.getContact().getPhoneNumber(), String.valueOf(LocalDate.now()), String.valueOf(LocalTime.now()));
                }

                int randomCode = (int) (Math.random() * 89999 + 10000);
                try {
                    if (message.getContact().getPhoneNumber().equals("998996168673")) {
                        Twilio.init("AC4c8120f45e635ad8849b349881fc4d06", "63ca1a6126580cf5e9e5f63422d31550");
                        com.twilio.rest.api.v2010.account.Message message1 = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+998996168673"),
                                new PhoneNumber("+16204980849"), "Your code " + randomCode).create();
                        random = randomCode;
                        sender = true;
                        responceText = "Отправьте нам код для подтрерждения ";
                    } else {
                        random = randomCode;
                        sender = true;
                        sendMessage.setText("Your code: " + randomCode + "\nОтправьте нам код для подтрерждения ");
                        sendMessage.setChatId(chatId1);
                        execute(sendMessage);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (message != null) {
            User user = message.getFrom();
            long chatId = user.getId();
            sendMessage.setChatId(user.getId().toString());
            String text = update.getMessage().getText();
            responceText1 = text;
            if (text.equals("/start")) {
                sendMessage.setParseMode("HTML");
                responceText = "<b>" + " <i>" + " <u>" + "Добропожаловать " + " </u>" + " </i>" + " </b> " + user.getFirstName() + "\n" + "<b>" + " <i> " + "Здравствуйте  \n Нажмите на SHARE CONTACT чтобы отправить нам  свой контакт  " + " </i>" + " </b> ";
                sendMessage.setChatId(chatId1);
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setSelective(true);
                List<KeyboardRow> rows = new ArrayList<>();
                KeyboardRow keyboardRow = new KeyboardRow();
                KeyboardButton button = new KeyboardButton("SHARE CONTACT");
                button.setRequestContact(true);
                keyboardRow.add(button);
                rows.add(keyboardRow);
                replyKeyboardMarkup.setKeyboard(rows);
                sendMessage.setReplyMarkup(replyKeyboardMarkup);
                sendMessage.setText(responceText);
            } else if (sender == true) {
                if (text.equals(String.valueOf(random))) {
                    if (chatId1.equals("1092837648")) {
                        step = "menu";
                        sendMessage.setText("<b>" + "<i>" + text + "</i>" + "</b>");
                        sendMessage.setParseMode("HTML");
                        responceText = "\uD835\uDCDC\uD835\uDCEE\uD835\uDCF7\uD835\uDCFE\n ";
                        sendMessage.setReplyMarkup(startKeyboard());
                        sender = false;
                    } else {
                        step = "menu";
                        sendMessage.setText("<b>" + "<i>" + text + "</i>" + "</b>");
                        sendMessage.setParseMode("HTML");
                        responceText = "\uD835\uDCDC\uD835\uDCEE\uD835\uDCF7\uD835\uDCFE\n ";
                        sendMessage.setReplyMarkup(startKeyboardUser());
                        sender = false;
                    }
                } else {
                    responceText = "Извините ваш код не подтвержден пожалуйста нажмите на /start  и попробуйте занова ";
                }
            } else if (text.equalsIgnoreCase("/menu")) {
                if (chatId1.equals("1092837648")) {
                    step = "menu";
                    sendMessage.setText("<b>" + "<i>" + text + "</i>" + "</b>");
                    sendMessage.setParseMode("HTML");
                    responceText = "\uD835\uDCDC\uD835\uDCEE\uD835\uDCF7\uD835\uDCFE\n ";
                    sendMessage.setReplyMarkup(startKeyboard());
                    sender = false;
                } else {
                    step = "menu";
                    sendMessage.setText("<b>" + "<i>" + text + "</i>" + "</b>");
                    sendMessage.setParseMode("HTML");
                    responceText = "\uD835\uDCDC\uD835\uDCEE\uD835\uDCF7\uD835\uDCFE\n ";
                    sendMessage.setReplyMarkup(startKeyboardUser());
                    sender = false;
                }
            }
            if (reclama) {
                int userscount = 0;
                for (users c : ConnectDB.select())
                    if (!c.getChatid().equals("1092837648")) {
                        sendMessage.setText("Реклама: " + text);
                        sendMessage.setChatId(c.getChatid());
                        userscount++;
                        execute(sendMessage);
                    }
                sendMessage.setText("Реклама была отправлена всем пользователям\n" + "Число ползователей " + userscount);
                sendMessage.setChatId("1092837648");
                reclama = false;

                execute(sendMessage);
            } else if (text.equals("User")
                    || text.equals("Admin")
                    || text.equals("Curency")
                    || text.equals("Convertor")
                    || text.equals("Скачать список пользователей в виде ехсеl файла")
                    || text.equals("Скачать список чисел  сделаные конвертация в виде ехсеl файла")
                    || text.equals("Скинуть всем пользователям рекламу")
                    || text.equals("Скинуть всем пользователям новости")
            ) {
                execute(replyMenu(chatId1, user, text, responceText, message, sendMessage));
                return;
            } else if (text.equals("Назад")) {
                String goToMenuName = step;
                switch (goToMenuName) {
                    case "User":
                        execute(replyMenu(chatId1, user, "menu", responceText, message, sendMessage));
                        break;
                    case "Admin":
                        execute(replyMenu(chatId1, user, "menu", responceText, message, sendMessage));
                        break;
                    case "Curency":
                        execute(replyMenu(chatId1, user, "User", responceText, message, sendMessage));
                        break;
                    case "Convertor":
                        execute(replyMenu(chatId1, user, "User", responceText, message, sendMessage));
                        break;
                    case "Скачать список чисел  сделаные конвертация в виде ехсеl файла":
                        execute(replyMenu(chatId1, user, "Admin", responceText, message, sendMessage));
                        break;
                    case "Скачать список пользователей в виде ехсеl файла":
                        execute(replyMenu(chatId1, user, "Admin", responceText, message, sendMessage));
                        break;
                    case "Скинуть всем пользователям рекламу":
                        execute(replyMenu(chatId1, user, "Admin", responceText, message, sendMessage));
                        break;
                    case "Скинуть всем пользователям новости":
                        execute(replyMenu(chatId1, user, "Admin", responceText, message, sendMessage));
                        break;
                }
                return;
            }
            if (enter) {
                CurencyMenu curencyMenu = new CurencyMenu();
                converterList.add(new Converter(count++, chatId1, update.getMessage().getFrom().getFirstName(), text, curencyMenu.converter(Valuta, text, user.getId())));
                enter = false;
                execute(curencyMenu.CurrencyStart(Valuta, text, user.getId()));
            }


        } else if (update.hasCallbackQuery()) {
        } else {
            sendMessage.setText("<b>" + "<i>" + "Sorry not found " + "</i>" + "</b>");
        }
        if (step.equals("Curency")) {
            switch (responceText1) {
                case "ARS":
                case "AUD":
                case "EUR":
                case "USD":
                case "BHD":
                case "BDT":
                case "AMD":
                case "GBP":
                case "HUF":
                case "BND":
                case "BGN":
                case "BYN":
                case "DKK":
                case "GEL":
                case "VND":
                case "VES":
                case "JOD":
                case "IDR":
                case "ZAR":
                case "DZD":
                case "KHR":
                case "ILS":
                case "ISK":
                case "IQD":
                case "AED":
                case "CAD":
                    CurencyMenu curencyMenu = new CurencyMenu();
                    sendMessage.setText(curencyMenu.Curency(responceText1));
                    execute(sendMessage);
            }
        } else if (step.equals("Convertor")) {
            Valuta = responceText1;
            switch (responceText1) {
                case "ARS":
                case "RUB":
                case "EUR":
                case "USD":
                    enter = true;
                    sendMessage.setText("Напиши сумму");
                    execute(sendMessage);
            }
        }
        sendMessage.setChatId(chatId1);
        sendMessage.setText(responceText);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public SendMessage replyMenu(String chatid, User user, String text, String responceText, Message message, SendMessage sendMessage) throws IOException {
        if (text.equals("menu")) {
            if (chatid.equals("1092837648")) {
                step = "menu";
                sendMessage.setText("<b>" + "<i>" + text + "</i>" + "</b>");
                sendMessage.setParseMode("HTML");
                responceText = "\uD835\uDCDC\uD835\uDCEE\uD835\uDCF7\uD835\uDCFE\n ";
                sendMessage.setReplyMarkup(startKeyboard());
            } else {
                step = "menu";
                sendMessage.setText("<b>" + "<i>" + text + "</i>" + "</b>");
                sendMessage.setParseMode("HTML");
                responceText = "\uD835\uDCDC\uD835\uDCEE\uD835\uDCF7\uD835\uDCFE\n ";
                sendMessage.setReplyMarkup(startKeyboardUser());
            }
        } else if (text.equalsIgnoreCase("Скачать список чисел  сделаные конвертация в виде ехсеl файла")) {
            step = "Скачать список чисел  сделаные конвертация в виде ехсеl файла";
            writeExel2();
            sendMessage.setParseMode("HTML");
            File file = new File("G:\\java abdumajid\\BotIkramova\\test.xlsx");
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(String.valueOf(chatid));
            sendDocument.setDocument(new InputFile(file));
            sendDocument.setCaption("Вот ваш файл");
            sendDocument.setReplyMarkup(Back());
            try {
                execute(sendDocument);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (text.equalsIgnoreCase("Скинуть всем пользователям рекламу")) {
            step = "Скинуть всем пользователям рекламу";
            reclama = true;
            sendMessage.setText("Cкинте нам рекламу которую хотите отправить всем ");
            sendMessage.setChatId("1092837648");
            sendMessage.setReplyMarkup(Back());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (text.equalsIgnoreCase("Скачать список пользователей в виде ехсеl файла")) {
            writeExel();
            step = "Скачать список пользователей в виде ехсеl файла";
            sendMessage.setParseMode("HTML");
            File file = new File("G:\\java abdumajid\\BotIkramova\\student.xlsx");
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(String.valueOf(chatid));
            sendDocument.setDocument(new InputFile(file));
            sendDocument.setCaption("Вот ваш файл");
            sendDocument.setReplyMarkup(Back());
            try {
                execute(sendDocument);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (text.equalsIgnoreCase("Скинуть всем пользователям новости")) {
            step = "Скинуть всем пользователям новости";
            int userscount = 0;
            for (users c : ConnectDB.select()) {
                if (!c.getChatid().equals("1092837648")) {
                    Yangiliklar yangiliklar = new Yangiliklar();
                    sendMessage.setText("Сегодняшние новости\n" + yangiliklar.main());
                    sendMessage.setChatId(c.getChatid());
                    sendMessage.setReplyMarkup(Back());
                    userscount++;
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                sendMessage.setText("Все новости были отправлены всем пользователям\n" + "Число ползователей " + userscount);
                sendMessage.setChatId("1092837648");
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (text.equalsIgnoreCase("User")) {
            step = "User";
            sendMessage.setText("<b>" + "<i>" + text + "</i>" + "</b>");
            sendMessage.setParseMode("HTML");
            responceText = "\uD835\uDE52\uD835\uDE5A\uD835\uDE61\uD835\uDE58\uD835\uDE64\uD835\uDE62\uD835\uDE5A \uD835\uDE50\uD835\uDE68\uD835\uDE5A\uD835\uDE67 ";
            sendMessage.setReplyMarkup(startKeyboard1());
        } else if (text.equalsIgnoreCase("Curency")) {
            step = "Curency";
            CurencyMenu curencyMenu = new CurencyMenu();
            sendMessage.setReplyMarkup(curencyMenu.menuKeyboard());
            responceText = "Choose option";
        } else if (text.equalsIgnoreCase("Convertor")) {
            step = "Convertor";
            CurencyMenu curencyMenu = new CurencyMenu();
            sendMessage.setReplyMarkup(curencyMenu.menuKeyboard1());
            responceText = "Выбирай";
        } else if (text.equalsIgnoreCase("Admin")) {
            if (chatid.equals("1092837648")) {
                step = "Admin";
                sendMessage.setText("<b>" + "<i>" + text + "</i>" + "</b>");
                sendMessage.setParseMode("HTML");
                responceText = "\uD835\uDE52\uD835\uDE5A\uD835\uDE61\uD835\uDE58\uD835\uDE64\uD835\uDE62\uD835\uDE5A \uD835\uDE3C\uD835\uDE59\uD835\uDE62\uD835\uDE5E\uD835\uDE63 \uD835\uDE4E\uD835\uDE5A\uD835\uDE67\uD835\uDE6B\uD835\uDE5E\uD835\uDE58\uD835\uDE5A ";
                sendMessage.setReplyMarkup(Admin());
            } else {
                responceText = "Error";
            }
        }
        sendMessage.setText(responceText);
        return sendMessage;
    }

    private ReplyKeyboardMarkup startKeyboard1() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        KeyboardRow FirstRow = new KeyboardRow();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        FirstRow.add(new KeyboardButton("Curency"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Convertor"));
        KeyboardRow Thirt = new KeyboardRow();
        Thirt.add(new KeyboardButton("Назад"));
        keyboardRowList.add(FirstRow);
        keyboardRowList.add(secondRow);
        keyboardRowList.add(Thirt);

        keyboard.setKeyboard(keyboardRowList);
        return keyboard;
    }

    private ReplyKeyboardMarkup startKeyboard() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        KeyboardRow FirstRow = new KeyboardRow();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        FirstRow.add(new KeyboardButton("User"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Admin"));

        keyboardRowList.add(FirstRow);
        keyboardRowList.add(secondRow);
        keyboard.setKeyboard(keyboardRowList);
        return keyboard;
    }

    private ReplyKeyboardMarkup Back() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        KeyboardRow FirstRow = new KeyboardRow();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        FirstRow.add(new KeyboardButton("Назад"));
        keyboardRowList.add(FirstRow);
        keyboard.setKeyboard(keyboardRowList);
        return keyboard;
    }

    private ReplyKeyboardMarkup startKeyboardUser() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        KeyboardRow FirstRow = new KeyboardRow();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        FirstRow.add(new KeyboardButton("User"));

        keyboardRowList.add(FirstRow);
        keyboard.setKeyboard(keyboardRowList);
        return keyboard;
    }

    private static void writeExel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("test");
        sheet.setColumnWidth(0, 5550);
        Row firstRow = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
//        font.set
        style.setFont(font);
        Cell cell = null;

        cell = firstRow.createCell(1);
        cell.setCellValue("Id");
        cell.setCellStyle(style);

        cell = firstRow.createCell(2);
        cell.setCellValue("Name");
        cell.setCellStyle(style);

        cell = firstRow.createCell(3);
        cell.setCellValue("Phone");
        cell.setCellStyle(style);

        cell = firstRow.createCell(4);
        cell.setCellValue("Date");
        cell.setCellStyle(style);

        cell = firstRow.createCell(5);
        cell.setCellValue("Time");
        cell.setCellStyle(style);

        Row secondrow = null;
        int count1 = 1;
        for (users c : ConnectDB.select()) {
            for (int i = 1; i <= ConnectDB.select().size(); i++) {

                secondrow = sheet.createRow(count1);

                cell = secondrow.createCell(0);
                cell.setCellValue(count1++);


                cell = secondrow.createCell(1);
                cell.setCellValue(c.getChatid());

                cell = secondrow.createCell(2);
                cell.setCellValue(c.getName());

                cell = secondrow.createCell(3);
                cell.setCellValue(c.getPhone());

                cell = secondrow.createCell(4);
                cell.setCellValue(c.getDate());

                cell = secondrow.createCell(5);
                cell.setCellValue(c.getTime());
                break;
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream("student.xlsx");
            workbook.write(outputStream);
        } catch (Exception e) {

        }
    }

    private void writeExel2() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("molodes");
        sheet.setColumnWidth(0, 5550);
        Row firstRow = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
//        font.set
        style.setFont(font);
        Cell cell = null;

        cell = firstRow.createCell(1);
        cell.setCellValue("Id");
        cell.setCellStyle(style);

        cell = firstRow.createCell(2);
        cell.setCellValue("Name");
        cell.setCellStyle(style);

        cell = firstRow.createCell(3);
        cell.setCellValue("Convert qilmoqchi son");
        cell.setCellStyle(style);

        cell = firstRow.createCell(4);
        cell.setCellValue("Convert qilingan son");
        cell.setCellStyle(style);

        Row secondrow = null;
        int count1 = 1;
        for (Converter c : converterList) {
            for (int i = 1; i <= converterList.size(); i++) {
                secondrow = sheet.createRow(count1);

                cell = secondrow.createCell(0);
                cell.setCellValue(count1++);


                cell = secondrow.createCell(1);
                cell.setCellValue(c.getId());

                cell = secondrow.createCell(2);
                cell.setCellValue(c.getName());

                cell = secondrow.createCell(3);
                cell.setCellValue(c.getStart());

                cell = secondrow.createCell(4);
                cell.setCellValue(c.getFinished());
                break;
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream("G:\\java abdumajid\\BotIkramova\\test.xlsx");
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private static void Readexcel(int count, String id, String phonenumber) {
        FileInputStream fileInputStream = new FileInputStream(new File("G:\\java abdumajid\\BotIkramova\\student.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        XSSFSheet sheet = workbook.getSheetAt(0);

        //qatrlar hammasi
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            Iterator<Cell> cellIterator = row.iterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    case FORMULA:
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case BLANK:
                        break;
                    case BOOLEAN:
                        break;
                }
            }
            System.out.println("");
            fileInputStream.close();
        }
    }

    public ReplyKeyboardMarkup Admin() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        KeyboardRow FirstRow = new KeyboardRow();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        FirstRow.add(new KeyboardButton("Скачать список пользователей в виде ехсеl файла"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Скачать список чисел  сделаные конвертация в виде ехсеl файла"));
        KeyboardRow Thirt = new KeyboardRow();
        Thirt.add(new KeyboardButton("Скинуть всем пользователям новости"));
        KeyboardRow Four = new KeyboardRow();
        Four.add(new KeyboardButton("Скинуть всем пользователям рекламу"));
        KeyboardRow Five = new KeyboardRow();
        Five.add(new KeyboardButton("Назад"));
        keyboardRowList.add(FirstRow);
        keyboardRowList.add(secondRow);
        keyboardRowList.add(Thirt);
        keyboardRowList.add(Four);
        keyboardRowList.add(Five);
        keyboard.setKeyboard(keyboardRowList);
        return keyboard;
    }
}

