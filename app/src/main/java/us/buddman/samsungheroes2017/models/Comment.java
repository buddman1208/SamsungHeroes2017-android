package us.buddman.samsungheroes2017.models;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Junseok Oh on 2017-06-29.
 */

public class Comment {
    private String author, title, content;
    private int likeCount;
    private Date date;
    private boolean isMenuOpened = false;

    public Comment(String author, String title, String content, int likeCount, Date date) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLikeCount() {
        return likeCount + "";
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getDate() {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isMenuOpened() {
        return isMenuOpened;
    }

    public void setMenuOpened(boolean menuOpened) {
        isMenuOpened = menuOpened;
    }
}
