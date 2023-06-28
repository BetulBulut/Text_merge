package com.example.demo.Repository;

import com.example.demo.Entity.Metin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MetinRepository extends MongoRepository<Metin, Integer> {


}
