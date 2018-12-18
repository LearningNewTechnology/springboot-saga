package servicesagaorchestrator.order;

import servicesagaorchestrator.coreapi.CreateOrderCommand;
import servicesagaorchestrator.coreapi.DeleteOrderCommand;
import servicesagaorchestrator.coreapi.OrderCreatedEvent;
import servicesagaorchestrator.coreapi.OrderDeletedEvent;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;
import servicesagaorchestrator.entities.OrderEntity;

import static org.junit.Assert.*;


public class OrderTest {
    private AggregateTestFixture<Order> fixture;
    //private FixtureConfiguration<Order> fixture; //version 4.0.3

    @Before

    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(Order.class); //version 3.4.1
        //fixture = new AggregateTestFixture<>(Order.class); //version 4.0.3
    }

    @Test
    public void testCreateOrder() throws Exception {
        fixture.givenNoPriorActivity()
                .when(new CreateOrderCommand("1234", "Alice", "shirt", 2, "30$"))
                .expectEvents(new OrderCreatedEvent("1234", "Alice", "shirt", 2, "30$"));
    }

    @Test
    public void testOrderCreated() throws Exception {
        OrderEntity orderEntity = new OrderEntity("1234", "Alice", "shirt", 2, "30$");
        Order order = new Order();
        OrderEntity orderEntityFromOrder = order.on(new OrderCreatedEvent("1234", "Alice", "shirt", 2, "30$"));
        assertEquals("These are equals", orderEntity.getOrderID(), orderEntityFromOrder.getOrderID());
        assertEquals("These are equals", orderEntity.getUser(), orderEntityFromOrder.getUser());
        assertEquals("These are equals", orderEntity.getArticle(), orderEntityFromOrder.getArticle());
        assertEquals("These are equals", orderEntity.getQuantity(), orderEntityFromOrder.getQuantity());
        assertEquals("These are equals", orderEntity.getPrice(), orderEntityFromOrder.getPrice());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        fixture.given(new OrderCreatedEvent("1234", "Alice", "shirt", 2, "30$"))
                .when(new DeleteOrderCommand("1234", "Alice", "shirt", 2, "30$"))
                .expectEvents(new OrderDeletedEvent("1234", "Alice", "shirt", 2, "30$"));
    }

    @Test
    public void testOrderDeleted() throws Exception {
        Order order = new Order();
        OrderEntity orderEntityFromOrder = order.on(new OrderDeletedEvent("1234", "Alice", "shirt", 2, "30$"));
        assertEquals("These are equals", null, orderEntityFromOrder.getOrderID());
        assertEquals("These are equals", null, orderEntityFromOrder.getUser());
        assertEquals("These are equals", null, orderEntityFromOrder.getArticle());
        assertEquals("These are equals", null, orderEntityFromOrder.getQuantity());
        assertEquals("These are equals", null, orderEntityFromOrder.getPrice());
    }

}