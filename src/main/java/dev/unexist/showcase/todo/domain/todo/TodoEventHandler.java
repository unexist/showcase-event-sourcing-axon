/**
 * @package Quarkus-Workflow-Showcase
 *
 * @file Todo service and domain service
 * @copyright 2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import dev.unexist.showcase.todo.domain.todo.events.CreatedEvent;
import dev.unexist.showcase.todo.domain.todo.events.DoneEvent;
import dev.unexist.showcase.todo.domain.todo.queries.FindAllQuery;
import dev.unexist.showcase.todo.domain.todo.queries.FindByIdQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TodoEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoEventHandler.class);

    private final Map<TodoIdentifier, Todo> todos = new HashMap<>();

    /**
     * Event handler for the event {@link CreatedEvent}
     *
     * @param  event  Event to handle
     **/

    @EventHandler
    public void on(CreatedEvent event) {
        LOGGER.info("Handle created event for id={}", event.getId());

        Todo todo = new Todo();

        todo.setId(event.getId());
        todo.setTitle(event.getTitle());
        todo.setDescription(event.getDescription());

        todos.put(event.getId(), todo);
    }

    /**
     * Event handler for the event {@link DoneEvent}
     *
     * @param  event  Event to handle
     **/

    @EventHandler
    public void on(DoneEvent event) {
        LOGGER.info("Handle done event for id={}", event.getId());

        todos.computeIfPresent(event.getId(), (id, todo) -> {
            todo.setDone(true);

            return todo;
        });
    }

    /**
     * Query handler for the query {@link FindAllQuery}
     *
     * @param  query  Query to handle
     **/

    @QueryHandler
    public List<Todo> handle(FindAllQuery query) {
        LOGGER.info("Handle find all query");

        return new ArrayList<>(todos.values());
    }

    /**
     * Query handler for the query {@link FindByIdQuery}
     *
     * @param  query  Query to handle
     **/

    @QueryHandler
    public Optional<Todo> handle(FindByIdQuery query) {
        LOGGER.info("Handle find by id query for id={}", query.getId());

        return Optional.ofNullable(todos.get(query.getId()));
    }
}