package com.graphql.repo;

import com.graphql.entity.Cricketer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CricketerRepo extends JpaRepository<Cricketer, Integer> {
}
