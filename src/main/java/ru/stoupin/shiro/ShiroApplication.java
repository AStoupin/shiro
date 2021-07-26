package ru.stoupin.shiro;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShiroApplication {

	/*@Bean
	public Realm realm() {
		IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
		return iniRealm;
	}*/




	public static void main(String[] args) {
		SpringApplication.run(ShiroApplication.class, args);
	}

}
