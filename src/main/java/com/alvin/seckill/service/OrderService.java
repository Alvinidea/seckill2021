package com.alvin.seckill.service;

import com.alvin.seckill.mapper.OrdersMapper;
import com.alvin.seckill.pojo.Orders;
import com.alvin.seckill.pojo.SeckillOrder;
import com.alvin.seckill.pojo.User;
import com.alvin.seckill.pojo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    public SeckillOrder getSeckillOrderByUserIdGoodsId(
            long userId, long goodsId){
        return new SeckillOrder();
    }

    public Orders getOrderById(long orderId)
    {
        return ordersMapper.getOrderById(orderId);
    }
    /**
     *  输入：User，GoodsVo
     *  创建订单，秒杀订单
     *
     * */
    public Orders createOrders(User user, GoodsVo goods)
    {

        Orders orderInfo = new Orders();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(Long.valueOf(user.getNickname()));
        ordersMapper.insert(orderInfo);
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setUserId(Long.valueOf(user.getNickname()));
        ordersMapper.insertSeckillOrder(seckillOrder);
        // redisService.set(OrderKey.getMiaoshaOrderByUidGid,""+user.getNickname()+"_"+goods.getId(), seckillOrder) ;
        return orderInfo;
    }

/*    public void closeOrder(int hour){
        Date closeDateTime = DateUtils.addHours(new Date(),-hour);
        List<Orders> orderInfoList = ordersMapper.selectOrderStatusByCreateTime(
                Integer.valueOf(ORDER_NOT_PAY.ordinal()), DateTimeUtils.dateToStr(closeDateTime));
        for (Orders orderInfo:orderInfoList){
            System.out.println("orderinfo  infomation "+orderInfo.getGoodsName());
        }
    } */
}
