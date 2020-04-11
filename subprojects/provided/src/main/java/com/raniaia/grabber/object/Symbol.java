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

import com.sun.org.apache.bcel.internal.generic.SWITCH;

/**
 * 注意前排提醒：
 * 不要格式化这个类！
 * 不要格式化这个类！
 * 不要格式化这个类！
 *
 * 符号种别码，数组中表示每个符号对应的种别码以及每个符号对应的类别。
 *
 * @author tiansheng
 */
@SuppressWarnings("ALL")
public interface Symbol {

    int

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

    /**
     * 索引符号，$i。
     * 在for循环和while循环介绍的时候有提到过。所以这里就不再
     * 赘述了。
     */
    INDEX               =          {0xf18, KEEP},

    /**
     * this理解为指针，指向当前对象
     */
    THIS                =          {0xf19, KEEP},

    /**
     * super也是一个指针，指向父类的对象
     */
    SUPER               =          {0xf20, KEEP},

    /**
     * switch选择，配合case使用
     */
    SWITCH              =          {0xf21, KEEP},
    CASE                =          {0xf22, KEEP},

    /**
     * 声明当前文件所在的包。
     */
    PACKAGE             =          {0xf23, KEEP},

    /**
     * 声明一个类，可以声明内部类，以及函数中声明一个类。
     */
    CLASS               =          {0xf24, KEEP},

    /**
     * 声明一个注解对象。声明方法如下<code>
     *     #scope 'type' 'runtime' // 声明对象的作用域
     *     annotation value {
     *     }
     * </code>
     *
     * 上面作用域的声明有很多，详情请参考{@link #SCOPE}
     */
    ANNOTATION          =          {0xf25, KEEP},

    /**
     * include导入结构文件以及包。
     */
    INCLUDE             =          {0xf26, KEEP},

    /**
     * for循环时使用
     */
    IN                  =          {0xf27, KEEP},

    /**
     * scope声明对象的作用域。
     * 什么意思呢？可以理解为对象的调用权限管控。
     * 比如说当前项目结构如下：<code>
     *     root
     *      | --- a
     *      |  |
     *      |  | --- Scope.grab
     *      |
     *      | --- b
     *      |  |
     *      |  | --- CallerB.grab
     *      |
     *      | --- c
     *      |  |
     *      |  | --- CallerC.grab
     *      |
     *      | --- d
     *      |  |
     *      |  | --- CallerD.grab
     *      |
     *      |
     * </code>
     *
     * 我们在Scope.grab文件中声明哪些包可以调用这个类，或者说哪些类可以调用这个类。
     * 如果不符合作用域声明的类去调用Scope就会报错。
     *
     * 比如我们指向让b包下的类调用Scope对象，可以这样声明<code>
     *     class Scope {
     *         scope {
     *             include 'root.b'
     *         }
     *     }
     * </code>
     *
     * 如果是注解类的话需要这样使用：<code>
     *     class Scope {
     *         scope {
     *             target 'type'
     *             retention 'runtime'
     *             include 'root.b'
     *         }
     *     }
     * </code>
     *
     * 如果是需要多个注解目标的话则在target后面多加几个参数即可，参数可以完全
     * 参照Java的来做，示例：<code>
     *     class Scope {
     *         scope {
     *             target 'type' 'field' 'method'
     *             retention 'runtime'
     *             include 'this'
     *         }
     *     }
     * </code>
     *
     * 当然如果你注解啥也不做只是一个标记的话，那么可以什么也不用指定。默认就是
     * 只在源码中存在注解。
     *
     */
    SCOPE               =          {0xf28, KEEP},

    /**
     * 运算符加号
     */
    ADD                 =          {0x26e, OP},

    /**
     * 运算符减号
     */
    SUB                 =          {0x27e, OP},

    /**
     * 运算符乘号
     */
    MUL                 =          {0x28e, OP},

    /**
     * 运算符除号
     */
    DIV                 =          {0x29e, OP},

    /**
     * 逻辑符等于
     */
    EQ                  =          {0x30e, OP},

    /**
     * 逻辑符不等于
     */
    NE                  =          {0x31e, OP},

    /**
     * 逻辑符大于
     */
    GT                  =          {0x32e, OP},

    /**
     * 逻辑符小于
     */
    LT                  =          {0x33e, OP},

    /**
     * 逻辑符大于等于
     */
    GE                  =          {0x34e, OP},

    /**
     * 逻辑符小于等于
     */
    LE                  =          {0x35e, OP},

    /**
     * 向左位移
     */
    DISL                =          {0x36e, OP},

    /**
     * 向右位移
     */
    DISR                =          {0x37e, OP},

    /**
     * 幂运算
     */
    POWER               =          {0x38e, OP},

    /**
     * 取余
     */
    SURPLUS             =          {0x39e, OP},

    /**
     * 表示一个char字符
     */
    CHAR                =          {0x01fe, CONST},

    /**
     * 表示一个String字符串
     */
    STRING              =          {0x02fe, CONST},

    /**
     * 整型（有符号的）
     */
    S_INTEGER           =          {0x03fe, CONST},

    /**
     * 整型（无符号的）
     *
     * 上面有符号的整型可能有点让人懵逼，为什么整型还分符号?因为在计算机里数字
     * 都是用二进制表示的，所以要表达一个整数是否为正整数或者是负整数的话一般二进制
     * 最左边的一位就是拿来表示正数或者是负数的符号。
     *
     * 那么无符号的整数就没有这个数字，它只能是整型。所以叫做无符号整数。无符号的整数的
     * 用处就是用来存储地址、索引等正整数。它们的范围可以是8位、16位、32位、64位甚至更多。
     *
     * 其取值范围8个二进制的正整数为255(2^8-1)、16位二进制位表示的正整数其取值范围是0~65535(2^16-1),
     * 32位二进制位表示的正整数其取值范围是(0-2^32-1)
     */
    U_INTEGER           =          {0x04fe, CONST},

    /**
     * 长整型
     */
    LONG                =          {0x05fe, CONST},

    /**
     * 小数点
     */
    DECIMAL             =          {0x06fe, CONST},

    /** 符号表示：$ **/
    DOLLAR              =         {0x39a, LIMIT},

    /** 符号表示：( **/
    LPBT                =         {0x40a, LIMIT},

    /** 符号表示：) **/
    RPBT                =         {0x40a, LIMIT},

    /** 符号表示：] **/
    LSBT                =         {0x41a, LIMIT},

    /** 符号表示：[ **/
    RSBT                =         {0x42a, LIMIT},

    /** 符号表示：{ **/
    LCBT                =         {0x43a, LIMIT},

    /** 符号表示：} **/
    RCBT                =         {0x44a, LIMIT};

    int

    //
    // 类的标识符，头信息
    //
    HEAD_INFO           =            0xF01,                  

    // 结束符
    __END__             =            0xF02;                  

}
