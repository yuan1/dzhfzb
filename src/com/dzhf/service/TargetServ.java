package com.dzhf.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dzhf.dao.DeptTypeDao;
import com.dzhf.dao.TargetDao;
import com.dzhf.dao.TargetHistoryDao;
import com.dzhf.model.DeptType;
import com.dzhf.model.Target;
import com.dzhf.model.TargetHistory;
import com.dzhf.util.BasicUtil;

@Service
public class TargetServ {

	@Resource
	private TargetDao targetDao;
	
	@Resource
	private DeptTypeDao depttypeDao;
	
	@Resource
	private TargetHistoryDao targetHistoryDao;
	
	/**
	 * 获取某个部门某个月的指标
	 * @param month
	 * @param depttypeid
	 * @return
	 */
	public List<Target> getTarget(String month,int depttypeid){
		try{
		Integer[] ids=getTargetID(month, depttypeid);
		if(ids.length>0){
			return targetDao.getByIds1(ids," parentid+0,sort ");
		}else
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<Target> getTarget1(String month,int depttypeid){
		try{
		Integer[] ids=getTargetID(month, depttypeid);
		if(ids.length>0){
			List<Target> listTar= this.getTopTarget();
			 String order=" charindex('|'+parentid+'|','|";
			 for(Target tar:listTar){
				 order+=""+tar.getId()+"|"; 
			 }
			 order+="'),sort";
			return targetDao.getByIds1(ids,order);
		}else
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<Target> getTarget2(String month,int depttypeid){
		try{
		Integer[] ids=getTargetID(month, depttypeid);
		if(ids.length>0){
			return targetDao.getByIds1(ids," parentid+0,sort ");
		}else
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<Target> getTarget3(String month,int depttypeid){
		try{
		Integer[] ids=getTargetID(month, depttypeid);
		if(ids.length>0){
			return targetDao.getByIds1(ids," sort ");
		}else
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取某个部门某个月的指标
	 * @param month
	 * @param depttypeid
	 * @return
	 */
	public List<Target> getTargetnotgongshi(String month,int depttypeid){
		Integer[] ids=getTargetID(month, depttypeid);
		if(ids.length>0){
			return targetDao.getByIds1(ids," parentid+0,sort ");
		}else
			return null;
	}
	
	/**
	 * 获取某个部门的某个月的指标编号
	 * @param month
	 * @param depttypeid
	 * @return
	 */
	public Integer[] getTargetID(String month,int depttypeid){
		List<TargetHistory> l=targetHistoryDao.findAll(new String[]{"month","depttype_id"}," id asc ",month,String.valueOf(depttypeid));
		Integer[] ids=new Integer[l.size()];
		for(int i=0;i<ids.length;i++){
			ids[i]=l.get(i).getTarget().getId();
		}
		return ids;
	}
	
	/**
	 * 获取全部指标
	 * @return
	 */
	public List<Target> getAllTarget(){
		return targetDao.findAll(new String[]{"sort"});
	}
	
	/**
	 * 获取一级指标
	 * @return
	 */
	public List<Target> getTopTarget(){
		return targetDao.getTopTarget();
	}
	
	/**
	 * 获取公司类型
	 * @return
	 */
	public List<DeptType> getDeptType(){
		return depttypeDao.findAll();
	}
	
	/**
	 * 获取有效月份
	 * @return
	 */
	public List<String> getMonths(){
		return targetHistoryDao.getMonths();
	}
	
	/**
	 * 获取未来三个月份
	 * @return
	 */
	public List<String> getMonths1(){
		List<String> m=new ArrayList<String>();
		m.add(BasicUtil.getCurrentTimeOfMonth());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        m.add(sdf.format(calendar.getTime()));
        calendar.add(Calendar.MONTH,1);
        m.add(sdf.format(calendar.getTime()));
        return m;
	}
	
	/**
	 * 获取指标详细信息
	 * @param id
	 * @return
	 */
	public Target getTarget(String id){
		return targetDao.getById1(Integer.valueOf(id));
	}
	
	/**
	 * 添加指标
	 * @param t
	 * @return
	 */
	public boolean addTarget(Target t){
		targetDao.save(t);
		return true;
	}
	
	/**
	 * 修改指标
	 * @param t
	 * @return
	 */
	public boolean updateTarget(Target t){
		targetDao.updateTarget(t);
		return true;
	}
	
	/**
	 * 修改指标历史设置
	 * @param depttypeid
	 * @param month
	 * @param num
	 * @return
	 */
	public boolean updateTargetHistory(int depttypeid,String month,String[] num){
		targetHistoryDao.delTargetHistory(month, depttypeid);
		for(String i:num){
			TargetHistory th=new TargetHistory();
			th.setDepttype(depttypeDao.getById(depttypeid));
			th.setMonth(month);
			th.setTarget(targetDao.getById(Integer.valueOf(i)));
			targetHistoryDao.save(th);
		}
		return true;
	}
}
