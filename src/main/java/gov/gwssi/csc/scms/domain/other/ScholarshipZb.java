package gov.gwssi.csc.scms.domain.other;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_SCHOLARSHIP_ZB")
public class ScholarshipZb {
    private String result_jjw;
    private String csc_id;
    private Date start_time;
    private String review;
    private String result;
    private String review_jjw;
    private Date end_time;
    private String reason;
    @Id
    private String id;
    private String main_id;

    public String getResult_jjw() {
        return result_jjw;
    }

    public void setResult_jjw(String result_jjw) {
        this.result_jjw = result_jjw;
    }

    public String getCsc_id() {
        return csc_id;
    }

    public void setCsc_id(String csc_id) {
        this.csc_id = csc_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReview_jjw() {
        return review_jjw;
    }

    public void setReview_jjw(String review_jjw) {
        this.review_jjw = review_jjw;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
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

    public String getMain_id() {
        return main_id;
    }

    public void setMain_id(String main_id) {
        this.main_id = main_id;
    }
}
