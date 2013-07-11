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
        AddAcc, DelAcc, GetAll, GetBalance, ModifyBalance, Transfer, Existence;
    }

    static Connection connect;
    static InputChecker checker;
    String[] parameters;
    RequestType type;

    public Request(String[] parameters, RequestType type) throws ProcessingException {
        this.parameters = parameters;
        this.type = type;
        checker = new InputChecker();
        try {
            connect = DriverManager.getConnection("jdbc:mysql://skupon.ru:3306/test1?autoReconnect=true&amp;encoding=UTF-8&amp;useUnicode=true&amp;characterEncoding=UTF-8", "test1", "jtUv2wUj8RE9mFDm");
            //connect = DriverManager.getConnection("jdbc:mysql://192.168.1.2:3306/test1?autoReconnect=true&amp;encoding=UTF-8&amp;useUnicode=true&amp;characterEncoding=UTF-8", "test1", "jtUv2wUj8RE9mFDm");
        } catch (SQLException e) {
            throw new ProcessingException("Can`t connect to SQL database");
        }
    }

    synchronized void execute() throws ProcessingException {
        try {
            executeHelper();
        } catch (SQLException e) {
            throw new ProcessingException("Database error");
        }
    }

    abstract void executeHelper() throws SQLException, ProcessingException;
}
