package com.jason.springboot_mall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jason.springboot_mall.constant.ProductCategory;

import java.util.Date;

public class Angular_Note
{
    private Integer note_id;
    private String title;
    private String description;
    private Date createdDate;
    private Date lastModifiedDate;


    public Integer getNote_id() {
        return note_id;
    }

    public void setNote_id(Integer note_id) {
        this.note_id = note_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
