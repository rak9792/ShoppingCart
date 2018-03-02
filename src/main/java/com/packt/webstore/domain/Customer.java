package com.packt.webstore.domain;

import java.io.Serializable;

public class Customer implements Serializable{
	
	private static final long serialVersionUID=2284040482222162898L;
	
	private Long customerId;
	private String name;
	private Address billingAddress;
	private String phoneNumber;
	public Customer()
	{
		super();
		this.billingAddress=new Address();
	}
	public Customer(Long customerId,String name)
	{
		this();
		this.customerId=customerId;
		this.name=name;
	}	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Customer other=(Customer)o;
		if(customerId==null) {
			if(other.customerId!=null)
				return false;			
		}else if(!customerId.equals(other.customerId))
			return false;
		return true;
	}

	@Override
	public int hashCode()
	{
		return (31 + ((customerId==null)? 0 : customerId.hashCode()));
	}
}
