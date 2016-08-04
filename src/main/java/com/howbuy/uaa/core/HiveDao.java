package com.howbuy.uaa.core;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * <pre>
 *  hive访问方法
 * </pre>
 *
 * @author ji.ma
 * @create 13-3-4 下午5:44
 * @modify
 * @since JDK1.6
 */
public class HiveDao extends NamedParameterJdbcTemplate{
	
    public HiveDao(DataSource dataSource) {
		super(dataSource);
	}
    
    
	private final Logger log = LoggerFactory.getLogger(this.getClass());
}
