package com.webblogTeam1.demo1.comment;

import com.webblogTeam1.demo1.message.PostMessage;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDTO {

    @Size(min = 3, max = 99)
    private String text;

    public CommentDTO(String text) {
        this.text = text;
    }

}
