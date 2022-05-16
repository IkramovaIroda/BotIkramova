package MYBot;

public class users {
    private Integer count;
    private String chatid;
    private String name;
    private String phone;
    private String date;
    private String time;

    public users(Integer count, String chatid, String name, String phone, String date, String time) {
        this.count = count;
        this.chatid = chatid;
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public users() {
    }

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
