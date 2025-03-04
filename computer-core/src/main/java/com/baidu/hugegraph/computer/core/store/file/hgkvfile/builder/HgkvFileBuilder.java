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

package com.baidu.hugegraph.computer.core.store.file.hgkvfile.builder;

import java.io.Closeable;
import java.io.IOException;

import com.baidu.hugegraph.computer.core.store.entry.KvEntry;

public interface HgkvFileBuilder extends Closeable {

    /**
     * Add kv entry to file.
     */
    void add(KvEntry entry) throws IOException;

    /**
     * Return size of new entry.
     */
    long sizeOfEntry(KvEntry entry);

    /**
     * Finish build file.
     */
    void finish() throws IOException;

    /**
     * Return the size of entry in bytes that has been written.
     */
    long dataLength();

    /**
     * Return the size of index block length.
     */
    long indexLength();

    /**
     * Return the size of header.
     */
    int headerLength();
}
