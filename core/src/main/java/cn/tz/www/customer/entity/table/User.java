package cn.tz.www.customer.entity.table;


import javax.persistence.*;

import cn.tz.www.customer.entity.BaseEntity;
import cn.tz.www.customer.entity.table.Role;

import java.util.*;

/**
 * Created by zzc on 11/11/2016.
 */
@Entity
@Table(name = "user", indexes = { @Index(name = "idx_login", columnList = "login"), @Index(name = "idx_locked", columnList = "locked") })
public class User extends BaseEntity {
    
	private static final long serialVersionUID = 4285275098457224134L;
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
    
    //身份证号
    @Column(length = 50)
    private String idcard;
    
    //头像
    @Column(length = 50, name = "head_photo")
    private String headPhoto;
    
    @Column(name = "create_time")
    private Date createTime = new Date();
    
    /**
     * 用户个人信息
     */
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserProfile profile;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private List<Role> roles;   

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String realName) {
        this.id = id;
        this.realName = realName;
    }

    public User(String mobile, String realName, String login, String password, boolean locked, List<Role> roles) {
        this.mobile = mobile;
        this.realName = realName;
        this.login = login;
        this.password = password;
        this.locked = locked;
        this.roles = roles;
    }

  

   

    public User(Long id, String mobile, String realName, String login, boolean locked, List<Role> roles) {
        this.id = id;
        this.mobile = mobile;
        this.login = login;
        this.realName = realName;
        this.locked = locked;
        this.roles = roles;
    }

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }
    // ------------- convert --------------

    /**
     * @return the profile
     */
    public UserProfile getProfile() {
        return profile;
    }

    /**
     * @param profile the profile to set
     */
    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
}
