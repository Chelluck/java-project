package servlets;

import com.google.gson.Gson;
import dbService.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final DBService dbService;

    public SignUpServlet(DBService dbService) {
        this.dbService = dbService;
    }

    //get logged user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");

        String pass = request.getParameter("password");
        if (login == null || pass == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            dbService.addUser(login, pass);
        }
        catch (dbService.DBException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
    }

    //sign out
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
    }
}
