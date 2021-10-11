package common;

public enum TaskStatus {
    TODO("To do"),
    INPROGRESS("In progress"),
    COMPLETE("Complete");

    private final String message;

    TaskStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
