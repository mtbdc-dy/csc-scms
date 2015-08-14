package gov.gwssi.csc.scms.domain.dictionary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZhenghua on 2015/4/23.
 * 抽象代码表的树形数据结构
 */
public class DictTreeJson implements Serializable {

    public List<DictTreeJson> children = new ArrayList<DictTreeJson>();
    private String code;
    private String codePid;
    private String value;
    private boolean valid;

    public static List<DictTreeJson> formatTree(List<DictTreeJson> list){
        DictTreeJson root = new DictTreeJson();
        DictTreeJson node = new DictTreeJson();

        // 拼凑好的json格式的数据
        List<DictTreeJson> treeList = new ArrayList<DictTreeJson>();
        // parentNodes存放所有的父节点
        List<DictTreeJson> parentNodes = new ArrayList<DictTreeJson>();

        if(list != null && list.size()>0){
            root = list.get(0);
            // 循环遍历Oracle树查询的所有节点
            for(int i=1;i<list.size();i++){
                node = list.get(i);
                if(node.getCodePid().equals(root.getCode())){
                    // 为tree root增加子节点
                    parentNodes.add(node);
                    root.getChildren().add(node);
                }else{
                    getChildrenNodes(parentNodes,node);
                    parentNodes.add(node);
                }
            }
        }
        treeList.add(root);
        // 特殊处理,对返回的List去掉根root节点，只取root节点下的所有children
        if(treeList != null && treeList.size()>0){
            DictTreeJson dictTreeJson = treeList.get(0);
            List<DictTreeJson> nodeList = dictTreeJson.getChildren();
            treeList = nodeList;
        }
        return treeList;
    }

    private static void getChildrenNodes(List<DictTreeJson> parentNodes,DictTreeJson node) {
        //循环遍历所有父节点和node进行匹配，确定父子关系
        for (int i = parentNodes.size() - 1; i >= 0; i--) {
            DictTreeJson pNode = parentNodes.get(i);
            //如果是父子关系，为父节点增加子节点，退出for循环
            if (pNode.getCode().equals(node.getCodePid())) {
                pNode.getChildren().add(node) ;
                return;
            } else {
                //如果不是父子关系，删除父节点栈里当前的节点，
                //继续此次循环，直到确定父子关系或不存在退出for循环
                parentNodes.remove(i);
            }
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getCodePid() {
        return codePid;
    }

    public void setCodePid(String codePid) {
        this.codePid = codePid;
    }

    public List<DictTreeJson> getChildren() {
        return children;
    }

    public void setChildren(List<DictTreeJson> children) {
        this.children = children;
    }
}
