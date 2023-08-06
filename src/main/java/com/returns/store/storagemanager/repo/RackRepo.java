package com.returns.store.storagemanager.repo;

import com.returns.store.storagemanager.model.entity.Rack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RackRepo extends JpaRepository<Rack, Long> {
    Optional<Rack> findByRackName(String rackName);
}
