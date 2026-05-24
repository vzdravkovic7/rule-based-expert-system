package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.Vezba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VezbaRepository extends JpaRepository<Vezba, String> {
}