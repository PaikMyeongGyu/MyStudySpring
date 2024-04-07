package Mystudy.spring.jparepositoryandcrudrepository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudRepositoryV1 extends CrudRepository<Member, Long> {
    List<Member> findByName(String name);
}
