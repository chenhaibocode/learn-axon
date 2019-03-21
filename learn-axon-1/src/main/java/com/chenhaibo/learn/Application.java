package com.chenhaibo.learn;

import com.chenhaibo.learn.command.aggregates.BankAccount;
import com.chenhaibo.learn.command.commands.CreateAccountCommand;
import com.chenhaibo.learn.command.commands.WithdrawMoneyCommand;
import com.chenhaibo.learn.domain.AccountId;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

public class Application {

    public static void main(String args[]) {
        Configuration config = DefaultConfigurer.defaultConfiguration()
                .configureAggregate(BankAccount.class)
                .configureEmbeddedEventStore(c -> new InMemoryEventStorageEngine())
                .buildConfiguration();
        config.start();
        AccountId id = new AccountId();
        config.commandGateway().send(new CreateAccountCommand(id, "MyAccount", 1000));
        config.commandGateway().send(new WithdrawMoneyCommand(id, 500));
        config.commandGateway().send(new WithdrawMoneyCommand(id, 500));
    }
}
