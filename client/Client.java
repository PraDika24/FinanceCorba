import FinanceApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            AuthService auth = AuthServiceHelper.narrow(
                ncRef.resolve_str("AuthService")
            );

            FinanceService finance = FinanceServiceHelper.narrow(
                ncRef.resolve_str("FinanceService")
            );

            Scanner sc = new Scanner(System.in);

            System.out.println("=== CLIENT CATATAN KEUANGAN CORBA ===");

            System.out.print("Username: ");
            String user = sc.nextLine();

            System.out.print("Password: ");
            String pass = sc.nextLine();

            boolean ok = auth.login(user, pass);

            if (!ok) {
                System.out.println("Login gagal!");
                return;
            }

            System.out.println("Login sukses!");

            while (true) {
                System.out.println("\n1. Tambah Transaksi");
                System.out.println("2. Lihat Transaksi");
                System.out.println("3. Keluar");
                System.out.print("Pilih: ");

                int pilih = Integer.parseInt(sc.nextLine());

                if (pilih == 1) {
                    System.out.print("Type (IN/OUT): ");
                    String type = sc.nextLine();

                    System.out.print("Jumlah: ");
                    double amount = Double.parseDouble(sc.nextLine());

                    System.out.print("Catatan: ");
                    String note = sc.nextLine();

                    boolean res = finance.addTransaction(user, type, amount, note);
                    System.out.println(res ? "Berhasil!" : "Gagal!");
                }
                else if (pilih == 2) {
                    String data = finance.getTransactions(user);
                    System.out.println("Data Transaksi:");
                    System.out.println(data);
                }
                else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
