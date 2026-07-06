// Kelas Utama untuk Binary Search Tree dan Metode Transversal
class BinaryTree {
    Node root;

    public BinaryTree() {
        root = null;
    }

    // ============ INSERT (Binary Search Tree) ============
    // Aturan BST:
    // - Child Kiri harus lebih KECIL dari Parent
    // - Child Kanan harus lebih BESAR dari Parent

    // Metode insert public (panggil dari luar)
    void insert(int data) {
        root = insertRecursive(root, data);
    }

    // Metode insert recursive (helper)
    private Node insertRecursive(Node current, int data) {
        // Jika posisi kosong, buat node baru di sini
        if (current == null) {
            return new Node(data);
        }

        // Jika data lebih KECIL, masuk ke subtree kiri
        if (data < current.data) {
            current.left = insertRecursive(current.left, data);
        }
        // Jika data lebih BESAR, masuk ke subtree kanan
        else if (data > current.data) {
            current.right = insertRecursive(current.right, data);
        }
        // Jika data SAMA, tidak ditambahkan (duplikat tidak diizinkan)

        return current;
    }

    // ============ TAMPILKAN TREE (Piramid Rapi) ============

    void display() {
        if (root == null) {
            System.out.println("(Tree kosong)");
            return;
        }

        int height = getHeight(root);

        // Kumpulkan node per level (BFS)
        java.util.List<java.util.List<Node>> levels = new java.util.ArrayList<>();
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(root);

        for (int lv = 0; lv < height; lv++) {
            int size = queue.size();
            java.util.List<Node> level = new java.util.ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node);
                queue.add(node != null ? node.left : null);
                queue.add(node != null ? node.right : null);
            }
            levels.add(level);
        }

        // Tentukan lebar tiap slot di level terbawah
        int maxBottom = 1 << (height - 1); // 2^(height-1)
        int cellW = 3; // lebar per slot (cukup untuk angka 1-99)

        // Cetak setiap level dengan posisi presisi
        for (int lv = 0; lv < height; lv++) {
            java.util.List<Node> nodes = levels.get(lv);
            int slotW = maxBottom / nodes.size() * cellW; // lebar per node di level ini
            int totalW = maxBottom * cellW; // total lebar piramid

            StringBuilder line = new StringBuilder();

            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                String val = node != null ? String.valueOf(node.data) : "";
                int leftPad = (slotW - val.length()) / 2;

                // Isi spasi hingga posisi nilai
                while (line.length() < i * slotW + leftPad) {
                    line.append(' ');
                }
                line.append(val);
            }

            // Isi sisa spasi
            while (line.length() < totalW) {
                line.append(' ');
            }

            System.out.println(line);
        }
    }

    // Hitung tinggi tree (jumlah level)
    private int getHeight(Node node) {
        if (node == null) return 0;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    // ============ TRANSVERSAL (Traversal) ============

    // 1. Pre-order Traversal (Root -> Left -> Right)
    void printPreOrder(Node node) {
        if (node == null) return;

        System.out.print(node.data + " "); // Cetak Root
        printPreOrder(node.left);          // Rekursif ke Kiri
        printPreOrder(node.right);         // Rekursif ke Kanan
    }

    // 2. In-order Traversal (Left -> Root -> Right)
    void printInOrder(Node node) {
        if (node == null) return;

        printInOrder(node.left);           // Rekursif ke Kiri
        System.out.print(node.data + " "); // Cetak Root
        printInOrder(node.right);          // Rekursif ke Kanan
    }

    // 3. Post-order Traversal (Left -> Right -> Root)
    void printPostOrder(Node node) {
        if (node == null) return;

        printPostOrder(node.left);          // Rekursif ke Kiri
        printPostOrder(node.right);         // Rekursif ke Kanan
        System.out.print(node.data + " ");  // Cetak Root
    }

    // Metode pembantu (wrapper) agar pemanggilan di main lebih rapi
    void preOrder()  {  printPreOrder(root);  }
    void inOrder()   {  printInOrder(root);   }
    void postOrder() {  printPostOrder(root); }

    // Cek apakah tree kosong
    boolean isEmpty() {
        return root == null;
    }
}
