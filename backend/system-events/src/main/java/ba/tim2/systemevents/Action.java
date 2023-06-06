package ba.tim2.systemevents;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    @NotBlank(message = "Action cannot be blank")
    private String action;

    @NotBlank(message = "Status cannot be blank")
    private String status;
    @NotBlank(message = "Resource name cannot be blank")
    private String resourceName;

    @NotBlank(message = "Time cannot be blank")
    private String eventTime;

    public Action() {
    }

    public Action(Long userId, String action, String status, String resourceName, String eventTime) {
        this.userId = userId;
        this.action = action;
        this.status = status;
        this.resourceName = resourceName;
        this.eventTime = eventTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
