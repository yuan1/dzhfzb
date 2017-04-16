package com.dzhf.dao;

import java.util.List;

import com.dzhf.commonDao.BaseDao;
import com.dzhf.model.DataInfo;

public interface DataInfoDao extends BaseDao<DataInfo>{
	/**
	 * ��ȡĳ���ŵ���Ч�·�
	 * @param deptid
	 * @return
	 */
	List<String> getMonth(int deptid);
	
	/**
	 * ��ȡȫ����Ч�·�
	 * @return
	 */
	List<String> getAllMonth();

	/**
	 * ��ȡ���ź�ʱ������ݣ�����statusΪ0.
	 * @param dept_id
	 * @param time
	 * @return
	 */
	List<DataInfo> getDataInfo(int dept_id,String time);
	
	/**
	 * ��ȡĳ�µ����в��źϼ�����
	 * @param month
	 * @return
	 */
	List<DataInfo> getDataSum(String month);
	
	/**
	 * ��ȡĳ���ŵ�һ��ʱ��ĺϼ�����
	 * @param deptid
	 * @param stime
	 * @param etime
	 * @return
	 */
	List<DataInfo> getDataSum(int deptid,String stime,String etime);
	
	/**
	 * ��ȡĳ����һ��ʱ�����Ч�·�
	 * @param deptid
	 * @param stime
	 * @param etime
	 * @return
	 */
	List<String> getDataSumMonth(int deptid,String stime,String etime);
}
