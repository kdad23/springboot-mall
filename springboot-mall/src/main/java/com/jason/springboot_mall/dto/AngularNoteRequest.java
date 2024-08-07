package com.jason.springboot_mall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
@Schema(title = "待辦事項請求參數DTO")
public class AngularNoteRequest
{

    @Schema(title = "待辦事項請求參數Id")
    private Integer note_id;
    @Schema(title = "待辦事項請求參數Title")
    @NotBlank
    private String title;

    @Schema(title = "待辦事項請求參數詳細訊息")
    @NotBlank
    private String description;
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
}
