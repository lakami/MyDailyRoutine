package request;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class CreateTaskRequest {
    private String name;
    private String description;
    private LocalDateTime deadline;
    private UUID assignee;
    private Set<String> labels;

    public CreateTaskRequest(String name, String description, LocalDateTime deadline, UUID assignee, Set<String> labels) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.assignee = assignee;
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public UUID getAssignee() {
        return assignee;
    }

    public void setAssignee(UUID assignee) {
        this.assignee = assignee;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public void setLabels(Set<String> labels) {
        this.labels = labels;
    }
}
