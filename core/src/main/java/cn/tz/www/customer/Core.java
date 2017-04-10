
package cn.tz.www.customer;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan
@Configuration
@ComponentScan
@EnableJpaRepositories
@EnableTransactionManagement
@PropertySource({ "core.properties" })//core 内核程序需要读取配置项@Value("${retail.sms.expires}")或者spring组件需要的配置
//核心資源加載
public class Core {
	//密码服务对象
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
