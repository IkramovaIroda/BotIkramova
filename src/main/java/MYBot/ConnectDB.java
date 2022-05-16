package MYBot;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConnectDB {

    public static List<users> select() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/currency_bot",
                    "javaDbUser", "root");

            Statement stmt = connection.createStatement();
            String sql = "Select id,name,phoneNumber,date,time FROM bot";
//            String sql = "SELECT * FROM profile";

            ResultSet result = stmt.executeQuery(sql);

            List<users> usersList=new LinkedList<>();
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String phoneNumber = result.getString(3);
                Timestamp time = result.getTimestamp(5);
                Date date = result.getDate(4);

                users users=new users();
                users.setChatid(String.valueOf(id));
                users.setName(name);
                users.setPhone(phoneNumber);
                users.setDate(String.valueOf(date));
                users.setTime(String.valueOf(time));
                usersList.add(users);
            }
            connection.close();
            return usersList;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insert(String id, String firstname, String phoneNumber, String date,String time) {
        try {

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/currency_bot",
                    "javaDbUser", "root");

            //1 usul

            String sql = "insert into bot(id,name,phoneNumber,date,time) values(?,?,?,?,?);";

            //2 usul

//            String sql = "insert into profile(name,surname,age,created_date) " +
//                    "values('"+name+"','"+surname+"',"+age+",'"+ LocalDateTime.now() +"')";
            //3 usul

//            String sql = "insert into profile(name,surname,age,created_date) values('%s' ,'%s',%d,'%s')";
//            sql=String.format(sql,name,surname,age,LocalDateTime.now());

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.setString(2, firstname);
            stmt.setString(3, phoneNumber);
            stmt.setString(4, date);
            stmt.setString(5, time);

            stmt.executeUpdate();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
