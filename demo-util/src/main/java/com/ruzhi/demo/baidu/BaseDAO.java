/*
package com.ruzhi.demo.baidu;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;


public class BaseDAO implements InitializingBean {

    protected Logger logger = Logger.getLogger(this.getClass());

	protected SqlMapClientTemplate sqlMapClient;

	protected JdbcTemplate jdbcTemplate;

	@Override
	public void afterPropertiesSet() throws Exception {
		if(sqlMapClient==null)
			throw new Exception("BaseDAO initilize fail,check related spring's configuration file");
	}

	public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	protected int update(String statementName, Object parameterObject) throws DAOException {
		try{
            return sqlMapClient.update(statementName, parameterObject);
        }catch (DataAccessException e){
            throw new DAOException("[BaseDAO-update]",e);
        }
	}

	protected Object insert(String statementName, Object parameterObject) throws DAOException {
		try{
            return sqlMapClient.insert(statementName, parameterObject);
        }catch (DataAccessException e){
            throw new DAOException("[BaseDAO-insert]",e);
        }
	}

	protected int delete(String statementName, Object parameterObject) throws DAOException {
		try{
            return sqlMapClient.delete(statementName, parameterObject);
        }catch (DataAccessException e){
            throw new DAOException("[BaseDAO-delete]",e);
        }
	}

	protected Object queryForObject(String statementName, Object parameterObject) throws DAOException {
		try{
            return sqlMapClient.queryForObject(statementName, parameterObject);
        }catch (DataAccessException e){
            throw new DAOException("[BaseDAO-queryForObject]",e);
        }
	}

	protected List<?> queryForList(String statementName, Object parameterObject) throws DAOException {
		try{
            return sqlMapClient.queryForList(statementName, parameterObject);
        }catch (DataAccessException e){
            throw new DAOException("[BaseDAO-queryForList]",e);
        }
	}

    */
/**
 * 取List，包含分页
 *
 * @param statementName
 * @param parameterObject
 * @param pageNo
 *             页次
 * @param pageSize
 *             每页记录数
 * @return
 * @throws DAOException
 *//*

	protected List<?> queryForList(String statementName, Object parameterObject, int pageNo, int pageSize) throws DAOException {
        try{
            return sqlMapClient.queryForList(statementName, parameterObject, pageSize * (pageNo - 1), pageSize);
        }catch (DataAccessException e){
            throw new DAOException("[BaseDAO-queryForList]",e);
        }
    }

	protected Map<?, ?> queryForMap(String statementName, Object parameterObject, String keyProperty) throws DAOException {
		try{
            return sqlMapClient.queryForMap(statementName, parameterObject, keyProperty);
        }catch (DataAccessException e){
            throw new DAOException("[BaseDAO-queryForMap]",e);
        }
	}

	protected Map<?, ?> queryForMap(String statementName, Object parameterObject, String keyProperty, String valueProperty) throws DAOException {
        try{
            return sqlMapClient.queryForMap(statementName, parameterObject, keyProperty, valueProperty);
        }catch (DataAccessException e){
            throw new DAOException("[BaseDAO-queryForMap]",e);
        }
    }

}*/
