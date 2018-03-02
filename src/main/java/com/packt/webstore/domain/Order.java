package com.packt.webstore.domain;

import java.io.Serializable;


public class Order implements Serializable {
	
	private static final long serialVersionUID=-3560539622417210365L;
	private Long orderId;
	private Cart cart;
	private Customer customer;
	private ShippingDetail shippingDetail;
	
	public Order()
	{
		this.customer=new Customer();
		this.shippingDetail=new ShippingDetail();
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public ShippingDetail getShippingDetail() {
		return shippingDetail;
	}
	public void setShippingDetail(ShippingDetail shippingDetail) {
		this.shippingDetail = shippingDetail;
	}
	@Override
	public boolean equals(Object o)
	{
		if(this==o)
			return true;
		if(o==null)
			return false;
		if(getClass()!=o.getClass())
			return false;
		Order other=(Order)o;
		if(orderId==null) {
			if(other.orderId!=null)
				return false;			
		}else if(!orderId.equals(other.orderId))
			return false;
		return true;
	}

	@Override
	public int hashCode()
	{
		return (31 + ((orderId==null)? 0 : orderId.hashCode()));
	}
	
}
