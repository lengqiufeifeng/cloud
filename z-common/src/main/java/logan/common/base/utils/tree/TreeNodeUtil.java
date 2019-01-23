package logan.common.base.utils.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeUtil {
    private static String TOP_ORG_CODE = "";
    private static String EASYUI_TREEGRID_CLOSED = "closed";

    /**
     * 获取父节点
     *
     * @param treeList
     * @return
     */
    public final static List<TreeBM> getfatherNode(List<TreeBM> treeList) {
        List<TreeBM> newTreeList = new ArrayList<TreeBM>();
        for (TreeBM tree : treeList) {
            if (TOP_ORG_CODE.equals(tree.getPid()) || tree.getPid() == null) {
                //获取父节点下的子节点
                tree.setChildren(getChildrenNode(tree.getTextCode(), treeList));
                //tree.setId(tree.getTextCode());
                //tree.setState(EASYUI_TREEGRID_CLOSED);
                newTreeList.add(tree);
            }
        }
        return newTreeList;
    }

    /**
     * 获取子节点
     *
     * @param pid
     * @param treeList
     * @return
     */
    private final static List<TreeBM> getChildrenNode(String pid, List<TreeBM> treeList) {
        List<TreeBM> newTreeList = new ArrayList<TreeBM>();
        for (TreeBM tree : treeList) {
            if (TOP_ORG_CODE.equals(tree.getPid()))
                continue;
            //这是一个子节点
            if (pid.equals(tree.getPid()) || pid == null) {
                //递归获取子节点下的子节点
                tree.setChildren(getChildrenNode(tree.getTextCode(), treeList));
                //tree.setPid(tree.getTextCode());
                newTreeList.add(tree);
            }
        }
        return newTreeList;
    }
}
