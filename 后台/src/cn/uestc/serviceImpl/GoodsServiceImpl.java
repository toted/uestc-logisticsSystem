package cn.uestc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.uestc.mapper.GoodsMapper;
import cn.uestc.pojo.Goods;
import cn.uestc.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	GoodsMapper goodsMapper;
	
	@Override
	public void insert(Goods goods) {
		goodsMapper.insert(goods);
	}
	@Override
	public Goods select(Goods goods) {
		return goodsMapper.select(goods);
	}
	@Override
	public List<Goods> selectId(Goods goods){
		return goodsMapper.selectId(goods);
	}
	@Override
	public List<Goods> selectAll(Goods goods){
		return goodsMapper.selectAll(goods);
	}
}
