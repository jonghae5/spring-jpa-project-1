package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

//        Item findItem = itemRepository.findOne(itemId); //DB에서 꺼내 온 JPA가 관리하는 영속성 객체
//        findItem.setPrice(param.getPrice());
//        findItem.setName(param.getName());
//        findItem.setStockQuantity(param.getStockQuantity());
////        itemRepository.save(findItem); // 이 것을 호출할 필요가 없다. 영속성이기 때문에 변경 감지를 통해
//        // TRANSACTIONAL > COMMIT > em.FLUSH > 변경 값 감지 > UPDATE 쳐버린다.
    }
    public List<Item> findItems() {
        return itemRepository.findAll()
;    }
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
