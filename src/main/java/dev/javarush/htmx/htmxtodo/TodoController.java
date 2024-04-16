package dev.javarush.htmx.htmxtodo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("todos")  // /todos
public class TodoController {

  private final TodoRepository repository;

  public TodoController(TodoRepository repository) {
    this.repository = repository;
    this.repository.deleteAll();
    this.repository.save(new Todo(null, "Learn htmx", false));
    this.repository.save(new Todo(null, "Take a walk", true));
    this.repository.save(new Todo(null, "Learn Java", true));
    this.repository.save(new Todo(null, "Learn JDBC", false));
  }

  @GetMapping
  String index(Model model) {
	List<Todo> todos = this.repository.findAllByCompleted (false);
	List<Todo> todosCompleted = this.repository.findAllByCompleted (true);
	System.out.println (todos);
	System.out.println (todosCompleted);
    model.addAttribute("todos", todos);
    model.addAttribute("todosCompleted", todosCompleted);
    return "index";
  }

  @PutMapping("complete/{id}")
  String complete(@PathVariable("id") Integer id, Model model) {
    Todo todo = this.repository.findById(id).orElseThrow();
    Todo newTodo = new Todo(todo.id(), todo.title(), true);
    this.repository.save(newTodo);
    model.addAttribute("todo", newTodo);
    return "todo-complete-response";
  }

  @PutMapping("uncomplete/{id}")
  String uncomplete(@PathVariable("id") Integer id, Model model) {
    Todo todo = this.repository.findById(id).orElseThrow();
    Todo newTodo = new Todo(todo.id(), todo.title(), false);
    this.repository.save(newTodo);
    model.addAttribute("todo", newTodo);
    return "todo-uncomplete-response";
  }

  @PostMapping
  String add (@RequestParam("title") String title, Model model) {
    Todo todo = this.repository.save(new Todo(null, title, false));
    model.addAttribute("todo", todo);
    return "todo-add-response";
  }

  @DeleteMapping("{id}")
  @ResponseBody
  String delete (@PathVariable("id") Integer id) {
    this.repository.deleteById(id);
    return "";
  }
}
