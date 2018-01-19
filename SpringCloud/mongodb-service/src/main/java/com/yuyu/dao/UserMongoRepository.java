package com.yuyu.dao;


import com.yuyu.bo.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<UserInfo, Long> {
    UserInfo findByName(String name);

    UserInfo findUserById(Long id);

    
}
