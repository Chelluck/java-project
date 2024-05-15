package main;


import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;


public class Main {
    public static void main(String[] args) throws Exception {
        DBService databaseService = new DBService();

        databaseService.printConnectInfo();

        try {
            long userId = databaseService.addUser("tully", "tully");

            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = databaseService.getUser(userId);

            System.out.println("User data set: " + dataSet);

        } catch (DBException e) {
            e.printStackTrace();
        }

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(databaseService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(databaseService)), "/signup");
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});
        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
