import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 09.07.13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
public class UserDeletingRequest extends Request {
    public UserDeletingRequest(String[] parameters, RequestType type) throws ProcessingException {
        super(parameters, type);
    }

    @Override
    void executeHelper() throws SQLException, ProcessingException {
        checker.checkUsername(parameters[0]);
        //allright
        PreparedStatement pstmt = PreparedStatements.DELETE_USER;
        pstmt.setString(1, parameters[0]);
        if (checker.exists(parameters[0])) {
            pstmt.execute();
        } else {
            throw new ProcessingException("Defunct username");
        }

    }
}
