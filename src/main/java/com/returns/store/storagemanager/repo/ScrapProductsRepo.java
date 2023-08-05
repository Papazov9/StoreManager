package com.returns.store.storagemanager.repo;

import com.returns.store.storagemanager.model.entity.ScrapProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapProductsRepo extends JpaRepository<ScrapProduct, Long> {
}
