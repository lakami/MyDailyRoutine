package controller;

import entity.Label;
import entity.Task;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.LabelRepository;
import repository.TaskRepository;
import repository.UserRepository;
import request.CreateTaskRequest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;

    @Autowired
    public TaskController(
            TaskRepository taskRepository,
            UserRepository userRepository,
            LabelRepository labelRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.labelRepository = labelRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody CreateTaskRequest createTaskRequest){
        //TODO: create task

        return ResponseEntity.ok().build();
    }


    private void create(CreateTaskRequest createTaskRequest){
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var assignee = userRepository.getById(createTaskRequest.getAssignee());
        Set<Label> labelSet = createTaskRequest.getLabels()
                .stream()
                .map(labelS -> {
                    Optional<Label> label = labelRepository.findByLabel(labelS);
                    if (label.isPresent()){
                        return label.get();
                    }
                    else {
                        Label labelNew = new Label(labelS);
                        labelRepository.save(labelNew);
                        return labelNew;
                    }
                })
                .collect(Collectors.toSet());
        Task task = new Task(
                createTaskRequest.getName(),
                createTaskRequest.getDescription(),
                createTaskRequest.getDeadline(),
                user,
                assignee,
                labelSet);

        taskRepository.save(task);

        user.addNewCreatedTask(task);
        assignee.addNewAsignedTask(task);

        userRepository.save(user);
        userRepository.save(assignee);


    }
}
