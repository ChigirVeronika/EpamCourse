package client.by.epam.fullparser.bean;

public class Response {
    private String message;
    private boolean success = true;

    public boolean isSuccess() {
        return success;
    }

    public void setError(String message) {
        success = false;
        this.message = message;
    }

    public void setSuccess(String message) {
        success = true;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}