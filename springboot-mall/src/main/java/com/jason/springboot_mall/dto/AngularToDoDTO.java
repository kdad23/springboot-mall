package com.jason.springboot_mall.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(title = "待辦事項回應參數DTO")
public class AngularToDoDTO
{
    @Schema(title = "待辦事項Id")
    private Integer note_id;
    @Schema(title = "待辦事項Title")
    private String title;
    @Schema(title = "待辦事項詳細訊息")
    private String description;
    @Schema(title = "待辦事項建立時間")
    private Date createdDate;
    @Schema(title = "待辦事項修改時間")
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
