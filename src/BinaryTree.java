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

    // ============ SEARCH (Cari Node) ============

    boolean search(int data) {
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(Node current, int data) {
        if (current == null) return false;
        if (data == current.data) return true;
        if (data < current.data) return searchRecursive(current.left, data);
        return searchRecursive(current.right, data);
    }

    // ============ DELETE (Hapus Node) ============
    // Ada 3 kemungkinan saat menghapus node:
    // 1. Node LEAF (tidak punya anak)      → hapus langsung
    // 2. Node punya 1 CHILD                → ganti node dengan child-nya
    // 3. Node punya 2 CHILDREN             → cari inorder successor,
    //                                        ganti nilai, hapus successor

    void delete(int data) {
        // Cari dulu apakah data ada di tree
        if (!search(data)) {
            return;
        }
        root = deleteRecursive(root, data);
    }

    private Node deleteRecursive(Node current, int data) {
        if (current == null) return null;

        if (data < current.data) {
            // Data ada di subtree kiri
            current.left = deleteRecursive(current.left, data);
        } else if (data > current.data) {
            // Data ada di subtree kanan
            current.right = deleteRecursive(current.right, data);
        } else {
            // Node ditemukan! Sekarang hapus.
            // Case 1 & 2: Leaf atau 1 child
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;

            // Case 3: 2 children
            // Cari inorder successor (nilai terkecil di subtree kanan)
            int successorValue = findMin(current.right);
            current.data = successorValue; // ganti nilai
            // Hapus successor dari subtree kanan
            current.right = deleteRecursive(current.right, successorValue);
        }

        return current;
    }

    // Cari nilai terkecil dalam subtree (untuk inorder successor)
    private int findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    // ============ EDIT (Ubah Nilai Node) ============
    // Caranya: cari node lama → hapus → insert node baru
    // Ini yang paling aman agar BST property tetap terjaga.

    boolean edit(int oldData, int newData) {
        if (!search(oldData)) return false; // data lama tidak ditemukan
        if (oldData == newData) return true; // sama saja, tidak perlu berubah
        if (search(newData)) return false; // data baru sudah ada (duplikat)

        delete(oldData);
        insert(newData);
        return true;
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
