
package cn.tz.www.customer.entity.repository.userProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.tz.www.customer.entity.table.UserProfile;

@Repository("userProfileJpaRepository")
public interface UserProfileJpaRepository extends JpaRepository<UserProfile, Long> {
}
