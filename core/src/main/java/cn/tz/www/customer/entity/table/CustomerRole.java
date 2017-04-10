package cn.tz.www.customer.entity.table;

import javax.persistence.*;

import cn.tz.www.customer.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzc on 14/11/2016.
 */
@Entity
@Table(name = "customer_role", indexes = { @Index(name = "idx_name", columnList = "name") })
public class CustomerRole extends BaseEntity {

	private static final long serialVersionUID = -9196730643894509307L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 20)
	private String name;

	// 描述
	@Column(length = 20)
	private String details;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "customerRoles", cascade = CascadeType.DETACH)
	private List<Customer> customers;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "customerRole_customerAuthority")
	private List<CustomerAuthority> authoritys;

	public CustomerRole() {
	}

	public CustomerRole(Long id) {
		this.id = id;
	}

	public CustomerRole(String name) {
		this.name = name;
	}

	public CustomerRole(Long id, String name, String details) {
		this.id = id;
		this.name = name;
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// ----------------- convert -------------------

	public String getDetails() {
		return details;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public void setDetails(String details) {
		this.details = details;
	}

	public List<CustomerAuthority> getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(List<CustomerAuthority> authoritys) {
		this.authoritys = authoritys;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	

	

}
