package com.dzhf.dao;

import java.util.List;

import com.dzhf.commonDao.BaseDao;
import com.dzhf.model.Dept;

/**
 * 
 * @author dzxy ������
 * @DeptDao.java
 * ����ʱ�䣺@2016��3��24�� @����8:30:18
 */
public interface DeptDao extends BaseDao<Dept> {
	public List<Dept> getNullDepts();
//	public List<Dept> getNullDepts(int deptid);
	/**
	 * �����֯ʱ ���ǵ�ǰ��ɫ���й���Χ��Ҫ��
	 * 
	 * role_dept�����������ĵ�λ�Ĺ���Ȩ��
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
	 * ��ȡδ�ύ�ĵ�λ��Ϣ
	 * @param time
	 * @return
	 */
	public List<Dept> getDept(String time);	
	
	/**
	 * ��ȡ
	 * @param status
	 * @param month
	 * @return
	 */
	List<Dept> getDeptList(String status,String month);
}
