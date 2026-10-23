package ru.academits.alekseev.todolistthymeleafoct2026alekseev.repository;

import ru.academits.alekseev.todolistthymeleafoct2026alekseev.entity.TodoItem;

import java.util.List;

public interface TodoItemRepository {
    List<TodoItem> getAll();

    void create(TodoItem todoItem);

    void update(TodoItem todoItem);

    void delete(int todoItemId);
}
