package com.example.service;

import com.example.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TaskRegistry {

    private final Map<String, Task> tasks = new HashMap<>();

    public TaskRegistry(List<Task> taskList) {
        for (Task task : taskList) {
            tasks.put(task.getCode(), task);
        }
    }

    public Task getTaskByCode(String code) {
        return tasks.get(code);
    }

    public Collection<Task> getAllTasks() {
        return tasks.values();
    }
}