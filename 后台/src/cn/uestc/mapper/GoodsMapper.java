package cn.uestc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.uestc.pojo.Goods;

public interface GoodsMapper {

	@Insert("insert into goods(userId,goodsId,start,end,time,weight,price,wasteTime) values(#{userId},#{goodsId},#{start},#{end},#{time},#{weight},#{price},#{wasteTime})")
	public void insert(Goods goods);
	@Select("select * from goods where goodsId = #{goodsId}")
	public Goods select(Goods goods);
	@Select("select * from goods where userId = #{userId}")
	public List<Goods> selectId(Goods goods);
	@Select("select * from goods")
	public List<Goods> selectAll(Goods goods);
}
