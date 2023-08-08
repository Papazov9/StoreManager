package com.returns.store.storagemanager.repo;

import com.returns.store.storagemanager.model.entity.InProgressProduct;
import com.returns.store.storagemanager.model.entity.SellingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InProgressProductRepo extends JpaRepository<InProgressProduct, Long>, JpaSpecificationExecutor<InProgressProduct> {
    Optional<InProgressProduct> findByReturnItemId(String returnItemId);
}
