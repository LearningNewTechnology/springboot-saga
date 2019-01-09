package service;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import service.logging.LogFile;

import java.util.UUID;
import java.util.logging.Logger;

@SpringBootApplication
public class SagaOrchestratorApplication {

    public static String sagaId;

    public static Logger logger = LogFile.writeLogFile();

    public static void main(String[] args) {

        ConfigurableApplicationContext config = SpringApplication.run(SagaOrchestratorApplication.class, args);
        CommandBus commandBus = config.getBean(CommandBus.class);
        CommandGateway commandGateway = config.getBean(CommandGateway.class);

        sagaId = UUID.randomUUID().toString();

        System.out.println("\nSaga:    " + sagaId);

        //dispatchSingleServices(commandBus, orderId, accountId, stockId);
        //sendSagaServices(commandGateway, orderId, accountId, stockId);
    }






    /*
    private static void dispatchSingleServices(CommandBus commandBus, String orderId, String accountId, String stockId) {

        commandBus.dispatch(asCommandMessage(new StartSagaCommand(orderId, "Alice", "shirt", 2, "30$")));
        commandBus.dispatch(asCommandMessage(new DeleteOrderCommand(orderId, "Alice", "shirt", 2, "30$")));
        commandBus.dispatch(asCommandMessage(new TriggerPaymentCommand(accountId, "Alice", "5555", "30$")));
        commandBus.dispatch(asCommandMessage(new TriggerCompensateOrderCommand(accountId, "Alice", "5555", "30$")));
        commandBus.dispatch(asCommandMessage(new TriggerStockUpdateCommand(stockId, "shirt", "9876", 2)));
    }

    private static void sendSagaServices(CommandGateway commandGateway, String orderId, String accountId, String articleId) {

        System.out.println("\n--------------------------------------------------- Start Saga " +
                sagaId + " ----------------------------------------------------");
        logger.info("Start Saga " + sagaId + "\n");
        System.out.println("\n-------------------------------------------------- Create Order " +
                sagaId + " ---------------------------------------------------");
        logger.info("Create Order " + sagaId + "\n");

        commandGateway.send(new StartSagaCommand(orderId, "Alice", "shirt", 2, "30$"));

        System.out.println("\n---------------------------------------------------- End Saga " +
                sagaId + " -----------------------------------------------------");
        SagaOrchestratorApplication.logger.info("End Saga " + SagaOrchestratorApplication.sagaId + "\n\n\n");
    }
    */
}
