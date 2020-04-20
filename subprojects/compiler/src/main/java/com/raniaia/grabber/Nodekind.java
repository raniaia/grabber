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

/**
 * 表示一个节点的语句含义
 *
 * @author tiansheng
 */
public enum Nodekind {

	/**
	 * 表达式
	 */
	CONST_EXPR,

	/**
	 * 定义一个数组
	 */
	CONST_ARRAY_DEF,

	/**
	 * 标识符
	 */
	CONST_IDEN,

	/**
	 * 定义函数块
	 */
	CONST_FEAT,

	/**
	 * return
	 */
	CONST_RETURN,

	/**
	 * 获取到了include token
	 */
	NK_INCLUDE,

	/**
	 * 声明导入文件
	 */
	STMT_INCLUDE_FILE,

	/**
	 * 导入声明文件
	 */
	STMT_INCLUDE_STMT,


}
