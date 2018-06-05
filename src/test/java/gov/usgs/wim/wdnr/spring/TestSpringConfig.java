package gov.usgs.wim.wdnr.spring;

import javax.sql.DataSource;

import gov.usgs.wim.wdnr.dao.SanitaryDataDao;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;

@Configuration
@Import(MybatisConfig.class)
@PropertySource(value = "classpath:test.properties")
public class TestSpringConfig {

	@Autowired
	private Environment env;

	@Bean
	public OracleDataSource dataSource() throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(env.getProperty("jdbc.wibeaches.url"));
		ds.setUser(env.getProperty("jdbc.wibeaches.username"));
		ds.setPassword(env.getProperty("jdbc.wibeaches.password"));
		return ds;
	}


}
