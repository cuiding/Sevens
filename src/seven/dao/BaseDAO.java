package seven.dao;

import java.io.Serializable;
import java.util.List;

import seven.entity.PageBean;

public interface BaseDAO<T> {

	/**
	 * 
	 * @param o
	 * @return
	 */
	//????
	public Serializable save(T o);

	/**
	 * 
	 * @param o
	 * 删除
	 */
	public void delete(T o);

	/**
	 * @param o
	 * 更新
	 */
	public void update(T o);

	/**
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o);
	
	/**
	 * @param o
	 */
	public void merge(T o);

	/**
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * 
	 * @param hql
	 * @param param
	 * @param page        
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, Object[] param, PageBean pageBean);

	/**
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, List<Object> param, PageBean pageBean);

	/**
	 * 
	 * @param c
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param);

	/**
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param);

	/**
	 * select count(*) from ��
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * select count(*) from ��
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, Object[] param);

	/**
	 * select count(*) from ��
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, List<Object> param);

	/**
	 * 
	 * @param hql
	 * @return 
	 */
	public Integer executeHql(String hql);

	/**
	 * 
	 * @param hql
	 * @param param
	 * @return 
	 */
	public Integer executeHql(String hql, Object[] param);

	/**
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, List<Object> param);

	/**
	 * @param sql
	 * @return
	 */
	public Integer executeSql(String sql);
	
	public List<T> findTopN(String hql, List<Object> param, int N);
	
}
