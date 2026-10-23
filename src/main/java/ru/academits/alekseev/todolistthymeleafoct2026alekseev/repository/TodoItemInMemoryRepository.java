package ru.academits.alekseev.todolistthymeleafoct2026alekseev.repository;

import org.springframework.stereotype.Repository;
import ru.academits.alekseev.todolistthymeleafoct2026alekseev.entity.TodoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TodoItemInMemoryRepository implements TodoItemRepository {
    private final List<TodoItem> todoItems = new ArrayList<>();
    private final AtomicInteger currentId = new AtomicInteger(1);

    @Override
    public List<TodoItem> getAll() {
        synchronized (todoItems) {
            return todoItems;
        }
    }

    @Override
    public void create(TodoItem todoItem) {
        synchronized (todoItems) {
            todoItems.add(new TodoItem(currentId.getAndIncrement(), todoItem.getText()));
        }
    }

    @Override
    public void update(TodoItem todoItem) {
        synchronized (todoItems) {
            TodoItem repositoryTodoItem = todoItems.stream()
                    .filter(item -> item.getId() == todoItem.getId())
                    .findFirst()
                    .orElse(null);

            if (repositoryTodoItem == null) {
                throw new IllegalArgumentException("TodoItem with id = " + todoItem.getId() + " does not exist");
            }

            repositoryTodoItem.setText(todoItem.getText());
        }
    }

    @Override
    public void delete(int todoItemId) {
        synchronized (todoItems) {
            todoItems.removeIf(item -> item.getId() == todoItemId);
        }
    }
}
