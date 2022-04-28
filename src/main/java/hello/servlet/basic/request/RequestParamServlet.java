package hello.servlet.basic.request;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[전체 파라미터 조회] - start");

//        printParameterOld(request);
        printParameterNew(request);

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");

        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);

        System.out.println("[단일 파라미터 조회] - end");
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회] - start");

        String[] usernames = request.getParameterValues("username");
        Arrays.stream(usernames).iterator()
                .forEachRemaining(name -> System.out.println("username = " + name));

        System.out.println("[이름이 같은 복수 파라미터 조회] - end");

        response.getWriter().write("OK");
    }

    private void printParameterNew(HttpServletRequest request) {
        request.getParameterNames().asIterator()
            .forEachRemaining(parameterName -> System.out.println(parameterName + " : " + request.getParameter(parameterName)));
    }

    private void printParameterOld(HttpServletRequest request) {
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
    }
}
