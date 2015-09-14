package gov.gwssi.csc.scms.domain.appropriation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by LiZhiSheng on 2015/9/14.
 */
@Entity
@Table(name = "SCMS_STATS_APPROPRIATION")
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
    private long col134c;
    //
    private long col134m;
    //
    private long col135c;
    //
    private long col135m;
    //
    private long col137c;
    //
    private long col137m;
    //
    private long col139c;
    //
    private long col139m;
    //
    private long col141c;
    //
    private long col141m;
    //
    private long subtotal1;
    /**
     * 本学期新入学学生数-人数  和页面一致 只显示5类 本科 硕研 博研 普进 高进 和小计
     */
    //
    private long col234c;
    //
    private long col234m;
    //
    private long col235c;
    //
    private long col235m;
    //
    private long col237c;
    //
    private long col237m;
    //
    private long col239c;
    //
    private long col239m;
    //
    private long col241c;
    //
    private long col241m;
    //
    private long subtotal2;
    /**
     * 目前在校生总数-人数  和页面一致 只显示5类 本科 硕研 博研 普进 高进 和小计
     */
    //
    private long col334c;
    //
    private long col334m;
    //
    private long col335c;
    //
    private long col335m;
    //
    private long col337c;
    //
    private long col337m;
    //
    private long col339c;
    //
    private long col339m;
    //
    private long col341c;
    //
    private long col341m;
    //
    private long subtotal3;

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

    public long getCol134c() {
        return col134c;
    }

    public void setCol134c(long col134c) {
        this.col134c = col134c;
    }

    public long getCol134m() {
        return col134m;
    }

    public void setCol134m(long col134m) {
        this.col134m = col134m;
    }

    public long getCol135c() {
        return col135c;
    }

    public void setCol135c(long col135c) {
        this.col135c = col135c;
    }

    public long getCol135m() {
        return col135m;
    }

    public void setCol135m(long col135m) {
        this.col135m = col135m;
    }

    public long getCol137c() {
        return col137c;
    }

    public void setCol137c(long col137c) {
        this.col137c = col137c;
    }

    public long getCol137m() {
        return col137m;
    }

    public void setCol137m(long col137m) {
        this.col137m = col137m;
    }

    public long getCol139c() {
        return col139c;
    }

    public void setCol139c(long col139c) {
        this.col139c = col139c;
    }

    public long getCol139m() {
        return col139m;
    }

    public void setCol139m(long col139m) {
        this.col139m = col139m;
    }

    public long getCol141c() {
        return col141c;
    }

    public void setCol141c(long col141c) {
        this.col141c = col141c;
    }

    public long getCol141m() {
        return col141m;
    }

    public void setCol141m(long col141m) {
        this.col141m = col141m;
    }

    public long getSubtotal1() {
        return subtotal1;
    }

    public void setSubtotal1(long subtotal1) {
        this.subtotal1 = subtotal1;
    }

    public long getCol234c() {
        return col234c;
    }

    public void setCol234c(long col234c) {
        this.col234c = col234c;
    }

    public long getCol234m() {
        return col234m;
    }

    public void setCol234m(long col234m) {
        this.col234m = col234m;
    }

    public long getCol235c() {
        return col235c;
    }

    public void setCol235c(long col235c) {
        this.col235c = col235c;
    }

    public long getCol235m() {
        return col235m;
    }

    public void setCol235m(long col235m) {
        this.col235m = col235m;
    }

    public long getCol237c() {
        return col237c;
    }

    public void setCol237c(long col237c) {
        this.col237c = col237c;
    }

    public long getCol237m() {
        return col237m;
    }

    public void setCol237m(long col237m) {
        this.col237m = col237m;
    }

    public long getCol239c() {
        return col239c;
    }

    public void setCol239c(long col239c) {
        this.col239c = col239c;
    }

    public long getCol239m() {
        return col239m;
    }

    public void setCol239m(long col239m) {
        this.col239m = col239m;
    }

    public long getCol241c() {
        return col241c;
    }

    public void setCol241c(long col241c) {
        this.col241c = col241c;
    }

    public long getCol241m() {
        return col241m;
    }

    public void setCol241m(long col241m) {
        this.col241m = col241m;
    }

    public long getSubtotal2() {
        return subtotal2;
    }

    public void setSubtotal2(long subtotal2) {
        this.subtotal2 = subtotal2;
    }

    public long getCol334c() {
        return col334c;
    }

    public void setCol334c(long col334c) {
        this.col334c = col334c;
    }

    public long getCol334m() {
        return col334m;
    }

    public void setCol334m(long col334m) {
        this.col334m = col334m;
    }

    public long getCol335c() {
        return col335c;
    }

    public void setCol335c(long col335c) {
        this.col335c = col335c;
    }

    public long getCol335m() {
        return col335m;
    }

    public void setCol335m(long col335m) {
        this.col335m = col335m;
    }

    public long getCol337c() {
        return col337c;
    }

    public void setCol337c(long col337c) {
        this.col337c = col337c;
    }

    public long getCol337m() {
        return col337m;
    }

    public void setCol337m(long col337m) {
        this.col337m = col337m;
    }

    public long getCol339c() {
        return col339c;
    }

    public void setCol339c(long col339c) {
        this.col339c = col339c;
    }

    public long getCol339m() {
        return col339m;
    }

    public void setCol339m(long col339m) {
        this.col339m = col339m;
    }

    public long getCol341c() {
        return col341c;
    }

    public void setCol341c(long col341c) {
        this.col341c = col341c;
    }

    public long getCol341m() {
        return col341m;
    }

    public void setCol341m(long col341m) {
        this.col341m = col341m;
    }

    public long getSubtotal3() {
        return subtotal3;
    }

    public void setSubtotal3(long subtotal3) {
        this.subtotal3 = subtotal3;
    }
}
