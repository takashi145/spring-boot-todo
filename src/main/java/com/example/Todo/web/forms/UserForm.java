package com.example.Todo.web.forms;

import com.example.Todo.web.validation.UniqueUsername;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserForm {

    @NotBlank
    @Size(max=30)
    @UniqueUsername
    private String username;

    @NotBlank
    @Size(min=8, max=128)
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[.?/-])[a-zA-Z0-9.?/-]{8,}$",
            message = "パスワードは大文字アルファベット、記号を１つ以上含む必要があります。"
    )
    private String password;

}
