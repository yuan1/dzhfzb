package com.dzhf.util;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dzhf.model.Dept;
import com.dzhf.model.Users;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author xuwei
 * @version 2015-3-28 下午02:33:30
 */
public class BasicUtil {

	/**
	 * 获得经过MD5加密的密码
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		StringBuffer sb = new StringBuffer(32);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(str.getBytes("UTF-8"));
			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.toUpperCase().substring(1, 3));
			}
		} catch (Exception e) {
			return null;
		}
		return sb.toString();
	}

	/**
	 * 获取上月月份
	 * 输入格式2016-04
	 * 输出格式2016-04
	 * @param month
	 * @return
	 */
	public static String getLastMonth(String month){
		String[] m=month.split("-");
		if(!m[1].equals("01")){
			m[1]=String.valueOf(Integer.valueOf(m[1])-1);
			if(m[1].length()==1)
				m[1]="0"+m[1];
		}else{
			m[0]=String.valueOf(Integer.valueOf(m[0])-1);
			m[1]="12";
		}
		return m[0]+"-"+m[1];
	}
	
	/**
	 * 获得格式化的时间
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

	/**
	 * 获得格式化的日期
	 * 
	 * @return
	 */
	public static String getCurrentTimeOfDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}
	
	/**
	 * 根据时间判断季度
	 * @param str
	 * @return
	 */
	public static String getQuarery(String str){
		String[] str1=str.split("-");
		String mon=str1[1];
		String x="";
		if(mon.equals("01")||mon.equals("02")||mon.equals("03"))
			x= "1";
		if(mon.equals("04")||mon.equals("05")||mon.equals("06"))
			x= "2";
		if(mon.equals("07")||mon.equals("08")||mon.equals("09"))
			x= "3";
		if(mon.equals("10")||mon.equals("11")||mon.equals("12"))
			x= "4";
		return x;
	}
	
	/**
	 * 获得格式化的月份
	 * 
	 * @return
	 */
	public static String getCurrentTimeOfMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		return df.format(new Date());
	}

	/**
	 * 获得年份
	 * 
	 * @return
	 */
	public static String getCurrentYear() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(new Date());
	}

	/**
	 * 获得session中user
	 * 
	 * @return
	 */
	public static Users getUsers() {
		return (Users) ActionContext.getContext().getSession().get("users");
	}

	/**
	 * 获得session中dept
	 * 
	 * @return
	 */
	public static Dept getdept() {
		return (Dept) ActionContext.getContext().getSession().get("dept");
	}

	/**
	 * 创建新名字
	 * 
	 * @param oldFileName
	 * @return
	 */
	public static String createNewName(String oldFileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(Calendar.getInstance().getTime())
				+ (int) (Math.random() * 10000) + "."
				+ getExtensionName(oldFileName);
	}

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/***
	 * 获得两个日期相差的天数
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate, String bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 将字符串按照格式转化为date类型
	 * 
	 * @param dateStr
	 * @param formaterString
	 * @return
	 */
	public static Date toDate(String dateStr, String formaterString) {
		Date date = null;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(formaterString);
		try {
			date = formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	

	// 获得Session中的userId
	public static int getUserId() {
		return (Integer) ActionContext.getContext().getSession().get("usersid");
	}
	// 获得Session中的dept_id
	public static int getDeptId() {
		return (Integer) ActionContext.getContext().getSession().get("dept_id");
	}

	/**
	 * 计算百分比
	 * @param x
	 * @param total
	 * @return
	 */
	public static String getPercent(Double x,Double total){  
		   String result="";//接受百分比的值  
		   double tempresult=x/total;  
		   //NumberFormat nf   =   NumberFormat.getPercentInstance();     注释掉的也是一种方法  
		   //nf.setMinimumFractionDigits( 2 );        保留到小数点后几位  
		   DecimalFormat df1 = new DecimalFormat("0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐  
		   //result=nf.format(tempresult);     
		   result= df1.format(tempresult);
		   return result;  
		} 
	
	/**
	 * 判断某个字符串数组里是否含有某个字符串
	 * @param str
	 * @param s
	 * @return
	 */
	public static boolean isHaving(String[] str,String s){
		boolean b=false;
		if(str!=null&&str.length>0){
			for(String s1:str){
				if(s1.equals(s))
					b=true;
			}
		}
		return b;
	}
}
