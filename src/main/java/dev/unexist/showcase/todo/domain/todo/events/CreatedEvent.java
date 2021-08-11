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

public class CreatedEvent {
    final int id;
    final String title;
    final String description;

    /**
     * Constructor
     *
     * @param  id           Id for this event
     * @param  title        Title for this event
     * @param  description  Description for this event
     **/

    public CreatedEvent(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /**
     * Get id
     *
     * @return Id of this event
     **/

    public int getId() {
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
