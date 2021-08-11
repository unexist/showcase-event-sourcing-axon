/**
 * @package Showcase-Event-Sourcing-Axon
 *
 * @file Todo find by id query
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo.queries;

public class FindByIdQuery {
    private int id;

    /**
     * Constructor
     *
     * @param  id  Id to find
     **/

    public FindByIdQuery(int id) {
        this.id = id;
    }

    /**
     * Get id
     *
     * @return Id to find
     **/

    public int getId() {
        return id;
    }
}
