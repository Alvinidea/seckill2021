package com.alvin.seckill.service;

import com.alvin.seckill.mapper.GoodsMapper;
import com.alvin.seckill.pojo.SeckillGoods;
import com.alvin.seckill.pojo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<GoodsVo> getGoodsVoList(){
        return goodsMapper.getGoodsVoList();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId)
    {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    public boolean reduceStock(GoodsVo goodsVo)
    {
        SeckillGoods sg = new SeckillGoods();
        sg.setGoodsId(goodsVo.getId());
        int ret = goodsMapper.reduceStock(sg);
        return ret > 0;
    }


}
