package cn.tz.www.customer.entity.repository.authority;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.tz.www.customer.entity.table.Authority;
import cn.tz.www.customer.entity.table.Role;


/**
 * Created by zzc on 16/11/2016.
 */
@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
  Role findByName(String name);
  Role findByDetails(String details);
}
