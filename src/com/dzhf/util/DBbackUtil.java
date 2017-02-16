package com.dzhf.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class DBbackUtil {

	public static String load(String fileurl) {
		Properties prop = getConnInfo();
		String dbname = prop.getProperty("jdbc.dbname");
		String username = prop.getProperty("jdbc.username");
		String password = prop.getProperty("jdbc.password");
		String stmt2 = "mysql -P 3309 -u " + username + " -p" + password + " " + dbname
				+ " < " + fileurl;
		String[] cmd = { "cmd", "/c", stmt2 };
		try {
			Runtime.getRuntime().exec(cmd);
			return "还原数据库成功!";
		} catch (IOException e) {
			e.printStackTrace();
			return "还原数据库失败!";
		}

	}

	public static String backup(String backdir) {
		Properties prop = getConnInfo();
		String port=prop.getProperty("jdbc.port");
		String dbname = prop.getProperty("jdbc.dbname");
		String username = prop.getProperty("jdbc.username");
		String password = prop.getProperty("jdbc.password");
		File file = new File(backdir);
		if (!file.exists()) {
			file.mkdir();
		}
		String filename = FormatDate.myformatDate(new Date(), 3, "-") + ".sql";
		try {
			Runtime rt = Runtime.getRuntime();
			String path ="mysqldump --opt -P "+port+" --user="+username+" --password="+password+" --lock-all-tables=true --result-file="+backdir + filename+" --default-character-set=utf8 "+dbname;
			rt.exec("cmd /c " + path);
			/*InputStream in = child.getInputStream();
			InputStreamReader xx = new InputStreamReader(in, "utf-8");
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			FileOutputStream fout = new FileOutputStream(backdir + filename);
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
			writer.write(outStr);
			writer.flush();
			in.close();
			xx.close();
			br.close();
			writer.close();
			fout.close();*/
			return "备份成功!";
		} catch (Exception e) {
			e.printStackTrace();
			return "备份失败!";
		}

	}

	private static Properties getConnInfo() {
		String uri = "jdbc.properties";
		Properties prop = new Properties();
		InputStream inStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(uri);
		try {
			prop.load(inStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return prop;
	}

	public static String deletefile(String pathname) {
		File file = new File(pathname);
		try {
			file.delete();
			return "删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			return "删除失败!";
		}
	}
}