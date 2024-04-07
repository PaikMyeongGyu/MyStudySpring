package Mystudy.spring.jparepositoryandcrudrepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final CrudRepositoryV1 crudRepositoryV1;
    private final JpaRepositoryV1 jpaRepositoryV1;

    private void save(){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        Iterable<Member> members = crudRepositoryV1.saveAll(List.of(member1, member2));
        members.forEach((member) -> {
            System.out.println("member = " + member.getName());
        });

        List<Member> memberList = jpaRepositoryV1.saveAll(List.of(member1, member2));
        for(Member member: memberList){
            System.out.println("member = " + member.getName());
        }
    }

    private void findById(){
        Optional<Member> byId = crudRepositoryV1.findById(1L);
        Optional<Member> byId1 = jpaRepositoryV1.findById(1L);
    }

    private void findAllPaging(){
        PageRequest pageRequest = PageRequest
                .of(0,3, Sort.by(Sort.Direction.DESC, "name"));

        Page<Member> page = jpaRepositoryV1.findAll(pageRequest);

        List<Member> sortedMembers = jpaRepositoryV1.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }
}
