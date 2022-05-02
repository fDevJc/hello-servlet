package hello.servlet.web.frontcontroller.v5my;

import hello.servlet.web.frontcontroller.JspViewResolver;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.MyViewResolver;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5my", urlPatterns = "/front-controller/v5my/*")
public class FrontControllerServletV5my extends HttpServlet {

    private final Map<String, Object> handlerMap = new HashMap<>();

    private final List<Object> handlers = new ArrayList<>();

    MyViewResolver myViewResolver = new JspViewResolver();

    public FrontControllerServletV5my() {
        handlerMap.put("/front-controller/v5my/v3/members/new-form", new MemberFormControllerV3());
        handlerMap.put("/front-controller/v5my/v3/members/save", new MemberSaveControllerV3());
        handlerMap.put("/front-controller/v5my/v3/members", new MemberListControllerV3());

        handlerMap.put("/front-controller/v5my/v4/members/new-form", new MemberFormControllerV4());
        handlerMap.put("/front-controller/v5my/v4/members/save", new MemberSaveControllerV4());
        handlerMap.put("/front-controller/v5my/v4/members", new MemberListControllerV4());


    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        Object handler = handlerMap.get(requestURI);
        ModelView modelView = null;
        MyView myView = null;

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        if (handler instanceof ControllerV3) {
            modelView = ((ControllerV3) handler).process(paramMap);
            myView = myViewResolver.getMyView(modelView);
            myView.render(modelView.getModel(), request, response);
        } else if (handler instanceof ControllerV4) {
            String view = ((ControllerV4) handler).process(paramMap, model);
            myView = myViewResolver.getMyView(view);
            myView.render(model, request, response);
        }


    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(parameterName -> paramMap.put(parameterName, request.getParameter(parameterName)));
        return paramMap;
    }

}
