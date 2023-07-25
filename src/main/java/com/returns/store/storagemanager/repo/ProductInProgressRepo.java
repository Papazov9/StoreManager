package com.returns.store.storagemanager.repo;

import com.returns.store.storagemanager.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInProgressRepo extends JpaRepository<Product, Long> {
}
