/**
 * @package Showcase-Event-Sourcing-Axon
 *
 * @file Todo create event
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo.events;

import dev.unexist.showcase.todo.domain.todo.TodoIdentifier;

public class CreatedEvent {
    final TodoIdentifier id;
    final String title;
    final String description;

    /**
     * Constructor
     *
     * @param  id           {@link TodoIdentifier} for this event
     * @param  title        Title for this event
     * @param  description  Description for this event
     **/

    public CreatedEvent(TodoIdentifier id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /**
     * Get id
     *
     * @return Id of this event
     **/

    public TodoIdentifier getId() {
        return id;
    }

    /**
     * Get title
     *
     * @return Title of this event
     **/

    public String getTitle() {
        return title;
    }

    /**
     * Get description
     *
     * @return Description of this event
     **/

    public String getDescription() {
        return description;
    }
}
