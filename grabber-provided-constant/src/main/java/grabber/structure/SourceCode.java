package grabber.structure;

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

import jdk.nashorn.internal.objects.annotations.Getter;
import org.raniaia.available.list.Lists;
import org.raniaia.available.string.LineReader;
import org.raniaia.available.string.StringUtils;

import java.io.IOException;
import java.util.List;


/**
 * @author tiansheng
 */
public class SourceCode {

    String path;

    String[] value;

    boolean isMultiLineComment = false;

    StringBuilder builder = new StringBuilder();

    public SourceCode() {
    }

    public SourceCode(String path) {
        this.path = path;
        read();
        System.out.println("PATH: " + path);
        System.out.println("------------------------------------------------------------------------");
        for (String v : value) {
            System.out.println(v);
        }
        System.out.println("------------------------------------------------------------------------");
        builder = null;
    }

    void read() {
        try {
            List<String> lines = Lists.newLinkedList();
            LineReader lr = new LineReader(path);
            int i = 1;
            while (lr.ready()) {
                String paragraph = erase(lr.readLine());
                if (StringUtils.isEmpty(paragraph)) {
                    continue;
                }
                String line = String.valueOf(i).concat(": ");
                lines.add(line.concat(paragraph).trim());
                i++;
            }
            value = new String[lines.size()];
            lines.toArray(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 擦除注释
     */
    String erase(String line) {
        int slash = 0;
        int minus = 0;
        char[] charArray = line.toCharArray();
        for (char c : charArray) {
            switch (c) {
                case '/': {
                    slash++;
                    if (slash >= 2) {
                        System.out.println("擦除单注释：" + line + ", 返回：" + builder.toString());
                        return StringUtils.clear(builder);
                    }
                    if (slash == 1 && minus == 2) {
                        isMultiLineComment = false;
                        System.out.println("擦除多注释：" + line + ", 返回：" + builder.toString());
                        StringUtils.clear(builder);
                        return null;
                    }
                    break;
                }
                case '-': {
                    minus++;
                    if (slash == 1 && minus == 2) {
                        if (isMultiLineComment) {
                            isMultiLineComment = false;
                        } else {
                            isMultiLineComment = true;
                        }
                        System.out.println("擦除多注释：" + line + ", 返回：" + builder.toString());
                        StringUtils.clear(builder);
                        return null;
                    }
                    break;
                }
                default:
                    builder.append(c);
            }
        }

        if (isMultiLineComment) {
            System.out.println("擦除多注释：" + line + ", 返回：" + builder.toString());
            return null;
        }
        return StringUtils.clear(builder);
    }

    public String[] getValue() {
        return this.value;
    }

}