package hello.servlet.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);

        //when
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId());

        //then
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("hello1", 20);
        Member member2 = new Member("hello2", 30);

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);
        List<Member> findMembers = memberRepository.findAll();

        //then
        assertThat(findMembers.size()).isEqualTo(2);
        assertThat(findMembers).contains(member1, member2);
    }
}