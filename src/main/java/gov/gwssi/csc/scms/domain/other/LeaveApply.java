package gov.gwssi.csc.scms.domain.other;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_LEAVE_APPLY")
public class LeaveApply {
    @Id
    private String id;
    private Date confirm_time;
    private String confirm_user_name;
    private String reason_id;
    private String confirm_user_id;
    private Date apply_time;
    private String apply_user_id;
    private String apply_user_name;
    private String explanation;
    private String csc_id;
    private String reason;
    private String state;
    private String url;

    public Date getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(Date confirm_time) {
        this.confirm_time = confirm_time;
    }

    public String getConfirm_user_name() {
        return confirm_user_name;
    }

    public void setConfirm_user_name(String confirm_user_name) {
        this.confirm_user_name = confirm_user_name;
    }

    public String getReason_id() {
        return reason_id;
    }

    public void setReason_id(String reason_id) {
        this.reason_id = reason_id;
    }

    public String getConfirm_user_id() {
        return confirm_user_id;
    }

    public void setConfirm_user_id(String confirm_user_id) {
        this.confirm_user_id = confirm_user_id;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public String getApply_user_id() {
        return apply_user_id;
    }

    public void setApply_user_id(String apply_user_id) {
        this.apply_user_id = apply_user_id;
    }

    public String getApply_user_name() {
        return apply_user_name;
    }

    public void setApply_user_name(String apply_user_name) {
        this.apply_user_name = apply_user_name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getCsc_id() {
        return csc_id;
    }

    public void setCsc_id(String csc_id) {
        this.csc_id = csc_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
