package object;/*
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


    /**
     * 每个符号对应的识别码
     */
    int

    //
    // 操作符
    //
    ELSE                =            0xA1,            // value:       else
    RETURN              =            0xA2,            // value:       return
    FOR                 =            0xA3,            // value:       for
    WHILE               =            0xA4,            // value:       while
    EXPORT              =            0xA5,            // value:       export
    DEF                 =            0xA6,            // value:       def
    SET                 =            0xA7,            // value:       set
    STATIC              =            0xA8,            // value:       static
    NULL                =            0xA9,            // value:       null
    NONE                =            0xA10,           // value:       none
    TRUE                =            0xA11,           // value:       true
    FALSE               =            0xA12,           // value:       false
    INSTANCEOF          =            0xA13,           // value:       instance
    ARRAYOF             =            0xA14,           // value:       arrayof
    GOTO                =            0xA15,           // value:       goto
    BREAK               =            0xA16,           // value:       break
    CONTINUE            =            0xA17,           // value:       continue
    PRE                 =            0xA18,           // value:       pre
    THIS                =            0xA19,           // value:       this
    SUPER               =            0xA20,           // value:       super
    SWITCH              =            0xA21,           // value:       switch
    CASE                =            0xA22,           // value:       case
    PACKAGE             =            0xA23,           // value:       package
    CLASS               =            0xA24,           // value:       class
    INTERFACE           =            0xA25,           // value:       interface

    //
    // 运算符
    //
    ADD                 =            0xB26,           // value:       add
    SUB                 =            0xB27,           // value:       sub
    MUL                 =            0xB28,           // value:       mul
    DIV                 =            0xB29,           // value:       div
    EQ                  =            0xB30,           // value:       eq
    NE                  =            0xB31,           // value:       ne
    GT                  =            0xB32,           // value:       gt
    LT                  =            0xB33,           // value:       lt
    GE                  =            0xB34,           // value:       ge
    LE                  =            0xB35,           // value:       le
    DISL                =            0xB36,           // value:       disl
    DISR                =            0xB37,           // value:       disr
    POWER               =            0xB38,           // value:       power

    //
    // 类的标识符，头信息
    //
    HEAD_INFO           =            0xF01,           // value: 0xF01

    // 结束符
    __END__             =            0xF02;            // value: 0xF02


    static void main(String[] args) throws Throwable {
        long s = System.currentTimeMillis();
        Operator op = new Operator(){};
        for(Field f : Operator.class.getDeclaredFields()){
             System.out.println(f.getName() + " = " + f.get(op));
         }
        long e = System.currentTimeMillis();
    }

}