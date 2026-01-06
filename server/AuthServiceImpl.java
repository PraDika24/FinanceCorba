import FinanceApp.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class AuthServiceImpl extends AuthServicePOA {

    public boolean register(String username, String password) {
        System.out.println("Register: " + username);
        return true;
    }

    public boolean login(String username, String password) {
        System.out.println("Login: " + username);
        return true;
    }
}
