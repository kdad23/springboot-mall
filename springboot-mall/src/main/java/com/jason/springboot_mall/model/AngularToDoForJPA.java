package com.jason.springboot_mall.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Date;

@Schema(title = "待辦事項Model")
@Entity
@Table(name="angulartodo")
public class AngularToDoForJPA
{
    @Schema(title = "待辦事項Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "note_id")
    private Integer note_id;
    @Schema(title = "待辦事項Title")
    @Column(name = "title")
    private String title;
    @Schema(title = "詳細訊息")
    @Column(name = "description")
    private String description;
    @Schema(title = "建立時間")
    @Column(name = "created_date")
    private Date createdDate=new Date();
    @Schema(title = "修改時間")
    @Column(name = "last_modified_date")
    private Date lastModifiedDate=new Date();

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
