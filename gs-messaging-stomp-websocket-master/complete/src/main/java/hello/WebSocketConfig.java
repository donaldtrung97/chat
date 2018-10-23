package hello;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

//
//
//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = {"hello"})
@PropertySource("classpath:/application.properties")

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource getDataSource() {
		
		String url = env.getProperty("jdbc.url");
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url);
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));
//		
		return dataSource;
		
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean   sessionFactory = new LocalSessionFactoryBean ();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[] {"hello"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	@Bean
	public Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
			}
		};
		
	}
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// TODO Auto-generated method stub
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");

	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub
		registry.addEndpoint("/message").withSockJS();
		registry.addEndpoint("/user").withSockJS();
	}
	
	@Bean
	public UserDAO getUserDAO() {
		return new UserDAOImpl(getDataSource());
	}
	
	@Bean
	public GroupDAO getGroupDAO() {
		return new GroupDAOImpl(getDataSource());
	}
	
}