package service.stock;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import service.coreapi.*;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Stock {

    @AggregateIdentifier
    private String articleId;
    private String article;
    private String stockId;
    private Integer quantity;
    private Integer available;

    public Stock() {

    }

    @CommandHandler
    public Stock(TriggerStockUpdateCommand command) {
        apply(new StockUpdateTriggeredEvent(command.getArticleId(), command.getArticle(), command.getStockId(), command.getQuantity()));
    }

    @CommandHandler
    public void handle(StockUpdateCommand command) {
        apply(new StockUpdatedEvent(command.getArticleId(), command.getArticle(), command.getStockId(), command.getQuantity()));
    }

    @CommandHandler
    public void handle(AbortStockCommand command) {
        apply(new StockAbortedEvent(command.getArticleId(), command.getArticle(), command.getStockId(), command.getQuantity()));
    }

    @CommandHandler
    public void handle(EndSagaStockCommand command) {
        apply(new StockSagaEndedEvent(command.getArticleId(), command.getArticle(), command.getStockId(), command.getQuantity()));
    }

    @CommandHandler
    public void handle(TriggerCompensatePaymentCommand command) {
        apply(new CompensatePaymentTriggeredEvent(command.getArticleId(), command.getArticle(), command.getStockId(), command.getQuantity()));
    }



    @EventSourcingHandler
    public void on(StockUpdateTriggeredEvent event) {
        this.articleId = event.getArticleId();
        this.stockId = event.getStockId();
        this.article = event.getArticle();
        this.quantity = event.getQuantity();
    }

    @EventSourcingHandler
    public void on(StockUpdatedEvent event) {

    }

    @EventSourcingHandler
    public void on(StockAbortedEvent event) {

    }

    @EventSourcingHandler
    public void on(StockSagaEndedEvent event) {

    }

    @EventSourcingHandler
    public void on(CompensatePaymentTriggeredEvent event) {

    }
}
