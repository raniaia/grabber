package com.raniaia.grabber.features.save;

/*
 * Copyright (C) 2020 the original author or authors.
 * Licensed under the GPL, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://b2evolution.net/about/gnu-gpl-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The source code of this program is only provided for learning and research.
 * If the program source code is used for criminal acts, such as illegal acts,
 * it is not related to the original author and need to be personally responsible.
 */

/*
 * Creates on 2020/4/5.
 */

import org.raniaia.available.list.Lists;
import org.raniaia.available.map.Maps;
import org.raniaia.available.string.LineReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author tiansheng
 */
public class SourceCode {

    public static SourceCode NONE = null;

    String path;

    String[] value;

    public SourceCode(){}

    public SourceCode(String path){
        this.path = path;
        read();
    }

    void read() {
        try {
            List<String> lines = Lists.newLinkedList();
            LineReader lr = new LineReader(path);
            while (lr.ready()){
                lines.add(lr.readLine());
            }
            value = new String[lines.size()];
            lines.toArray(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}