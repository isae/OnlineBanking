import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 05.07.13
 * Time: 1:23
 * To change this template use File | Settings | File Templates.
 */
public abstract class Request {

    public enum RequestType {
        AddAcc, DelAcc, GetAll, GetBalance, ModifyBalance, Transfer, Existence, CastInterest
    }

    static Connection connect;
    static InputChecker checker;
    static PreparedStatements statements;
    String[] parameters;
    RequestType type;

    public Request(String[] parameters, RequestType type) throws ProcessingException {
        this.parameters = parameters;
        this.type = type;
        checker = new InputChecker();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(/*...*/);
            statements = new PreparedStatements(connect);

        } catch (SQLException e) {
            throw new ProcessingException("Can`t connect to SQL database");
        } catch (ClassNotFoundException e) {

            throw new ProcessingException("Can`t find SQL driver");
        }
    }

    void execute() throws ProcessingException {
        try {
            synchronized (connect) {
                executeHelper();
            }
        } catch (SQLException e) {
            throw new ProcessingException("Database error");
        }
    }

    abstract void executeHelper() throws SQLException, ProcessingException;
}
