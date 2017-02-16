package com.dzhf.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dzhf.model.DataInfo;
import com.dzhf.service.DataInfoServ;
import com.dzhf.service.SymServ;
import com.dzhf.service.TargetServ;
import com.dzhf.util.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



/**
 * 公司数据提交和查看
 * @author dzxy 惠晓东
 * @DataAction.java
 * 创建时间：@2016年3月26日 @上午1:10:06
 */

@Controller("data")
@Scope("prototype")
public class DataAction extends ActionSupport{

	private static final long serialVersionUID = 4546851976079554342L;
	
	private int p;
	
	@Resource
	private SymServ symServ;
	@Resource
	private TargetServ targetServ;
	@Resource
	private DataInfoServ datainfoServ;
	
	private DataInfo datainfo;
	
	private String month="";
	private int depttypeid=0;
	private int deptid=0;
	private int l=0;
	
	private String status;
	
	/**
	 * 获取历史记录
	 * @return
	 * @throws Exception
	 */
	public String DataList() throws Exception{
		if(month==null||month.length()==0)
			month=BasicUtil.getLastMonth(BasicUtil.getCurrentTimeOfMonth());
		if(depttypeid==0)
			depttypeid=Integer.valueOf(BasicUtil.getdept().getDept_type().getId());
		if(deptid==0)
			deptid=BasicUtil.getDeptId();
		ActionContext.getContext().put("tarList",targetServ.getTarget(month, depttypeid));
		ActionContext.getContext().put("dataList",datainfoServ.getDataByMonth(String.valueOf(deptid), month));
		ActionContext.getContext().put("status",datainfoServ.getReviewStatus(deptid, month));
		return "DataList";
	}
	
	/**
	 * 跳转添加数据页面
	 * @return
	 * @throws Exception 
	 */
	public String addData() throws Exception{
		if(month==null||month.length()==0)
			month=BasicUtil.getLastMonth(BasicUtil.getCurrentTimeOfMonth());
		if(deptid==0)
			deptid=BasicUtil.getDeptId();
		if(depttypeid==0)
			depttypeid=symServ.getDeptMess(deptid).getDept_type().getId();
		ActionContext.getContext().put("tarList",targetServ.getTarget(month, depttypeid));
		ActionContext.getContext().put("dataList",datainfoServ.getDataByMonth(String.valueOf(deptid), month));
		ActionContext.getContext().put("zhuangtai",symServ.getPro(1).getType());
		ActionContext.getContext().put("status",datainfoServ.getReviewStatus(deptid, month));
		return "AddData";
	}
	
	/**
	 * 提交数据操作
	 */
	public String addDataDo(){
		try{
			if(month==null||month.length()==0){
				month=BasicUtil.getCurrentTimeOfMonth();
			}
			datainfoServ.ReviewData(BasicUtil.getDeptId(), month,1);
			return "success";
		}catch(Exception e){
			return "failed";
		}
	}
	
	/**
	 * 保存数据操作并进行运算
	 */
	public String addDataDo2(){
		if(deptid==0)
			deptid=BasicUtil.getDeptId();
		depttypeid=symServ.getDeptMess(deptid).getDept_type().getId();
		Integer[] l1=targetServ.getTargetID(month, depttypeid);
		if(l1.length==0)
			l1=targetServ.getTargetID(BasicUtil.getLastMonth(month),depttypeid);
		String a="";
		for(Integer i:l1){
			a+=","+String.valueOf(i);
		}
		a+=",";
		String dept_id=String.valueOf(BasicUtil.getDeptId());
		if(a.contains(",91,")){
			DataInfo d=new DataInfo();
			d.setMonth(month);
			d.setDept(BasicUtil.getdept());
			d.setTargets(targetServ.getTarget("91"));
			d.setAddtime(BasicUtil.getCurrentTime());
			d.setValue(jisuan.getXJJLLHJ(
					datainfoServ.getValue(dept_id,month,"86"),
					datainfoServ.getValue(dept_id,month,"87"),
					datainfoServ.getValue(dept_id,month,"88"),
					datainfoServ.getValue(dept_id,month,"89"),
					datainfoServ.getValue(dept_id,month,"90")
					));
			datainfoServ.saveDataInfo(d);
		}
		if(a.contains(",85,")){
			DataInfo d=new DataInfo();
			d.setMonth(month);
			d.setDept(BasicUtil.getdept());
			d.setTargets(targetServ.getTarget("85"));
			d.setAddtime(BasicUtil.getCurrentTime());
			d.setValue(jisuan.getGFJZCPZBHJ(
					datainfoServ.getValue(dept_id,month,"81"),
					datainfoServ.getValue(dept_id,month,"82"),
					datainfoServ.getValue(dept_id,month,"83"),
					datainfoServ.getValue(dept_id,month,"84")
					));
			datainfoServ.saveDataInfo(d);
		}
		//万锭用工不用计算了
//		if(a.contains(",60,")){
//			DataInfo d=new DataInfo();
//			d.setMonth(month);
//			d.setDept(BasicUtil.getdept());
//			d.setTargets(targetServ.getTarget("60"));
//			d.setAddtime(BasicUtil.getCurrentTime());
//			d.setValue(jisuan.getWDCL(
//					datainfoServ.getValue(dept_id,month,"59"),
//					datainfoServ.getValue(dept_id,month,"110"),
//					datainfoServ.getValue(dept_id,month,"111")
//					));
//			datainfoServ.saveDataInfo(d);
//		}
		//调整利润放在这
		if(a.contains(",106,")){
			DataInfo d=new DataInfo();
			d.setMonth(month);
			d.setDept(BasicUtil.getdept());
			d.setTargets(targetServ.getTarget("106"));
			d.setAddtime(BasicUtil.getCurrentTime());
			d.setValue(jisuan.getTZLR(
					datainfoServ.getValue(dept_id,month,"95"),
					datainfoServ.getValue(dept_id,month,"96"),
					datainfoServ.getValue(dept_id,month,"97"),
					datainfoServ.getValue(dept_id,month,"98"),
					datainfoServ.getValue(dept_id,month,"99"),
					datainfoServ.getValue(dept_id,month,"101"),
					datainfoServ.getValue(dept_id,month,"102"),
					datainfoServ.getValue(dept_id,month,"130"),
					datainfoServ.getValue(dept_id,month,"131"),
					datainfoServ.getValue(dept_id,month,"132"),
					datainfoServ.getValue(dept_id,month,"103"),
					datainfoServ.getValue(dept_id,month,"104"),
					datainfoServ.getValue(dept_id,month,"105"),
					datainfoServ.getValue(dept_id,month,"122"),
					datainfoServ.getValue(dept_id,month,"123"),
					datainfoServ.getValue(dept_id,month,"124"),
					datainfoServ.getValue(dept_id,month,"125"),
					datainfoServ.getValue(dept_id,month,"126"),
					datainfoServ.getValue(dept_id,month,"127"),
					datainfoServ.getValue(dept_id,month,"128"),
					datainfoServ.getValue(dept_id,month,"129")
					));
			datainfoServ.saveDataInfo(d);
		}
		//考核利润放在这 
		if(a.contains(",52,")){
			DataInfo d=new DataInfo();
			d.setMonth(month);
			d.setDept(BasicUtil.getdept());
			d.setTargets(targetServ.getTarget("52"));
			d.setAddtime(BasicUtil.getCurrentTime());
			//两个数字相加可以借用考核利润这个公式
			d.setValue(jisuan.getKHLR(
					datainfoServ.getValue(dept_id,month,"50"),
					datainfoServ.getValue(dept_id,month,"106")
					));
			datainfoServ.saveDataInfo(d);
		}
		//一定要注意先后顺序
		if(a.contains(",53,")){
			DataInfo d=new DataInfo();
			d.setMonth(month);
			d.setDept(BasicUtil.getdept());
			d.setTargets(targetServ.getTarget("53"));
			d.setAddtime(BasicUtil.getCurrentTime());
			d.setValue(jisuan.getKHLRWCBFB(
					datainfoServ.getValue(dept_id,month,"51"),
					datainfoServ.getValue(dept_id,month,"52")
					));
			datainfoServ.saveDataInfo(d);
		}
		if(a.contains(",54,")){
			DataInfo d=new DataInfo();
			d.setMonth(month);
			d.setDept(BasicUtil.getdept());
			d.setTargets(targetServ.getTarget("54"));
			d.setAddtime(BasicUtil.getCurrentTime());
			//两个数字相加可以借用考核利润这个公式
			d.setValue(jisuan.getKHLR(
					datainfoServ.getValue(dept_id,BasicUtil.getLastMonth(month),"54"),
					datainfoServ.getValue(dept_id,month,"52")
					));
			if(month!=null&&month.split("-").length>=2&&month.split("-")[1].trim().equals("12")){
				d.setValue(
						datainfoServ.getValue(dept_id,month,"52"));
			}
			datainfoServ.saveDataInfo(d);
		}
		if(a.contains(",56,")){
			DataInfo d=new DataInfo();
			d.setMonth(month);
			d.setDept(BasicUtil.getdept());
			d.setTargets(targetServ.getTarget("56"));
			d.setAddtime(BasicUtil.getCurrentTime());
			//两个数字相加可以借用考核利润这个公式
			d.setValue(jisuan.getKHLR(
					datainfoServ.getValue(dept_id,BasicUtil.getLastMonth(month),"56"),
					datainfoServ.getValue(dept_id,month,"55")
					));
			if(month!=null&&month.split("-").length>=2&&month.split("-")[1].trim().equals("12")){
				d.setValue(
						datainfoServ.getValue(dept_id,month,"55"));
			}
			datainfoServ.saveDataInfo(d);
		}
		if(a.contains(",117,")){
			DataInfo d=new DataInfo();
			d.setMonth(month);
			d.setDept(BasicUtil.getdept());
			d.setTargets(targetServ.getTarget("117"));
			d.setAddtime(BasicUtil.getCurrentTime());
			//两个数字相加可以借用考核利润这个公式
			d.setValue(jisuan.getSum(
					datainfoServ.getValue(dept_id,month,"16"),
					datainfoServ.getValue(dept_id,month,"47"),
					datainfoServ.getValue(dept_id,month,"48"),
					datainfoServ.getValue(dept_id,month,"49"),
					datainfoServ.getValue(dept_id,month,"143"),
					datainfoServ.getValue(dept_id,month,"146"),
					datainfoServ.getValue(dept_id,month,"147"),
					datainfoServ.getValue(dept_id,month,"148"),
					datainfoServ.getValue(dept_id,month,"149")
					));
			datainfoServ.saveDataInfo(d);
		}
		ActionContext.getContext().put("tarList",targetServ.getTarget(month, depttypeid));
		ActionContext.getContext().put("dataList",datainfoServ.getDataByMonth(String.valueOf(deptid), month));
		ActionContext.getContext().put("zhuangtai",symServ.getPro(1).getType());
		ActionContext.getContext().put("status",datainfoServ.getReviewStatus(deptid, month));
		if(l!=0){
			return "success";
		}
		else{
			return "baocun";
		}
	}
	
	/**
	 * 临时保存数据操作
	 */
	public void addDataDo1(){
		datainfo.setAddtime(BasicUtil.getCurrentTime());
		datainfoServ.saveDataInfo(datainfo);
	}

	public int getP() {
		return p;
	}
	
	public void setP(int p) {
		this.p = p;
	}

	public DataInfo getDatainfo() {
		return datainfo;
	}

	public void setDatainfo(DataInfo datainfo) {
		this.datainfo = datainfo;
	}
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getDepttypeid() {
		return depttypeid;
	}

	public void setDepttypeid(int depttypeid) {
		this.depttypeid = depttypeid;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}
}
