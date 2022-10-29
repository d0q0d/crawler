package com.github.alirezagolshan.crawler.repository;

import com.github.alirezagolshan.crawler.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
