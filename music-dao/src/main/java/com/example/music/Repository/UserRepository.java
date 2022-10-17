package com.example.music.Repository;

import com.example.music.Entity.Pojo.Entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends Repository<User, Long> {

    @Transactional
    @Modifying
    @Query(value = "update x_user set password =?2 where user_id = ?1",nativeQuery = true)
    int modifyPassword(Long id,String password);
}
