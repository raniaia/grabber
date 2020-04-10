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

    //
    // 操作符
    //
    ELSE                =             1,           // value:       else
    RETURN              =             2,           // value:       return
    FOR                 =             3,           // value:       for
    WHILE               =             4,           // value:       while
    EXPORT              =             5,           // value:       export
    DEF                 =             6,           // value:       def
    SET                 =             7,           // value:       set
    STATIC              =             8,           // value:       static
    NULL                =             9,           // value:       null
    NONE                =            10,           // value:       none
    TRUE                =            11,           // value:       true
    FALSE               =            12,           // value:       false
    INSTANCEOF          =            13,           // value:       instance
    ARRAYOF             =            14,           // value:       arrayof
    GOTO                =            15,           // value:       goto
    BREAK               =            16,           // value:       break
    CONTINUE            =            17,           // value:       continue
    PRE                 =            18,           // value:       pre
    THIS                =            19,           // value:       this
    SUPER               =            20,           // value:       super
    SWITCH              =            21,           // value:       switch
    CASE                =            22,           // value:       case
    PACKAGE             =            23,           // value:       package
    CLASS               =            24,           // value:       class
    IFACE               =            25,           // value:       @interface       注解

    //
    // 运算符
    //
    ADD                 =            26,           // value:       add              加
    SUB                 =            27,           // value:       sub              减
    MUL                 =            28,           // value:       mul              乘
    DIV                 =            29,           // value:       div              除
    EQ                  =            30,           // value:       eq               等于
    NE                  =            31,           // value:       ne               不等于
    GT                  =            32,           // value:       gt               大于
    LT                  =            33,           // value:       lt               小于
    GE                  =            34,           // value:       ge               大于等于
    LE                  =            35,           // value:       le               小于等于
    DISL                =            36,           // value:       <<               左位移
    DISR                =            37,           // value:       >>               有位移
    POWER               =            38,           // value:       ^                计算次方

    //
    // 类的标识符，头信息
    //
    HEAD_INFO           =            0xF01,        // value: 0xF01

    // 结束符
    __END__             =            0xF02;         // value: 0xF02

    static Integer get0(String name) {
        name = StringUtils.toUpperCase(name);
        try {
            Field field = current.getDeclaredField(name);
            return (Integer) field.get(op);
        } catch (Throwable e) {
            // 忽略异常
            return null;
        }
    }

//    static void main(String[] args) throws Throwable {
//        long s = System.currentTimeMillis();
//        Operator op = new Operator(){};
//        for(Field f : Operator.class.getDeclaredFields()){
//             System.out.println(f.getName() + " = " + f.get(op));
//        }
//        long e = System.currentTimeMillis();
//    }

}