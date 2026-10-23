package ru.academits.alekseev.todolistthymeleafoct2026alekseev.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoItem {
    private int id;
    private String text;

    public TodoItem(int id, String text) {
        this.id = id;
        this.text = text;
    }

}
