package com.projects.transactions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.projects.transactions.message.TransactionMessage;
import com.projects.transactions.service.ProducerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * This class is to simulate the Kafka Sender. This is for application testing.
 */
@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(name = "bootstrap.kafka.testing", havingValue = "true")
public class InitApplication  implements CommandLineRunner{
    
    private final ProducerService service;

    @Override
    public void run(String... args) throws Exception {
        buildTestMessages().stream().forEach( t -> {
            service.sendMessage(t);
            try {
                Thread.sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    } 

    private List<TransactionMessage> buildTestMessages(){

        
        return List.of(
            buildTranMessage(10001,"PRODUCT_008",5),
            buildTranMessage(10001,"PRODUCT_001",5),
            buildTranMessage(10001,"PRODUCT_003",10),
            buildTranMessage(10001,"PRODUCT_005",15),
            buildTranMessage(10002,"PRODUCT_001",12),
            buildTranMessage(10002,"PRODUCT_003",8),
            buildTranMessage(10003,"PRODUCT_003",15),
            buildTranMessage(10003,"PRODUCT_005",5),
            buildTranMessage(10001,"PRODUCT_001",500)
        );
    }

    private TransactionMessage buildTranMessage(long customerId, String productId, long qty) {
        return TransactionMessage.builder().customerId(customerId).transactionTime(LocalDateTime.now())
        .productCode(productId).quantity(qty).build();
    }

}
