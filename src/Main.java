import java.util.Scanner;

// Program CLI Binary Search Tree (BST)
// Aturan BST:
//   - Binary (Biner): Setiap Parent maksimal memiliki 2 Child (kiri dan kanan)
//   - Search (Pencarian): Child Kiri < Parent < Child Kanan

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Insert Node");
            System.out.println("2. Tampilkan Tree");
            System.out.println("3. Transversal Tree (In-order)");
            System.out.println("4. Transversal Pre-order");
            System.out.println("5. Transversal Post-order");
            System.out.println("6. Keluar");
            System.out.print("Pilihan: ");

            // Validasi input angka
            while (!scanner.hasNextInt()) {
                System.out.println("Input tidak valid! Masukkan angka 1-6.");
                scanner.next();
                System.out.print("Pilihan: ");
            }

            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan data: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Input tidak valid! Masukkan angka.");
                        scanner.next();
                        System.out.print("Masukkan data: ");
                    }
                    int data = scanner.nextInt();
                    tree.insert(data);
                    System.out.println("Node " + data + " berhasil ditambahkan!");
                    break;

                case 2:
                    System.out.println("--- STRUKTUR TREE ---");
                    tree.display();
                    break;

                case 3:
                    if (tree.isEmpty()) {
                        System.out.println("Tree masih kosong!");
                    } else {
                        System.out.print("In-order Traversal   : ");
                        tree.inOrder();
                        System.out.println();
                    }
                    break;

                case 4:
                    if (tree.isEmpty()) {
                        System.out.println("Tree masih kosong!");
                    } else {
                        System.out.print("Pre-order Traversal  : ");
                        tree.preOrder();
                        System.out.println();
                    }
                    break;

                case 5:
                    if (tree.isEmpty()) {
                        System.out.println("Tree masih kosong!");
                    } else {
                        System.out.print("Post-order Traversal : ");
                        tree.postOrder();
                        System.out.println();
                    }
                    break;

                case 6:
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        } while (pilihan != 6);

        scanner.close();
    }
}
