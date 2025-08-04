package com.blogplatform.simpleblogplatform.dto;

// We will add validation annotations like @NotEmpty in a later step.
// For now, it's a simple container for the form data.
public class CommentDto {

    private String content;

    // Standard getters and setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}