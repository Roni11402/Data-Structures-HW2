import com.sun.source.tree.Tree;

/**
 * @param <T> The type of the satellite data of the elements in the data structure.
 */
public class MyFirstDataStructure<T> {
    private MyAVLTree tree;
    private TreeNode maximum;
    private TreeNode first;
    private TreeNode last;

    /***
     * This function is the Init function.
     * @param N The maximum number of elements in the data structure at each time.
     */
    public MyFirstDataStructure(int N) {
        this.tree = new MyAVLTree();
        this.maximum = null;
        this.first = null;
        this.last = null;

    }

    public void insert(Element<T> x) {
        TreeNode newX = new TreeNode(x.key(), x.satelliteData());
        if (this.first == null) {
            this.first = newX;
        }
        if (this.maximum == null || newX.key() > this.maximum.key()) {
            this.maximum = newX;
        }
        if (last != null) {
            this.last.setNext(newX);
            newX.setPrev(last);
        }
        this.last = newX;
        tree.insert(newX);
    }

    public void findAndRemove(int k) {
        TreeNode foundKey = tree.search(k);
        if (foundKey != null) {
            if (foundKey.getPrev() != null) {
                foundKey.getPrev().setNext(foundKey.getNext());
            } else {
                this.first = foundKey.getNext();
            }
            if (foundKey.getNext() != null) {
                foundKey.getNext().setPrev(foundKey.getPrev());
            } else {
                this.last = foundKey.getPrev();
            }
            tree.delete(foundKey);
            if (foundKey.equals(maximum)) {
                TreeNode currNode = tree.root();
                if (currNode != null) {
                    while (currNode.getRight() != null) {
                        currNode = currNode.getRight();
                    }
                    this.maximum = currNode;
                } else {
                    this.maximum = null;
                }
            }
        }
    }

    public Element<T> maximum() {
        return this.maximum;
    }

    public Element<T> first() {
        return this.first;
    }

    public Element<T> last() {
        return this.last;
    }

}
