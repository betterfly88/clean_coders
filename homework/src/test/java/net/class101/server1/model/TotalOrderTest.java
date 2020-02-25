package net.class101.server1.model;

import net.class101.server1.service.FetchProductService;
import net.class101.server1.service.OrderProcessService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TotalOrderTest {
    private OrderProcessService orderService;
    private FetchProductService fetchProductService;

    private Map<String, OrderItem> orderItemList = new HashMap<>();

    @Before
    public void init() throws IOException {
        fetchProductService = new FetchProductService();
        fetchProductService.setFileName("product_list.csv");
        orderService = new OrderProcessService(fetchProductService);
        orderService.init();
    }

    @Test
    public void 결제_금액_배송비추가(){
        //given
        OrderItem o1 = OrderItem.builder().productId(10L).price(5000L).title("샘플1").counts(1).productType(ProductType.KIT).build();
        OrderItem o2 = OrderItem.builder().productId(15L).price(10000L).title("샘플2").counts(1).productType(ProductType.CLASS).build();
        OrderItem o3 = OrderItem.builder().productId(30L).price(2000L).title("샘플3").counts(5).productType(ProductType.KIT).build();



        orderItemList.put("A User", o1);
        orderItemList.put("B User", o2);
        orderItemList.put("C User", o3);

        orderService.setOrderItemList(orderItemList);

        // when
        TotalOrder order = TotalOrder.of(orderItemList);

        // then
        assertTrue(order.getOrderList().size() == 3);
        assertThat(order.getOrderPrice(), is(25000L));
        assertThat(order.getShippingCost(), is(5000L));
        assertThat(order.getPayment(), is(30000L));
    }

    @Test
    public void 결제_금액_배송비제외(){
        //given
        OrderItem o1 = OrderItem.builder().productId(10L).price(5000L).title("샘플1").counts(1).productType(ProductType.KIT).build();
        OrderItem o2 = OrderItem.builder().productId(15L).price(50000L).title("샘플2").counts(1).productType(ProductType.CLASS).build();
        OrderItem o3 = OrderItem.builder().productId(30L).price(2000L).title("샘플3").counts(5).productType(ProductType.KIT).build();

        orderItemList.put("A User", o1);
        orderItemList.put("B User", o2);
        orderItemList.put("C User", o3);

        orderService.setOrderItemList(orderItemList);

        // when
        TotalOrder order = TotalOrder.of(orderItemList);

        // then
        assertTrue(order.getOrderList().size() == 3);
        assertThat(order.getOrderPrice(), is(65000L));
        assertThat(order.getShippingCost(), is(0L));
        assertThat(order.getPayment(), is(65000L));
    }

}