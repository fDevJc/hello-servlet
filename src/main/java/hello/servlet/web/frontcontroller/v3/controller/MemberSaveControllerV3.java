package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        System.out.println("username = " + username);
        System.out.println("age = " + age);

        Member member = new Member(username, age);
        Member savedMember = memberRepository.save(member);

        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", savedMember);
        return modelView;

    }
}
