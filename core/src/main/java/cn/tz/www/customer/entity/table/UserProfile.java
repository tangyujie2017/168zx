
package cn.tz.www.customer.entity.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.tz.www.customer.entity.BaseEntity;



/**
 * 用户个人信息
 * @author Wang.ch
 *
 */
@Table(name = "user_profile")
@Entity
public class UserProfile extends BaseEntity {
    /**
     * 
     */
	
    private static final long serialVersionUID = -2183498989620085463L;
    /**
     * 头像
     */
	@Id
	@GeneratedValue
	@Column(name = "id")
	protected Long id;
    @Column(length = 200)
    private String avatar;
    /**
     * 年龄
     */
    @Column(length = 3)
    private Integer age;
    /**
     * 性别 1:男/2:女
     */
    @Column(length = 1)
    private Integer sex;
    
    /**
     * 用户
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return the sex
     */
    public Integer getSex() {
        return sex;
    }

  

    /**
     * @param sex the sex to set
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

   
}
