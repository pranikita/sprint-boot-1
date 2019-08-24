package com.silverbar.marketplace.silverbarmarketplace.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "order_table")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String userId;
	private Double quantity;
	private BigDecimal price;
	private String orderType;
	private String unitType;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public Double getQuantity() {
		return quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public String getOrderType() {
		return orderType;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderType == null) ? 0 : orderType.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((unitType == null) ? 0 : unitType.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderType == null) {
			if (other.orderType != null)
				return false;
		} else if (!orderType.equals(other.orderType))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (unitType == null) {
			if (other.unitType != null)
				return false;
		} else if (!unitType.equals(other.unitType))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", quantity=" + quantity + ", price=" + price + ", orderType="
				+ orderType + ", unitType=" + unitType + "]";
	}
	
	
	
}
