package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import common.TaskStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
public class Task {

    @Id
    @Type(type =  "uuid-char")
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private int taskNumber;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private LocalDateTime executedAt;

    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @ManyToOne
    private User creator;

    @ManyToOne
    private User assignee;


    private TaskStatus taskStatus;

    @ManyToOne
    private User modifiedByUserId;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "labelTask",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn (name = "label_id")
    )
    Set<Label> labels;

    public Task(String name, String description, LocalDateTime deadline, User creator, User assignee, Set<Label> labels) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.creator = creator;
        this.assignee = assignee;
        this.labels = labels;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public User getModifiedByUserId() {
        return modifiedByUserId;
    }

    public void setModifiedByUserId(User modifiedByUserId) {
        this.modifiedByUserId = modifiedByUserId;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }
}
