package com.raniaia.grabber.bytecode;

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
public enum Instruction {

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

}