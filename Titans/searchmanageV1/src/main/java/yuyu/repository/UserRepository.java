package yuyu.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<RcUserEntity,Integer> {
    RcUserEntity findByUsername(String username);
}
