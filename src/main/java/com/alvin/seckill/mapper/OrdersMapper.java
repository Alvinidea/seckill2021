package com.alvin.seckill.mapper;

import com.alvin.seckill.pojo.Orders;
import com.alvin.seckill.pojo.SeckillOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {

    public SeckillOrder getSeckillOrderByUserIdGoodsId(
            @Param("userNickName") long userNickName, @Param("goodsId") long goodsId);

    public long insert(Orders orders);

    public int insertSeckillOrder(SeckillOrder seckillOrder);

    public Orders getOrderById(@Param("orderId")long orderId);

    public List<Orders> selectOrderStatusByCreateTime(
            @Param("status")Integer status, @Param("createDate") String createDate);

    public int closeOrderByOrderInfo();
}
