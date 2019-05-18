package ypdp.api;

public class GeneralResponse {

    private String status;

    public GeneralResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
