package com.toy.truffle.destination.repository;

import com.toy.truffle.destination.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, String> {

}
