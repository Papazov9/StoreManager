package com.returns.store.storagemanager.repo;

import com.returns.store.storagemanager.model.bindings.SearchProductBinding;
import com.returns.store.storagemanager.model.entity.SellingProduct;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification implements Specification<SellingProduct> {

    private final SearchProductBinding productBinding;

    public ProductSpecification(SearchProductBinding productBinding) {
        this.productBinding = productBinding;
    }

    @Override
    public Predicate toPredicate(Root<SellingProduct> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {

        Predicate p = cb.conjunction();

        if(productBinding.getReturnItemId() != null && !productBinding.getReturnItemId().trim().isEmpty()){
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("returnItemId"), productBinding.getReturnItemId()))
            );
        }

        if(productBinding.getAsin() != null && !productBinding.getAsin().trim().isEmpty()){
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("asin"), productBinding.getAsin())));
        }

        if(productBinding.getCategory() != null && !productBinding.getCategory().trim().isEmpty()){
            p.getExpressions().add(
                    cb
                            .and(cb.equal(root.get("category"), productBinding.getCategory()))
            );
        }

        if(productBinding.getCondition() != null && !productBinding.getCondition().trim().isEmpty()){
            p.getExpressions().add(
                    cb
                            .and(cb.equal(root.get("condition"), productBinding.getCondition()))
            );
        }

        if(productBinding.getDepartment() != null && !productBinding.getDepartment().trim().isEmpty()){
            p.getExpressions().add(
                    cb
                            .and(cb.equal(root.get("department"), productBinding.getDepartment()))
            );
        }

        if(productBinding.getEan() != null && !productBinding.getEan().trim().isEmpty()){
            p.getExpressions().add(
                    cb
                            .and(cb.equal(root.get("ean"), productBinding.getEan()))
            );
        }

        if(productBinding.getLpn() != null && !productBinding.getLpn().trim().isEmpty()){
            p.getExpressions().add(
                    cb
                            .and(cb.equal(root.get("lpn"), productBinding.getLpn()))
            );
        }

        if(productBinding.getPalletId() != null && !productBinding.getPalletId().trim().isEmpty()){
            p.getExpressions().add(
                    cb
                            .and(cb.equal(root.get("palletId"), productBinding.getPalletId()))
            );
        }

        return p;
    }
}
