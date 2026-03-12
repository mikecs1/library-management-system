package src.repository.file;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import src.model.Member;
import src.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;

public class FileMemberRepository implements MemberRepository {

    private final String filePath = "src/data/members.txt";

    @Override
    public void add(Member member) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            writer.write(serialize(member));
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null)
                members.add(deserialize(line));

        } catch (IOException e) {
            throw new RuntimeException("File error: " + filePath, e);
        }
        return members;
    }

    @Override
    public Member findByID(int ID) {
        for (Member m : findAll())
            if (m.getID() == ID)
                return m;
        return null;
    }

    private String serialize(Member m) {
        return m.getID() + "," + m.getName() + "," + m.getEmail() + "," + m.getMaxBooks();
    }

    private Member deserialize(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String email = parts[2];
        int maxBooks = Integer.parseInt(parts[3]);
        return new Member(id, name, email, maxBooks);
    }
}
