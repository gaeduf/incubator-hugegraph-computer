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

package com.baidu.hugegraph.computer.core.sort.sorter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.baidu.hugegraph.computer.core.store.entry.KvEntry;

public class JavaInputSorter implements InputSorter {

    private static final ThreadLocal<List<KvEntry>> SORT_LOCAL =
                         ThreadLocal.withInitial(ArrayList::new);

    private static List<KvEntry> threadLocalSortList() {
        List<KvEntry> list = SORT_LOCAL.get();
        list.clear();
        return list;
    }

    @Override
    public Iterator<KvEntry> sort(Iterator<KvEntry> entries)
                             throws IOException {
        List<KvEntry> kvEntries = threadLocalSortList();
        while (entries.hasNext()) {
            kvEntries.add(entries.next());
        }
        kvEntries.sort(KvEntry::compareTo);
        return kvEntries.iterator();
    }
}
