package com.raniaia.grabber.object;/*
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
 * Creates on 2020/4/8.
 */

import org.raniaia.available.map.Maps;

import java.util.Map;

/**
 * 符号种别码
 * @author tiansheng
 */
public interface Symbol {

    Map<String,Integer> table = Maps.newHashMap(new SymbolTable().table);

    int

    //
    // 标识符
    //
    IDEN      =       1,

    //
    // 常数
    //
    CONST     =       2,

    //
    // 保留字符
    //
    KEEP      =       3,

    //
    // 运算符
    //
    OP        =       4,

    //
    // 界限符
    //
    LIMIT     =       5;

    /**
     * 符号Hash表
     */
    class SymbolTable{
        Map<String,Integer> table = Maps.newHashMap();

        {
            /*
             * 保留字符
             */
            table.put(  "public",                           KEEP);
            table.put(  "private",                          KEEP);
            table.put(  "set",                              KEEP);
            table.put(  "const",                            KEEP);
            table.put(  "if",                               KEEP);
            table.put(  "else",                             KEEP);
            table.put(  "switch",                           KEEP);
            table.put(  "case",                             KEEP);
            table.put(  "default",                          KEEP);
            table.put(  "define",                           KEEP);
            table.put(  "def",                              KEEP);
            table.put(  "return",                           KEEP);
            table.put(  "class",                            KEEP);
            table.put(  "@interface",                       KEEP);
            table.put(  "#include",                         KEEP);
            table.put(  "null",                             KEEP);
            table.put(  "true",                             KEEP);
            table.put(  "false",                            KEEP);
            table.put(  "instanceof",                       KEEP);
            table.put(  "goto",                             KEEP);
            table.put(  "break",                            KEEP);
            table.put(  "for",                              KEEP);
            table.put(  "while",                            KEEP);
            table.put(  "this",                             KEEP);
            table.put(  "func",                             KEEP);

            /*
             * 运算符
             */
            table.put(  "+",                                OP);
            table.put(  "-",                                OP);
            table.put(  "*",                                OP);
            table.put(  "/",                                OP);
            table.put(  "%",                                OP);
            table.put(  "^",                                OP);
            table.put(  "=",                                OP);
            table.put(  "<",                                OP);
            table.put(  ">",                                OP);
            table.put(  "++",                               OP);
            table.put(  "--",                               OP);
            table.put(  "==",                               OP);
            table.put(  ">=",                               OP);
            table.put(  "<=",                               OP);
            table.put(  ">>",                               OP);
            table.put(  "<<",                               OP);
            table.put(  "===",                              OP);

            /*
             * 界限符
             */
            table.put(  ";",                                LIMIT);
            table.put(  "(",                                LIMIT);
            table.put(  ")",                                LIMIT);
            table.put(  "[",                                LIMIT);
            table.put(  "]",                                LIMIT);
            table.put(  "{",                                LIMIT);
            table.put(  "}",                                LIMIT);
            table.put(  ".",                                LIMIT);
            table.put(  "\\",                               LIMIT);
        }

    }

}
