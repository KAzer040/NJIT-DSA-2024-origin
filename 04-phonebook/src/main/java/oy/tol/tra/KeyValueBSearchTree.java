package oy.tol.tra;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    // This is the BST implementation, KeyValueHashTable has the hash table
    // implementation

    private TreeNode<K, V> root;
    private int count = 0;
    private int maxTreeDepth = 0;

    @Override
    public Type getType() {
        return Type.BST;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String getStatus() {
        String toReturn = "Tree has max depth of " + maxTreeDepth + ".\n";
        toReturn += "Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n";
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        if (root != null) {
            root.accept(visitor);
            toReturn += "Min path height to bottom: " + visitor.minHeight + "\n";
            toReturn += "Max path height to bottom: " + visitor.maxHeight + "\n";
            toReturn += "Ideal height if balanced: " + Math.ceil(Math.log(count)) + "\n";
        } else {
            toReturn += "Tree is empty.\n";
        }
        return toReturn;
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        if (root == null) {
            root = new TreeNode<>(key, value);
            count++;
        } else {
            int result = root.insert(key, value, key.hashCode());
            if (result == 1) {
                count++;
            }
        }
        return true;
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        if (root != null) {
            return root.find(key, key.hashCode());
        }
        return null; // Return null if the tree is empty
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // No need to implement for BST
    }

    @Override
    public Pair<K, V>[] toSortedArray() {
        if (root != null) {
            TreeToArrayVisitor<K, V> visitor = new TreeToArrayVisitor<>(count);
            root.accept(visitor);
            Pair<K, V>[] sorted = visitor.getArray();
            Algorithms.fastSort(sorted);
            return sorted;
        }
        return null; // Return null if the tree is empty
    }

    @Override
    public void compress() throws OutOfMemoryError {
        // Nothing to do here, since BST does not use extra space like array based
        // structures.
    }
}
