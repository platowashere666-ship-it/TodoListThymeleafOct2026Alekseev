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
                if (text == null || text.isEmpty()) {
                    redirectAttributes.addFlashAttribute("createError", "Text must not be empty");
                    break;
                }

                todoItemService.create(new TodoItem(0, text));
            }

            case "save" -> {
                if (text == null || text.isEmpty()) {
                    redirectAttributes.addFlashAttribute("saveError", "Text must not be empty");
                    redirectAttributes.addFlashAttribute("saveErrorId", id);
                    break;
                }

                if (id == null) {
                    redirectAttributes.addFlashAttribute("idError", "Id must not be null");
                    break;
                }

                todoItemService.update(new TodoItem(id, text));
            }

            case "delete" -> {
                if (id == null) {
                    redirectAttributes.addFlashAttribute("idError", "Id must not be null");
                    break;
                }

                todoItemService.delete(id);
            }
        }

        return "redirect:/";
    }
}