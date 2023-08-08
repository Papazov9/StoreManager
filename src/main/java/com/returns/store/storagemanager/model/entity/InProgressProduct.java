package com.returns.store.storagemanager.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "in_progress_products")
public class InProgressProduct extends Product{

}
