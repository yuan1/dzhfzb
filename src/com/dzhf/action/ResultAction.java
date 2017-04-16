package com.dzhf.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.tools.ant.taskdefs.Tar;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.dzhf.model.DataInfo;
import com.dzhf.model.Dept;
import com.dzhf.model.DeptType;
import com.dzhf.model.Target;
import com.dzhf.service.DataInfoServ;
import com.dzhf.service.SymServ;
import com.dzhf.service.TargetServ;
import com.dzhf.util.BasicUtil;
import com.dzhf.util.ExportExcelUtils;
import com.dzhf.util.jisuan;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 统计查询的action
 * @author dzxy 惠晓东
 * @ResultAction.java
 * 创建时间：@2016年4月1日 @上午11:14:44
 */

@Controller("result")
@Scope("prototype")
public class ResultAction extends ActionSupport{

	private static final long serialVersionUID = 7804454593607717247L;
	
	public String month;     //月份
	public String type;      //类型
	public int p;
	public int deptid=0;
	public String stime;
	public String etime;
	public int c=0;
	
	@Resource
	private DataInfoServ dataInfoServ;
	@Resource
	private SymServ symServ;
	@Resource
	private TargetServ targetServ;
	
	private String tarid;
	
	/**
	 * 查找数据   c=0正常查询   c=1总结查询
	 * @return
	 */
	public String SelectData(){
		
		//如果时间为空，默认当前月份
		if(month==null||month.length()==0)
			month=BasicUtil.getLastMonth(BasicUtil.getCurrentTimeOfMonth());
		//公司类型为空，默认当前公司类型
		if(type==null||type.length()==0)
			type=ActionContext.getContext().getSession().get("depttype").toString();
		try{
		//根据公司类型获取公司列表
		List<Dept> l=new ArrayList<Dept>();
		if(c==0)
			l=symServ.getDeptByType(type);
		else
			l=symServ.getDept();
		List<Dept> d2=new ArrayList<Dept>();
		List<Integer> l1=symServ.getAllDept();
		for(Dept d1:l){
			if(l1.contains(d1.getId())&&d1.getId()!=101){
				d2.add(d1);
			}
		}
		ActionContext.getContext().put("deptlist",d2);
		DeptType d=symServ.getDeptType(type);
		ActionContext.getContext().put("depttype",d);
		
		//获取指标类型
		List<Target> tarlist=targetServ.getTarget2(month,d.getId());
		ActionContext.getContext().put("tarlist",getTarDataMapbyTarget(tarlist));
		
		//获取全部公司类型
		ActionContext.getContext().put("depttypeList",symServ.getDeptType());
		if(c==0){
			//ActionContext.getContext().put("dataList",dataInfoServ.getDataByMonth(month));
			List<DataInfo> datalist=dataInfoServ.getDataByMonth(month);
			ActionContext.getContext().put("dataList",getDataMapbyTarget(datalist));
		}
		else{
			List<DataInfo> datal=dataInfoServ.getDataByMonth(month);
			List<DataInfo> dlist=new ArrayList<DataInfo>();
			for(DataInfo dd:datal){
				if(dd.getTargets().getId()==107||dd.getTargets().getId()==108||dd.getTargets().getId()==109){
					dlist.add(dd);
				}
			}
			List<DataInfo> datalist=dataInfoServ.getDataByMonth(month);
			ActionContext.getContext().put("dataList",getDataMapbyDept(datalist));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(c==0)
			return "result";
		else
			return "result1";
	}
	
	
	/**
	 * 报表的导出
	 * @return
	 */
	public String ExportData(){
		try{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("octets/stream");
		response.addHeader("Content-Disposition",
				"attachment;filename=export.xls");
		OutputStream out = response.getOutputStream();
		List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();
		//获取部门
		List<Dept> l=symServ.getDeptByType(type);
		List<Dept> d2=new ArrayList<Dept>();
		List<Integer> l1=symServ.getAllDept();
		for(Dept d1:l){
			if(l1.contains(d1.getId())&&d1.getId()!=101){
				d2.add(d1);
			}
		}
		List<DataInfo> di=dataInfoServ.getDataByMonth(month);
		List<Target> tarlist=targetServ.getTarget1(month,Integer.valueOf(type));
		for(Target tar:tarlist){
			Double sum=0.0000;
			if(tar.getParentid()!=null && tar.getParentid().length()>0){
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("targettype",targetServ.getTarget(tar.getParentid()).getValue());
				map.put("target",tar.getValue());
				for(int i=2;i<d2.size()+2;i++){
					int o=0;
					for(DataInfo d:di){
						if(d.getDept().getId()==d2.get(i-2).getId() && d.getValue()!=null && d.getValue().length()>0 && d.getTargets().getId()==tar.getId()){
							String ss="";
							if(jisuan.isNumber(d.getValue())){
								if(targetServ.getTarget(tar.getParentid()).getValue().equals("消耗指标")){
									ss=jisuan.getInt(jisuan.getDouble(d.getValue()));
								}else if(tar.getValue().equals("公司规模")){
									ss=jisuan.getDouble4(d.getValue());
								}else{
									ss=jisuan.getDouble2(d.getValue());
								}
							}else if(tar.getValue().contains("百分比")){
								ss=jisuan.FormatBFS(d.getValue());
							}else{
								ss=d.getValue();
							}
							map.put("data"+(i-2),ss);
							try{
							if(tar.getSum().equals("1")){
								sum+=jisuan.getDouble(d.getValue());
							}}catch (Exception e) {
							}
							o=1;
						}
					}
					if(o==0){
						map.put("data"+(i-2),"");
					}
				}
				if(tar.getSum().equals("1"))
					map.put("sum",jisuan.FormatDouble(sum));
				result.add(map);
			}
		}
		String[] headers=new String[d2.size()+3];
		headers[0]="指标类别";
		headers[1]="指标名称";
		headers[d2.size()+2]="合计";
		for(int i=2;i<d2.size()+2;i++){
			headers[i]=d2.get(i-2).getDept_name();
		}
		String[] columns=new String[d2.size()+3];
		columns[0]="targettype";
		columns[1]="target";
		columns[d2.size()+2]="sum";
		for(int i=2;i<d2.size()+2;i++){
			columns[i]="data"+(i-2);
		}
		ExportExcelUtils.exportExcel("Export", headers, columns, result,
				out, "");
		out.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}

	/**
	 * 报表的导出
	 * @return
	 */
	public String ExportData1(){
		try{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("octets/stream");
		response.addHeader("Content-Disposition",
				"attachment;filename=export.xls");
		OutputStream out = response.getOutputStream();
		List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();
		//获取部门
		List<Dept> l=symServ.getDept();
		List<Dept> d2=new ArrayList<Dept>();
		List<Integer> l1=symServ.getAllDept();
		for(Dept d1:l){
			if(l1.contains(d1.getId())&&d1.getId()!=101){
				d2.add(d1);
			}
		}
		List<DataInfo> di=dataInfoServ.getDataByMonth(month);
		int i=1;
		for(Dept dept:d2){
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("sno",i);
			map.put("dept",dept.getDept_name());
			for(DataInfo a:di){
				if(a.getDept().getId()==dept.getId()&&a.getTargets().getId()==107){
					map.put("ld",a.getValue());
				}
				if(a.getDept().getId()==dept.getId()&&a.getTargets().getId()==108){
					map.put("ad",a.getValue());
				}
				if(a.getDept().getId()==dept.getId()&&a.getTargets().getId()==109){
					map.put("wt",a.getValue());
				}
			}
			result.add(map);
			i++;
		}
		
		String[] headers=new String[5];
		headers[0]="序号";
		headers[1]="公司";
		headers[2]="亮点";
		headers[3]="暗点";
		headers[4]="需协调问题";
		
		String[] columns=new String[5];
		columns[0]="sno";
		columns[1]="dept";
		columns[2]="ld";
		columns[3]="ad";
		columns[4]="wt";
		ExportExcelUtils.exportExcel("Export", headers, columns, result,
				out, "");
		out.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}

	

	/**
	 * 报表的导出
	 * @return
	 */
	public String ExportDataSum(){
		try{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("octets/stream");
		response.addHeader("Content-Disposition",
				"attachment;filename=export.xls");
		OutputStream out = response.getOutputStream();
		List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();
		//获取部门
		List<Dept> d2=new ArrayList<Dept>();
		List<Integer> l1=symServ.getAllDept();
		for(Integer d1:l1){
			if(l1.contains(d1)&&d1!=101){
				d2.add(symServ.getDeptMess(d1));
			}
		}
		List<DataInfo> dl=dataInfoServ.getDataSum(month);
		Double sum1=0.0000,sum2=0.0000,sum3=0.0000,sum4=0.0000,sum5=0.0000,sum6=0.0000,sum7=0.0000;
		int i=1;
		for(Dept d:d2){
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("no",i);
			i++;
			map.put("gongsi",d.getDept_name());
			int x=0;
			for(DataInfo di:dl){
				if(di.getDept().getId()==d.getId()&&di.getTargets().getId()==52){
					map.put("t1",di.getValue());
					sum1+=jisuan.getDouble(di.getValue());
					x=1;
				}
			}if(x==0)
				map.put("t1","");
			int x1=0;
			for(DataInfo di:dl){
				if(di.getDept().getId()==d.getId()&&di.getTargets().getId()==54){
					map.put("t2",di.getValue());
					sum2+=jisuan.getDouble(di.getValue());
					x1=1;
				}
			}if(x1==0)
				map.put("t2","");
			int x2=0;
			for(DataInfo di:dl){
				if(di.getDept().getId()==d.getId()&&di.getTargets().getId()==91){
					map.put("t3",di.getValue());
					sum3+=jisuan.getDouble(di.getValue());
					x2=1;
				}
			}if(x2==0)
				map.put("t3","");
			int x3=0;
			for(DataInfo di:dl){
				if(di.getDept().getId()==d.getId()&&di.getTargets().getId()==117){
					map.put("t4",di.getValue());
					sum4+=jisuan.getDouble(di.getValue());
					x3=1;
				}
			}if(x3==0)
				map.put("t4","");
			int x5=0;
			for(DataInfo di:dl){
				if(di.getDept().getId()==d.getId()&&di.getTargets().getId()==57){
					map.put("t5",di.getValue());
					sum5+=jisuan.getDouble(di.getValue());
					x5=1;
				}
			}if(x5==0)
				map.put("t5","");
			int x6=0;
			for(DataInfo di:dl){
				if(di.getDept().getId()==d.getId()&&di.getTargets().getId()==59){
					map.put("t6",di.getValue());
					sum6+=jisuan.getDouble(di.getValue());
					x6=1;
				}
			}if(x6==0)
				map.put("t6","");
			int x7=0;
			for(DataInfo di:dl){
				if(di.getDept().getId()==d.getId()&&di.getTargets().getId()==60){
					map.put("t7",di.getValue());
					sum7+=jisuan.getDouble(di.getValue());
					x7=1;
				}
			}if(x7==0)
				map.put("t7","");
			result.add(map);
		}
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("gongsi","合计");
		map.put("t1",jisuan.FormatDouble(sum1));
		map.put("t2",jisuan.FormatDouble(sum2));
		map.put("t3",jisuan.FormatDouble(sum3));
		map.put("t4",jisuan.FormatDouble(sum4));
		map.put("t5",jisuan.FormatDouble(sum5));
		map.put("t6",jisuan.FormatDouble(sum6));
		map.put("t7",jisuan.FormatDouble(sum7));
		result.add(map);
		String[] headers=new String[d2.size()+6];
		headers[0]="序号";
		headers[1]="公司名称\\指标名称";
		headers[2]=targetServ.getTarget("52").getValue();
		headers[3]=targetServ.getTarget("54").getValue();
		headers[4]=targetServ.getTarget("91").getValue();
		headers[5]=targetServ.getTarget("117").getValue();
		headers[6]=targetServ.getTarget("57").getValue();
		headers[7]=targetServ.getTarget("59").getValue();
		headers[8]=targetServ.getTarget("60").getValue();
		
		String[] columns=new String[d2.size()+6];
		columns[0]="no";
		columns[1]="gongsi";
		columns[2]="t1";
		columns[3]="t2";
		columns[4]="t3";
		columns[5]="t4";
		columns[6]="t5";
		columns[7]="t6";
		columns[8]="t7";
		ExportExcelUtils.exportExcel("Export", headers, columns, result,
				out, "");
		out.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}


	/**
	 * 历史报表的导出
	 * @return
	 */
	public String ExportDataSum1(){
		try{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("octets/stream");
		response.addHeader("Content-Disposition",
				"attachment;filename=export.xls");
		OutputStream out = response.getOutputStream();
		List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();
		List<Integer> l=symServ.getAllDept();
		List<String> months=dataInfoServ.getDataSumMonth(deptid,stime,etime);
		if(l.contains(deptid)){
			List<DataInfo> di=dataInfoServ.getDataSum1(deptid,stime,etime);
			List<Target> tarlist=targetServ.getTarget(etime,symServ.getDeptMess(deptid).getDept_type().getId());
			for(Target tar:tarlist){
				if(tar.getParentid()!=null && tar.getParentid().length()>0){
					LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
					map.put("lb",targetServ.getTarget(tar.getParentid()).getValue());
					map.put("mc",tar.getValue());
					Double x=0.00;
					for(int i=2;i<months.size()+2;i++){
						int o=0;
						for(DataInfo d:di){
							if(d.getTargets().getId()==tar.getId()&&d.getMonth().equals(months.get(i-2))){
								map.put("A"+i,d.getValue());
								o=1;
								if(tar.getSum().equals("1")){
									x+=jisuan.getDouble(d.getValue());
								}
							}
						}
						if(o==0){
							map.put("A"+i,"");
						}
					}
					if(tar.getSum().equals("1")){
						map.put("sum",jisuan.FormatDouble(x));
					}else{
						map.put("sum","");
					}							
					result.add(map);
				}
			}
		}
		
		String[] headers=new String[months.size()+3];
		headers[0]="指标类别";
		headers[1]="指标名称";
		int i=2;
		for(String m:months){
			headers[i]=m;
			i++;
		}
		headers[months.size()+2]="合计";
		String[] columns=new String[months.size()+3];
		columns[0]="lb";
		columns[1]="mc";
		int i1=2;
		for(String m:months){
			columns[i1]="A"+i1;
			i1++;
		}
		columns[months.size()+2]="sum";
		ExportExcelUtils.exportExcel(symServ.getDeptMess(deptid).getDept_name(), headers, columns, result,
				out, "");
		out.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}
	
	/**
	 * 当期指标合计
	 * @return
	 */
	public String SelectDataSum(){
		try{
		if(month==null||month.length()==0)
			month=BasicUtil.getLastMonth(BasicUtil.getCurrentTimeOfMonth());
		List<DataInfo> datalist=dataInfoServ.getDataSum(month);
		ActionContext.getContext().put("dataList",getDataMapbyDept(datalist));
		List<Dept> d2=new ArrayList<Dept>();
		List<Integer> l1=symServ.getAllDept();
		for(Integer d1:l1){
			if(l1.contains(d1)&&d1!=101){
				Dept d=symServ.getDeptMess(d1);
				d2.add(d);
			}
		}
		ActionContext.getContext().put("deptlist",d2);
		List<Target> tar=new ArrayList<Target>();
		tar.add(targetServ.getTarget("16"));
		tar.add(targetServ.getTarget("117"));
		tar.add(targetServ.getTarget("52"));
		tar.add(targetServ.getTarget("53"));
		tar.add(targetServ.getTarget("54"));
		tar.add(targetServ.getTarget("60"));
		tar.add(targetServ.getTarget("61"));
		tar.add(targetServ.getTarget("62"));
		tar.add(targetServ.getTarget("63"));
		tar.add(targetServ.getTarget("66"));
		tar.add(targetServ.getTarget("69"));
		tar.add(targetServ.getTarget("70"));
		tar.add(targetServ.getTarget("71"));
		tar.add(targetServ.getTarget("72"));
		tar.add(targetServ.getTarget("73"));
		tar.add(targetServ.getTarget("77"));
		tar.add(targetServ.getTarget("80"));
		tar.add(targetServ.getTarget("142"));
		tar.add(targetServ.getTarget("85"));
		tar.add(targetServ.getTarget("92"));
		tar.add(targetServ.getTarget("93"));
		tar.add(targetServ.getTarget("86"));
		tar.add(targetServ.getTarget("87"));
		tar.add(targetServ.getTarget("88"));
		tar.add(targetServ.getTarget("89"));
		tar.add(targetServ.getTarget("90"));
		tar.add(targetServ.getTarget("91"));
		ActionContext.getContext().put("tarlist",tar);
		ActionContext.getContext().put("tarsize",tar.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Sum1";
	}
	
	public String tx(){
		//----------------------------图表--------------------------
		try{
		List<Dept> d2=new ArrayList<Dept>();
		List<Integer> l1=symServ.getAllDept();
		String month1="[";
		for(Integer d1:l1){
			if(l1.contains(d1)&&d1!=101){
				Dept d=symServ.getDeptMess(d1);
				d2.add(d);
				month1+="'"+d.getDept_name()+"',";
			}
		}
		ActionContext.getContext().put("deptname", month);
		ActionContext.getContext().put("monthl",month1.substring(0,month1.length()-1)+"]");
		List<DataInfo> datalist=dataInfoServ.getDataSum(month);
		Target tar=targetServ.getTarget(tarid);
				ActionContext.getContext().put("zhibiaostr","['"+tar.getValue()+"']");
				List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
					List<Double> list=new ArrayList<Double>();
					for(Dept o:d2){
						int x=0;
						for(DataInfo s:datalist){
							if(s.getDept().getId()==o.getId()&&s.getTargets().getId()==tar.getId()){
								list.add(jisuan.getDouble(s.getValue()));
								x=1;
							}
						}
						if(x==0)
							list.add(0.00);
					}
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("name",tar.getValue());
					map.put("type","bar");
					map.put("data",list);
						List<Map<String,Object>> listmap1=new ArrayList<Map<String,Object>>();
						Map<String,Object> map1=new HashMap<String,Object>();
						map1.put("type","max");
						map1.put("name","最大值");
						listmap1.add(map1);
						Map<String,Object> map11=new HashMap<String,Object>();
						map11.put("type","min");
						map11.put("name","最小值");
						listmap1.add(map11);
						Map<String,Object> map2=new HashMap<String,Object>();
						map2.put("data",listmap1);
						
						List<Map<String,Object>> listmap2=new ArrayList<Map<String,Object>>();
						Map<String,Object> map3=new HashMap<String,Object>();
						map3.put("type","average");
						map3.put("name","平均值");
						listmap2.add(map3);
						Map<String,Object> map4=new HashMap<String,Object>();
						map4.put("data",listmap2);
						
					map.put("markPoint",map2);
					map.put("markLine",map4);
					listmap.add(map);
				ActionContext.getContext().put("json",JSON.toJSONString(listmap));
		}catch(Exception e){
			e.printStackTrace();
		}
				return "tx";
	}
	
	/**
	 * 历史指标合计
	 * @return
	 */
	public String SelectDataSum1(){
		if(deptid==0)
			deptid=BasicUtil.getDeptId();
		if(stime==null||stime.length()==0)
			stime=Integer.valueOf(BasicUtil.getCurrentYear())-1+"-12";
		if(etime==null||etime.length()==0)
			etime=BasicUtil.getLastMonth(BasicUtil.getCurrentTimeOfMonth());
		List<Integer> l=symServ.getAllDept();
		if(l.contains(deptid)){
			List<DataInfo> datalist=dataInfoServ.getDataSum1(deptid,stime,etime);
			List<String> month=dataInfoServ.getDataSumMonth(deptid,stime,etime);
			ActionContext.getContext().put("dataList",getDataMapbyTarget(datalist));
			ActionContext.getContext().put("monthlist",month);
			ActionContext.getContext().put("tarlist",getTarDataMapbyTarget(targetServ.getTarget(stime,symServ.getDeptMess(deptid).getDept_type().getId())));
		}else{
			ActionContext.getContext().put("datalist",1);
		}
		
		return "Sum2";
	}
	
	public String tx1(){
		//================图标开始---------------
		if(deptid==0)
			deptid=BasicUtil.getDeptId();
		if(stime==null||stime.length()==0)
			stime=Integer.valueOf(BasicUtil.getCurrentYear())-1+"-12";
		if(etime==null||etime.length()==0)
			etime=BasicUtil.getLastMonth(BasicUtil.getCurrentTimeOfMonth());
		Target tar=targetServ.getTarget(tarid);
		List<Integer> l=symServ.getAllDept();
		if(l.contains(deptid)){
			List<DataInfo> datalist=dataInfoServ.getDataSum1(deptid,stime,etime);
			ActionContext.getContext().put("deptname",symServ.getDeptMess(deptid).getDept_name());
			ActionContext.getContext().put("zhibiaostr","['"+tar.getValue()+"']");
			List<String> month=dataInfoServ.getDataSumMonth(deptid,stime,etime);
			String monthl="[";
			for(String s:month){
				monthl+="'"+s+"',";
			}
			monthl=monthl.substring(0,monthl.length()-1)+"]";
			ActionContext.getContext().put("monthl", monthl);
			
			//json
			List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
				List<Double> list=new ArrayList<Double>();
				for(String o:month){
					for(DataInfo s:datalist){
						if(s.getMonth().equals(o)&&s.getTargets().getId()==tar.getId()){
							list.add(jisuan.getDouble(s.getValue()));
						}
					}
				}
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("name",tar.getValue());
				map.put("type","line");
				map.put("data",list);
					List<Map<String,Object>> listmap1=new ArrayList<Map<String,Object>>();
					Map<String,Object> map1=new HashMap<String,Object>();
					map1.put("type","max");
					map1.put("name","最大值");
					listmap1.add(map1);
					Map<String,Object> map11=new HashMap<String,Object>();
					map11.put("type","min");
					map11.put("name","最小值");
					listmap1.add(map11);
					Map<String,Object> map2=new HashMap<String,Object>();
					map2.put("data",listmap1);
					
					List<Map<String,Object>> listmap2=new ArrayList<Map<String,Object>>();
					Map<String,Object> map3=new HashMap<String,Object>();
					map3.put("type","average");
					map3.put("name","平均值");
					listmap2.add(map3);
					Map<String,Object> map4=new HashMap<String,Object>();
					map4.put("data",listmap2);
					
				map.put("markPoint",map2);
				map.put("markLine",map4);
				listmap.add(map);
				ActionContext.getContext().put("json",JSON.toJSONString(listmap));
		}
		//------------------------图表结束-----------------------
		return "tx2";
	}
	
	/**
	 * 将数据根据部门分组
	 * @param datalist
	 * @return
	 */
	public Map<Dept,List<DataInfo>> getDataMapbyDept(List<DataInfo> datalist){
		Map<Dept,List<DataInfo>> map = new TreeMap<Dept, List<DataInfo>>(
				new Comparator<Dept>(){
					@Override
					public int compare(Dept o1, Dept o2) {
						return o1.getId() - o2.getId();
					}
            }
		);
		for(DataInfo da:datalist){
			List<DataInfo> list = map.get(da.getDept());
			if (list != null) {
				list.add(da);
			} else {
				ArrayList<DataInfo> arrayList = new ArrayList<DataInfo>();
				arrayList.add(da);
				map.put(da.getDept(), arrayList);
			}
		}
		return map;
	}
	
	/**
	 * 将数据根据指标分组
	 * @param datalist
	 * @return
	 */
	public Map<Target,List<DataInfo>> getDataMapbyTarget(List<DataInfo> datalist){
		Map<Target,List<DataInfo>> map = new TreeMap<Target, List<DataInfo>>(
				new Comparator<Target>(){
					@Override
					public int compare(Target o1, Target o2) {
						return o1.getId() - o2.getId();
					}
            }
		);
		for(DataInfo da:datalist){
			List<DataInfo> list = map.get(da.getTargets());
			if (list != null) {
				list.add(da);
			} else {
				ArrayList<DataInfo> arrayList = new ArrayList<DataInfo>();
				arrayList.add(da);
				map.put(da.getTargets(), arrayList);
			}
		}
		return map;
	}
	
	/**
	 * 将指标根据一级指标分组
	 * @param datalist
	 * @return
	 */
	public Map<Target,List<Target>> getTarDataMapbyTarget(List<Target> tarlist){
		Map<Target,List<Target>> map=new LinkedHashMap<Target,List<Target>>();
		if(tarlist!=null&&!tarlist.isEmpty()){
			for(Target t:tarlist){
				if(t.getParentid()==null){
					List<Target> targetlist=new ArrayList<Target>();
					for(Target t1:tarlist){
						if(t1.getParentid()!=null&&t1.getParentid().equals(String.valueOf(t.getId()))){
						targetlist.add(t1);
						}
					}
					map.put(t,targetlist);
				}
			}
			return map;
		}else{
			return null;
		}
	}
	
	//===============================get   and    set=============================//
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}


	public int getC() {
		return c;
	}


	public void setC(int c) {
		this.c = c;
	}


	public String getTarid() {
		return tarid;
	}


	public void setTarid(String tarid) {
		this.tarid = tarid;
	}
}
