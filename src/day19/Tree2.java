package day19;

import java.util.ArrayList;
import java.util.List;

public class Tree2 {
}


class BinaryTree {
    private BinaryTree lChild;
    private BinaryTree rChild;
    private BinaryTree root;
    private Object data;
    private List<BinaryTree> datas;

    public BinaryTree(BinaryTree lChild, BinaryTree rChild, Object data) {
        this.lChild = lChild;
        this.rChild = rChild;
        this.data = data;
    }

    public BinaryTree(Object data) {
        this(null, null, data);
    }

    public BinaryTree() {
    }

    public void createTree(Object[] objs) {
        datas = new ArrayList<BinaryTree>();
        for (Object object : objs) {
            datas.add(new BinaryTree(object));
        }
        root = datas.get(0);//将第一个作为根节点
        for (int i = 0; i < objs.length / 2; i++) {
            datas.get(i).lChild = datas.get(i * 2 + 1);
            if (i * 2 + 2 < datas.size()) {//避免偶数的时候 下标越界
                datas.get(i).rChild = datas.get(i * 2 + 2);
            }
        }
    }

    //先序遍历
    public void preorder(BinaryTree root) {
        if (root != null) {
            visit(root.getData());
            preorder(root.lChild);
            preorder(root.rChild);
        }

    }

    //中序遍历
    public void inorder(BinaryTree root) {
        if (root != null) {
            inorder(root.lChild);
            visit(root.getData());
            inorder(root.rChild);
        }

    }

    //后序遍历
    public void afterorder(BinaryTree root) {
        if (root != null) {
            afterorder(root.lChild);
            afterorder(root.rChild);
            visit(root.getData());
        }

    }

    private void visit(Object obj) {
        System.out.print(obj + " ");
    }

    public Object getData() {
        return data;
    }

    public BinaryTree getRoot() {
        return root;
    }

}