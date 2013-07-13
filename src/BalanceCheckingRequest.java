import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 09.07.13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
public class BalanceCheckingRequest extends Request {
    public BalanceCheckingRequest(String[] parameters, RequestType type) throws ProcessingException {
        super(parameters, type);
    }

    @Override
    void executeHelper() throws SQLException, ProcessingException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public synchronized int getResult() throws ProcessingException {
        try {
            checker.checkUsername(parameters[0]);
            //allright
            int result;
            PreparedStatement temp = PreparedStatements.SELECT_ALL;
            temp.setString(1, parameters[0]);
            ResultSet rs = temp.executeQuery();
            if (rs.next()) {
                rs.first();
                result = rs.getInt("balance");
            } else {
                throw new ProcessingException("Defunct username");
            }

            return result;
        } catch (SQLException e) {
            throw new ProcessingException("Database problem");
        }
    }
}
