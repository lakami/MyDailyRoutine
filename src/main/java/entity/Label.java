package entity;

import org.hibernate.annotations.Type;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Label {
    @Id
    @Type(type = "uuid-char")
    @Column(unique = true, nullable = false)
    private UUID id;


    @ManyToMany(mappedBy = "labels")
    Set<Task> tasks = new HashSet<>();

    @Column(nullable = false, unique = true)
    private String label;

    public Label(String label) {
        this.id = UUID.randomUUID();
        this.label = label;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }


}
