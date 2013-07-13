import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Xottab
 * Date: 11.07.13
 * Time: 19:25
 */
public class InterestCastingRequest extends Request {
    public InterestCastingRequest(String[] parameters, RequestType type) throws ProcessingException {
        super(parameters, type);
    }


    @Override
    void executeHelper() throws SQLException, ProcessingException {
        checker.checkUsername(parameters[0]);
        //allright;
        if (checker.exists(parameters[0])) {
            PreparedStatement temp = PreparedStatements.SELECT_ALL;
            temp.setString(1, parameters[0]);
            ResultSet rs = temp.executeQuery();
            rs.first();
            int curr = rs.getInt("balance");
            PreparedStatement pstmt = PreparedStatements.INC_BALANCE;
            if (curr > 100000) {
                pstmt.setInt(1, (int) Math.round(Math.floor(curr * 0.05)));
                pstmt.setString(2, parameters[0]);
                pstmt.execute();
            } else if (curr > 10000) {
                pstmt.setInt(1, (int) Math.round(Math.floor(curr * 0.1)));
                pstmt.setString(2, parameters[0]);
                pstmt.execute();
            }
        } else {
            throw new ProcessingException("Defunct username");
        }
    }
}
