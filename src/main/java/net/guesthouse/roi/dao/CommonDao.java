package net.guesthouse.roi.dao;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.StringUtils;

public class CommonDao extends SqlSessionDaoSupport {
	private String defaultNamespace = "";

	public void setDefaultNamespace(String defaultNamespace) {
		if (StringUtils.hasText(defaultNamespace)) {
			this.defaultNamespace = defaultNamespace + ".";
		} else {
			this.defaultNamespace = "";
		}
	}

	public String getDefaultNamespace() {
		return defaultNamespace;
	}

	public Object insert(String statementName, Object obj) {
		return getSqlSession().insert(
				defaultNamespace.concat(statementName), obj);
	}
	
	public Object insert(String namespace, String statementName, Object obj) {
		return getSqlSession().insert(
				namespace.concat(".").concat(statementName), obj);
	}
	
	public int update(String statementName, Object obj) {
		return getSqlSession().update(defaultNamespace.concat(statementName), obj);
	}

	public int update(String namespace, String statementName, Object obj) {
		return getSqlSession().update(namespace.concat(".").concat(statementName), obj);
	}

	public int delete(String statementName, Object obj) {
		return getSqlSession().delete(defaultNamespace.concat(statementName), obj);
	}

	public int delete(String namespace, String statementName, Object obj) {
		return getSqlSession().delete(namespace.concat(".").concat(statementName), obj);
	}

	
	@SuppressWarnings("unchecked")
	public <T> T selectOne(String statementName) {
		return (T)getSqlSession().selectOne(defaultNamespace.concat(statementName));
	}
	@SuppressWarnings("unchecked")
	public <T> T selectOne(String statementName, Object obj) {
		return (T)getSqlSession().selectOne(defaultNamespace.concat(statementName), obj);
	}

	@SuppressWarnings("unchecked")
	public <T> T selectOne(String namespace, String statementName, Object obj) {
		if (namespace == null) {
			return (T)getSqlSession().selectOne(statementName, obj);
		} else {
			return (T)getSqlSession().selectOne(namespace.concat(".").concat(statementName), obj);
		}
	}
	@SuppressWarnings("unchecked")
	public <T> List<T> selectList(String statementName) {
		return (List<T>)getSqlSession().selectList(defaultNamespace.concat(statementName));
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> selectList(String statementName, Object obj) {
		return (List<T>)getSqlSession().selectList(defaultNamespace.concat(statementName), obj);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> selectList(String namespace, String statementName, Object obj) {
		return (List<T>)getSqlSession().selectList(namespace.concat(".").concat(statementName), obj);
	}

	//마이바티스에서 배치는 설정으로 해결됨
}