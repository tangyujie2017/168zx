package cn.tz.www.customer.entity.repository.customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.tz.www.customer.entity.table.Customer;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>, CustomerRepositoryCustom {
    public Customer findByMobile(String mobile);
    
    
}
