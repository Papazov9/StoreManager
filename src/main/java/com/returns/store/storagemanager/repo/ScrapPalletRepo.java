package com.returns.store.storagemanager.repo;

import com.returns.store.storagemanager.model.entity.ScrapPallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScrapPalletRepo extends JpaRepository<ScrapPallet, Long> {
    Optional<ScrapPallet> findByName(String palletName);
}
