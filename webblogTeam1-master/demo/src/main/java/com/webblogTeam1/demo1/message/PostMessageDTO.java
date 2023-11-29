package com.webblogTeam1.demo1.message;

import lombok.Getter;

import jakarta.validation.constraints.Size;
import lombok.Setter;

@Setter
@Getter
public class PostMessageDTO {

    @Size(min = 3, max = 99)
    private  String text;
    @Size(min = 3, max = 99)
    private String title;


    public PostMessageDTO(String text, String title) {
        this.text = text;
        this.title = title;
    }
}
