package src.repository.memory;

import java.util.ArrayList;
import java.util.List;

import src.model.Member;
import src.repository.MemberRepository;

public class InMemoryMemberRepository implements MemberRepository {

    private List<Member> members = new ArrayList<>();

    @Override
    public void add(Member member) {
        members.add(member);
    }

    @Override
    public Member findByID(int id) {
        for (Member m : members)
            if (m.getID() == id)
                return m;
        return null;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(members);
    }
}
