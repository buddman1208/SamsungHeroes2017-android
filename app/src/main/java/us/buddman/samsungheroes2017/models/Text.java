package us.buddman.samsungheroes2017.models;

/**
 * Created by Junseok Oh on 2017-07-18.
 */

public class Text {
    private String _id, writer, content;
    private int like;

    public Text() {
    }

    public Text(String _id, String writer, String content, int like) {
        this._id = _id;
        this.writer = writer;
        this.content = content;
        this.like = like;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
