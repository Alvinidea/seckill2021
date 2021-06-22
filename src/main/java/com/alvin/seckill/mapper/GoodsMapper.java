package com.alvin.seckill.mapper;

import com.alvin.seckill.pojo.SeckillGoods;
import com.alvin.seckill.pojo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {

    public List<GoodsVo> getGoodsVoList();

    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    public int reduceStock(SeckillGoods seckillGoods);

}
