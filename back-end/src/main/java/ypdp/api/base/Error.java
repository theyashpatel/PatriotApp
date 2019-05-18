package ypdp.api.base;

public class Error {

    private String code;

    public Error(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
