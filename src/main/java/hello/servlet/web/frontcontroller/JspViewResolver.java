package hello.servlet.web.frontcontroller;

public class JspViewResolver implements MyViewResolver{

    private final String prefix = "/WEB-INF/views/";
    private final String postfix = ".jsp";

    @Override
    public MyView getMyView(ModelView modelView) {
        return new MyView(prefix+modelView.getView()+postfix);
    }
}
