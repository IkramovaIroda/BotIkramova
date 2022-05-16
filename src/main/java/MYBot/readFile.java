package MYBot;


import javax.annotation.processing.Filer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readFile {
    public static List<User1> retunbtninfo(String txt){
        List<User1> list=new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(txt);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int count=1;
            while (line!=null){
                    String[] split=line.split("#");
                    User1 user1 = new User1(count++, split[0], split[1], split[2], split[3], split[4]);
                    line = bufferedReader.readLine();
                    list.add(user1);
                }
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
