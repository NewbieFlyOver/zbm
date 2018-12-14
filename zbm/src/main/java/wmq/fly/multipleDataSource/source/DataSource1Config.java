package wmq.fly.multipleDataSource.source;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

//@Configuration // 注册到springboot容器中
//wmq.fly.multipleDataSource.test01.dao下的的*mapper.java使用test01数据源
//@MapperScan(basePackages = "wmq.fly.multipleDataSource.test01.dao", sqlSessionFactoryRef = "test1SqlSessionFactory")
public class DataSource1Config {

	/**
	 * 配置test1数据库
	 */
	@Bean(name = "test1DataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.test1") //从配置文件中读取spring.datasource.test1开头的配置
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * test1 sql会话工厂
	 */
	@Bean(name = "test1SqlSessionFactory")
	@Primary  //设置为主链接，启动项目时使用此链接，不然不知道使用哪个而报错
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		//从xml配置文件中读取配置信息
//		bean.setMapperLocations(
//				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
		return bean.getObject();
	}

	/**
	 * test1 事物管理
	 */
	@Bean(name = "test1TransactionManager")
	@Primary
	public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "test1SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}