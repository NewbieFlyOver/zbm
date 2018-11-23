package wmq.fly.design.patterns;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 组合模式：结构模式
 * 	组合模式有时又叫部分-整体模式在处理类似树形结构的问题时比较方便。
 * 
 * 使用场景：将多个对象组合在一起进行操作，常用于表示树形结构中，例如二叉树，数等。
 */

class TreeNode{
	
	private String name;
	
	private TreeNode parent;
	
	private Vector<TreeNode> children = new Vector<TreeNode>();
	
	public TreeNode(String name){  
        this.name = name;  
    }  
  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TreeNode getParent() {
		return parent;
	}
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	
	//添加孩子节点
	public void add(TreeNode node) {
		children.add(node);
	}
	
	//删除孩子节点
	public void remove(TreeNode node) {
		children.remove(node);
	}
	
	//获取孩子节点
    public Enumeration<TreeNode> getChildren(){  
        return children.elements();  
    } 
}


class Tree {
	
	public TreeNode root = null;
	
	public Tree(String name) {
		this.root = new TreeNode(name);
	}
}

public class StructureComposite {
	public static void main(String[] args) {
		Tree tree = new Tree("A");  
        TreeNode nodeB = new TreeNode("B");  
        TreeNode nodeC = new TreeNode("C");  
          
        nodeB.add(nodeC);  
        tree.root.add(nodeB);  
        
        System.out.println("build the tree finished!");  
	}
	
}
