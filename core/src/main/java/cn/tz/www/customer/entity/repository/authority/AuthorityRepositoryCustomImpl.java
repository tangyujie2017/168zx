package cn.tz.www.customer.entity.repository.authority;

import org.springframework.stereotype.Repository;

import cn.tz.www.customer.entity.repository.HibernateRepositoryImpl;
import cn.tz.www.customer.entity.table.Authority;
import cn.tz.www.customer.entity.table.Role;



@Repository
public class AuthorityRepositoryCustomImpl extends HibernateRepositoryImpl<Authority> implements AuthorityRepositoryCustom {

}
