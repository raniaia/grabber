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

import org.raniaia.available.array.ArrayUtils;

/**
 * 操作符以及运算符byte数组常量。注意：请勿随意格式化.
 *
 * 每个符号都是一个byte数组，假设我们有一段代码：
 * <code>
 *     set x = 0;
 *     set y = 0;
 *     def main(args)
 *     {
 *         print("Hello,World!");
 *         print(1 + 1);
 *     }
 *     def test()
 *     {
 *         print("two")
 *     }
 * </code>
 *
 * 当我们解析成字节码就是这样的:
 * <code>
 * </code>
 *
 * @author tiansheng
 */
public interface Operator {


    /**
     * 每个符号都是byte数组，真实的值为注释value：后面的内容。
     */
    byte[]

    //
    // 操作符
    //
    ELSE                =            {101,108,115,101},                                     // value:       else
    RETURN              =            {114,101,116,117,114,110},                             // value:       return
    FOR                 =            {102,111,114},                                         // value:       for
    WHILE               =            {119,104,105,108,101},                                 // value:       while
    EXPORT              =            {101,120,112,111,114,116},                             // value:       export
    DEF                 =            {100,101,102},                                         // value:       def
    SET                 =            {115,101,116},                                         // value:       set
    STATIC              =            {115,116,97,116,105,99},                               // value:       static
    NULL                =            {110,117,108,108},                                     // value:       null
    NONE                =            {110,111,110,101},                                     // value:       none
    TRUE                =            {116,114,117,101},                                     // value:       true
    FALSE               =            {102,97,108,115,101},                                  // value:       false
    INSTANCEOF          =            {105,110,115,116,97,110,99,101,111,102},               // value:       instance
    ARRAYOF             =            {97,114,114,97,121,111,102},                           // value:       arrayof
    GOTO                =            {103,111,116,111},                                     // value:       goto
    BREAK               =            {98,114,101,97,107},                                   // value:       break
    CONTINUE            =            {99,111,110,116,105,110,117,101},                      // value:       continue
    PRE                 =            {112,114,101},                                         // value:       pre
    THIS                =            {116,104,105,115},                                     // value:       this
    SUPER               =            {115,117,112,101,114},                                 // value:       super
    SWITCH              =            {115,119,105,116,99,104},                              // value:       switch
    CASE                =            {99,97,115,101},                                       // value:       case
    PACKAGE             =            {112,97,99,107,97,103,101},                            // value:       package
    CLASS               =            {99,108,97,115,115},                                   // value:       class
    INTERFACE           =            {105,110,116,101,114,102,97,99,101},                   // value:       interface

    //
    // 运算符
    //
    ADD                 =            {43},                                                  // value:       add
    SUB                 =            {45},                                                  // value:       sub
    MUL                 =            {42},                                                  // value:       mul
    DIV                 =            {47},                                                  // value:       div
    EQ                  =            {61},                                                  // value:       eq
    NE                  =            {33,61},                                               // value:       ne
    GT                  =            {62},                                                  // value:       gt
    LT                  =            {60},                                                  // value:       lt
    GE                  =            {62,61},                                               // value:       ge
    LE                  =            {60,61},                                               // value:       le
    DISL                =            {60,60},                                               // value:       disl
    DISR                =            {62,62},                                               // value:       disr
    POWER               =            {94},                                                  // value:       power

    //
    // 类的标识符，头信息
    //
    HEAD_INFO           =            {(byte) 67726162 & 0XF626572},                         // value: byte<82> String<grabber>

    // 结束符
    __END__             =            {59};                                                  // value:       ;


    static void main(String[] args) {
        System.out.println(67726162 & 0XF626572);
        System.out.println(ArrayUtils.toString("print(\"Hello,World!\");".getBytes()," "));
    }

}