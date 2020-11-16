package cn.cxnxs.webspider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author potatomato
 */
@SpringBootApplication
@MapperScan("cn.cxnxs.webspider.web.mapper")
@EnableTransactionManagement
public class AutomationPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomationPlatformApplication.class, args);
    }

}
