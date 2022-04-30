package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(ModelView modelView) {
        String username = modelView.getParameter("username");
        int age = Integer.parseInt(modelView.getParameter("age"));
        Member member = new Member(username, age);
        Member savedMember = memberRepository.save(member);

        modelView.setAttribute("member", savedMember);
        modelView.setView("save-result");
        return modelView;
    }
}
