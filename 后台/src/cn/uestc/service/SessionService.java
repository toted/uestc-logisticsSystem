package cn.uestc.service;


import cn.uestc.pojo.Session;

public interface SessionService {

	public int selectCount(Session session);
	public void insert(Session session);
	public Session select(Session session);
	public void update(Session session);
	public int selectSessionCount(Session session);
	public String selectSession(Session session);
	
}
