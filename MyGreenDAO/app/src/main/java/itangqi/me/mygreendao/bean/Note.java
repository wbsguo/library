package itangqi.me.mygreendao.bean;

import java.util.Date;

/**
 * Entity mapped to table NOTE.
 */
public class Note {
    private Long id;//主键
    private String noteId;//id值
    private String comment;
    private java.util.Date date;
    private String name;
    private double kcal;
    private String descript;

    public Note() {
    }
    public Note(Long id, String noteId, String comment, Date date, String name, double kcal) {
        this.id = id;
        this.noteId = noteId;
        this.comment = comment;
        this.date = date;
        this.name = name;
        this.kcal = kcal;
    }

    public Note(Long id, String noteId, String comment, Date date, String name, double kcal, String descript) {
        this.id = id;
        this.noteId = noteId;
        this.comment = comment;
        this.date = date;
        this.name = name;
        this.kcal = kcal;
        this.descript = descript;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
}
