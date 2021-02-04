
package com.demo.springdoc.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

public class Book {

    private long id;

    @NotBlank
    @Size(min = 0, max = 20)
    @Schema(description = "本のタイトル", 
            example = "Star Wars", required = true)
    private String title;

    @NotBlank
    @Schema(description = "著者", 
            example = "George Lucas", required = true)
    @Size(min = 0, max = 30)
    private String author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}