package com.dzhf.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dzhf.model.DataInfo;
import com.dzhf.model.DeptType;
import com.dzhf.service.DataInfoServ;
import com.dzhf.service.SymServ;
import com.dzhf.service.TargetServ;
import com.dzhf.util.BasicUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * �û��ύ���ݵ����
 * @author dzxy ������
 * @ReviewAction.java
 * ����ʱ�䣺@2016��3��27�� @����1:25:51
 */

@Controller("review")
@Scope("prototype")
public class ReviewAction extends ActionSupport{
	
	private static final long serialVersionUID = -7215542225351080771L;
	
	@Resource
	private DataInfoServ dataInfoServ;
	@Resource
	private SymServ symServ;
	@Resource
	private TargetServ targetServ;
	
	private String status;  //״̬   1δ���  2�����
	private String time;
	private int dept_id=0; //����id
	
	private String o="a";  //��¼����
	
	private int dataid;  //��¼id
	
	private int p;   //��¼��ҳ

	
	/**
	 * �����ҳ��
	 * @return
	 */
	public String reviewlist(){
		if(time==null||time.length()==0)
			time=BasicUtil.getLastMonth(BasicUtil.getCurrentTimeOfMonth());
		try{
		ActionContext.getContext().put("deptlist",dataInfoServ.getDeptList(status,time));
		}catch(Exception e){e.printStackTrace();}
		if(status.equals("1"))
			ActionContext.getContext().put("name","δ����б�");
		if(status.equals("2"))
			ActionContext.getContext().put("name","������б�");
		if(status.equals("3"))
			ActionContext.getContext().put("name","δ�ύ�б�");
		return "reviewlist";
	}

	/**
	 * �鿴��ʷ��˼�¼
	 * @return
	 * @throws Exception 
	 */
	public String review() throws Exception{
		if(time==null||time.length()==0)
			time=BasicUtil.getLastMonth(BasicUtil.getCurrentTimeOfMonth());
		if(dept_id==0)
			dept_id=BasicUtil.getDeptId();
		List<Integer> l=symServ.getAllDept();
		if(l.contains(dept_id)){
			ActionContext.getContext().put("tarList",targetServ.getTarget(time,symServ.getDeptMess(dept_id).getDept_type().getId()));
			ActionContext.getContext().put("dataList",dataInfoServ.getDataByMonth(String.valueOf(dept_id), time));
			ActionContext.getContext().put("status",dataInfoServ.getReviewStatus(dept_id,time));
		}else{
			ActionContext.getContext().put("depttype","2");
		}
		ActionContext.getContext().put("months",dataInfoServ.getAllMonth());
		return "reviewhistory";
	}
	
	public String reviewdo(){
		if(dataInfoServ.ReviewData(dept_id,time,Integer.valueOf(status)))
			return "success";
		else
			return "failed";
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getDept_id() {
		return dept_id;
	}

	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}

	public int getDataid() {
		return dataid;
	}

	public void setDataid(int dataid) {
		this.dataid = dataid;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}
}
