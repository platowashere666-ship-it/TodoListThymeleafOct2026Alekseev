package ru.academits.alekseev.todolistthymeleafoct2026alekseev.service;

import ru.academits.alekseev.todolistthymeleafoct2026alekseev.entity.TodoItem;

import java.util.List;

public interface TodoItemService {
    List<TodoItem> getAll();

    void create(TodoItem todoItem);

    void update(TodoItem todoItem);

    void delete(int todoItemId);
}
