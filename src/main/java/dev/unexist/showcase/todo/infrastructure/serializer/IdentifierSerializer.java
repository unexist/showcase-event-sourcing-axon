/**
 * @package Showcase-Event-Sourcing-Axon
 *
 * @file Todo serializer
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.infrastructure.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.unexist.showcase.todo.domain.todo.TodoIdentifier;

import java.io.IOException;
import java.time.LocalDate;

public class IdentifierSerializer extends JsonSerializer<TodoIdentifier> {

    /**
     * Serialize {@link LocalDate} to format
     *
     * @param  value        Value to convert
     * @param  gen          A {@link JsonGenerator}
     * @param  serializers  A {@link SerializerProvider}
     * @throws IOException
     **/

    @Override
    public void serialize(TodoIdentifier value, JsonGenerator gen,
                          SerializerProvider serializers) throws IOException {
        gen.writeString(value.toString());
    }
}
