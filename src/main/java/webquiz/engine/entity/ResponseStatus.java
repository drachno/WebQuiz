package webquiz.engine.entity;

public class ResponseStatus {

    private String text;
    private boolean success;

    public ResponseStatus(boolean success, String text) {
        this.success = success;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}