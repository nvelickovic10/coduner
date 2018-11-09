package com.nvelickovic10.coduner.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nvelickovic10.coduner.mongo.model.ExecutionEntity;

public interface ExecutionRepository extends MongoRepository<ExecutionEntity, String> {

}
