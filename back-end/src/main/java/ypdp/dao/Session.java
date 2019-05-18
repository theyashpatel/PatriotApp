package ypdp.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Session {

    @Id
    private String id;

    private String userId;
    private Date startDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
