package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.JspViewResolver;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.MyViewResolver;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();
    private MyViewResolver viewResolver = new JspViewResolver();;

    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controllerV3 = controllerV3Map.get(requestURI);

        if (controllerV3 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);

        //컨트롤러에 HttpServletRequest, HttpServletResponse를 파라미터로 넘기는것이 아니라
        //맵을 이용하여 컨트롤러가 HttpServlet에 의존하지 않도록 함
        //ModelView클래스를 이용하여 모델 및 뷰 정보를 받아옴
        ModelView modelView = controllerV3.process(paramMap);

        //viewResolver를 이용하여 viewPath 만드는 기능을 한곳에서 함
        MyView myView = viewResolver.getMyView(modelView);
        myView.render(modelView.getModel(), request, response);

    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(parameterName -> paramMap.put(parameterName, request.getParameter(parameterName)));
        return paramMap;
    }
}
