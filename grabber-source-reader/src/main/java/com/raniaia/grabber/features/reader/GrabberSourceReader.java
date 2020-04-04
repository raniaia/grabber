package com.raniaia.grabber.features.reader;

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
 * Creates on 2020/4/4.
 */

import com.raniaia.grabber.Commonly;
import org.raniaia.available.io.file.Files;
import org.raniaia.available.list.Lists;
import org.raniaia.available.map.Maps;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author tiansheng
 */
public class GrabberSourceReader {

    /**
     * 获取源码目录
     */
    void readSourceDirectory(String path) {
        SourceDirectory directory = new SourceDirectory(path);
        directory.init();
    }

    class SourceDirectory {
        String prefix;
        List<String> classfile;
        String previousPackage;
        Map<String, List<String>> packages;

        SourceDirectory(String prefix) {
            this.prefix = prefix;
        }

        void init() {
            File dirs = Files.newFile(prefix);
            getPackages(dirs);
        }

        void getPackages(File files) {
            for (File file : Objects.requireNonNull(files.listFiles())) {
                //
                // 如果是一个目录就保存到previousPackage
                //
                if (file.isDirectory()) {
                    previousPackage = file.getPath();
                    getPackages(file);
                }
                if (Commonly.GRABBER.equals(Files.getSuffix(file))) {
                    if (classfile == null) {
                        classfile = Lists.newArrayList();
                    }
                    classfile.add(
                            file.getPath().substring(prefix.length() + 1)
                            .replaceAll("\\\\",".")
                    );
                }
            }
        }

        void put(String packageName, List<String> files) {
            if (packages == null) {
                packages = Maps.newHashMap();
            }
            packages.put(packageName, files);
        }

    }

    public static void main(String[] args) {
        GrabberSourceReader gr = new GrabberSourceReader();
        gr.readSourceDirectory("E:\\IdeaProjects\\grabber\\grabber-code-tamplate\\grabber");
    }

}