package hello.servlet.web.frontcontroller;

import javax.servlet.http.HttpServletRequest;

public class ModelView {
    private final HttpServletRequest request;
    private String view;

    public ModelView(HttpServletRequest request) {
        this.request = request;
    }

    public String getView() {
        return view;
    }

    public String getParameter(String key) {
        return request.getParameter(key);
    }

    public void setView(String view) {
        this.view = view;
    }

    public void setAttribute(String key, Object obj) {
        request.setAttribute(key, obj);
    }
}
