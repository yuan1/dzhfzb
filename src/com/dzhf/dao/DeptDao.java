package com.dzhf.dao;

import java.util.List;

import com.dzhf.commonDao.BaseDao;
import com.dzhf.model.Dept;

/**
 * 
 * @author dzxy 惠晓东
 * @DeptDao.java
 * 创建时间：@2016年3月24日 @上午8:30:18
 */
public interface DeptDao extends BaseDao<Dept> {
	public List<Dept> getNullDepts();
//	public List<Dept> getNullDepts(int deptid);
	/**
	 * 添加组织时 若是当前角色具有管理范围需要在
	 * 
	 * role_dept表中添加新添的单位的管理权限
	 * 
	 * 
	 * @param role_id
	 * @param dept_id
	 */
	public void saveRoleDept(int role_id, int dept_id);

	public void updateDept(Dept depts);

	public void deleteDept(int dept_id);

	public void moveDept(int d, int pid);
	
	/**
	 * 获取未提交的单位信息
	 * @param time
	 * @return
	 */
	public List<Dept> getDept(String time);	
	
	/**
	 * 获取
	 * @param status
	 * @param month
	 * @return
	 */
	List<Dept> getDeptList(String status,String month);
}
