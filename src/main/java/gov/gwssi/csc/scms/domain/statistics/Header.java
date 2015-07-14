package gov.gwssi.csc.scms.domain.statistics;

/**
 * Created by WangZhenghua on 2015/6/11.
 * 返回预览的表头，包括：value，position,merge
 */
public class Header {

    // 表头显示值
    private String value;
    // 坐标位置
    private Position postion;
    // colspan、rowspan值
    private Merge merge;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Position getPostion() {
        return postion;
    }

    public void setPostion(Position postion) {
        this.postion = postion;
    }

    public Merge getMerge() {
        return merge;
    }

    public void setMerge(Merge merge) {
        this.merge = merge;
    }
}
