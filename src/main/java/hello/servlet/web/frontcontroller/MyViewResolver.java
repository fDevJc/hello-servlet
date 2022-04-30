package hello.servlet.web.frontcontroller;

public interface MyViewResolver {
    MyView getMyView(String view);

    MyView getMyView(ModelView modelView);
}
