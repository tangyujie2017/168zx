package cn.tz.www.customer.entity.repository.customerRole;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.tz.www.customer.entity.table.CustomerRole;
import cn.tz.www.customer.entity.table.Role;


/**
 * 
 */
@Repository
public interface CustomerRoleRepository extends CrudRepository<CustomerRole, Integer> {
	CustomerRole findByName(String name);
	CustomerRole findByDetails(String details);
}
