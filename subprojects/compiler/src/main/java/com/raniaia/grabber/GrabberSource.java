package com.raniaia.grabber;

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

import org.raniaia.available.io.file.Files;


/**
 * 源文件
 *
 * @author tiansheng
 */
public class GrabberSource {

    String path;

    String name;

    String value;

    public GrabberSource() {
    }

    public GrabberSource(String path) {
        this.path = path;
        read();
        name = Files.newFile(path).getName();
    }

    void read() {
        this.value = Files.read(path);
    }

    public void read(String value) {
        this.value = value;
    }

}