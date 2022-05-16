package MYBot;

import io.jsonwebtoken.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.jsoup.Connection;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;

public class Yangiliklar {

        public static String main() throws IOException, java.io.IOException {
String str=" ";
            Connection connect = (Connection) Jsoup.connect("https://kun.uz/uz/news/category/uzbekiston");
            Document document = connect.get();
            Elements dateList = document.getElementsByClass("small-news__content");
//        Elements titleList = document.getElementsByClass("small-news__title");

            for (Element element : dateList) {
                Elements span = element.getElementsByTag("span");
                Elements link = element.getElementsByTag("a");

                for (Element element1 : span) {
                 str+= "\n"+element1.ownText()+"\n";
                }
                for (Element element1 : link) {
                   str+="\n"+element1.ownText()+"\n";
                }

            }
            return  str;
        }}
