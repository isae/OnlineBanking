import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 09.07.13
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
public class GettingAllRequest extends Request {
    public GettingAllRequest(String[] parameters, RequestType type) throws ProcessingException {
        super(parameters, type);
    }

    @Override
    void executeHelper() throws SQLException {
        PreparedStatement statement = connect.prepareStatement("SELECT * FROM users");
    }

    synchronized String[] getResults() throws SQLException {
        PreparedStatements.LOCK_READ.execute();
        Statement statement = connect.createStatement();
        int size = statement.executeUpdate("SELECT COUNT(*) FROM users");
        PreparedStatement pstmt = PreparedStatements.SELECT;
        pstmt.setString(1, "*");
        pstmt.setString(2, "");
        ResultSet rs = pstmt.executeQuery();
        rs.first();
        String[] result = new String[size];
        int i = 0;
        while (rs.next()) {
            result[i] = rs.getString("username");
            i++;
        }
        result[i] = rs.getString("username");
        PreparedStatements.UNLOCK.execute();
        return result;
    }
}
