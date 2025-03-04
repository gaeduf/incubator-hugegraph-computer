/*
 * Copyright 2017 HugeGraph Authors
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.baidu.hugegraph.computer.core.store.entry;

import java.io.IOException;

import com.baidu.hugegraph.computer.core.io.Readable;

public interface EntryInput {

    /**
     * Read entry with multiple sub-key and sub-value.
     * Used when read vertex with edges, each sub-key is target id of an edge,
     * each sub-value is the properties of an edge.
     * The output format:
     * | key length | key | total sub-entry length | sub-entry count |
     * | sub-key1 length | sub-key1 | sub-value1 length | sub-value1 |
     * | sub-key2 length | sub-key2 | sub-value2 length | sub-value2 |
     */
    KvEntryReader readEntry(Readable key) throws IOException;

    /**
     * Read entry with single value.
     * Used when read vertex without edges and read message.
     * The output format:
     * | key length | key | value length | value |
     */
    void readEntry(Readable key, Readable value) throws IOException;
}
