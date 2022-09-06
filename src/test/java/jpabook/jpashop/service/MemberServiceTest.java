package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class) //junit실행시 SpringBoot와 연관시키겠다.
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
//    @Rollback(value = false)
    public void 회원가입() {
        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long saveId = memberService.join(member);

        //then
//        em.flush(); COMMIT하게 해주는 것 (실제는 RollBack)
        assertThat(member).isEqualTo(memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_제외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        //then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }

}