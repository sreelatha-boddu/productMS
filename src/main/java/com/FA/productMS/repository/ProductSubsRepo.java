package com.FA.productMS.repository;

import org.springframework.data.repository.CrudRepository;

import com.FA.productMS.entity.ProductSubs;
import com.FA.productMS.utility.PrimaryKey;

public interface ProductSubsRepo extends CrudRepository<ProductSubs, PrimaryKey> {
	
	

}

