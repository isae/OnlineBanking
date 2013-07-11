import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 09.07.13
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class UserAddingRequest extends Request {
    public UserAddingRequest(String[] parameters, Request.RequestType type) throws ProcessingException {
        super(parameters, type);
    }

    @Override
    void executeHelper() throws SQLException, ProcessingException {
        checker.checkUsername(parameters[0]);
        checker.checkPassword(parameters[1]);
        //allright
        PreparedStatement pstmt = PreparedStatements.ADD_USER;
        pstmt.setString(1, parameters[0]);
        pstmt.setInt(2, Integer.parseInt(parameters[1]));
        PreparedStatements.LOCK_READ.execute();
        if (checker.exists(parameters[0])) {
            pstmt.execute();
            PreparedStatements.UNLOCK.execute();
        } else {
            PreparedStatements.UNLOCK.execute();
            throw new ProcessingException("Defunct username");
        }

    }
}
