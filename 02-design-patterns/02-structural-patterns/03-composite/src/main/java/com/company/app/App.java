package com.company.app;
import java.util.ArrayList;
import java.util.List;
public class App
{
    static class TreeNode{
        private List<TreeNode> children = new ArrayList<TreeNode>();
        public String Content;
        public TreeNode(String child){ this.Content = child; }
        public TreeNode add(String child){
            TreeNode newNode = new TreeNode(child);
            children.add(newNode);
            return newNode;
        }
        public void remove(String child){
            for(TreeNode treeNode : children){
                if(treeNode.Content.compareTo(child) == 0){
                    children.remove(treeNode);
                    return;
                }
            }
        }
        public List<TreeNode> getChildren() { return children; }
        public void display(TreeNode node, int indentation){
            String line = new String(new char[indentation]).replace("\0", "-");
            System.out.format("%s %s\n", line, node.Content);
            node.getChildren().forEach(n -> display(n, indentation + 1));
        }
    }
    public static void main( String[] args )
    {
        TreeNode root = new TreeNode("Picture");
        root.add("Red Line");
        root.add("Blue Circle");
        root.add("Green Box");
        TreeNode branch = root.add("Two Circles");
        branch.add("Black Circle");
        branch.add("White Circle");
        String shape = "Yellow Line";
        root.add(shape);
        root.remove(shape);
        root.add(shape);
        root.display(root, 1);
    }
}
