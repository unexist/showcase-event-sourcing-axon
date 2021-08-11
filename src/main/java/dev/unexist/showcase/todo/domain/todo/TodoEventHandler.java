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

import dev.unexist.showcase.todo.domain.todo.commands.CreateCommand;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCommand.class);

    private final Map<Integer, Todo> todos = new HashMap<>();

    @EventHandler
    public void on(CreatedEvent evt) {
        LOGGER.info("Handle event handler created event");

        Todo todo = new Todo();

        todo.setId(evt.getId());
        todo.setTitle(evt.getTitle());
        todo.setDescription(evt.getDescription());

        todos.put(evt.getId(), todo);
    }

    @EventHandler
    public void on(DoneEvent evt) {
        LOGGER.info("Handle event handler done event");

        todos.computeIfPresent(evt.getId(), (id, todo) -> {
            todo.setDone(true);

            return todo;
        });
    }

    @QueryHandler
    public List<Todo> handle(FindAllQuery query) {
        LOGGER.info("Handle event handler find all query");

        return new ArrayList<>(todos.values());
    }

    @QueryHandler
    public Optional<Todo> handle(FindByIdQuery query) {
        LOGGER.info("Handle event handler find by id query");

        return Optional.ofNullable(todos.get(query.getId()));
    }
}