import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 09.07.13
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class MoneyTransferRequest extends Request {
    public MoneyTransferRequest(String[] parameters, RequestType type) throws ProcessingException {
        super(parameters, type);
    }

    void executeHelper() throws SQLException, ProcessingException {
        checker.checkUsername(parameters[0]);
        checker.checkUsername(parameters[1]);
        checker.checkSum(parameters[2]);
        int sum = Integer.parseInt(parameters[2]);
        //allright
        PreparedStatement pstmt = PreparedStatements.DEC_BALANCE;
        PreparedStatement pstmt1 = PreparedStatements.INC_BALANCE;
        pstmt.setInt(1, sum);
        pstmt.setString(2, parameters[0]);
        pstmt1.setInt(1, sum);
        pstmt1.setString(2, parameters[1]);
        if (checker.exists(parameters[0]) && checker.exists(parameters[1])) {
            PreparedStatement temp = PreparedStatements.SELECT_ALL;
            temp.setString(1, parameters[0]);
            ResultSet rs = temp.executeQuery();
            rs.first();
            int curr = rs.getInt("balance");
            if (sum > 0) {
                if (curr - sum > 0) {
                    pstmt.execute();
                    pstmt1.execute();
                } else {
                    throw new ProcessingException("Balance can`t be negative");
                }
            } else {
                throw new ProcessingException("Sum can`t be negative");
            }
        } else {
            throw new ProcessingException("Defunct username");
        }
    }
}
