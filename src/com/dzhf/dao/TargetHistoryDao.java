package com.dzhf.dao;

import java.util.List;

import com.dzhf.commonDao.BaseDao;
import com.dzhf.model.TargetHistory;

public interface TargetHistoryDao extends BaseDao<TargetHistory>{

	void delTargetHistory(String month,int depttypeid);
	
	List<String> getMonths();
}
