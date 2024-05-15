package servlets;

import com.google.gson.Gson;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final DBService dbService;

    public SignInServlet(DBService dbService) {
        this.dbService = dbService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
    }

    //sign in
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");

        String pass = request.getParameter("password");

        if (login == null || pass == null) {

            response.setContentType("text/html;charset=utf-8");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }
        try {
            UsersDataSet profile = dbService.getUserByLogin(login);
            if (!profile.getName().equals(login)) {

                response.setContentType("text/html;charset=utf-8");

                response.getWriter().println("Anauthorized");

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            response.setContentType("text/html;charset=utf-8");

            String resp = String.format("Authorized: %s", profile.getName());

            response.getWriter().println(resp);

            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch ( dbService.DBException e ) {
            response.setContentType("text/html;charset=utf-8");

            response.getWriter().println("Anauthorized");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
    }

    //sign out
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
    }
}
