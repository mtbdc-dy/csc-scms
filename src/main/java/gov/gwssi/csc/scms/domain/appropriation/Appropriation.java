package gov.gwssi.csc.scms.domain.appropriation;

import javax.persistence.*;

/**
 * Created by LiZhiSheng on 2015/9/14.
 */
@Entity
@Table(name = "SCMS_STATS_APPROPRIATION")
@NamedStoredProcedureQueries(value = {
        @NamedStoredProcedureQuery(name = "Appropriation.statistic", procedureName = "P_SCMS_STATS_APPROPRIATION", parameters = {
                @StoredProcedureParameter(name = "fundAttr", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "fundType", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "fundStandard", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "planned", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "projectAttr", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "projectType", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "projectName", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "sameId", mode = ParameterMode.OUT, type = String.class)
        })
})
public class Appropriation {
    //id
    @Id
    private String id;
    //sameId
    private  String sameId;
    //no
    private String no;
    //school
    private String school;
    /**
     * 上学期离校生-人数  和页面一致 只显示5类 本科 硕研 博研 普进 高进 和小计
     */
    //
    private Long col134c;
    //
    private Long col134m;
    //
    private Long col135c;
    //
    private Long col135m;
    //
    private Long col137c;
    //
    private Long col137m;
    //
    private Long col139c;
    //
    private Long col139m;
    //
    private Long col141c;
    //
    private Long col141m;
    //
    private Long subtotal1;
    /**
     * 本学期新入学学生数-人数  和页面一致 只显示5类 本科 硕研 博研 普进 高进 和小计
     */
    //
    private Long col234c;
    //
    private Long col234m;
    //
    private Long col235c;
    //
    private Long col235m;
    //
    private Long col237c;
    //
    private Long col237m;
    //
    private Long col239c;
    //
    private Long col239m;
    //
    private Long col241c;
    //
    private Long col241m;
    //
    private Long subtotal2;
    /**
     * 目前在校生总数-人数  和页面一致 只显示5类 本科 硕研 博研 普进 高进 和小计
     */
    //
    private Long col334c;
    //
    private Long col334m;
    //
    private Long col335c;
    //
    private Long col335m;
    //
    private Long col337c;
    //
    private Long col337m;
    //
    private Long col339c;
    //
    private Long col339m;
    //
    private Long col341c;
    //
    private Long col341m;
    //
    private Long subtotal3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSameId() {
        return sameId;
    }

    public void setSameId(String sameId) {
        this.sameId = sameId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Long getCol134c() {
        return col134c;
    }

    public void setCol134c(Long col134c) {
        this.col134c = col134c;
    }

    public Long getCol134m() {
        return col134m;
    }

    public void setCol134m(Long col134m) {
        this.col134m = col134m;
    }

    public Long getCol135c() {
        return col135c;
    }

    public void setCol135c(Long col135c) {
        this.col135c = col135c;
    }

    public Long getCol135m() {
        return col135m;
    }

    public void setCol135m(Long col135m) {
        this.col135m = col135m;
    }

    public Long getCol137c() {
        return col137c;
    }

    public void setCol137c(Long col137c) {
        this.col137c = col137c;
    }

    public Long getCol137m() {
        return col137m;
    }

    public void setCol137m(Long col137m) {
        this.col137m = col137m;
    }

    public Long getCol139c() {
        return col139c;
    }

    public void setCol139c(Long col139c) {
        this.col139c = col139c;
    }

    public Long getCol139m() {
        return col139m;
    }

    public void setCol139m(Long col139m) {
        this.col139m = col139m;
    }

    public Long getCol141c() {
        return col141c;
    }

    public void setCol141c(Long col141c) {
        this.col141c = col141c;
    }

    public Long getCol141m() {
        return col141m;
    }

    public void setCol141m(Long col141m) {
        this.col141m = col141m;
    }

    public Long getSubtotal1() {
        return subtotal1;
    }

    public void setSubtotal1(Long subtotal1) {
        this.subtotal1 = subtotal1;
    }

    public Long getCol234c() {
        return col234c;
    }

    public void setCol234c(Long col234c) {
        this.col234c = col234c;
    }

    public Long getCol234m() {
        return col234m;
    }

    public void setCol234m(Long col234m) {
        this.col234m = col234m;
    }

    public Long getCol235c() {
        return col235c;
    }

    public void setCol235c(Long col235c) {
        this.col235c = col235c;
    }

    public Long getCol235m() {
        return col235m;
    }

    public void setCol235m(Long col235m) {
        this.col235m = col235m;
    }

    public Long getCol237c() {
        return col237c;
    }

    public void setCol237c(Long col237c) {
        this.col237c = col237c;
    }

    public Long getCol237m() {
        return col237m;
    }

    public void setCol237m(Long col237m) {
        this.col237m = col237m;
    }

    public Long getCol239c() {
        return col239c;
    }

    public void setCol239c(Long col239c) {
        this.col239c = col239c;
    }

    public Long getCol239m() {
        return col239m;
    }

    public void setCol239m(Long col239m) {
        this.col239m = col239m;
    }

    public Long getCol241c() {
        return col241c;
    }

    public void setCol241c(Long col241c) {
        this.col241c = col241c;
    }

    public Long getCol241m() {
        return col241m;
    }

    public void setCol241m(Long col241m) {
        this.col241m = col241m;
    }

    public Long getSubtotal2() {
        return subtotal2;
    }

    public void setSubtotal2(Long subtotal2) {
        this.subtotal2 = subtotal2;
    }

    public Long getCol334c() {
        return col334c;
    }

    public void setCol334c(Long col334c) {
        this.col334c = col334c;
    }

    public Long getCol334m() {
        return col334m;
    }

    public void setCol334m(Long col334m) {
        this.col334m = col334m;
    }

    public Long getCol335c() {
        return col335c;
    }

    public void setCol335c(Long col335c) {
        this.col335c = col335c;
    }

    public Long getCol335m() {
        return col335m;
    }

    public void setCol335m(Long col335m) {
        this.col335m = col335m;
    }

    public Long getCol337c() {
        return col337c;
    }

    public void setCol337c(Long col337c) {
        this.col337c = col337c;
    }

    public Long getCol337m() {
        return col337m;
    }

    public void setCol337m(Long col337m) {
        this.col337m = col337m;
    }

    public Long getCol339c() {
        return col339c;
    }

    public void setCol339c(Long col339c) {
        this.col339c = col339c;
    }

    public Long getCol339m() {
        return col339m;
    }

    public void setCol339m(Long col339m) {
        this.col339m = col339m;
    }

    public Long getCol341c() {
        return col341c;
    }

    public void setCol341c(Long col341c) {
        this.col341c = col341c;
    }

    public Long getCol341m() {
        return col341m;
    }

    public void setCol341m(Long col341m) {
        this.col341m = col341m;
    }

    public Long getSubtotal3() {
        return subtotal3;
    }

    public void setSubtotal3(Long subtotal3) {
        this.subtotal3 = subtotal3;
    }
}
