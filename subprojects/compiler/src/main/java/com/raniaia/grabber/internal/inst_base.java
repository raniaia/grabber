package com.raniaia.grabber.internal;

/*
 * copyright (c) 2020 the original author or authors.
 * licensed under the gpl, version 2.0 (the "license");
 * you may not use this file except in compliance with the license.
 * you may obtain a copy of the license at
 *
 *     https://b2evolution.net/about/gnu-gpl-license
 *
 * unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "as is" basis,
 * without warranties or conditions of any kind, either express or implied.
 * see the license for the specific language governing permissions and
 * limitations under the license.
 *
 * the source code of this program is only provided for learning and research.
 * if the program source code is used for criminal acts, such as illegal acts,
 * it is not related to the original author and need to be personally responsible.
 */

/*
 * creates on 2020/4/22.
 */

/**
 * 中间代码，grabber指令集
 *
 * @author tiansheng
 */
@SuppressWarnings("all")
public enum inst_base {

    /**
     * 什么也不做
     */
    nop,

    /**
     * 将int类型的常量放入内存
     */
    iconst,

    /**
     * 将无符号int类型的常量放入内存
     */
    uiconst,

    /**
     * 将long类型的常量放入内存
     */
    lconst,

    /**
     * 将无符号long类型的常量放入内存
     */
    ulconst,

    /**
     * 将float类型的常量放入内存
     */
    fconst,

    /**
     * 将double类型的常量放入内存
     */
    dconst,

    /**
     * 将char类型的常量放入内存
     */
    chconst,

    /**
     * 将字符串作为放入内存
     */
    strconst,

    /**
     * 将bool类型的常量true放入内存
     */
    blconst_true,

    /**
     * 将bool类型的常量false放入内存
     */
    blconst_false,

    /**
     * 将byte类型的常量放入内存
     */
    btcosnt,

    /**
     * 从内存中读取int类型的常量
     */
    iload,

    /**
     * 从内存中读取无符号int类型的常量
     */
    uiload,

    /**
     * 从内存中读取long类型的常量
     */
    lload,

    /**
     * 从内存中读取无符号long类型的常量
     */
    ulload,

    /**
     * 从内存中读取float类型的常量
     */
    fload,

    /**
     * 从内存中读取double类型的常量
     */
    dload,

    /**
     * 从内存中读取char类型的常量
     */
    chload,

    /**
     * 从内存中读取bool类型的常量true
     */
    blload_true,

    /**
     * 从内存中读取bool类型的常量false
     */
    blload_false,

    /**
     * 从内存中读取byte类型的常量
     */
    btload,

    /**
     * 执行int类型的常量加法
     */
    iadd,

    /**
     * 执行int类型的常量减法
     */
    isub,

    /**
     * 执行int类型常量的乘法
     */
    imul,

    /**
     * 执行int类型常量的除法
     */
    idiv,

    /**
     * 执行无符号int类型的常量加法
     */
    uiadd,

    /**
     * 执行无符号int类型的常量减法
     */
    uisub,

    /**
     * 执行无符号int类型常量的乘法
     */
    uimul,

    /**
     * 执行无符号int类型常量的除法
     */
    uidiv,

    /**
     * 执行long类型的常量加法
     */
    ladd,

    /**
     * 执行long类型的常量减法
     */
    lsub,

    /**
     * 执行long类型常量的乘法
     */
    lmul,

    /**
     * 执行long类型常量的除法
     */
    ldiv,

    /**
     * 执行无符号long类型的常量加法
     */
    uladd,

    /**
     * 执行无符号long类型的常量减法
     */
    ulsub,

    /**
     * 执行无符号long类型常量的乘法
     */
    ulmul,

    /**
     * 执行无符号long类型常量的除法
     */
    uldiv,

    /**
     * 执行float类型的常量加法
     */
    fadd,

    /**
     * 执行float类型的常量减法
     */
    fsub,

    /**
     * 执行float类型常量的乘法
     */
    fmul,

    /**
     * 执行float类型常量的除法
     */
    fdiv,

    /**
     * 执行double类型的常量加法
     */
    dadd,

    /**
     * 执行double类型的常量减法
     */
    dsub,

    /**
     * 执行double类型常量的乘法
     */
    dmul,

    /**
     * 执行double类型常量的除法
     */
    ddiv,

    /**
     * 对两个对象的地址进行比较，如果为true则跳转
     */
    ifcomp,

    /**
     * 比较两个常量是否相等，如果为true则跳转
     */
    ifeq,

    /**
     * 比较两个常量是否不相等，如果为true则跳转
     */
    ifne,

    /**
     * 比较常量1是否小于常量2，如果为true则跳转
     */
    iflt,

    /**
     * 比较常量1是否小于等于常量2，如果为true则跳转
     */
    ifle,

    /**
     * 比较常量1是否大于常量2，如果为true则跳转
     */
    ifgt,

    /**
     * 比较常量1是否大于等于常量2，如果为true则跳转
     */
    ifge,

    /**
     * 比较对象是否为null，如果为true则跳转
     */
    ifnull,

    /**
     * 比较对象是否不为null，如果为true则跳转
     */
    ifnonnull,

    /**
     * 无条件跳转
     */
    gotow,

    /**
     * 当条件为truer时跳转
     */
    loop,

    /**
     * 程序中断
     */
    inte,

    /**
     * 向屏幕打印内存
     */
    printstrem,

}