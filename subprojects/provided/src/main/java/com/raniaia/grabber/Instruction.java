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
 * Creates on 2020/4/14.
 */

import com.sun.org.apache.bcel.internal.generic.NOP;

/**
 * 中间代码指令
 *
 * PI_*				 - 压入数据到栈顶
 * NOP				 - 什么也不做
 * DUP				 - 从栈顶复制数据
 *
 * @author tiansheng
 */
public enum Instruction {

	/**
	 * 什么也不做
	 */
	NOP,

	/**
	 * 向栈中压入int
	 */
	PI_INT,

	/**
	 * 向栈中压入无符号int
	 */
	PI_UINT,

	/**
	 * 向栈中压入long
	 */
	PI_LONG,

	/**
	 * 向栈中压入无符号long
	 */
	PI_ULONG,

	/**
	 * 向栈中压入double
	 */
	PI_DOUBLE,

	/**
	 * 向栈中压入float
	 */
	PI_FLOAT,

	/**
	 * 向栈中压入char
	 */
	PI_CHAR,

	/**
	 * 向栈中压入布尔
	 */
	PI_BOOL,

	/**
	 * 向栈中压入NULL
	 */
	PI_NULL,

	/**
	 * 从栈顶弹出一个常量
	 */
	POP,

	/**
	 * 从栈顶弹出两个常量
	 */
	POP2,

	/**
	 * 指定从栈顶弹出多少个常量。
	 * 比如我们想弹出栈顶3个常量的话就这样使用<code>
	 *     POPC 3
	 * </code>
	 */
	POPC,

	/**
	 * 拷贝栈顶的一个常量，到任意地址。
	 * 如果没设置拷贝到哪个地址的话默认拷贝到栈顶
	 */
	DUP,

	/**
	 * 拷贝栈顶的2个常量，到任意地址。
	 * 如果没设置拷贝到哪个地址的话默认拷贝到栈顶
	 */
	DUP2,

	/**
	 * 拷贝栈顶的n个常量，到任意地址。
	 * 如果没设置拷贝到哪个地址的话默认拷贝到栈顶
	 */
	DUPC,

	/**
	 * 对int类型的常量进行加法运算
	 */
	IADD,

	/**
	 * 对int类型的常量做减法运算
	 */
	ISUB,


	/**
	 * 对int类型的常量做乘法运算
	 */
	IMUL,

	/**
	 * 对int类型的常量做除法运算
	 */
	IDIV,
}
