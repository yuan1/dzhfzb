package com.dzhf.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dzhf.dao.DataInfoDao;
import com.dzhf.dao.DeptDao;
import com.dzhf.dao.ReviewInfoDao;
import com.dzhf.model.DataInfo;
import com.dzhf.model.Dept;
import com.dzhf.model.DeptType;
import com.dzhf.model.ReviewInfo;
import com.dzhf.model.Target;
import com.dzhf.util.BasicUtil;

@Service
public class DataInfoServ {

	@Resource
	private DataInfoDao dataInfoDao;
	@Resource
	private DeptDao deptDao;
	@Resource
	private ReviewInfoDao reviewInfoDao;
	
	/**
	 * 获取数据    i是查询条件    1可以查询已提交    0查询未提交和提交全部
	 * @param deptid
	 * @param time
	 * @param i
	 * @return
	 */
	public DataInfo getDataInfo(int deptid,String time,int i){
		List<DataInfo> list=null;
		try{
		if(i==1)
			list=dataInfoDao.getDataInfo(deptid,time);
		else
			list= dataInfoDao.findAll(new String[]{"dept_id","month"}," id asc ",String.valueOf(deptid),time);
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
		}catch(Exception e){e.printStackTrace();return null;}
	}
	
	/**
	 * 获取月份
	 * @return
	 */
	public List<String> getMonths(){
		return dataInfoDao.getMonth(BasicUtil.getDeptId());
	}
	
	public List<String> getAllMonth(){
		return dataInfoDao.getAllMonth();
	}
	
	
	/**
	 * 获取某月某状态的公司列表
	 * @param status
	 * @param month
	 * @return
	 */
	public List<Dept> getDeptList(String status,String month){
		List<Dept> l=new ArrayList<Dept>();
		if(!status.equals("3")){
			l= deptDao.getDeptList(status, month);
		}else{
			l= deptDao.getDept(month);
		}
		for(Dept d:l){
			if(d.getId()==101){
				l.remove(d);
			}
		}
		return l;
	}
	
	/**
	 * 保存用户提交数据
	 * @param datainfo
	 * @return
	 */
	public boolean saveDataInfo(DataInfo datainfo){
		List<DataInfo> dlist=dataInfoDao.findAll(new String[]{"dept_id","month","tar_id"}," id asc ",
				String.valueOf(datainfo.getDept().getId()),datainfo.getMonth(),String.valueOf(datainfo.getTargets().getId()));
		if(dlist.isEmpty()){
			dataInfoDao.save(datainfo);
		}else{
			DataInfo di=dlist.get(0);
			di.setValue(datainfo.getValue());
			dataInfoDao.update(di);
		}
		return true;
	}

	public String getValue(String dept_id,String month,String tar_id){
		String a="";
		List<DataInfo> dlist=dataInfoDao.findAll(new String[]{"dept_id","month","tar_id"}," id asc ",
				dept_id,month,tar_id);
		if(!dlist.isEmpty()){
			a=dlist.get(0).getValue();
		}
		return a;
	}
	
	/**
	 * 审核或取消审核、status=1取消审核、status=2审核
	 * @param dataid
	 * @param status
	 * @return
	 */
	public boolean ReviewData(int dept_id,String month,int status){
		List<ReviewInfo> l=reviewInfoDao.findAll(new String[]{"dept_id","month"}," id asc ",String.valueOf(dept_id),month);
		if(!l.isEmpty()){
			ReviewInfo d=l.get(0);
			d.setStatus(status);
			reviewInfoDao.update(d);
		}else{
			ReviewInfo d=new ReviewInfo();
			d.setDept(deptDao.getById(dept_id));
			d.setMonth(month);
			d.setStatus(status);
			reviewInfoDao.save(d);
		}
		return true;
	}
	
	public int getReviewStatus(int dept_id,String month){
		List<ReviewInfo> l=reviewInfoDao.findAll(new String[]{"dept_id","month"}," id asc ",String.valueOf(dept_id),month);
		int x=0;
		if(!l.isEmpty()){
			ReviewInfo d=l.get(0);
			x=d.getStatus();
		}
		return x;
	}
	
	/**
	 * 根据部门id和月份获取数据
	 * @param type
	 * @param month
	 * @return
	 */
	public List<DataInfo> getDataByMonth(String deptid,String month){
		return dataInfoDao.findAll(new String[]{"dept_id","month"}," id asc ",
				deptid,month);
	}
	
	/**
	 * 获取某月的所有数据
	 * @param month
	 * @return
	 */
	public List<DataInfo> getDataByMonth(String month){
		return dataInfoDao.findAll(new String[]{"month"}," dept.id asc ",
				month);
	}
	
	/**
	 * 获取数据合计
	 * @param month
	 * @return
	 */
	public List<DataInfo> getDataSum(String month){
		List<DataInfo> l=new ArrayList<DataInfo>();
		l=dataInfoDao.getDataSum(month);
		return l;
	}
	
	/**
	 * 获取数据合计1
	 * @param month
	 * @return
	 */
	public List<DataInfo> getDataSum1(int deptid,String stime,String etime){
		List<DataInfo> l=new ArrayList<DataInfo>();
		l=dataInfoDao.getDataSum(deptid,stime,etime);
		return l;
	}
	
	/**
	 * 获取数据合计月份
	 * @param month
	 * @return
	 */
	public List<String> getDataSumMonth(int deptid,String stime,String etime){
		List<String> l=new ArrayList<String>();
		l=dataInfoDao.getDataSumMonth(deptid,stime,etime);
		return l;
	}
}
