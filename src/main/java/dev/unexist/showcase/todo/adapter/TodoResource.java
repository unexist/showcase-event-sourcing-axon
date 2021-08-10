/**
 * @package Showcase-Event-Sourcing-Axon
 *
 * @file Todo resource
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.adapter;

import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.domain.todo.TodoBase;
import dev.unexist.showcase.todo.domain.todo.commands.CreateCommand;
import dev.unexist.showcase.todo.domain.todo.queries.FindAllQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class TodoResource {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private QueryGateway queryGateway;

    @Operation(summary = "Create new todo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Todo created"),
            @ApiResponse(responseCode = "406", description = "Bad data"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping(value = "/todo", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<Void> create(@RequestBody TodoBase base) {
        return this.commandGateway.send(new CreateCommand(base));
    }

    @Operation(summary = "Get all todos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of todo", content =
                @Content(array = @ArraySchema(schema = @Schema(implementation = Todo.class)))),
            @ApiResponse(responseCode = "204", description = "Nothing found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<List<Todo>> getAll() {
        return this.queryGateway.query(new FindAllQuery(), ResponseTypes.multipleInstancesOf(Todo.class));
    }

    @Operation(summary = "Get todo by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Todo found", content =
                @Content(schema = @Schema(implementation = Todo.class))),
            @ApiResponse(responseCode = "404", description = "Todo not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping(value = "/todo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@Parameter(description = "Todo id")
                                             @RequestParam("id") int id) {
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @Operation(summary = "Update todo by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Todo updated"),
            @ApiResponse(responseCode = "404", description = "Todo not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PutMapping(value = "/todo/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@Parameter(description = "Todo id")
                                     @RequestParam("id") int id, @RequestBody TodoBase base) {
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @Operation(summary = "Delete todo by id")
    @DeleteMapping(value = "/todo/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@Parameter(description = "Todo id") @RequestParam("id") int id) {
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}
