package cn.tz.www.customer.entity.table;

import javax.persistence.*;

import cn.tz.www.customer.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzc on 14/11/2016.
 */
@Entity
@Table(name = "role", indexes = { @Index(name = "idx_name", columnList = "name") })
public class Role extends BaseEntity {

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

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles", cascade = CascadeType.DETACH)
	private List<User> users;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_authority")
	private List<Authority> authoritys;

	public Role() {
	}

	public Role(Long id) {
		this.id = id;
	}

	public Role(String name) {
		this.name = name;
	}

	public Role(Long id, String name, String details) {
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

	public List<Authority> getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(List<Authority> authoritys) {
		this.authoritys = authoritys;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	

}
