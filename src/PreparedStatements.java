import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Xottab
 * Date: 10.07.13
 * Time: 22:00
 */
public class PreparedStatements {
    static Connection connect;
    public static PreparedStatement ADD_USER;
    public static PreparedStatement DELETE_USER;
    public static PreparedStatement INC_BALANCE;
    public static PreparedStatement DEC_BALANCE;
    public static PreparedStatement SELECT_ALL;
    public PreparedStatements(Connection con) throws SQLException {
        connect = con;
        ADD_USER = connect.prepareStatement("INSERT INTO users VALUE (?,?,0)");
        DELETE_USER = connect.prepareStatement("DELETE FROM users WHERE username=?");
        SELECT_ALL = connect.prepareStatement("SELECT * FROM users WHERE username=?");
        INC_BALANCE = connect.prepareStatement("UPDATE users SET balance=balance + ? WHERE username=?");
        DEC_BALANCE = connect.prepareStatement("UPDATE users SET balance=balance - ? WHERE username=?");
    }
}
