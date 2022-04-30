package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(ModelView modelView) {
        modelView.setView("new-form");
        return modelView;
    }
}
