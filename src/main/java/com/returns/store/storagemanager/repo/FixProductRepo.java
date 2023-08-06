package com.returns.store.storagemanager.repo;

import com.returns.store.storagemanager.model.entity.FixProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixProductRepo extends JpaRepository<FixProduct, Long> {
}
