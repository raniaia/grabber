package com.raniaia.grabber.bytecode;

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
 * Creates on 2020/4/22.
 */

/**
 * 中间代码，Grabber指令集
 *
 * ICONST           -- ICONST代表int类型
 * UICONST          -- UICONST代表无符号int类型
 * LCONST           -- LCONST代表long类型
 * ULCONST          -- ULCONST代表无符号long类型
 * CHCONST          -- CHCONST代表char类型
 *
 * @author tiansheng
 */
@SuppressWarnings("all")
public enum Instruction {

    /**
     * 什么也不做
     */
    NOP,

    /**
     * 将int类型的0推送至栈顶
     */
    ICONST_0,

    /**
     * 将int类型的1推送至栈顶
     */
    ICONST_1,

    /**
     * 将int类型的2推送至栈顶
     */
    ICONST_2,

    /**
     * 将int类型的3推送至栈顶
     */
    ICONST_3,

    /**
     * 将int类型的4推送至栈顶
     */
    ICONST_4,

    /**
     * 将int类型的5推送至栈顶
     */
    ICONST_5,

    /**
     * 将int类型的6推送至栈顶
     */
    ICONST_6,

    /**
     * 将int类型的7推送至栈顶
     */
    ICONST_7,

    /**
     * 将int类型的8推送至栈顶
     */
    ICONST_8,

    /**
     * 将int类型的9推送至栈顶
     */
    ICONST_9,

    /**
     * 将无符号int类型的0推送至栈顶
     */
    UICONST_0,

    /**
     * 将无符号int类型的1推送至栈顶
     */
    UICONST_1,

    /**
     * 将无符号int类型的2推送至栈顶
     */
    UICONST_2,

    /**
     * 将无符号int类型的3推送至栈顶
     */
    UICONST_3,

    /**
     * 将无符号int类型的4推送至栈顶
     */
    UICONST_4,

    /**
     * 将无符号int类型的5推送至栈顶
     */
    UICONST_5,

    /**
     * 将无符号int类型的6推送至栈顶
     */
    UICONST_6,

    /**
     * 将无符号int类型的7推送至栈顶
     */
    UICONST_7,

    /**
     * 将无符号int类型的8推送至栈顶
     */
    UICONST_8,

    /**
     * 将无符号int类型的9推送至栈顶
     */
    UICONST_9,

    /**
     * 将long类型的0推送至栈顶
     */
    LCONST_0,

    /**
     * 将long类型的1推送至栈顶
     */
    LCONST_1,

    /**
     * 将long类型的2推送至栈顶
     */
    LCONST_2,

    /**
     * 将long类型的3推送至栈顶
     */
    LCONST_3,

    /**
     * 将long类型的4推送至栈顶
     */
    LCONST_4,

    /**
     * 将long类型的5推送至栈顶
     */
    LCONST_5,

    /**
     * 将long类型的6推送至栈顶
     */
    LCONST_6,

    /**
     * 将long类型的7推送至栈顶
     */
    LCONST_7,

    /**
     * 将long类型的8推送至栈顶
     */
    LCONST_8,

    /**
     * 将long类型的9推送至栈顶
     */
    LCONST_9,

    /**
     * 将无符号long类型的0推送至栈顶
     */
    ULCONST_0,

    /**
     * 将无符号long类型的1推送至栈顶
     */
    ULCONST_1,

    /**
     * 将无符号long类型的2推送至栈顶
     */
    ULCONST_2,

    /**
     * 将无符号long类型的3推送至栈顶
     */
    ULCONST_3,

    /**
     * 将无符号long类型的4推送至栈顶
     */
    ULCONST_4,

    /**
     * 将无符号long类型的5推送至栈顶
     */
    ULCONST_5,

    /**
     * 将无符号long类型的6推送至栈顶
     */
    ULCONST_6,

    /**
     * 将无符号long类型的7推送至栈顶
     */
    ULCONST_7,

    /**
     * 将无符号long类型的8推送至栈顶
     */
    ULCONST_8,

    /**
     * 将无符号long类型的9推送至栈顶
     */
    ULCONST_9,

    /**
     * 将一个byte类型的值推送至栈顶
     */
    BYCONST,

    /**
     * 将一个布尔类型的true推送至栈顶
     */
    BOOLCONST_TRUE,

    /**
     * 将一个布尔类型的false推送至栈顶
     */
    BOOLCONST_FALSE,

    /**
     * 执行int类型的加法
     */
    IADD,

    /**
     * 执行int类型的减法
     */
    ISUB,

    /**
     * 执行int的类型的乘法
     */
    IMUL,

    /**
     * 执行int类型的除法
     */
    IDIV,

    /**
     * 执行无符号int类型的加法
     */
    UIADD,

    /**
     * 执行无符号int类型的减法
     */
    UISUB,

    /**
     * 执行无符号int的类型的乘法
     */
    UIMUL,

    /**
     * 执行无符号int类型的除法
     */
    UIDIV,

    /**
     * 执行long类型的加法
     */
    LADD,

    /**
     * 执行long类型的减法
     */
    LSUB,

    /**
     * 执行long的类型的乘法
     */
    LMUL,

    /**
     * 执行long类型的除法
     */
    LDIV,

    /**
     * 执行无符号long类型的加法
     */
    ULADD,

    /**
     * 执行无符号long类型的减法
     */
    ULSUB,

    /**
     * 执行无符号long类型的乘法
     */
    ULMUL,

    /**
     * 执行无符号long类型的除法
     */
    ULDIV,

}