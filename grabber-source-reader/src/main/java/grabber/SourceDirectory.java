package grabber;

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

import grabber.structure.SourceCode;
import org.raniaia.available.io.file.Files;
import org.raniaia.available.list.Lists;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * 获取源码目录，并读取每个包下的所有源码文件。
 *
 * @author tiansheng
 */
public class SourceDirectory {

    String prefix;
    List<String> classfile;
    String previousPackage;

    List<String> packages;

    public SourceDirectory(String prefix) {
        this.prefix = prefix;
    }

    public void init() {
        File dirs = Files.newFile(prefix);
        getPackages(dirs);
    }

    void getPackages(File files) {
        for (File file : Objects.requireNonNull(files.listFiles())) {
            //
            // 如果是一个目录就保存到previousPackage
            //
            if (file.isDirectory()) {
                getPackages(file);
            }
            if (Commonly.GRABBER.equals(Files.getSuffix(file))
                    || Commonly.MACRO.equals(Files.getSuffix(file))) {
                if (classfile == null) {
                    classfile = Lists.newArrayList();
                }
                classfile.add(
                        file.getPath().substring(prefix.length() + 1)
                );
            }
        }
        if (classfile != null && !classfile.isEmpty()) {
            add(Lists.newArrayList(classfile));
            classfile.clear();
            previousPackage = null;
        }
    }

    void add(List<String> files) {
        if (packages == null) {
            packages = Lists.newArrayList(files);
        }
        packages.addAll(Lists.newArrayList(files));
    }

    public List<SourceCode> toSourceCodeList() {
        List<SourceCode> codes = Lists.newArrayList();
        for (String aPackage : packages) {
            String key = prefix.concat("\\").concat(aPackage);
            codes.add(new SourceCode(key));
        }
        return codes;
    }

}