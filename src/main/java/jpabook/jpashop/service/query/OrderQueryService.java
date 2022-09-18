package jpabook.jpashop.service.query;

import jpabook.jpashop.api.OrderApiController;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderRepository orderRepository;
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        //JPA 입장에서는 DB에서 4개를 뿌려줬기 때문에 줄여서 줘야하는지 아닌지 구분을 못한다 >> distinct
        // pk 기준으로 distinct 를 한 번 하고 App 단에서 실제 order의 distinct를 실행
        for (Order order : orders) {
            System.out.println("order ref= " + order + " id=" + order.getId());
        }
        List<OrderDto> collect = orders.stream()
                .map(order -> new OrderDto(order))
                .collect(toList());
        return collect;
    }


}
