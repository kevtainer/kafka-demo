package com.instana.kafka.demo.emaildispatch;

import com.instana.kafka.demo.EmailRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRecordRepository extends MongoRepository<EmailRecord, String> {
}
