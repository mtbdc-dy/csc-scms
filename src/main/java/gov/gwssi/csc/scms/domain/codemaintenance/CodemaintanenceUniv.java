package gov.gwssi.csc.scms.domain.codemaintenance;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Entity
@NamedStoredProcedureQueries(
        @NamedStoredProcedureQuery(name = "CodemaintanenceUniv.changeSortValue", procedureName = "p_scms_univ_sort",
                parameters = {
                        @StoredProcedureParameter(name = "id", mode = ParameterMode.IN, type = String.class),
                        @StoredProcedureParameter(name = "direction", mode = ParameterMode.IN, type = String.class),
                        @StoredProcedureParameter(name = "step", mode = ParameterMode.IN, type = Long.class),
                        @StoredProcedureParameter(name = "result", mode = ParameterMode.OUT, type = String.class)
                })
)
@Table(name = "v_dim_univ_codemaintanence")
public class CodemaintanenceUniv
{
    @Id
    private String id;

    @Column
    private Long customSort;

    @Column
    private String name;

    @Column
    private String enabled;

    @Column
    private String fullname;

    @Column
    private Date updated;

    @Column
    private String tableen;

    @Column
    private String parentid;

    @Column
    private String type;

    @Column
    private String admindept;

    @Column
    private String code;

    public String getParentid()
    {
        return parentid;
    }

    public void setParentid(String parentid)
    {
        this.parentid = parentid;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Long getCustomSort()
    {
        return customSort;
    }

    public void setCustomSort(Long customSort)
    {
        this.customSort = customSort;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEnabled()
    {
        return enabled;
    }

    public void setEnabled(String enabled)
    {
        this.enabled = enabled;
    }

    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public void setUpdated(Date updated)
    {
        this.updated = updated;
    }

    public String getTableen()
    {
        return tableen;
    }

    public void setTableen(String tableen)
    {
        this.tableen = tableen;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getAdmindept()
    {
        return admindept;
    }

    public void setAdmindept(String admindept)
    {
        this.admindept = admindept;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
