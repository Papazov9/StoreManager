package com.returns.store.storagemanager.repo;

import com.returns.store.storagemanager.model.entity.SellingProduct;
import com.returns.store.storagemanager.model.view.SellingProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellingProductRepo extends JpaRepository<SellingProduct, Long>,
        JpaSpecificationExecutor<SellingProduct> {
    List<SellingProduct> findBySoldIsFalse();

    List<SellingProduct> findBySoldIsTrueOrderBySaleTimeDesc();

}
