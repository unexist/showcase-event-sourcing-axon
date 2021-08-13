/**
 * @package Showcase-Event-Sourcing-Axon
 *
 * @file Todo base class
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import org.apache.commons.lang3.Validate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TodoBase {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private Boolean done;

    @NotNull
    private TimeWindow timeWindow;

    /**
     * Get title of the entry
     *
     * @return Title of the entry
     **/

    public String getTitle() {
        return title;
    }

    /**
     * Set title of the entry
     *
     * @param  title  Title of the entry
     **/

    public void setTitle(String title) {
        this.title = Validate.notNull(title,
                "Title must be set");
    }

    /**
     * Get description of entry
     *
     * @return Description of the entry
     **/

    public String getDescription() {
        return description;
    }

    /**
     * Set description of the entry
     *
     * @param  description  Description of the entry
     **/

    public void setDescription(String description) {
        this.description = Validate.notNull(description,
                "Description must be set");
    }

    /**
     * Get done state of entry
     *
     * @return Done state of the entry
     **/

    public Boolean getDone() {
        return done;
    }

    /**
     * Set done state of entry
     *
     * @param  done  Done state of the entry
     **/

    public void setDone(Boolean done) {
        this.done = done;
    }

    /**
     * Get due state of the entry
     *
     * @return Due state of the entry
     **/

    public TimeWindow getTimeWindow() {
        return timeWindow;
    }

    /**
     * Set due date of the entry
     *
     * @param  timeWindow  {@link TimeWindow} of the entry
     **/

    public void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = Validate.notNull(timeWindow,
                "TimeWindow must be set");
    }
}
