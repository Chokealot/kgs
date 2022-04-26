package com.chokealot.konyiguitar.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityByUsername(String username);

    UserEntity deleteUserEntitiesByUsername(String username);
}
