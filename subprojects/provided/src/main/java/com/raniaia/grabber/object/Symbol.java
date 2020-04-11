package com.raniaia.grabber.object;/*
 * Copyright (C) 2020 the original author or authors.
 * Licensed under the GPL,  Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://b2evolution.net/about/gnu-gpl-license
 *
 * Unless required by applicable law or agreed to in writing,  software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,  either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The source code of this program is only provided for learning and research.
 * If the program source code is used for criminal acts,  such as illegal acts, 
 * it is not related to the original author and need to be personally responsible.
 */

/*
 * Creates on 2020/4/8.
 */

import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

/**
 * 注意前排提醒：
 *              不要格式化这个类！
 *              不要格式化这个类！
 *              不要格式化这个类！
 *
 * 符号种别码，数组中表示每个符号对应的种别码以及每个符号对应的类别。
 *
 *
 * @author tiansheng
 */
@SuppressWarnings("ALL")
public interface Symbol {

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
     * 每个符号对应的识别码
     */
    int[]

    /**
     * else，在if块后面使用
     * else为Grabber中的保留字符
     */
    ELSE                =          {0xf01, KEEP},

    /**
     * return，返回函数值。
     * 在Grabber中return是一个保留字符。
     *
     * 在闭包函数中，可以不声明返回值。但是前提是闭包函数中只有一行代码的情况下
     * 可以不声明返回值。
     */
    RETURN              =          {0xf02, KEEP},

    /**
     * for循环。
     * 在Grabber中for循环是一个保留字符。
     *
     * 与Java和其他强类型语言的for循环不同，Grabber不支持使用<p>i<xxx</p>
     * 来进行for循环，只能通过<code>for(x in xs)</code>进行循环。
     *
     * 如果只是单纯的想要循环10次的话，也不需要像Java一样定义<code>
     *
     *     for(int i = 0; i < 10; i++) {}
     *
     * </code>
     *
     * 在Grabber中循环可以这样使用<code>
     *
     *     // 循环10次, 这是加加循环
     *     for(i inadd 10) {
     *         // 省略....
     *     }
     *
     * </code>
     *
     * 如果要减减循环的话可以这样写。<code>
     *
     *     for(i insub 10){
     *         // 省略....
     *     }
     *
     * </code>
     *
     * 按照以上方法可以大家可以发现除了加加的循环可以获取到当前循环到第几次了，那么其他循环怎么获取
     * 当前到底循环了多少次了？
     * Grabber提供了一个索引操作符，<$i>。使用$i即可获取当前循环到了多少次。例如：<code>
     *
     *     set list = {1,2,3,4,5,6,7,8,9,0}
     *     for ( number in list ) {
     *          print("当前循环到了第{}次", $i)
     *     }
     * </code>
     *
     */
    FOR                 =          {0xf03, KEEP},

    /**
     * while循环。
     * while为Grabber中保留的关键字。
     *
     * 使用方法很简单，当表达式始终为true的时候循环就会继续。例如<code>
     *
     *     // 当表达式一直为true循环就会一直继续
     *     while(true) {}
     *
     * </code>
     *
     * 获取当前循环到了多少次也是使用获取当前索引的符号：$i
     *
     */
    WHILE               =          {0xf04, KEEP},

    /**
     * export代码块。
     * 暂无定义，后续可能会更改。目前export关键字未分配任何功能。
     * 只是一个保留关键字而已。
     *
     * 或许后续会给export分配功能，但是目前还没有。
     */
    EXPORT              =          {0xf05, KEEP},

    /**
     * def定义函数块的关键字。
     * 使用def可以定义一个函数。使用def定义的函数不需要声明返回值。因为
     * 编译器会在解析阶段进行优化的时候推算出函数的返回值。这是一个语法糖。
     */
    DEF                 =          {0xf06, KEEP},

    /**
     * set声明一个变量或者是其他数据类型的时候需要使用的关键字。
     * 你可以用set声明一个int类型的整数：<code>
     *     set x = 10
     * </code>
     *
     * 当然你也可以用set声明一个map、数组等内容。
     */
    SET                 =          {0xf07, KEEP},

    /**
     * static关键字在现阶段也是和{@link #EXPORT}关键字一样。只是
     * 将它定义成了关键字，但是目前并没有任何作用。
     */
    STATIC              =          {0xf08, KEEP},

    /**
     * null关键字表示一个空指针，未引用任何类型的对象。同时
     * 在Grabber中它也是一个保留字符（仅限小写是保留字符）
     *
     * null是没有任何内存占用的。
     */
    NULL                =          {0xf09, KEEP},

    /**
     * to关键字可以理解为强制类型转换。
     * 比如我们要将一个字符串转换成2进制可以这样做转换<code>
     *     set x = "grabber"
     *     set x = x to 2hex // 编译器会自动将字符串转换成2进制
     * </code>
     *
     * 或者说你想将一个字符串转换成int，也可以使用to。<code>
     *     set x = 10
     *     x = x to int
     * </code>
     *
     * 其他数据类型的转换也是类似的。
     */
    TO                  =          {0xf10, KEEP},

    /**
     * true表示一个表达式的结果是否符合我们的预期结果。
     * 如果如何则反会true，不符合返回fasle
     */
    TRUE                =          {0xf11, KEEP},

    /**
     * 当表达式的结果不合符预期的时候返回fase
     */
    FALSE               =          {0xf12, KEEP},

    /**
     * instanceof判断两个对象的类型是否相同，或者说对象是否
     * 相同。
     *
     * 只要instanceof返回的是true，那么久代表这两个对象可以进行
     * 互相的转换。如果是false，那么这两个对象进行互转的话就会出错。
     *
     * 什么情况下可以互转呢？
     *      第一、 两个对象都存在相同的结构文件，则可以进行相互转换。
     *            转化后的结果以及函数可以结构文件为准。
     *
     *      第二、 两个对象都存在相同的继承对象，则可以进行互转。
     *            转换结果以父类为准。
     */
    INSTANCEOF          =          {0xf13, KEEP},

    /**
     * tag定义标签的关键字。tag在脚本语言中应该是非常常用的东西了。
     * 比如我们需要跳出if判断，让if不去执行下面的操作的时候我们就可以使用tag来定义
     * 一个标签了，例如：<code>
     *
     *     set x = 10
     *
     *     if(x == 10) {
     *          /--
     *           当我们要执行x+1的时候如果需要阻止程序执行下面的代码就
     *           可以使用标签。然后加上goto关键字跳转到下面的代码去执行。
     *          --/
     *          goto stop
     *          x++
     *     }
     *
     *     // 定义一个标签
     *     tag: stop
     *     if(x > 10) {
     *         throws (NumberException)
     *     }
     *
     * </code>
     *
     * tag + goto这一套组合在对象中仅限于在方法中使用。但是如果是脚本文件的话
     * 就可以在任何地方使用。
     */
    TAG                 =          {0xf14, KEEP},

    /**
     * goto跳转到指定标签
     */
    GOTO                =          {0xf15, KEEP},

    /**
     * break，跳出当前循环或者是当前switch。
     */
    BREAK               =          {0xf16, KEEP},

    /**
     * continue，进入下一次循环。该关键字仅限于循环中使用。
     */
    CONTINUE            =          {0xf17, KEEP},


    PRE                 =          {0xf18, KEEP},
    THIS                =          {0xf19, KEEP},
    SUPER               =          {0xf20, KEEP},
    SWITCH              =          {0xf21, KEEP},
    CASE                =          {0xf22, KEEP},
    PACKAGE             =          {0xf23, KEEP},
    CLASS               =          {0xf24, KEEP},
    INTERFACE           =          {0xf25, KEEP},
    INCLUDE             =          {0xf26, KEEP},
    IN                  =          {0xf27, KEEP},

    //
    // 运算符
    //
    ADD                 =          {0x26e, OP},              // value:       +
    SUB                 =          {0x27e, OP},              // value:       -
    MUL                 =          {0x28e, OP},              // value:       *
    DIV                 =          {0x29e, OP},              // value:       /
    EQ                  =          {0x30e, OP},              // value:       ==
    NE                  =          {0x31e, OP},              // value:       !=
    GT                  =          {0x32e, OP},              // value:       >
    LT                  =          {0x33e, OP},              // value:       <
    GE                  =          {0x34e, OP},              // value:       >=
    LE                  =          {0x35e, OP},              // value:       <=
    DISL                =          {0x36e, OP},              // value:       <<
    DISR                =          {0x37e, OP},              // value:       >>
    POWER               =          {0x38e, OP},              // value:       ^               次方计算
    SURPLUS             =          {0x39e, OP},              // value:       %               取余

    //
    // 比较特殊的符号，字符串以及数字等
    //
    CHAR                =          {0x01fe, CONST},          // value:        单个字符
    STRING              =          {0x02fe, CONST},          // value:        字符串
    S_INTEGER           =          {0x03fe, CONST},          // value:        有符号整数
    U_INTEGER           =          {0x04fe, CONST},          // value:        无符号整数
    S_LONG              =          {0x05fe, CONST},          // value:        有符号长整数
    U_LONG              =          {0x06fe, CONST},          // value:        无符号长整数
    DECIMAL             =          {0x07fe, CONST},          // value:        小数

    //
    // 其他符号
    //
    DOLLAR               =         {0x39a, LIMIT},           // value:       $
    LPBT                 =         {0x40a, LIMIT},           // value:       (
    RPBT                 =         {0x40a, LIMIT},           // value:       )
    LSBT                 =         {0x41a, LIMIT},           // value:       [
    RSBT                 =         {0x42a, LIMIT},           // value:       ]
    LCBT                 =         {0x43a, LIMIT},           // value:       {
    RCBT                 =         {0x44a, LIMIT};           // value:       }

    int

    //
    // 类的标识符，头信息
    //
    HEAD_INFO           =            0xF01,                  // value: 0xF01

    // 结束符
    __END__             =            0xF02;                  // value: 0xF02

}
