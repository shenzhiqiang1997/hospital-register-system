package priv.shen.hospitalregistersystem.service;

import org.apache.http.impl.cookie.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.shen.hospitalregistersystem.constant.Key;
import priv.shen.hospitalregistersystem.constant.Message;
import priv.shen.hospitalregistersystem.entity.MedicalNum;
import priv.shen.hospitalregistersystem.entity.Order;
import priv.shen.hospitalregistersystem.exception.GlobalException;
import priv.shen.hospitalregistersystem.repository.MedicalNumRepository;
import priv.shen.hospitalregistersystem.repository.OrderRepository;
import priv.shen.hospitalregistersystem.vo.Result;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MedicalNumRepository medicalNumRepository;

    public Result<List<Order>> list(HttpSession session) {
        String telNum = (String) session.getAttribute(Key.TEL_NUM.value);
        List<Order> orders = orderRepository.findAllByTelNum(telNum);
        return new Result<>(true,orders);
    }


    @Transactional
    public Result delete(Long id, HttpSession session) throws Exception{
        String telNum = (String) session.getAttribute(Key.TEL_NUM.value);
        Order order = orderRepository.getOne(id);
        if (telNum.equals(order.getTelNum())){
            long deadLine = order.getDate().getTime();
            if (order.getTime().equals("上午")){
                deadLine += 3600*1000*12;
            }else if (order.getTime().equals("下午")){
                deadLine += 3600*1000*17;
            }else if (order.getTime().equals("晚上")){
                deadLine += 3600*1000*22;
            }

            if (new Date().getTime()<deadLine){
                Long medicalNumId = order.getMedicalNumId();
                MedicalNum medicalNum = medicalNumRepository.getOne(medicalNumId);
                medicalNum.setQuota(medicalNum.getQuota()+1);
                orderRepository.deleteById(order.getId());
                return new Result(true);

            }else{
                throw new GlobalException(Message.ORDER_TIMEOUT.content);
            }
        }else{
            throw new GlobalException(Message.UN_AUTHENTICATION.content);
        }
    }
}
