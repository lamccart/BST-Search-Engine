import java.util.Iterator;
import java.util.LinkedList;

public class BSTree<T extends Comparable<? super T>> implements Iterable {

    private int nelems;
    private BSTNode root;

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.dataList = new LinkedList<>();
        }

        public T getKey() {
            return key;
        }

        public BSTNode getLeft() {
            return left;
        }

        public BSTNode getRight() {
            return right;
        }

        public LinkedList<T> getDataList() {
            return dataList;
        }

        public void setLeft(BSTNode newleft) {
            left = newleft;
        }

        public void setRight(BSTNode newright) {
            right = newright;
        }

        public void setDataList(LinkedList<T> newData) {
            dataList = newData;
        }

        public void addNewInfo(T data) {
            dataList.add(data);
        }

        public boolean removeInfo(T data) {
            if(dataList.contains(data)) {
                dataList.remove(data);
                return true;
            }else{
                return false;
            }
        }
    }

    public BSTree() {
        root = null;
        nelems = 0;
    }

    public BSTNode getRoot() {
        if(root != null){
            return root;
        }else{
            return null;
        }
    }

    public int getSize() {
        return nelems;
    }

    public void insert(T key) throws NullPointerException{
        if(key == null){
            throw new NullPointerException();
        }else{
            BSTNode node = new BSTNode(null, null, key);
            if(root == null){
                root = node;
            }else{
                BSTNode currentNode = root;
                while (currentNode != null){
                    if ( node.getKey().compareTo(currentNode.getKey()) < 0) {
                        if (currentNode.getLeft() == null){
                            currentNode.setLeft(node);
                            currentNode = null;
                        }else{
                            currentNode = currentNode.getLeft();
                        }
                    }else{
                        if (currentNode.getRight() == null){
                            currentNode.setRight(node);
                            currentNode = null;
                        }else{
                            currentNode = currentNode.getRight();
                        }
                    }
                }
            }
            node.setRight(null);
            node.setLeft(null);
        }
    }

    private BSTNode traverseTree(BSTNode currentNode, T key){
        if (currentNode == null || key.equals(currentNode.getKey())){
            return currentNode;
        } else if (key.compareTo(currentNode.getKey()) < 0) {
            return traverseTree(currentNode.getLeft(), key);
        } else {
            return traverseTree(currentNode.getRight(), key);
        }
    }

    public boolean findKey(T key) throws NullPointerException{
        if(key == null){
            throw new NullPointerException();
        }else{
            BSTNode currentNode = root;
            return traverseTree(currentNode, key) != null;
        }
    }

    public void insertData(T key, T data) throws NullPointerException, IllegalArgumentException{
        if(key == null || data == null){
            throw new NullPointerException();
        }else if(!findKey(key)){
            throw new IllegalArgumentException();
        }else{
            BSTNode currentNode = root;
            BSTNode foundNode = traverseTree(currentNode,key);
            if(foundNode != null){
                foundNode.addNewInfo(data);
            }
        }
    }

    public LinkedList<T> findDataList(T key) throws NullPointerException, IllegalArgumentException{
        if(key == null){
            throw new NullPointerException();
        }else{
            BSTNode currentNode = root;
            BSTNode foundNode = traverseTree(currentNode, key);
            if(foundNode != null){
                return foundNode.getDataList();
            }else{
                throw new IllegalArgumentException();
            }
        }
    }

    private int findNodeHeight(BSTNode node){
        if(node == null) {
            return -1;
        }
        return Math.max(findNodeHeight(node.getLeft()), findNodeHeight(node.getRight()))+1;
    }

    public int findHeight() {
        return findNodeHeight(root);
    }

    private int getLeafCount(BSTNode node){
        if (node == null)
            return 0;
        if (node.getLeft() == null && node.getRight() == null)
            return 1;
        else
            return getLeafCount(node.left) + getLeafCount(node.right);
    }

    public int leafCount() {
        return getLeafCount(root);
    }

    //******************ITERATOR STARTS HERE!!!********************************
    public class BSTree_Iterator implements Iterator<T> {

        public BSTree_Iterator() {
            //TODO
        }

        public boolean hasNext() {
            //TODO
            return false;
        }

        public T next() {
            //TODO
            return null;
        }
    }

    public Iterator<T> iterator() {
        //TODO
        return null;
    }
}
