package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import accounts.AccountService;

public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        // Проверка параметров
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters 'login' or 'password'");
            return;
        }

        // Проверка регистрации пользователя
        if (AccountService.getInstance().isRegistered(login, password)) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Authorized: " + login);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().println("Unauthorized");
        }
    }
}
