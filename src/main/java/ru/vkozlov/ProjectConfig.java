package ru.vkozlov;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultLdapUsernameToDnMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager;

@Configuration
public class ProjectConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		DefaultSpringSecurityContextSource context = new DefaultSpringSecurityContextSource("ldap://127.0.0.1:33389/dc=springframework,dc=org");
		context.afterPropertiesSet();
		
		LdapUserDetailsManager manager = new LdapUserDetailsManager(context);
		manager.setUsernameMapper(new DefaultLdapUsernameToDnMapper("ou=groups", "uid"));
		manager.setGroupSearchBase("ou=groups");
		
		return manager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
