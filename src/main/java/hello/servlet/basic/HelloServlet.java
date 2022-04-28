package hello.servlet.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("req = " + req);
        System.out.println("res = " + res);
        System.out.println("HelloServlet.service");

        String username = req.getParameter("username");
        System.out.println("username = " + username);

        //header
        res.setContentType("text/plain");
        res.setCharacterEncoding("utf-8");
        //body
        res.getWriter().write("hello " + username);
    }
}
