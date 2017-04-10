package cn.tz.www.customer.entity.table;

import java.util.Date;
import java.util.List;

import javax.persistence.*;


import cn.tz.www.customer.entity.BaseEntity;
import cn.tz.www.customer.entity.em.CustomerEnum;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	protected Long id;

	@Column(name = "mobile")
    private String mobile;
	
    @Column(name = "real_name")
    private String realName;
    
    @Column(name = "login")
    private String login;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "locked")
    private boolean locked;
    
    //韬唤璇佸彿
    @Column(length = 50)
    private String idcard;
    
    //澶村儚
    @Column(length = 50, name = "head_photo")
    private String headPhoto;
    
    @Column(name = "create_time")
    private Date createTime = new Date();
    
   
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "customer_customerrole", joinColumns = { @JoinColumn(name = "customerid", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "roleid", referencedColumnName = "id") })
    private List<CustomerRole> customerRoles;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getRealName() {
		return realName;
	}



	public void setRealName(String realName) {
		this.realName = realName;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isLocked() {
		return locked;
	}



	public void setLocked(boolean locked) {
		this.locked = locked;
	}



	public String getIdcard() {
		return idcard;
	}



	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}



	public String getHeadPhoto() {
		return headPhoto;
	}



	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public List<CustomerRole> getCustomerRoles() {
		return customerRoles;
	}



	public void setCustomerRoles(List<CustomerRole> customerRoles) {
		this.customerRoles = customerRoles;
	}



	

}
