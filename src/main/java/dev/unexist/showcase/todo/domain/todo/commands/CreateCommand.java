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
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.concurrent.atomic.LongAdder;

public class CreateCommand {
    static final LongAdder ADDER = new LongAdder();

    @TargetAggregateIdentifier
    final int id;
    final String title;
    final String description;

    public CreateCommand(String title, String description) {
        ADDER.increment();

        this.id = ADDER.intValue();
        this.title = title;
        this.description = description;
    }

    public CreateCommand(TodoBase base) {
        ADDER.increment();

        this.id = ADDER.intValue();
        this.title = base.getTitle();
        this.description = base.getDescription();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
