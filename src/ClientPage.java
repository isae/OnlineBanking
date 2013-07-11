import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 06.07.13
 * Time: 1:45
 * To change this template use File | Settings | File Templates.
 */
public class ClientPage extends HttpServlet {
  /*  RequestQueue queue;
    PrintWriter out;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }



    void printButtons() {
        out.println("<form action=\"\"\" method=POST>");
        out.println("<button type=\"submit\" name=\"button\" value=\"1\">Add user</button>");
        out.println("<button type=\"submit\" name=\"button\" value=\"2\">Delete user</button><br> ");
        out.println("<button type=\"submit\" name=\"button\" value=\"3\">Get list of all users</button>");
        out.println("<button type=\"submit\" name=\"button\" value=\"4\">Check balance</button><br>");
        out.println("<button type=\"submit\" name=\"button\" value=\"5\">Money transfer</button> ");
        out.println("<button type=\"submit\" name=\"button\" value=\"6\">Increment balance</button><br> ");
        out.println("<button type=\"submit\" name=\"button\" value=\"7\">Decrement balance</button> ");
        out.println("<button type=\"submit\" name=\"button\" value=\"8\">Check username existence</button><br>");
        out.println("</form>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale l = new Locale("ru");
        out = response.getWriter();
        try {
            RequestQueue queue = new RequestQueue();
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
            String title = "Client Page";
            out.println("<title>" + title + "</title>");
            printStyle();
            out.println("</head>");
            out.println("<body bgcolor=\"white\">");
            printButtons();
            boolean ff = request.getParameterMap().containsKey("data");
            if (request.getParameterMap().containsKey("button")) {
                int type = Integer.parseInt(request.getParameter("button"));

                String param1 = request.getParameter("param1");
                String param2 = request.getParameter("param2");
                String param3 = request.getParameter("param3");
                String req = "";
                boolean f = false;
                switch (type) {
                    case 1: {
                        out.println("<form action=\"\"\" method=POST>");
                        //out.print("clientpage\" ");
                        out.println("Username:<input type=text size=20 name=param1><br>");
                        out.println("Password:<input type=text size=20 name=param2><br>");
                        out.println("<input type=hidden name=\"button\" value=\"" + type + "\">");
                        out.println("<input type=submit name=\"data\" value=\"Submit\" >");
                        out.println("</form>");
                        req = param1 + " " + param2;
                        if (ff) {
                            queue.put(new Request(req, Request.RequestType.AddAcc));
                        }
                        break;
                    }
                    case 2: {
                        out.println("<form action=\"\"\" method=POST>");
                        //out.print("clientpage\" ");
                        out.println("Username:<input type=text size=20 name=param1><br>");
                        out.println("<input type=hidden name=\"button\" value=\"" + type + "\">");
                        out.println("<input type=submit name=\"data\" value=\"Submit\" >");
                        out.println("</form>");
                        req = param1;
                        if (ff) {
                            queue.put(new Request(req, Request.RequestType.DelAcc));
                        }
                        break;
                    }
                    case 3: {
                        ff=true;
                        queue.put(new Request(req, Request.RequestType.GetAll));
                        break;
                    }
                    case 4: {
                        out.println("<form action=\"\"\" method=POST>");
                        //out.print("clientpage\" ");
                        out.println("Username:<input type=text size=20 name=param1><br>");
                        out.println("<input type=hidden name=\"button\" value=\"" + type + "\">");
                        out.println("<input type=submit name=\"data\" value=\"Submit\" >");
                        out.println("</form>");
                        req = param1;
                        if (ff) {
                            queue.put(new Request(req, Request.RequestType.GetBalance));
                        }
                        break;
                    }
                    case 5: {
                        out.println("<form action=\"\"\" method=POST>");
                        //out.print("clientpage\" ");
                        out.println("Sender username:<input type=text size=20 name=param1><br>");
                        out.println("Reciever username:<input type=text size=20 name=param2><br>");
                        out.println("Sum:<input type=text size=20 name=param3><br>");
                        out.println("<input type=hidden name=\"button\" value=\"" + type + "\">");
                        out.println("<input type=submit name=\"data\" value=\"Submit\" >");
                        out.println("</form>");
                        req = param1 + " " + param2 + " " + param3;
                        if (ff) {
                            queue.put(new Request(req, Request.RequestType.Transfer));
                        }
                        break;
                    }
                    case 6: {
                        out.println("<form action=\"\"\" method=POST>");
                        //out.print("clientpage\" ");
                        out.println("Username:<input type=text size=20 name=param1><br>");
                        out.println("Sum:<input type=text size=20 name=param2><br>");
                        out.println("<input type=hidden name=\"button\" value=\"" + type + "\">");
                        out.println("<input type=submit name=\"data\" value=\"Submit\" >");
                        out.println("</form>");
                        req = param1 + " " + param2;
                        if (ff) {
                            queue.put(new Request(req, Request.RequestType.ModifyBalance));
                        }
                        break;
                    }
                    case 7: {
                        out.println("<form action=\"\"\" method=POST>");
                        //out.print("clientpage\" ");
                        out.println("Username:<input type=text size=20 name=param1><br>");
                        out.println("Sum:<input type=text size=20 name=param2><br>");
                        out.println("<input type=hidden name=\"button\" value=\"" + type + "\">");
                        out.println("<input type=submit name=\"data\" value=\"Submit\" >");
                        out.println("</form>");
                        req = param1 + " -" + param2;
                        if (ff) {
                            queue.put(new Request(req, Request.RequestType.ModifyBalance));
                        }
                        break;
                    }
                    case 8: {
                        out.println("<form action=\"\"\" method=POST>");
                        //out.print("clientpage\" ");
                        out.println("Username:<input type=text size=20 name=param1><br>");
                        out.println("<input type=hidden name=\"button\" value=\"" + type + "\">");
                        out.println("<input type=submit name=\"data\" value=\"Submit\" >");
                        out.println("</form>");
                        req = param1;
                        if (ff) {
                            queue.put(new Request(req, Request.RequestType.Contains));
                        }
                        break;
                    }
                    default: {
                        out.println("Invalid type of request");
                        f = true;
                        break;
                    }
                }
                if (!f && ff) {
                    out.println("<br><br>Result: " + queue.get() + "<br>");
                }
            } else {
                out.println("Cannot find parameters for request<br>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            out.println(e.getMessage());
            out.println("please, refresh");
        }

    } */
}
