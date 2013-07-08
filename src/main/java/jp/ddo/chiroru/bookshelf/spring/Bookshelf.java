package jp.ddo.chiroru.bookshelf.spring;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "Bookshelf")
public class Bookshelf
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int volume;
    private String description;
    private String picture;
    private int bookshelfId;
    @XmlElement(name = "createdAt", required = true) 
    @XmlJavaTypeAdapter(TimestampAdapter.class)
    private Timestamp createdAt;
    private String createdUser;
    @XmlElement(name = "updatedAt", required = true)
    @XmlJavaTypeAdapter(TimestampAdapter.class)
    private Timestamp updatedAt;
    private String updatedUser;
    
    public Bookshelf() {}
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public int getBookshelfId() {
        return bookshelfId;
    }
    public void setBookshelfId(int bookshelfId) {
        this.bookshelfId = bookshelfId;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public String getCreatedUser() {
        return createdUser;
    }
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getUpdatedUser() {
        return updatedUser;
    }
    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }
}
