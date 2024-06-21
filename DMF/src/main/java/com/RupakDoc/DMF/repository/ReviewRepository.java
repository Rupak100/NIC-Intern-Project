package com.RupakDoc.DMF.repository;



import com.RupakDoc.DMF.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, String> {

    @Query("{'application_transaction_id': ?0 } ")
    Optional<Review> findByApplicationTransactionId(long application_id);
}

