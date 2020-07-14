package day19;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Tree1 {

    public static void main(String[] agrs){
        Tree tree = new Tree();
        int[] a = new int[]{1,2,3,'#','#',4,'#','#',5,6,'#','#','#' };
        tree.root=tree.CreateBTree(a);
        System.out.print("先序遍历：");
        tree.prevOrder(tree.root);
        System.out.print("\n中序遍历：");
        tree.inOrder(tree.root);
        System.out.print("\n后序遍历：");
        tree.postOrder(tree.root);
        System.out.println();

        System.out.println("tree Leaf Num:"+tree.getLeaf(tree.root));
        System.out.println("K=2 num:"+tree.getNodeKNum(tree.root,2));
        System.out.println("tree height:"+tree.getHeight());

        System.out.println("3 find:"+tree.findNode(tree.root,3).getData());
        System.out.println("1 find:"+tree.findNode(tree.root,1).getData());
        System.out.println("6 find:"+tree.findNode(tree.root,6).getData());
        System.out.println("7 find:"+tree.findNode(tree.root,7));

        System.out.println("6 paren :"+tree.getParent(tree.root,6).getData());
        System.out.println("3 paren :"+tree.getParent(tree.root,3).getData());
        System.out.println("5 paren :"+tree.getParent(tree.root,5).getData());
        System.out.println("1 paren :"+tree.getParent(tree.root,1));
        System.out.print("层序遍历：");
        tree.BTreeLevelOrder();
        System.out.println();

        System.out.println("the tree is complete?  "+tree.isCompleteBTree());

    }
}



class Node{
    private Node left;
    private Node right;
    private int data;

    public Node(int data){
        this.data = data;
    }

    public void setLChild(Node left){
        this.left = left;
    }

    public void setRChild(Node right){
        this.right = right;
    }

    public void setData(int data){
        this.data = data;
    }

    public int getData(){
        return this.data;
    }
    public Node getLChild(){
        return this.left;
    }
    public Node getRChild(){
        return this.right;
    }
}



//二叉树树类
class Tree {

    public Node root;   //有一个根节点
    public static int index;

    public Node CreateBTree(int[] a) {
        Node root = null;
        if (a[index] != '#') {
            root = new Node(a[index]);
            index++;
            root.setLChild(CreateBTree(a));
            index++;
            root.setRChild(CreateBTree(a));
        }
        return root;

    }

    //先序遍历
    public void prevOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getData() + ",");
        prevOrder(root.getLChild());
        prevOrder(root.getRChild());
    }

    //中序遍历

    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.getLChild());
        System.out.print(root.getData() + ",");
        inOrder(root.getRChild());
    }

    //后序遍历
    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.getLChild());
        postOrder(root.getRChild());
        System.out.print(root.getData() + ",");
    }

    //获得高度
    public int getHeight() {
        Node cur = this.root;
        int height = 0;
        while (cur != null) {
            cur = cur.getLChild();
            height++;
        }
        return height;
    }

    //获得叶子数
    public int getLeaf(Node root) {
        if (root == null) {
            return 0;
        } else if (root.getLChild() == null && root.getLChild() == null) {
            System.out.println("Leaf:" + root.getData());
            return 1;
        }
        return getLeaf(root.getLChild()) + getLeaf(root.getRChild());
    }

    //获得第K层节点数
    public int getNodeKNum(Node root, int k) {
        if (k == 1) {
            if (root == null)
                return 0;
            System.out.println("K Node:" + root.getData());
            return 1;
        }
        return getNodeKNum(root.getLChild(), k - 1) + getNodeKNum(root.getRChild(), k - 1);
    }

    //查找某个节点
    public Node findNode(Node root, int x) {
        if (root == null) {
            return null;
        } else if (root.getData() == x) {
            return root;
        }
        Node leftNode = findNode(root.getLChild(), x);
        if (null != leftNode)
            return leftNode;
        Node rightNode = findNode(root.getRChild(), x);
        if (null != rightNode)
            return rightNode;
        return null;

    }

    //返回某节点的父节点
    public Node getParent(Node root, int x) {
        if (root == null)
            return null;
        Node childL = root.getLChild();
        Node childR = root.getRChild();
        if (childL != null && childL.getData() == x)
            return root;
        if (childR != null && childR.getData() == x)
            return root;
        Node parentL = getParent(root.getLChild(), x);
        if (parentL != null)
            return parentL;
        Node parentR = getParent(root.getRChild(), x);
        if (parentR != null)
            return parentR;
        return null;

    }

    //层次遍历，用到队列
    public void BTreeLevelOrder(){
        Node root = this.root;
        Queue<Node> queue = new LinkedList<Node>();
        LinkedList<Node> list = new LinkedList<Node>();
        queue.offer(root);
        while(!queue.isEmpty()){
            Node pre = queue.poll();
            list.add(pre);
            if(pre.getLChild()!=null)
                queue.offer(pre.getLChild());
            if(pre.getRChild()!=null)
                queue.offer(pre.getRChild());
        }
        Iterator<Node> it = list.iterator();
        while(it.hasNext()){
            Node cur = (Node)it.next();
            System.out.print(cur.getData()+", ");
        }

    }


    //判断一棵树是否是完全二叉树（层次遍历的变形）
    public boolean isCompleteBTree(){
        Node root = this.root;
        Queue <Node> queue = new LinkedList<Node>();
        queue.offer(root);

        while(!queue.isEmpty()){
            Node pre = queue.poll();
            if(pre==null)
                break;
            queue.offer(pre.getLChild());
            queue.offer(pre.getRChild());

        }
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur!=null)
                return false;
        }
        return true;

    }





}