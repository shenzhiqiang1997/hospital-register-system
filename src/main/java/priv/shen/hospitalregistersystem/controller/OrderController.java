package priv.shen.hospitalregistersystem.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import priv.shen.hospitalregistersystem.entity.Order;
import priv.shen.hospitalregistersystem.service.OrderService;
import priv.shen.hospitalregistersystem.vo.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("查看订单列表")
    @GetMapping("/orders")
    public Result<List<Order>> list(HttpSession session){
        return orderService.list(session);
    }

    @ApiOperation("取消订单")
    @DeleteMapping("/order/{id}")
    public Result delete(@PathVariable("id")Long id,HttpSession session) throws Exception{
        return orderService.delete(id,session);
    }


}
