package com.FA.productMS.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.FA.productMS.utility.PrimaryKey;

@Entity
@Table(name = "subscribed_product")
public class ProductSubs {
	
	@EmbeddedId
	private PrimaryKey customId;

	public PrimaryKey getCustomId() {
		return customId;
	}

	public void setCustomId(PrimaryKey customId) {
		this.customId = customId;
	}
	
	

}
