/**   
 * <p>Title: MyBatisConfig.java</p>
 * @Package com.hello.boot1.config 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @since 2017年10月18日 下午4:06:40 
 * @version V1.0   
 */
package com.cdtech.vclass.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;



/** 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @version V1.0 
 */
@Configuration
@MapperScan(basePackages = MyBatisConfig.PACKAGE, sqlSessionFactoryRef = "boot1SqlSessionFactory")
public class MyBatisConfig{

	private static final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);
	// 精确到目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.cdtech.vclass.dao.mapper";
    static final String MAPPER_LOCATION ="classpath:com/cdtech/vclass/dao/mapper/*.xml";
    
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.filters}")
    private String filters;
    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;
    
    @Bean(name = "dataSource")
    public DataSource makeDataSource(){
    	DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
			dataSource.setFilters(filters);
		} catch (SQLException e) {
			logger.info("carrierDruidDataSource filters Exceptions",e);
		}
        dataSource.setConnectionProperties(connectionProperties);
        return dataSource;
    }
    
    @Bean(name = "bootTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(makeDataSource());
    }
    
	@Bean(name="boot1SqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSource") DataSource bootDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(bootDataSource);
     

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources(MAPPER_LOCATION));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    

}
