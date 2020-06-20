package cn.uestc.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.uestc.pojo.User;

public interface UserMapper {

	@Insert("insert into user(userId,password,email,code,isVip,status) values(#{userId},#{password},#{email},#{code},#{isVip},#{status})")
	public void insert(User user);
	@Delete("delete from user where userId = #{userId}")
	public void delete(User user);
	@Update("update user set status = #{status},code = #{code},password = #{password} where userId = #{userId}")
	public void update(User user);
	@Select("select count(*) from user where userId = #{userId} or email = #{email}")
	public int selectCount(User user);
	@Select("select * from user where userId = #{userId} or email = #{email}")
	public User select(User user);
	@Select("select * from user where userId = #{userId} and password = #{password}")
	public User loginSelectCount(User user);
	@Select("select * from user where email = #{email} and password = #{password}")
	public User loginSelectCount1(User user1);

}
