package com.raniaia.grabber.object;

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
 * Creates on 2020/4/11.
 */

/**
 * @author tiansheng
 */
public enum LexerStatus {

	/**
	 * 最初状态
	 */
	INITIAL,

	/**
	 * 词法解析器当前正读取到字符串
	 */
	STRING,

	/**
	 * 词法解析器当前正读取到保留关键字
	 */
	KEEP,

	/**
	 * 词法解析器当前正在读数字
	 */
	NUMBER,

	/**
	 * 词法解析器当前正在读表达式
	 */
	EXP,

	/**
	 * 解析异常，语法声明等不正确情况
	 */
	ERROR

}
