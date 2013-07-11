import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 09.07.13
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
public class ExistenceCheckingRequest extends Request {
    public ExistenceCheckingRequest(String[] parameters, RequestType type) throws ProcessingException {
        super(parameters, type);
    }

    @Override
    void executeHelper() throws SQLException, ProcessingException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public synchronized boolean getResult() throws SQLException {
        PreparedStatements.LOCK_READ.execute();
        PreparedStatement pstmt = PreparedStatements.SELECT;
        pstmt.setString(1, "*");
        pstmt.setString(2, "username='" + parameters[0] + "\'");
        ResultSet rs = pstmt.executeQuery();
        boolean result = rs.next();
        PreparedStatements.UNLOCK.execute();
        return result;
    }
}
