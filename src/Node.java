// Kelas untuk merepresentasikan setiap simpul (Node) pada pohon
class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}
