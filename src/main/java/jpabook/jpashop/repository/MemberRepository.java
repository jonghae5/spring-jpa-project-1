package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 실행시 Rule을 보고 알아서 만들어줌. select m from Member m where m.name=:name
    List<Member> findByName(String name);
}
