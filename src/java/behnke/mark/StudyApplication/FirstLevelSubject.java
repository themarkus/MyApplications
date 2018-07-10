package behnke.mark.StudyApplication;

import java.util.Date;

public class FirstLevelSubject {

    private int id;
    private String name;
    private String link;
    private Date createdAt;

    public FirstLevelSubject(String name, String link) {
        this.id = -1;
        this.name = name;
        this.link = link;
        this.createdAt = null;
    }

    public FirstLevelSubject(int id, String name, String link, Date date) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.createdAt = date;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
