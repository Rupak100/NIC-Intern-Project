package com.RupakDoc.DMF.repository;


import com.RupakDoc.DMF.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    @Query("{'client_id': ?0}")
    Optional<UserEntity> findByClientId(String client_id);
    Optional<UserEntity> findByMobileNo(long mobileNo);
    void deleteByMobileNo(long mobileNo);

}
