import FinanceApp.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;

public class Server {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(
                orb.resolve_initial_references("RootPOA")
            );
            rootpoa.the_POAManager().activate();

            AuthServiceImpl authImpl = new AuthServiceImpl();
            FinanceServiceImpl financeImpl = new FinanceServiceImpl();

            org.omg.CORBA.Object ref1 = rootpoa.servant_to_reference(authImpl);
            AuthService authRef = AuthServiceHelper.narrow(ref1);

            org.omg.CORBA.Object ref2 = rootpoa.servant_to_reference(financeImpl);
            FinanceService financeRef = FinanceServiceHelper.narrow(ref2);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            ncRef.rebind(ncRef.to_name("AuthService"), authRef);
            ncRef.rebind(ncRef.to_name("FinanceService"), financeRef);

            System.out.println("Server CORBA siap dijalankan...");
            orb.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
