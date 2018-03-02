package com.packt.webstore.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ShippingDetail implements Serializable {
	
	private static final long serialVersionUID=6350930334140807514L;
	private Long id;
	private String name;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date shippingDate;
	private Address shippingAddress;
	
	public ShippingDetail()
	{
		this.shippingAddress=new Address();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
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
		ShippingDetail other=(ShippingDetail)o;
		if(id==null) {
			if(other.id!=null)
				return false;			
		}else if(!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode()
	{
		return (31 + ((id==null)? 0 : id.hashCode()));
	}
	
}
