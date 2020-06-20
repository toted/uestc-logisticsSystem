package cn.uestc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.uestc.mapper.SessionMapper;
import cn.uestc.pojo.Session;
import cn.uestc.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	SessionMapper sessionMapper;
	
	@Override
	public int selectCount(Session session) {
		return sessionMapper.selectCount(session);
	}
	@Override
	public void insert(Session session) {
		sessionMapper.insert(session);
	}
	@Override
	public Session select(Session session) {
		return sessionMapper.select(session);
	}
	@Override
	public void update(Session session) {
		sessionMapper.update(session);
	}
	@Override
	public int selectSessionCount(Session session) {
		return sessionMapper.selectSessionCount(session);
	}
	@Override
	public String selectSession(Session session) {
		return sessionMapper.selectSession(session);
	}
}
