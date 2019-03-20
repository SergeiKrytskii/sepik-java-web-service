package edu.stepik.authorization.controllers;

import com.google.gson.Gson;
import edu.stepik.authorization.entities.UserProfile;
import edu.stepik.authorization.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    private AccountService accountService;

    public RegistrationServlet (AccountService accountService){
        this.accountService = accountService;
    }


    //get public user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        UserProfile profile = accountService.getUserByLogin(login);
        if (profile != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(profile);
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (accountService.getUserByLogin(login) == null){
            UserProfile userProfile = new UserProfile();
            userProfile.setLogin(req.getParameter("login"));
            userProfile.setPassword(req.getParameter("password"));
            accountService.saveUser(userProfile);
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().print(accountService.getSize());
        } else{
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    //change profile
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String c;
        while ((c = reader.readLine()) != null){
            sb.append(c);
        };
        Gson gson = new Gson();
        UserProfile userProfile = gson.fromJson(sb.toString(), UserProfile.class);
        if (userProfile != null){
            if (userProfile.getPassword().equals(accountService.getUserByLogin(userProfile.getLogin()))){
                accountService.updateUserProfile(userProfile.getLogin(), userProfile);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print("Updated : \n" + userProfile.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("text/html;charset=utf-8");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/html;charset=utf-8");
        }
    }

    //unregister
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserProfile userProfile = accountService.getUserByLogin(login);
        if ( userProfile != null && password.equals(password)){
            accountService.deleteUser(login);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("https://www.google.com");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
