package cn.uestc.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.uestc.pojo.Session;

public interface SessionMapper {

	@Select("select count(*) from sessions where userId = #{userId}")
	public int selectCount(Session session);
	@Insert("insert into sessions(userId,session,time) values(#{userId},#{session},#{time})")
	public void insert(Session session);
	@Select("select * from sessions where userId = #{userId}")
	public Session select(Session session);
	@Update("update sessions set time = #{time} where session = #{session}")
	public void update(Session session);
	@Select("select count(*) from sessions where session = #{session}")
	public int selectSessionCount(Session session);
	@Select("select userId from sessions where session = #{session}")
	public String selectSession(Session session);
}
