package com.dzhf.util;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import com.dzhf.dao.DataInfoDao;
import com.dzhf.daoImpl.DataInfoDaoImpl;
import com.dzhf.model.DataInfo;

/**
 * 项目中涉及的公式计算
 * @author dzxy 惠晓东
 * @jisuan.java
 * 创建时间：@2016年3月30日 @下午5:57:30
 */

public class jisuan {
	
	/**
	 * 获取考核利润
	 * 考核利润=账面利润+调整利润
	 * b3=b1+调整利润
	 * @param di
	 * @return
	 */
	public static String getKHLR(String... str){
		try{
			Double d=getDouble(str[0]);
			Double d1=getDouble(str[1]);
			Double dd=d+d1;
			return FormatDouble(dd);
		}catch(Exception e){
			return "计算失败";
		}
	}
	 
	/**
	 * 获取调整利润
	 * 调整利润=账外下脚收入+其他收入+增原料成本+增销售成本+增工资、保险+股息+折旧-放入待摊中当期应计未计成本、费用 -当期少提成本、费用-其他（补缴以前年度税金等）
	 * J12=J1+J2+j3+j4+j5+j7+j8-j9-j10-j11
	 * @param di
	 * @return
	 */
	public static String getTZLR(String... str){
		try{
			Double d0=getDouble(str[0]);
			Double d1=getDouble(str[1]);
			Double d2=getDouble(str[2]);
			Double d3=getDouble(str[3]);
			Double d4=getDouble(str[4]);
			Double d5=getDouble(str[5]);
			Double d6=getDouble(str[6]);
			Double d7=getDouble(str[7]);
			Double d8=getDouble(str[8]);
			Double d9=getDouble(str[9]);
			Double d10=getDouble(str[10]);
			Double d11=getDouble(str[11]);
			Double d12=getDouble(str[12]);
			Double d13=getDouble(str[13]);
			Double d14=getDouble(str[14]);
			Double d15=getDouble(str[15]);
			Double d16=getDouble(str[16]);
			Double d17=getDouble(str[17]);
			Double d18=getDouble(str[18]);
			Double d19=getDouble(str[19]);
			Double d20=getDouble(str[20]);
			Double dd=d0+d1+d2+d3+d4+d5+d6-d7+d8-d9-d10-d11-d12+d13+d14+d15+d16+d17+d18+d19+d20;
			return FormatDouble(dd);
		}catch(Exception e){
			return "计算失败";
		}
	}

	
	/**
	 * 获取现金净流量合计(测试有效)
	 * 现金净流量合计=银行贷款加减+个人贷款加减-应收账款加减+应付账款加减-库存及在制品加减
	 * h6=h1+h2-h3+h4-h5
	 * @param di
	 * @return
	 */
	public static String getXJJLLHJ(String... str){
		try{
			Double d0=getDouble(str[0]);
			Double d1=getDouble(str[1]);
			Double d2=getDouble(str[2]);
			Double d3=getDouble(str[3]);
			Double d4=getDouble(str[4]);
			Double dd=d0+d1-d2+d3-d4;
			return FormatDouble(dd);
		}catch(Exception e){
			return "计算失败";
		}
	}
	
	/**
	 * 获取高附加值产品占比合计。
	 * 高附加值产品占比合计=
	 * +480锭40支及以上赛络纺单机台加工费1300元，赛络紧密纺1450元及以上占比
	 * +480锭30支-40支赛络纺单机台加工费1500元，赛络紧密纺1650元及以上占比
	 * +480锭20支-30支赛络纺单机台加工费1800元，赛络紧密纺1950元及以上占比
	 * +480锭20支以下赛络纺单机台加工费2000元占比
	 * g5=g1+g2+g3+g4
	 * @param di
	 * @return
	 */
	public static String getGFJZCPZBHJ(String... str){
		try{
			Double d=getDouble(str[0]);
			Double d1=getDouble(str[1]);
			Double d2=getDouble(str[2]);
			Double d3=getDouble(str[3]);
			Double dd=d+d1+d2+d3;
			return FormatDouble(dd);
		}catch(Exception e){
			return "计算失败";
		}
	}
	
	/**
	 * 万锭产量=折合40只实际完成产量/公司规模*30/实际工作天数
	 * c4=(c3/guimo)*(30/tianshu)
	 * @param di
	 * @return
	 */
	public static String getWDCL(String... str){
		try{
			Double d=getDouble(str[0]);
			Double d1=getDouble(str[1]);
			Double d2=getDouble(str[2]);
			Double dd=(d/d1)*(30/d2);
			return FormatDouble(dd);
		}catch(Exception e){
			return "计算失败";
		}
	}
	
	/**
	 * 获取考核利润完成百分比
	 * 由计划数和实际数决定
	 * 考核利润完成百分比     b4
	 * 计划数 b2
	 * 实际数 b3
	 * 
	 * 5种情况
	 * b2<0   b3<0  计划数b2>实际数b3  b4=-(b3-b2)/b2
	 * b2<0   b3<0  计划数b2<实际数b3  b4=1-[(b3-b2)/b2]
	 * b2<0   b3>0 b4=-(b3-b2)/b2
	 * b2>0   b3<0 b4=(b3-b2)/b2
	 * b2>0   b3>0 b4=b3/b2
	 * 
	 * @param di
	 * @return
	 */
	public static String getKHLRWCBFB(String jihuashu,String shijishu){
		try{
			Double d=getDouble(jihuashu);
			Double d1=getDouble(shijishu);
			Double dd=0.00;
			Double j=d;    //计划数
			Double s=d1;    //实际数
			if(j>0){
				if(s<0)
					dd=s/j-1;
				else
					dd=s/j;
			}else{
				if(s>0)
					dd=1-s/j;
				else{
					if(j<s)
						dd=2-s/j;
					else
						dd=1-s/j;
				}
			}
			return FormatBFS(dd);
		}catch(Exception e){
			return "计算失败";
		}
	}
	
	/**
	 * 将数组的元素转化为double，抛出异常则返回0
	 * @param str
	 * @return
	 */
	public static Double getDouble(String str){
		Double d=0.00;
		try{
			DecimalFormat df=new DecimalFormat("######0.00");  
			d=Double.parseDouble(str);
			df.format(d);
		}catch(Exception e){
			d=0.00;
		}
		return d;
	}
	/**
	 * 格式化String类型保留2位小数转化String
	 * @param str
	 * @return
	 */
	public static String getDouble2(String str){
		Double d=0.00;
		try{
			DecimalFormat df=new DecimalFormat("######0.00");  
			d=Double.parseDouble(str);
			df.format(d);
		}catch(Exception e){
			d=0.00;
		}
		return String.format("%.2f",d);
	}
	/**
	 * 格式化String类型保留4位小数转化String
	 * @param str
	 * @return
	 */
	public static String getDouble4(String str){
		Double d=0.0000;
		try{
			DecimalFormat df=new DecimalFormat("######0.00000");  
			d=Double.parseDouble(str);
			df.format(d);
		}catch(Exception e){
			d=0.00;
		}
		return String.format("%.4f",d);
	}
	/**
	 * 格式化String类型转换为整数
	 * @param str
	 * @return
	 */
	public static String getInt(Double d){
		int doubleToInt = d.intValue();
		return String.valueOf(doubleToInt);
	}
	/**
	 * 格式化Double类型保留2位小数转化String
	 * @param d
	 * @return
	 */
	public static String FormatDouble(Double d){
		return String.format("%.2f",d);
	}
	
	/**
	 * 格式化Double类型保留2位小数转化String
	 * @param d
	 * @return
	 */
	private static String FormatBFS(Double d){
		return String.format("%.2f",d*100)+"%";
	}
	/**
	 * 格式化String类型保留2位小数转化String
	 * @param d
	 * @return
	 */
	public static String FormatBFS(String d){
		String aNew="";
		int x=d.indexOf(".")+1;
		int y =d.indexOf("%")+1;
		if(x>1&&y>6){
			aNew=d.substring(0,x+2)+"%";
		}else{
			aNew=d;
		}
		return aNew;
	}
	
	/**
	 * 多个数值的和
	 * @param s
	 * @return
	 */
	public static String getSum(String... s){
		Double dd=0.0000;
		for(String ss:s){
			dd+=getDouble(ss);
		}
		return FormatDouble(dd);
	}
	/**
	  * 判断字符串是否是整数
	  */
	 public static boolean isInteger(String value) {
	  try {
	   Integer.parseInt(value);
	   return true;
	  } catch (NumberFormatException e) {
	   return false;
	  }
	 }

	 /**
	  * 判断字符串是否是浮点数
	  */
	 public static boolean isDouble(String value) {
	  try {
	   Double.parseDouble(value);
	   if (value.contains("."))
	    return true;
	   return false;
	  } catch (NumberFormatException e) {
	   return false;
	  }
	 }

	 /**
	  * 判断字符串是否是数字
	  */
	 public static boolean isNumber(String value) {
	  return isInteger(value) || isDouble(value);
	 }
}
