package com.example.Todo.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD}) //メソッドとフィールドにアノテーション付けれる
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= UniqueUsernameValidator.class)
public @interface UniqueUsername {
    String message() default "ユーザ名がすでに登録されています。";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
