package com.yuyu.dao;

import com.yuyu.bo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByName(String name);

    @Query("from UserInfo u where u.id=:id")
    UserInfo findUserById(@Param("id") Long id);

    @Override
    List<UserInfo> findAll();
}
