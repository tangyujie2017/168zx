package cn.tz.www.customer.entity.repository.customerAuthority;

import org.springframework.stereotype.Repository;

import cn.tz.www.customer.entity.repository.HibernateRepositoryImpl;
import cn.tz.www.customer.entity.table.Authority;
import cn.tz.www.customer.entity.table.CustomerAuthority;
import cn.tz.www.customer.entity.table.Role;



@Repository
public class CustomerAuthorityRepositoryCustomImpl extends HibernateRepositoryImpl<CustomerAuthority> implements CustomerAuthorityRepositoryCustom {

}
