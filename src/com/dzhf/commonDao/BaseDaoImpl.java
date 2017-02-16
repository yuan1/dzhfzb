package com.dzhf.commonDao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	public BaseDaoImpl() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		// System.out.println("clazz ---> " + clazz);
	}

	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		Session session = null;
		/*
		 * try { session=sessionFactory.getCurrentSession(); } catch (Exception
		 * ex){ session=sessionFactory.openSession(); }
		 */
		// System.out.println(sessionFactory);
		session = sessionFactory.getCurrentSession();
		return session;

	}

	@Override
	public Long getCount() {
		String hql = "SELECT COUNT(*) FROM " + clazz.getSimpleName();
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long getCount(String[] colums, String... parmeters) {
		String hql = "SELECT COUNT(*) FROM " + clazz.getSimpleName()
				+ " where ";
		for (String str : colums) {
			hql += str + "=? and ";
		}
		hql = hql.substring(0, hql.length() - 4);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < parmeters.length; i++) {
			query.setString(i, parmeters[i]);
		}
		return (Long) query.uniqueResult();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void update(T entity) {
		try{
			Session session=getSession();
			session.update(entity);}catch(Exception e){
			System.out.println(e);
		}
	}

	@Override
	public void update1(T entity){
		try{
			getSession().clear();
			getSession().update(entity);
		}catch(Exception e){
		}
	}
	
	@Override
	public void delete(int id) {
		Object obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	@Override
	public T getById(int id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public T getById1(int id) {
		Session s=getSession();
		T t=(T) s.get(clazz, id);
		s.clear();
		return t;
	}
	
	@Override
	public List<T> getByIds(Integer[] ids) {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}
	
	@Override
	public List<T> getByIds1(Integer[] ids,String orderby) {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)  ORDER BY "+orderby)//
				.setParameterList("ids", ids)//
				.list();
	}

	@Override
	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName())//
				.list();
	}
	
	@Override
	public List<T> findAll(String[] orderColum) {
		String hql = "FROM " + clazz.getSimpleName();
		for(int i=0;i<orderColum.length;i++){
			hql=hql+" ,"+orderColum[i];
		}
		hql=hql.replaceFirst(","," ORDER BY ");
		return getSession().createQuery(hql)//
				.list();
	}
	
	@Override
	public List<T> findAll(String[] colums, String orderColum,
			String... parmeters) {
		String hql = "FROM " + clazz.getSimpleName();
		hql += " WHERE ";
		for (String str : colums) {
			hql += str + "=? AND ";
		}
		hql = hql.substring(0, hql.length() - 4);
		if (orderColum != "") {
			hql += " ORDER BY " + orderColum;
		}
//		System.out.println(hql);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < parmeters.length; i++) {
			query.setString(i, parmeters[i]);
		}
		List<T> list = query.list();
		return list;
	}

	@Override
	public List<T> getByParmeter1(String[] colums, String orderColum,
			String... parmeters) {
		String hql = "FROM " + clazz.getSimpleName();
		hql += " WHERE ";
		for (String str : colums) {
			hql += str + " like ? AND ";
		}
		hql = hql.substring(0, hql.length() - 4);
		if (orderColum != "") {
			hql += " ORDER BY " + orderColum;
		}
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < parmeters.length; i++) {
			query.setString(i, "%"+parmeters[i]+"%");
		}
		
		List<T> list = query.list();
		return list;
	}
	
	@Override
	public List<T> findAllName(String[] colums, String orderColum,
			String... parmeters) {
		String hql = "select username FROM " + clazz.getSimpleName();
		hql += " WHERE ";
		for (String str : colums) {
			hql += str + "=? AND ";
		}
		hql = hql.substring(0, hql.length() - 4);
		if (orderColum != "") {
			hql += " ORDER BY " + orderColum;
		}
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < parmeters.length; i++) {
			query.setString(i, parmeters[i]);
		}

		List<T> list = query.list();
		return list;
	}

	@Override
	public List<T> getByParmeter(String[] colums, String orderColum,
			String... parmeters) {
		String hql = "FROM " + clazz.getSimpleName();
		hql += " WHERE ";
		for (String str : colums) {
			hql += str + "=? AND ";
		}
		hql = hql.substring(0, hql.length() - 4);
		if (orderColum != "") {
			hql += " ORDER BY " + orderColum;
		}
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < parmeters.length; i++) {
			query.setString(i, parmeters[i]);
		}

		List<T> list = query.list();
		return list;
	}

	@Override
	public List<T> getByParmeterOnPage(int pageIndex, int count,
			String[] colums, String orderColum, String... parmeters) {
		String hql = "FROM " + clazz.getSimpleName() + " where ";
		for (String str : colums) {
			hql += str + "=? AND ";
		}
		hql = hql.substring(0, hql.length() - 4);
		hql += " ORDER BY " + orderColum;
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < parmeters.length; i++) {
			query.setString(i, parmeters[i]);
		}
		query.setFirstResult(pageIndex*10);
		query.setMaxResults(count);
		return query.list();
	}

	@Override
	public List<T> getByParmeterOnPage1(int pageIndex, int count,
			String[] colums, String orderColum, String... parmeters) {
		String hql = "FROM " + clazz.getSimpleName() + " where ";
		for (String str : colums) {
			hql += str + " like ? AND ";
		}
		hql = hql.substring(0, hql.length() - 4);
		hql += " ORDER BY " + orderColum;
		Query query = getSession().createQuery(hql).setFirstResult(count*(pageIndex-1))
				.setMaxResults(count);
		for (int i = 0; i < parmeters.length; i++) {
			query.setString(i, "%"+parmeters[i]+"%");
		}
		return query.list();
	}
	
	
	@Override
	public List<T> getAllOnPage(int pageIndex, int count) {
		String hql = "FROM " + clazz.getSimpleName() + " ";
		Query query = getSession().createQuery(hql).setFirstResult(pageIndex)
				.setMaxResults(count);
		return query.list();
	}
	
	@Override
	public List<T> getAllOnPage1(int pageIndex, int count) {
		String hql = "FROM " + clazz.getSimpleName() + " ORDER BY id desc ";
		Query query = getSession().createQuery(hql).setFirstResult(pageIndex)
				.setMaxResults(count);
		return query.list();
	}

}
