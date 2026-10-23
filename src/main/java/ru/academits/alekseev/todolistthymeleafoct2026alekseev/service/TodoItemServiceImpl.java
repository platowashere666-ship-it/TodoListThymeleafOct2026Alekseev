package ru.academits.alekseev.todolistthymeleafoct2026alekseev.service;

import org.springframework.stereotype.Service;

import ru.academits.alekseev.todolistthymeleafoct2026alekseev.entity.TodoItem;
import ru.academits.alekseev.todolistthymeleafoct2026alekseev.repository.TodoItemRepository;

import java.util.List;

@Service
public class TodoItemServiceImpl implements TodoItemService {
    private final TodoItemRepository todoItemRepository;

    public TodoItemServiceImpl(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @Override
    public List<TodoItem> getAll() {
        return todoItemRepository.getAll();
    }

    @Override
    public void create(TodoItem todoItem) {
        if (todoItem == null) {
            throw new IllegalArgumentException("TodoItem must not be null");
        }

        String newText = todoItem.getText().trim();

        if (newText.isEmpty()) {
            throw new IllegalArgumentException("Text must not be empty");
        }

        todoItem.setText(newText);
        todoItemRepository.create(todoItem);
    }

    @Override
    public void update(TodoItem todoItem) {
        if (todoItem == null) {
            throw new IllegalArgumentException("TodoItem must not be null");
        }

        String newText = todoItem.getText().trim();

        if (newText.isEmpty()) {
            throw new IllegalArgumentException("Text must not be empty");
        }

        todoItem.setText(newText);
        todoItemRepository.update(todoItem);
    }

    @Override
    public void delete(int todoItemId) {
        todoItemRepository.delete(todoItemId);
    }
}
