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
 * Creates on 2020/4/4.
 */

import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.raniaia.available.string.StringUtils;

import java.lang.reflect.Field;

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

    Operator op          = new Operator(){};

    Class<?> current     = op.getClass();

    /**
     * 每个符号对应的识别码
     */
    int

    IDEN                =             0,           // value:      variable

    //
    // 操作符
    //
    ELSE                =             0xf1,           // value:       else
    RETURN              =             0xf2,           // value:       return
    FOR                 =             0xf3,           // value:       for
    WHILE               =             0xf4,           // value:       while
    EXPORT              =             0xf5,           // value:       export
    DEF                 =             0xf6,           // value:       def
    SET                 =             0xf7,           // value:       set
    STATIC              =             0xf8,           // value:       static
    NULL                =             0xf9,           // value:       null
    NONE                =            0xf10,           // value:       none
    TRUE                =            0xf11,           // value:       true
    FALSE               =            0xf12,           // value:       false
    INSTANCEOF          =            0xf13,           // value:       instance
    ARRAYOF             =            0xf14,           // value:       arrayof
    GOTO                =            0xf15,           // value:       goto
    BREAK               =            0xf16,           // value:       break
    CONTINUE            =            0xf17,           // value:       continue
    PRE                 =            0xf18,           // value:       pre
    THIS                =            0xf19,           // value:       this
    SUPER               =            0xf20,           // value:       super
    SWITCH              =            0xf21,           // value:       switch
    CASE                =            0xf22,           // value:       case
    PACKAGE             =            0xf23,           // value:       package
    CLASS               =            0xf24,           // value:       class
    INTERFACE           =            0xf25,           // value:       @interface       注解
    INCLUDE             =            0xf26,

    //
    // 运算符
    //
    ADD                 =            0x26e,           // value:       add              加
    SUB                 =            0x27e,           // value:       sub              减
    MUL                 =            0x28e,           // value:       mul              乘
    DIV                 =            0x29e,           // value:       div              除
    EQ                  =            0x30e,           // value:       eq               等于
    NE                  =            0x31e,           // value:       ne               不等于
    GT                  =            0x32e,           // value:       gt               大于
    LT                  =            0x33e,           // value:       lt               小于
    GE                  =            0x34e,           // value:       ge               大于等于
    LE                  =            0x35e,           // value:       le               小于等于
    DISL                =            0x36e,           // value:       <<               左位移
    DISR                =            0x37e,           // value:       >>               有位移
    POWER               =            0x38e,           // value:       ^                计算次方

    //
    // 其他符号
    //
    DOLLAR               =           0x39e,            // value:       $                 美元符号
    LPBT                 =           0x40e,            // value:       (
    RPBT                 =           0x40e,            // value:       )
    LSBT                 =           0x41e,            // value:       [
    RSBT                 =           0x42e,            // value:       ]
    LCBT                 =           0x42e,            // value:       ]
    RCBT                 =           0x42e,            // value:       ]

    //
    // 类的标识符，头信息
    //
    HEAD_INFO           =            0xF01,        // value: 0xF01

    // 结束符
    __END__             =            0xF02;         // value: 0xF02


//    static void main(String[] args) throws Throwable {
//        long s = System.currentTimeMillis();
//        Operator op = new Operator(){};
//        for(Field f : Operator.class.getDeclaredFields()){
//             System.out.println(f.getName() + " = " + f.get(op));
//        }
//        long e = System.currentTimeMillis();
//    }

}