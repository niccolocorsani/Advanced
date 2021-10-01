package operations.domains.useroperation.repositories;

import operations.domains.useroperation.entity.UserOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOperationRepository extends CrudRepository<UserOperation, Long> {

    /* ///TODO non testata
    @Query("SELECT User FROM UserOperationPermission User WHERE User.operation_id = operation_id ")
    List<User> findUserOperationPermissionByUser_id(@Param("operation_id") Long operation_id);

    /// TODO non testata
    @Query("SELECT Operation FROM UserOperationPermission Operation WHERE Operation.operation_id = user_id ")
    List<Operation> findUserOperationPermissionByUser(@Param("user_id") Long user_id);*/
}
