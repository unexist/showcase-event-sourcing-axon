/**
 * @package Showcase-Event-Sourcing-Axon
 *
 * @file Todo done event
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo.events;

public class DoneEvent {
    private int id;

    public DoneEvent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
