package cn.uestc.service;

import java.util.List;

import cn.uestc.pojo.Goods;

public interface GoodsService {

	public void insert(Goods goods);
	public Goods select(Goods goods);
	public List<Goods> selectId(Goods goods);
	public List<Goods> selectAll(Goods goods);
	
}
