import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Xottab
 * Date: 10.07.13
 * Time: 19:53
 */
public class InputChecker {
    final static String USERNAME_REGEXP = "[a-zA-Z_]{5,20}";
    final static String PASSWORD_REGEXP = "[]{6,}";

    public void checkUsername(String username) throws ProcessingException {
        if (!username.matches(USERNAME_REGEXP)) {
            throw new ProcessingException("Incorrect username");
        }
    }

    public void checkPassword(String password) throws ProcessingException {
        if (!password.matches(PASSWORD_REGEXP)) {
            throw new ProcessingException("Incorrect password");
        }
    }

    public void checkSum(String sum) throws ProcessingException {
        try {
            double i = Integer.parseInt(sum);
        } catch (NumberFormatException e) {
            throw new ProcessingException("Incorrect sum");
        }
    }

    public boolean exists(String username) throws SQLException{
        PreparedStatement pstmt = PreparedStatements.SELECT;
        pstmt.setString(1, "*");
        pstmt.setString(2, "username='" + username + "\'");
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }
}
