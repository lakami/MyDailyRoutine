package entity;

import common.UserRole;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class User implements UserDetails {


    @Id
    @Type(type = "uuid-char")
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(nullable = false)
    private Boolean locked = false;

    @Column(nullable = false)
    private Boolean enabled = false;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.DETACH)
    private List<Task> createdTasks;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.DETACH)
    private List<Task> assignedTasks;


    public User() {
    }

    public User(String login,
                   String username,
                   String email,
                   String password,
                   UserRole userRole) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.createdTasks = new ArrayList<>();
        this.assignedTasks = new ArrayList<>();
    }

    public void addNewCreatedTask(Task task){
        createdTasks.add(task);
    }

    public void addNewAsignedTask(Task task){
        assignedTasks.add(task);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority= new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    public List<Task> getCreatedTasks(){
        return createdTasks;
    }

    public void setCreatedTasks(List<Task> createdTasks){
        this.createdTasks = createdTasks;
    }

    public List<Task> getAssignedTasks(){
        return assignedTasks;
    }

    public void setAssignedTasks(List<Task> assignedTasks){
        this.assignedTasks = assignedTasks;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public UUID getAccountId() {
        return id;
    }

    public void setAccountId(UUID accountId) {
        this.id = accountId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User appUser = (User) o;
        return id.equals(appUser.id) &&
                login.equals(appUser.login) &&
                username.equals(appUser.username) &&
                email.equals(appUser.email) &&
                password.equals(appUser.password) &&
                userRole == appUser.userRole &&
                locked.equals(appUser.locked) &&
                enabled.equals(appUser.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, username, email, password, userRole, locked, enabled);
    }
}
