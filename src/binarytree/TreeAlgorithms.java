package binarytree;

/**
 * Created by kingfernandez on 10/25/17.
 */
public class TreeAlgorithms {

    public Node mergeTrees(Node n1, Node n2) {

        if(n1 == null && n2 == null)
            return null;

        int val = (n1 == null) ? 0 : n1.val;
        val += (n2 == null) ? 0 : n2.val;

        Node n = (n1 == null) ? n2 : n1;
        n.val = val;

        n.left = mergeTrees(n1 == null ? null : n1.left, n2 == null ? null : n2.left);
        n.right = mergeTrees(n1 == null ? null : n1.right, n2 == null ? null : n2.right);
        return n;
    }


    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(7);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(9);
    }


}
