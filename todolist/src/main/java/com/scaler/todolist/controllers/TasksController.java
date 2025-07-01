package com.scaler.todolist.controllers;

import com.scaler.todolist.models.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TasksController {

    private ArrayList<Task> taskList = new ArrayList<>();

    @GetMapping("/")
    ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskList);
    }

    @PostMapping("/")
    ResponseEntity<Task> addNewTask(@RequestBody Task task) {
        Task taskToAdd = new Task(task.getName());
        taskList.add(taskToAdd);
        return ResponseEntity.status(201).body(taskToAdd);
    }

    @GetMapping("/{index}")
    ResponseEntity<Task> getTaskAtIndex(@PathVariable int index) {
        if(taskList.size() <= index) {
            return ResponseEntity.status(404).body(null);
        }
        else {
            Task taskToSend = taskList.get(index);
            return ResponseEntity.status(200).body(taskToSend);
        }
    }

    @PatchMapping("/{index}")
    ResponseEntity<Task> markTaskAsDone(@PathVariable int index) {
        if(taskList.size() <= index) {
            return ResponseEntity.status(404).body(null);
        }
        else {
            Task taskToSend = taskList.get(index);
            taskToSend.setDone(true);
            return ResponseEntity.status(200).body(taskToSend);
        }
    }

    @DeleteMapping("/{index}")
    ResponseEntity<Task> deleteTask(@PathVariable int index) {
        if(taskList.size() <= index) {
            return ResponseEntity.status(404).body(null);
        }
        else {
            Task taskToSend = taskList.get(index);
            taskList.remove(index);
            return ResponseEntity.status(200).body(taskToSend);
        }
    }
}
