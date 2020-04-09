package reader;

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

import object.structure.SourceCode;

import java.util.List;

/**
 * 获取源码目录
 *
 * @author tiansheng
 */
public class ReaderStart {

    /**
     * 获取源码目录。
     * 这个{@param path}传入源码目录，而不是项目的根目录
     */
    public static List<SourceCode> exec(String path) {
        SourceDirectory directory = new SourceDirectory(path);
        //
        // 初始化directory，内部递归遍历获取源码目录
        //
        directory.init();
        return directory.toSourceCodeList();
    }

}