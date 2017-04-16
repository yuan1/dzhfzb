package com.dzhf.util;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import com.dzhf.dao.DataInfoDao;
import com.dzhf.daoImpl.DataInfoDaoImpl;
import com.dzhf.model.DataInfo;

/**
 * ��Ŀ���漰�Ĺ�ʽ����
 * @author dzxy ������
 * @jisuan.java
 * ����ʱ�䣺@2016��3��30�� @����5:57:30
 */

public class jisuan {
	
	/**
	 * ��ȡ��������
	 * ��������=��������+��������
	 * b3=b1+��������
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
			return "����ʧ��";
		}
	}
	 
	/**
	 * ��ȡ��������
	 * ��������=�����½�����+��������+��ԭ�ϳɱ�+�����۳ɱ�+�����ʡ�����+��Ϣ+�۾�-�����̯�е���Ӧ��δ�Ƴɱ������� -��������ɱ�������-������������ǰ���˰��ȣ�
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
			return "����ʧ��";
		}
	}

	
	/**
	 * ��ȡ�ֽ������ϼ�(������Ч)
	 * �ֽ������ϼ�=���д���Ӽ�+���˴���Ӽ�-Ӧ���˿�Ӽ�+Ӧ���˿�Ӽ�-��漰����Ʒ�Ӽ�
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
			return "����ʧ��";
		}
	}
	
	/**
	 * ��ȡ�߸���ֵ��Ʒռ�Ⱥϼơ�
	 * �߸���ֵ��Ʒռ�Ⱥϼ�=
	 * +480��40֧����������ĵ���̨�ӹ���1300Ԫ��������ܷ�1450Ԫ������ռ��
	 * +480��30֧-40֧����ĵ���̨�ӹ���1500Ԫ��������ܷ�1650Ԫ������ռ��
	 * +480��20֧-30֧����ĵ���̨�ӹ���1800Ԫ��������ܷ�1950Ԫ������ռ��
	 * +480��20֧��������ĵ���̨�ӹ���2000Ԫռ��
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
			return "����ʧ��";
		}
	}
	
	/**
	 * �򶧲���=�ۺ�40ֻʵ����ɲ���/��˾��ģ*30/ʵ�ʹ�������
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
			return "����ʧ��";
		}
	}
	
	/**
	 * ��ȡ����������ɰٷֱ�
	 * �ɼƻ�����ʵ��������
	 * ����������ɰٷֱ�     b4
	 * �ƻ��� b2
	 * ʵ���� b3
	 * 
	 * 5�����
	 * b2<0   b3<0  �ƻ���b2>ʵ����b3  b4=-(b3-b2)/b2
	 * b2<0   b3<0  �ƻ���b2<ʵ����b3  b4=1-[(b3-b2)/b2]
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
			Double j=d;    //�ƻ���
			Double s=d1;    //ʵ����
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
			return "����ʧ��";
		}
	}
	
	/**
	 * �������Ԫ��ת��Ϊdouble���׳��쳣�򷵻�0
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
	 * ��ʽ��String���ͱ���2λС��ת��String
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
	 * ��ʽ��String���ͱ���4λС��ת��String
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
	 * ��ʽ��String����ת��Ϊ����
	 * @param str
	 * @return
	 */
	public static String getInt(Double d){
		int doubleToInt = d.intValue();
		return String.valueOf(doubleToInt);
	}
	/**
	 * ��ʽ��Double���ͱ���2λС��ת��String
	 * @param d
	 * @return
	 */
	public static String FormatDouble(Double d){
		return String.format("%.2f",d);
	}
	
	/**
	 * ��ʽ��Double���ͱ���2λС��ת��String
	 * @param d
	 * @return
	 */
	private static String FormatBFS(Double d){
		return String.format("%.2f",d*100)+"%";
	}
	/**
	 * ��ʽ��String���ͱ���2λС��ת��String
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
	 * �����ֵ�ĺ�
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
	  * �ж��ַ����Ƿ�������
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
	  * �ж��ַ����Ƿ��Ǹ�����
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
	  * �ж��ַ����Ƿ�������
	  */
	 public static boolean isNumber(String value) {
	  return isInteger(value) || isDouble(value);
	 }
}
