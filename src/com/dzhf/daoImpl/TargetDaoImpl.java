package com.dzhf.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dzhf.commonDao.BaseDaoImpl;
import com.dzhf.dao.TargetDao;
import com.dzhf.model.Target;

@Repository
public class TargetDaoImpl extends BaseDaoImpl<Target> implements TargetDao{

	@Override
	public List<Target> getTopTarget() {
		return getSession().createQuery(//
				"FROM Target WHERE parentid is null order by sort")//
				.list();
	}

	@Override
	public void updateTarget(Target t) {
		try{
			Target t1=getById(t.getId());
			t1.setSum(t.getSum());
			t1.setTar_name(t.getTar_name());
			t1.setTar_prompt(t.getTar_prompt());
			t1.setValue(t.getValue());
			t1.setRemark(t.getRemark());
			t1.setIndexvalue(t.getIndexvalue());
			t1.setSort(t.getSort());
			t1.setParentid(t.getParentid());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
