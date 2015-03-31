package gov.gwssi.csc.scms.domain.other;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_PUB_NODE")
public class PubNode {
    @Id
    private String parent_id;
    private String node_level;
    private String node_name;
    private String node_type;
    private String node_no;

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getNode_level() {
        return node_level;
    }

    public void setNode_level(String node_level) {
        this.node_level = node_level;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getNode_type() {
        return node_type;
    }

    public void setNode_type(String node_type) {
        this.node_type = node_type;
    }

    public String getNode_no() {
        return node_no;
    }

    public void setNode_no(String node_no) {
        this.node_no = node_no;
    }
}