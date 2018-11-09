package com.nvelickovic10.liaservices.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExecutionRepository extends MongoRepository<ExecutionEntity, String> {

}
