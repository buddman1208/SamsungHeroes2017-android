package us.buddman.samsungheroes2017.models;

import java.util.Date;

/**
 * Created by Administrator on 2017-09-16.
 */

public class PIFMessage {
    private String _id;

    private String message, target, author;
    private Date date;

    public PIFMessage(String message, String target, String author, Date date) {
        this.message = message;
        this.target = target;
        this.author = author;
        this.date = date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return author + ", " + date.toLocaleString();
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
