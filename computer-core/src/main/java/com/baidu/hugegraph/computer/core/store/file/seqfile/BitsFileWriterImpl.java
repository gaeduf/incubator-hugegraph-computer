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

package com.baidu.hugegraph.computer.core.store.file.seqfile;

import java.io.File;
import java.io.IOException;
import com.baidu.hugegraph.computer.core.config.Config;

public class BitsFileWriterImpl implements BitsFileWriter {

    private static final int BUFFER_BITS = Long.BYTES << 3;

    private final ValueFileOutput output;
    private boolean finished;
    private long byteBuffer;
    private int cursor;

    public BitsFileWriterImpl(Config config, String dir) throws IOException {
        this(config, new File(dir));
    }

    public BitsFileWriterImpl(Config config, File dir) throws IOException {
        this.output = new ValueFileOutput(config, dir);
        this.finished = false;

        this.byteBuffer = 0L;
        this.cursor = 0;
    }

    @Override
    public void writeBoolean(boolean value) throws IOException {
        if (value) {
            this.byteBuffer |= (1L << this.cursor);
        }
        this.cursor++;

        if (this.cursor >= BUFFER_BITS) {
            this.output.writeLong(this.byteBuffer);
            this.byteBuffer = 0L;
            this.cursor = 0;
        }
    }

    @Override
    public void flush() throws IOException {
        this.output.flushBuffer();
    }

    @Override
    public void close() throws IOException {
        if (this.finished) {
            return;
        }
        this.finished = true;
        this.output.writeLong(this.byteBuffer);
        this.output.close();
    }
}
