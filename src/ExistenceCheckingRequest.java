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

    public boolean getResult() throws ProcessingException {
        synchronized (connect) {
            try {
                PreparedStatement pstmt = PreparedStatements.SELECT_ALL;
                pstmt.setString(1, parameters[0]);
                ResultSet rs = pstmt.executeQuery();
                return rs.next();
            } catch (SQLException e) {
                throw new ProcessingException("Database problem");
            }
        }
    }
}
