package ru.stoupin.shiro;

import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.support.DefaultEventBus;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    @Bean
    public EventBus eventBus(){
        return new DefaultEventBus();
    }

    @Bean(name = "shiroFilterFactoryBean")
    @Autowired
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) throws Exception {
        ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
        Map<String, Filter> filters = new HashMap<String, Filter>();
        filters.put("1", appFilter());
        shiroFilterFactory.setSecurityManager(securityManager);

        return shiroFilterFactory;
    }

    @Bean

    public Filter appFilter() throws Exception {
        org.apache.shiro.web.servlet.IniShiroFilter iniShiroFilter = new
                org.apache.shiro.web.servlet.IniShiroFilter();
        iniShiroFilter.setConfigPath("classpath:shiro.ini");
        iniShiroFilter.init();

        return iniShiroFilter;
    }
}
