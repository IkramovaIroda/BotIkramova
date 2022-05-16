package MYBot;

public class Converter {
    int count;
    private String id;
    private String name;
    private String start;
    private String finished;

    public Converter(int count, String id, String name, String start, String finished) {
        this.count = count;
        this.id = id;
        this.name = name;
        this.start = start;
        this.finished = finished;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }
}
