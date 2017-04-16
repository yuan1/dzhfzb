package com.dzhf.commonDao;

import java.util.List;

public interface BaseDao<T> {

	/**
	 * ��ѯ�ܼ�¼��
	 */
	Long getCount();
	Long getCount(String[] colums,String...parmeters);
	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * ɾ��ʵ��
	 * 
	 * @param id
	 */
	void delete(int id);

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 */
	void update(T entity);
	
	void update1(T entity);

	/**
	 * ��id��ѯ
	 * 
	 * @param id
	 * @return
	 */
	T getById(int id);
	
	/**
	 * ��id��ѯ����ʹ�û��ʵ��״̬��Ϊ����̬�������Զ�����
	 * @param id
	 * @return
	 */
	T getById1(int id);

	/**
	 * ��id��ѯ
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Integer[] ids);
	
	/**
	 * ��id��ѯ
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds1(Integer[] ids,String orderby);

	/**
	 * ��ѯ����
	 * 
	 * @return
	 */
	List<T> findAll();
	List<T> findAll(String[] orderColum);
	List<T> findAll(String[] colums,String orderColum,String ...parmeters);
	List<T> findAllName(String[] colums,String orderColum,String ...parmeters);
	/**
	  * ��������ѯ
	  * @return
	  */
	List<T> getByParmeter(String[] colums,String orderColum,String ...parmeters);
	
	/**
	 * ������ģ����ѯ
	 * @param colums
	 * @param orderColum
	 * @param parmeters
	 * @return
	 */
	List<T> getByParmeter1(String[] colums,String orderColum,String ...parmeters);
	/**
	 * ��ҳ������ѯ
	 * @param pageIndex �ڼ�����ʼ��
	 * @param count ����
	 * @param colums
	 * @param orderColum
	 * @param parmeters
	 * @return
	 */
	List<T> getByParmeterOnPage(int pageIndex,int count,String[] colums,String orderColum,String ...parmeters);
	
	/**
	 * ģ����ҳ��ѯ
	 * @param pageIndex
	 * @param count
	 * @param colums
	 * @param orderColum
	 * @param parmeters
	 * @return
	 */
	List<T> getByParmeterOnPage1(int pageIndex,int count,String[] colums,String orderColum,String ...parmeters);
	/**
	 * ��ҳȫ����ѯ
	 * @param pageIndex
	 * @param count
	 * @return
	 */
	List<T> getAllOnPage(int pageIndex,int count);
	
	List<T> getAllOnPage1(int pageIndex,int count);
	

}
