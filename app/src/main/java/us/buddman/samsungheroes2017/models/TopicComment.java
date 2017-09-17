package us.buddman.samsungheroes2017.models;

import java.util.Date;

/**
 * Created by Administrator on 2017-09-16.
 */

public class TopicComment {
    private String _id;
    private Date date;
    private String author, comment;

    public TopicComment() {
    }

    public TopicComment(Date date, String author, String comment) {
        this._id = _id;
        this.date = date;
        this.author = author;
        this.comment = comment;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDate() {
        return date.toLocaleString();
    }

    public String getDescription() {
        return author + ", " + date.toLocaleString();
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
