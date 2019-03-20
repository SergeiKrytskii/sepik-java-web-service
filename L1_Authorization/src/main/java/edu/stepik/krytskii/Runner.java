package edu.stepik.krytskii;

import edu.stepik.krytskii.services.AccountService;
import edu.stepik.krytskii.services.impl.AccountServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class Runner {

//    private static final String BASE_URL = "/api/v1";

    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountServiceImpl();
        SignInServlet authServlet = new SignInServlet(accountService);
        SignUpServlet registrationServlet = new SignUpServlet(accountService);

        Server server = new Server(8080);
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(servletContextHandler);
        servletContextHandler.addServlet(new ServletHolder(authServlet), "/signin");
        servletContextHandler.addServlet(new ServletHolder(registrationServlet), "/signup");

        server.start();
        server.join();

    }

}
