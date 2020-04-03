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
 * Creates on 2020/4/4.
 */

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * 操作符以及运算符。
 *
 * @author tiansheng
 */
public interface Operator {

    String

    // 操作符
    IF               = "if",
    ELSE             = "else",
    RETURN           = "return",
    FOR              = "for",
    WHILE            = "while",
    EXPORT           = "export",
    DEF              = "def",
    SET              = "set",
    STATIC           = "static",
    NULL             = "null",
    NONE             = "none",
    TRUE             = "true",
    FALSE            = "false",
    INSTANCEOF       = "instanceof",
    ARRAYOF          = "arrayof",
    GOTO             = "goto",
    BREAK            = "break",
    CONTINUE         = "continue",
    PRE              = "pre",
    THIS             = "this",
    SUPER            = "super",
    SWITCH           = "switch",
    CASE             = "case",
    PACKAGE          = "package",

    // 运算符
    ADD              = "+",
    SUB              = "-",
    MUL              = "*",
    DIV              = "/",

    EQ               = "=",
    NE               = "!=",
    GT               = ">",
    LT               = "<",
    GE               = ">=",
    LE               = "<=",

    DISL             = "<<",    // 向左位移
    DISR             = ">>",    // 向右位移

    POWER            = "^",     // 次方

    // 结束
    __END__          = null;

}