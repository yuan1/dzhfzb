package com.dzhf.util;

import java.io.File;
import jxl.Sheet;
import jxl.Workbook;

public class readexcel {
	public static String[][] getAllByExcel(String file){
        String[][] str = null;
		try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            str=new String[rows][clos];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j <clos; j++) {
                	str[i][j]=rs.getCell(j,i).getContents();
                }
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return str;
    }
	
	/**
	 * 获取某个字符在二维数组中第一次出现所在的行
	 * @param str
	 * @return
	 */
	public static int getrows(String[][] str,String str1){
		for(int i=0;i<str.length;i++){
			for(int j=0;j<str[i].length;j++){
				if(str[i][j].contains(str1)){
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * 获取某个字符在二维数组中第一次出现的列
	 * @param str
	 * @param str1
	 * @return
	 */
	public static int getclo(String[][] str,String str1){
		for(int i=0;i<str.length;i++){
			for(int j=0;j<str[i].length;j++){
				if(str[i][j].contains(str1)){
					return j;
				}
			}
		}
		return -1;
	}
	
	/**
	 * 给定一个数组和一个字符串，以字符串第一次出现位置为0，0点重置数组
	 * @param str
	 * @param str1
	 * @return
	 */
	public static String[][] reset(String[][] str,String str1){
		int x=getrows(str,str1);
        int y=getclo(str,str1);
        int x1=str.length-x;
        int y1=str[0].length-y;
        String[][] newstr=new String[x1][y1];
		if(str.length>0){
	        for(int i=0;i<x1;i++){
	        	for(int j=0;j<y1;j++){
	        		newstr[i][j]=str[i+x][j+y];
	        	}
	        }
        }
		return newstr;
	}
	
	/**
	 * 删除二维数组的空行
	 * @param str
	 * @return
	 */
	public static String[][] delnull(String[][] str){
		int nullrow=0;
		for(int i=0;i<str.length;i++){
			String str1="";
			for(int j=0;j<str[i].length;j++){
				str1+=str[i][j];
			}
			if(str1.length()==0){
				nullrow++;
			}
		}
		int nullrowindex=0;
		String[][] newstr=new String[str.length-nullrow][str[0].length];
		for(int i=0;i<newstr.length;i++){
			String str1="";
			for(int j=0;j<str[i+nullrowindex].length;j++){
				str1+=str[i+nullrowindex][j];
			}
			if(str1.length()!=0){
		        for(int j=0;j<str[0].length;j++){
		        	newstr[i][j]=str[i+nullrowindex][j];
		        }
			}else{
				nullrowindex++;
				i--;
			}
		}
		return newstr;
	}
	
	/**
	 * 删除二维数组中带有某个字符的行
	 * @param str
	 * @param s
	 * @return
	 */
	public static String[][] delString(String[][] str,String s){
		int nullrow=0;
		for(int i=0;i<str.length;i++){
			String str1="";
			for(int j=0;j<str[i].length;j++){
				str1+=str[i][j];
			}
			if(str1.contains(s)){
				nullrow++;
			}
		}
		String[][] newstr=new String[str.length-nullrow][str[0].length];
		int nullrowindex=0;
		for(int i=0;i<newstr.length;i++){
			String str1="";
			for(int j=0;j<str[i+nullrowindex].length;j++){
				str1+=str[i+nullrowindex][j];
			}
			if(!str1.contains(s)){
		        for(int j=0;j<str[0].length;j++){
		        	newstr[i][j]=str[i+nullrowindex][j];
		        }
			}else{
				nullrowindex++;
				i--;
			}
		}
		return newstr;
	}
	
	/**
	 * 删除二维数组的某一行
	 * @param str
	 * @param s
	 * @return
	 */
	public static String[][] delrowbyindex(String[][] str,int s){
		String[][] newstr=new String[str.length-1][str[0].length];
		int nullrowindex=0;
		for(int i=0;i<newstr.length;i++){
			if(s!=i+nullrowindex){
		        for(int j=0;j<str[0].length;j++){
		        	newstr[i][j]=str[i+nullrowindex][j];
		        }
			}else{
				nullrowindex++;
				i--;
			}
		}
		return newstr;
	}
	
	/**
	 * 删除二维数组的空列
	 * @param str
	 * @return
	 */
	public static String[][] delnullclo(String[][] str){
		int nullclo=0;
		for(int i=0;i<str[0].length;i++){
			String str1="";
			for(int j=0;j<str.length;j++){
				str1+=str[j][i];
			}
			if(str1.length()==0){
				nullclo++;
			}
		}
		int nullcloindex=0;
		String[][] newstr=new String[str.length][str[0].length-nullclo];
		for(int i=0;i<newstr[0].length;i++){
			String str1="";
			for(int j=0;j<str.length;j++){
				str1+=str[j][i+nullcloindex];
			}
			if(str1.length()!=0){
		        for(int j=0;j<str.length;j++){
		        	newstr[j][i]=str[j][i+nullcloindex];
		        }
			}else{
				nullcloindex++;
				i--;
			}
		}
		return newstr;
	}
	
	/**
	 * 根据列索引删除某列
	 * @param str
	 * @param s
	 * @return
	 */
	public static String[][] delclobyindex(String[][] str,int s){
		String[][] newstr=new String[str.length][str[0].length-1];
		int nullrowindex=0;
		for(int i=0;i<newstr[0].length;i++){
			if(s!=i+nullrowindex){
		        for(int j=0;j<str.length;j++){
		        	newstr[j][i]=str[j][i+nullrowindex];
		        }
			}else{
				nullrowindex++;
				i--;
			}
		}
		return newstr;
	}
}
