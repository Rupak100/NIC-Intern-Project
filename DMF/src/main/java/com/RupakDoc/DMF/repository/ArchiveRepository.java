package com.RupakDoc.DMF.repository;



import com.RupakDoc.DMF.model.ArchiveDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ArchiveRepository extends MongoRepository<ArchiveDocument, Long> {
    @Query("{'applicationTransactionId' :  ?0 }")
    Optional<ArchiveDocument> findByApplicationTransactionId(Long applicationTransactionId);
}

