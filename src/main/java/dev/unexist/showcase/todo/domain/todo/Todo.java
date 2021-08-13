/**
 * @package Showcase-Event-Sourcing-Axon
 *
 * @file Todo class and aggregate root
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.unexist.showcase.todo.domain.todo.commands.CreateCommand;
import dev.unexist.showcase.todo.domain.todo.commands.DoneCommand;
import dev.unexist.showcase.todo.domain.todo.events.CreatedEvent;
import dev.unexist.showcase.todo.domain.todo.events.DoneEvent;
import dev.unexist.showcase.todo.infrastructure.serializer.IdentifierSerializer;
import org.apache.commons.lang3.Validate;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
public class Todo extends TodoBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Todo.class);

    @AggregateIdentifier
    @JsonSerialize(using = IdentifierSerializer.class)
    private TodoIdentifier id;

    /**
     * Constructor
     **/

    protected Todo() {
    }

    /**
     * Command handler for the {@link CreateCommand}
     *
     * @param  command  {@link CreateCommand} to handle
     **/

    @CommandHandler
    public Todo(CreateCommand command) {
        LOGGER.info("Handle aggregate create command");

        AggregateLifecycle.apply(new CreatedEvent(command.getId(),
                command.getTitle(), command.getDescription()));
    }

    /**
     * Command handler for the {@link DoneCommand}
     *
     * @param  command  {@link DoneCommand} to handle
     **/

    @CommandHandler
    public void handle(DoneCommand command) {
        LOGGER.info("Handle aggregate done command");

        AggregateLifecycle.apply(new DoneEvent(command.getId()));
    }

    /**
     * Event handler for the {@link CreatedEvent}
     *
     * @param  event  {@link CreatedEvent} to handle
     **/

    @EventSourcingHandler
    public void on(CreatedEvent event) {
        LOGGER.info("Handle aggregate created event");

        this.setId(event.getId());
        this.setTitle(event.getTitle());
        this.setDescription(event.getDescription());
    }

    /**
     * Event handler for the {@link DoneEvent}
     *
     * @param  event  {@link DoneEvent} to handle
     **/

    @EventSourcingHandler
    public void on(DoneEvent event) {
        LOGGER.info("Handle aggregate done event");

        this.setId(event.getId());
        this.setDone(true);
    }

    /**
     * Get id of entry
     *
     * @return Id of the entry
     **/

    public TodoIdentifier getId() {
        return this.id;
    }

    /**
     * Set id of entry
     *
     * @param  id  Id of the entry
     **/

    public void setId(TodoIdentifier id) {
        this.id = Validate.notNull(id,
                "Id must be set");
    }
}
