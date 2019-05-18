package ypdp.api;

import ypdp.api.base.SecuredRequest;

public class DropClassRequest extends SecuredRequest {

    private String classId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
