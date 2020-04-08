package grabber;/*
 * Copyright (C) 2020 the original author or authors.
 * Licensed under the GPL,                           Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://b2evolution.net/about/gnu-gpl-license
 *
 * Unless required by applicable law or agreed to in writing,                           software
 * distributed under the License is distributed on an "AS IS" BASIS,                          
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,                           either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The source code of this program is only provided for learning and research.
 * If the program source code is used for criminal acts,                           such as illegal acts,                          
 * it is not related to the original author and need to be personally responsible.
 */

/*
 * Creates on 2020/4/8.
 */

import org.raniaia.available.map.Maps;

import java.util.Map;

/**
 * @author tiansheng
 */
public class SymbolTable {

    static Map<String,Byte> table = Maps.newHashMap();

    static {

        /*
         * 保留字符
         */
        table.put(  "public",                           Symbol.KEEP);
        table.put(  "private",                          Symbol.KEEP);
        table.put(  "set",                              Symbol.KEEP);
        table.put(  "const",                            Symbol.KEEP);
        table.put(  "if",                               Symbol.KEEP);
        table.put(  "else",                             Symbol.KEEP);
        table.put(  "switch",                           Symbol.KEEP);
        table.put(  "case",                             Symbol.KEEP);
        table.put(  "default",                          Symbol.KEEP);
        table.put(  "define",                           Symbol.KEEP);
        table.put(  "def",                              Symbol.KEEP);
        table.put(  "return",                           Symbol.KEEP);
        table.put(  "class",                            Symbol.KEEP);
        table.put(  "@interface",                       Symbol.KEEP);
        table.put(  "#include",                         Symbol.KEEP);
        table.put(  "null",                             Symbol.KEEP);
        table.put(  "true",                             Symbol.KEEP);
        table.put(  "false",                            Symbol.KEEP);
        table.put(  "instanceof",                       Symbol.KEEP);
        table.put(  "goto",                             Symbol.KEEP);
        table.put(  "break",                            Symbol.KEEP);
        table.put(  "for",                              Symbol.KEEP);
        table.put(  "while",                            Symbol.KEEP);
        table.put(  "this",                             Symbol.KEEP);
        table.put(  "func",                             Symbol.KEEP);

        /*
         * 运算符
         */
        table.put(  "+",                                Symbol.OP);
        table.put(  "-",                                Symbol.OP);
        table.put(  "*",                                Symbol.OP);
        table.put(  "/",                                Symbol.OP);
        table.put(  "%",                                Symbol.OP);
        table.put(  "^",                                Symbol.OP);
        table.put(  "=",                                Symbol.OP);
        table.put(  "<",                                Symbol.OP);
        table.put(  ">",                                Symbol.OP);
        table.put(  "++",                               Symbol.OP);
        table.put(  "--",                               Symbol.OP);
        table.put(  "==",                               Symbol.OP);
        table.put(  ">=",                               Symbol.OP);
        table.put(  "<=",                               Symbol.OP);
        table.put(  ">>",                               Symbol.OP);
        table.put(  "<<",                               Symbol.OP);
        table.put(  "===",                              Symbol.OP);

        /*
         * 界限符
         */
        table.put(  ";",                                Symbol.LIMIT);
        table.put(  "(",                                Symbol.LIMIT);
        table.put(  ")",                                Symbol.LIMIT);
        table.put(  "[",                                Symbol.LIMIT);
        table.put(  "]",                                Symbol.LIMIT);
        table.put(  "{",                                Symbol.LIMIT);
        table.put(  "}",                                Symbol.LIMIT);
        table.put(  ".",                                Symbol.LIMIT);
        table.put(  "\\",                               Symbol.LIMIT);

    }

}
