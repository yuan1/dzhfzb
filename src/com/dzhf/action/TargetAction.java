package com.dzhf.action;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dzhf.model.Target;
import com.dzhf.service.SymServ;
import com.dzhf.service.TargetServ;
import com.dzhf.util.BasicUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("target")
@Scope("prototype")
public class TargetAction extends ActionSupport{

	private static final long serialVersionUID = 6452953697100751020L;
	@Resource
	private TargetServ targetServ;
	@Resource
	private SymServ symServ;
	
	private int p;
	private String o="l";
	
	private String tar_id;
	
	private String month;
	private int depttypeid=0;

	private String[] values;
	
	private Target target;

	/**
	 * 跳转添加指标页
	 * @return
	 */
	public String AddTar(){
		ActionContext.getContext().put("Tartype",targetServ.getTopTarget());
		return "addtar";
	}
	
	public String AddTarDo(){
		if(targetServ.addTarget(target))
			return "success";
		else
			return "failed";
	}
	
	/**
	 * 指标预览
	 * @return
	 */
	public String LookTar(){
		if(month==null||month.length()==0)
			month=BasicUtil.getCurrentTimeOfMonth();
		if(depttypeid==0)
			depttypeid=Integer.valueOf(BasicUtil.getdept().getDept_type().getId());
		ActionContext.getContext().put("tarList",targetServ.getTarget3(month, depttypeid));
		ActionContext.getContext().put("depttypeList",targetServ.getDeptType());
		return "lookTar";
	}

	/**
	 *查看指标详细信息
	 * @return
	 */
	public String seeTarget(){
		ActionContext.getContext().put("tarMes",targetServ.getTarget(tar_id));
		ActionContext.getContext().put("Tartype",targetServ.getTopTarget());
		return "seeTar";
	}
	
	/**
	 * 修改指标信息
	 * @return
	 */
	public String updateTarget(){
		if(targetServ.updateTarget(target))
			return "success";
		else
			return "failed";
	}
	
	public String Write(){
		return "Write";
	}
	
	/**
	 * 指标设置
	 * @return
	 */
	public String SetTar(){
		if(month==null||month.length()==0)
			month=BasicUtil.getCurrentTimeOfMonth();
		if(depttypeid==0)
			depttypeid=Integer.valueOf(BasicUtil.getdept().getDept_type().getId());
		ActionContext.getContext().put("tarList1",targetServ.getAllTarget());
		ActionContext.getContext().put("depttypeList",targetServ.getDeptType());
		Integer[] l=targetServ.getTargetID(month, depttypeid);
		if(l.length==0)
			l=targetServ.getTargetID(BasicUtil.getLastMonth(month),depttypeid);
		String a="";
		for(Integer i:l){
			a+=","+String.valueOf(i);
		}
		a+=",";
		ActionContext.getContext().put("tarlist",a);
		return "SetTar";
	}
	
	/**
	 * 指标设置的提交
	 * @return
	 */
	public String SetTarDo(){
		if(targetServ.updateTargetHistory(depttypeid, month, values))
			return "success";
		else
			return "failed";
	}
	
	public String TarList(){
		ActionContext.getContext().put("tarList",targetServ.getAllTarget());
		return "btar";
	}

	//---------------get  and  set------------------//
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
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

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getTar_id() {
		return tar_id;
	}

	public void setTar_id(String tar_id) {
		this.tar_id = tar_id;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}
}
