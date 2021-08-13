/**
 * @package Showcase-Event-Sourcing-Axon
 *
 * @file Todo create command
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo.commands;

import dev.unexist.showcase.todo.domain.todo.TodoBase;
import dev.unexist.showcase.todo.domain.todo.TodoIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCommand.class);

    @TargetAggregateIdentifier
    final TodoIdentifier id;

    final String title;
    final String description;

    /**
     * Constructor
     *
     * @param  title        Title for this command
     * @param  description  Description for this command
     **/

    public CreateCommand(String title, String description) {
        this.id = TodoIdentifier.nextId();
        this.title = title;
        this.description = description;

        LOGGER.info("Create command id={}", this.getId());
    }

    /**
     * Constructor
     *
     * @param  base  Init with values from a {@link TodoBase}
     **/

    public CreateCommand(TodoBase base) {
        this.id = TodoIdentifier.nextId();
        this.title = base.getTitle();
        this.description = base.getDescription();

        LOGGER.info("Create command id={}", this.getId());
    }

    /**
     * Get id
     *
     * @return Id of this command
     **/

    public TodoIdentifier getId() {
        return id;
    }

    /**
     * Get title
     *
     * @return Title of this command
     **/

    public String getTitle() {
        return title;
    }

    /**
     * Get description
     *
     * @return Description of this command
     **/

    public String getDescription() {
        return description;
    }
}
