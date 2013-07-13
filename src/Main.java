import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 05.07.13
 * Time: 16:51
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {

        try {

            String[] parameters = new String[2];
            parameters[0] = "zarmi";
            parameters[1] = "500";
                Request r = new BalanceModifyRequest(parameters, Request.RequestType.ModifyBalance);
                r.execute();
        } catch (ProcessingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}


