package com.mshistory.history.repository;

import com.mshistory.history.entity.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends MongoRepository<History, Integer> {

    History findHistoryByUserId(int parseInt);
}
