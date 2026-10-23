package ru.academits.alekseev.todolistthymeleafoct2026alekseev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.academits.alekseev.todolistthymeleafoct2026alekseev.entity.TodoItem;
import ru.academits.alekseev.todolistthymeleafoct2026alekseev.service.TodoItemService;

@Controller
public class TodoListController {
    private final TodoItemService todoItemService;

    public TodoListController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todoItems", todoItemService.getAll());

        return "index";
    }

    @PostMapping("/")
    public String indexPost(@RequestParam String action,
                            @RequestParam(required = false) String text,
                            @RequestParam(required = false) Integer id,
                            RedirectAttributes redirectAttributes) {
        switch (action) {
            case "create" -> {
                try {
                    if (text == null) {
                        throw new IllegalArgumentException("Text must not be null");
                    }

                    todoItemService.create(new TodoItem(0, text));
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("createError", e.getMessage());
                }
            }

            case "save" -> {
                try {
                    if (text == null) {
                        throw new IllegalArgumentException("Text must not be null");
                    }

                    if (id == null) {
                        throw new NullPointerException("Id must not be null");
                    }

                    todoItemService.update(new TodoItem(id, text));
                } catch (NullPointerException e) {
                    redirectAttributes.addFlashAttribute("idError", e.getMessage());
                } catch (IllegalArgumentException e) {
                    redirectAttributes.addFlashAttribute("saveError", e.getMessage());
                    redirectAttributes.addFlashAttribute("saveErrorId", id);
                }
            }

            case "delete" -> {
                try {
                    if (id == null) {
                        throw new NullPointerException("Id must not be null");
                    }

                    todoItemService.delete(id);
                } catch (NullPointerException e) {
                    redirectAttributes.addFlashAttribute("idError", e.getMessage());
                }
            }
        }

        return "redirect:/";
    }
}