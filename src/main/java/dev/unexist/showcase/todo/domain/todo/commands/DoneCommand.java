/**
 * @package Showcase-Event-Sourcing-Axon
 *
 * @file Todo done command
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo.commands;

import dev.unexist.showcase.todo.domain.todo.TodoIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DoneCommand {
    @TargetAggregateIdentifier
    private final TodoIdentifier id;

    /**
     * Constructor
     *
     * @param  id  {@link TodoIdentifier} for this command
     **/

    public DoneCommand(TodoIdentifier id) {
        this.id = id;
    }

    /**
     * Get id
     *
     * @return Id of this command
     **/

    public TodoIdentifier getId() {
        return id;
    }
}
