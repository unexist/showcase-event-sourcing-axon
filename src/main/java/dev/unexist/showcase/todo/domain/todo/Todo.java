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

import dev.unexist.showcase.todo.domain.todo.commands.CreateCommand;
import dev.unexist.showcase.todo.domain.todo.commands.DoneCommand;
import dev.unexist.showcase.todo.domain.todo.events.CreatedEvent;
import dev.unexist.showcase.todo.domain.todo.events.DoneEvent;
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
    private int id;

    /**
     * Constructor
     **/

    public Todo() {
    }

    /**
     * Constructor
     *
     * @param base
     *          Base entry
     **/

    public Todo(final TodoBase base) {
        this.update(base);
    }

    /**
     * Command handler for the {@link CreateCommand}
     *
     * @param cmd
     *          {@link CreateCommand} to handle
     **/

    @CommandHandler
    public Todo(CreateCommand cmd) {
        LOGGER.info("Handle aggregate create command");

        AggregateLifecycle.apply(new CreatedEvent(cmd.getId(),
                cmd.getTitle(), cmd.getDescription()));
    }

    /**
     * Command handler for the {@link DoneCommand}
     *
     * @param cmd
     *          {@link DoneCommand} to handle
     **/

    @CommandHandler
    public void handle(DoneCommand cmd) {
        LOGGER.info("Handle aggregate done command");

        AggregateLifecycle.apply(new DoneEvent(cmd.getId()));
    }

    /**
     * Event handler for the {@link CreatedEvent}
     *
     * @param evt
     *          {@link CreatedEvent} to handle
     **/

    @EventSourcingHandler
    public void on(CreatedEvent evt) {
        LOGGER.info("Handle aggregate created event");

        this.setId(evt.getId());
        this.setTitle(evt.getTitle());
        this.setDescription(evt.getDescription());
    }

    /**
     * Event handler for the {@link DoneEvent}
     *
     * @param evt
     *          {@link DoneEvent} to handle
     **/

    @EventSourcingHandler
    public void on(DoneEvent evt) {
        LOGGER.info("Handle aggregate done event");

        this.setId(evt.getId());
        this.setDone(true);
    }

    /**
     * Update values from base
     *
     * @param base
     *          Todo base class
     **/

    public void update(final TodoBase base) {
        this.setDueDate(base.getDueDate());
        this.setTitle(base.getTitle());
        this.setDescription(base.getDescription());
        this.setDone(base.getDone());
    }

    /**
     * Get id of entry
     *
     * @return
     *          Id of the entry
     **/

    public int getId() {
        return id;
    }

    /**
     * Set id of entry
     *
     * @param id
     *          Id of the entry
     **/

    public void setId(int id) {
        this.id = id;
    }
}
