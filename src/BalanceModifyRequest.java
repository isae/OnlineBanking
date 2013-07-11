import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 09.07.13
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class BalanceModifyRequest extends Request {
    public BalanceModifyRequest(String[] parameters, RequestType type) throws ProcessingException {
        super(parameters, type);
    }

    @Override
    void executeHelper() throws SQLException, ProcessingException {
        checker.checkUsername(parameters[0]);
        checker.checkSum(parameters[1]);
        int sum = Integer.parseInt(parameters[1]);
        //allright
        PreparedStatement pstmt;
        if (sum > 0) {
            pstmt = PreparedStatements.INC_BALANCE;
        } else {
            pstmt = PreparedStatements.DEC_BALANCE;
        }
        pstmt.setInt(1, Math.abs(sum));
        pstmt.setString(2, "username='" + parameters[0] + "\'");
        PreparedStatements.LOCK_READ.execute();
        if (checker.exists(parameters[0])) {
            PreparedStatement temp = PreparedStatements.SELECT;
            temp.setString(1, "balance");
            temp.setString(2, "username='" + parameters[0] + "\'");
            ResultSet rs = temp.executeQuery();
            rs.first();
            int curr = rs.getInt("balance");
            if (curr - sum > 0) {
                pstmt.execute();
                PreparedStatements.UNLOCK.execute();
            } else {
                PreparedStatements.UNLOCK.execute();
                throw new ProcessingException("Balance can`t be negative");
            }
        } else {
            PreparedStatements.UNLOCK.execute();
            throw new ProcessingException("Defunct username");
        }
    }
}
