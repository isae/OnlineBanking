import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Xottab
 * Date: 11.07.13
 * Time: 13:01
 */
public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (!((Boolean) session.getAttribute("ready"))) {
            switch (request.getParameter("type")) {
                case "toMain": {
                    response.sendRedirect("index.jsp");
                    break;
                }
                case "AddAcc": {
                    String[] parameters = new String[2];
                    parameters[0] = request.getParameter("param1");
                    parameters[1] = request.getParameter("param2");
                    try {
                        Request r = new UserAddingRequest(parameters, Request.RequestType.AddAcc);
                        r.execute();
                        session.setAttribute("result", "User was added");
                    } catch (ProcessingException e) {
                        session.setAttribute("result", e.getMessage());
                    }
                    session.setAttribute("ready", true);
                    response.sendRedirect("add.jsp");
                    break;
                }
                case "DelAcc": {
                    String[] parameters = new String[1];
                    parameters[0] = request.getParameter("param1");
                    try {
                        Request r = new UserDeletingRequest(parameters, Request.RequestType.DelAcc);
                        r.execute();
                        session.setAttribute("result", "User was deleted");
                    } catch (ProcessingException e) {
                        session.setAttribute("result", e.getMessage());
                    }
                    session.setAttribute("ready", true);
                    response.sendRedirect("delete.jsp");
                    break;
                }
                case "GetAll": {
                    String[] parameters = null;
                    try {
                        GettingAllRequest r = new GettingAllRequest(parameters, Request.RequestType.GetAll);
                        List<String> results = r.getResults();
                        session.setAttribute("result", results);
                    } catch (ProcessingException e) {
                        session.setAttribute("error", e.getMessage());
                    }
                    session.setAttribute("ready", true);
                    response.sendRedirect("get_all.jsp");
                    break;
                }
                case "CastInterest": {
                    String[] parameters = new String[1];
                    parameters[0] = request.getParameter("param1");
                    try {
                        Request r = new InterestCastingRequest(parameters, Request.RequestType.CastInterest);
                        r.execute();
                        session.setAttribute("result", "Interests were casted");
                    } catch (ProcessingException e) {
                        session.setAttribute("result", e.getMessage());
                    }
                    session.setAttribute("ready", true);
                    response.sendRedirect("cast_interest.jsp");
                    break;
                }
                case "CheckBalance": {
                    String[] parameters = new String[1];
                    parameters[0] = request.getParameter("param1");
                    try {
                        BalanceCheckingRequest r = new BalanceCheckingRequest(parameters, Request.RequestType.GetBalance);
                        int result = r.getResult();
                        session.setAttribute("result", result);
                    } catch (ProcessingException e) {
                        session.setAttribute("error", e.getMessage());
                    }
                    session.setAttribute("ready", true);
                    response.sendRedirect("check_balance.jsp");
                    break;
                }
                case "Existence": {
                    String[] parameters = new String[1];
                    parameters[0] = request.getParameter("param1");
                    try {
                        ExistenceCheckingRequest r = new ExistenceCheckingRequest(parameters, Request.RequestType.Existence);
                        boolean f = r.getResult();
                        session.setAttribute("result", f);
                    } catch (ProcessingException e) {
                        session.setAttribute("error", e.getMessage());
                    }
                    session.setAttribute("ready", true);
                    response.sendRedirect("check_existence.jsp");
                    break;
                }
                case "DecBalance": {
                    String[] parameters = new String[2];
                    parameters[0] = request.getParameter("param1");
                    parameters[1] = "-" + request.getParameter("param2");
                    try {
                        Request r = new BalanceModifyRequest(parameters, Request.RequestType.ModifyBalance);
                        r.execute();
                        session.setAttribute("result", "Successful transaction");
                    } catch (ProcessingException e) {
                        session.setAttribute("result", e.getMessage());
                    }
                    session.setAttribute("ready", true);
                    response.sendRedirect("dec_balance.jsp");
                    break;
                }
                case "IncBalance": {
                    String[] parameters = new String[2];
                    parameters[0] = request.getParameter("param1");
                    parameters[1] = request.getParameter("param2");
                    try {
                        Request r = new BalanceModifyRequest(parameters, Request.RequestType.ModifyBalance);
                        r.execute();
                        session.setAttribute("result", "Successful transaction");
                    } catch (ProcessingException e) {
                        session.setAttribute("result", e.getMessage());
                    }
                    session.setAttribute("ready", true);
                    response.sendRedirect("inc_balance.jsp");
                    break;
                }
                case "Transfer": {
                    String[] parameters = new String[3];
                    parameters[0] = request.getParameter("param1");
                    parameters[1] = request.getParameter("param2");
                    parameters[2] = request.getParameter("param3");
                    try {
                        Request r = new MoneyTransferRequest(parameters, Request.RequestType.Transfer);
                        r.execute();
                        session.setAttribute("result", "Transfer completed successfully");
                    } catch (ProcessingException e) {
                        session.setAttribute("result", e.getMessage());
                    }
                    session.setAttribute("ready", true);
                    response.sendRedirect("transfer.jsp");
                    break;
                }
            }


        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
