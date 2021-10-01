package operations.domains.user.repositories;

import operations.domains.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

//    @Query("SELECT cc FROM User cc WHERE cc.active = true AND cc.subject = :subject")
//    Optional<User> findActiveBySubject(@Param("subject") String subject);

    @Query("SELECT cc FROM User cc WHERE cc.subject = :subject")
    Optional<User> findBySubject(@Param("subject") String subject);

    @Override
    @Query("SELECT cc FROM User cc WHERE cc.id = :id")
    Optional<User> findById(Long id);




    }
