package cn.tz.www.customer.entity.table;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import cn.tz.www.customer.entity.BaseEntity;

@Entity
@Table(name = "customer_authority")
public class CustomerAuthority extends BaseEntity {

	private static final long serialVersionUID = -2824880752071083208L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	// 权限名称
	@Column(length = 50)
	private String name;

	// 权限代码
	@Column(length = 50)
	private String code;

	// 描述
	@Column(length = 200)
	private String details;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private CustomerAuthority parent;

	@OrderBy(value = "sort ASC")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private List<CustomerAuthority> children;

	private Integer sort;

	public CustomerAuthority() {

	}

	public CustomerAuthority(Long id, String name, String code, String details, Integer sort, CustomerAuthority parent) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.details = details;
		this.sort = sort;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public CustomerAuthority getParent() {
		return parent;
	}

	public void setParent(CustomerAuthority parent) {
		this.parent = parent;
	}

	public List<CustomerAuthority> getChildren() {
		return children;
	}

	public void setChildren(List<CustomerAuthority> children) {
		this.children = children;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
