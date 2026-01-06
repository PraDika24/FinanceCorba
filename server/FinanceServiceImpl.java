import FinanceApp.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class FinanceServiceImpl extends FinanceServicePOA {

    public boolean addTransaction(String username, String type, double amount, String note) {
        System.out.println("Add: " + username + " " + type + " " + amount + " " + note);
        return true;
    }

    public String getTransactions(String username) {
        return "Belum ada data untuk " + username;
    }
}
