package com.dzhf.dao;

import java.util.List;

import com.dzhf.commonDao.BaseDao;
import com.dzhf.model.Target;

public interface TargetDao extends BaseDao<Target>{
	
	public List<Target> getTopTarget();
	
	public void updateTarget(Target t);
	
}
