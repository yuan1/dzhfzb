package com.dzhf.dao;

import java.util.List;

import com.dzhf.commonDao.BaseDao;
import com.dzhf.model.DataInfo;

public interface DataInfoDao extends BaseDao<DataInfo>{
	/**
	 * 获取某部门的有效月份
	 * @param deptid
	 * @return
	 */
	List<String> getMonth(int deptid);
	
	/**
	 * 获取全部有效月份
	 * @return
	 */
	List<String> getAllMonth();

	/**
	 * 获取部门和时间的数据，排序status为0.
	 * @param dept_id
	 * @param time
	 * @return
	 */
	List<DataInfo> getDataInfo(int dept_id,String time);
	
	/**
	 * 获取某月的所有部门合计数据
	 * @param month
	 * @return
	 */
	List<DataInfo> getDataSum(String month);
	
	/**
	 * 获取某部门的一段时间的合计数据
	 * @param deptid
	 * @param stime
	 * @param etime
	 * @return
	 */
	List<DataInfo> getDataSum(int deptid,String stime,String etime);
	
	/**
	 * 获取某部门一段时间的有效月份
	 * @param deptid
	 * @param stime
	 * @param etime
	 * @return
	 */
	List<String> getDataSumMonth(int deptid,String stime,String etime);
}
