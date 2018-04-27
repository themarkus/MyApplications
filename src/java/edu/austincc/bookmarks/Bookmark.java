
package edu.austincc.bookmarks;

public class Bookmark {
    private final String name;
    private final String link;

    public Bookmark(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
    
}
