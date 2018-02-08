package com.packt.webstore.domain;

import java.io.Serializable;

public class Customer implements Serializable{
	
	private String customerId;
	private String name;
	private String address;
	private long noOfOrdersMade;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getNoOfOrdersMade() {
		return noOfOrdersMade;
	}
	public void setNoOfOrdersMade(long i) {
		this.noOfOrdersMade = i;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(customerId.equals(((Customer)o).getCustomerId()))
			return true;
		else
			return false;
	}

	@Override
	public int hashCode()
	{
		return (31*((customerId==null)? 0 : customerId.hashCode()));
	}
}
