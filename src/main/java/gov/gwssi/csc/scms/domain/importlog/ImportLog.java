package gov.gwssi.csc.scms.domain.importlog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by LiZhiSheng on 2015/9/1.
 */
@Entity
@Table(name = "SCMS_IMPORT_LOG")
public class ImportLog {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "FILENAME")
    private String fileName;

    @Column(name = "CNT")
    private int cnt;

    @Column(name = "STATE")
    private String state;
    @Column(name = "CLASS")
    private String importclass;

    @Column(name = "CREATEBY")
    private String createBy;
    @Column(name = "CREATED")
    private Date created;

    @Column(name = "ERROR")
    private String error;

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public int getCnt() {
        return cnt;
    }

    public String getState() {
        return state;
    }

    public String getImportclass() {
        return importclass;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Date getCreated() {
        return created;
    }

    public String getError() {
        return error;
    }
}
