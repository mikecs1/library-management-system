package src.repository;

import java.util.List;
import src.model.Member;

public interface MemberRepository {
    void add(Member member);

    Member findByID(int id);

    List<Member> findAll();
}