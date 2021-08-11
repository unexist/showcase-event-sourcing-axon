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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TodoEventHandler {
    private final Map<Integer, Todo> todos = new HashMap<>();

    @EventHandler
    public void on(CreatedEvent evt) {
        Todo todo = new Todo();

        todo.setId(evt.getId());
        todo.setTitle(evt.getTitle());
        todo.setDescription(evt.getDescription());

        todos.put(evt.getId(), todo);
    }

    @EventHandler
    public void on(DoneEvent evt) {
        todos.computeIfPresent(evt.getId(), (id, todo) -> {
            todo.setDone(true);

            return todo;
        });
    }

    @QueryHandler
    public List<Todo> handle(FindAllQuery query) {
        return new ArrayList<>(todos.values());
    }

    @QueryHandler
    public Optional<Todo> handle(FindByIdQuery query) {
        return Optional.ofNullable(todos.get(query.getId()));
    }
}