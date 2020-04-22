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

import org.raniaia.available.list.Lists;

import java.util.List;

/**
 * 语义分析器
 *
 * @author tiansheng
 */
public class SyntaxTreeGen implements Constants {

	/**
	 * 推导出当前节点是在做什么事情
	 */
	Nodekind nodekind;

	Nodekind previous;

	List<SyntaxToken> tokens = Lists.newLinkedList();

	/**
	 * 生成抽象语法树
	 *
	 * @param finalToken 需要形成树结构的语法token
	 */
	public void genSyntaxTree(FinalToken finalToken) {
		//
		// 先构造出一个最基本的树
		//
		for (SyntaxToken syntaxToken : finalToken.tokens) {
			buildNode(syntaxToken);
		}
	}

	void buildNode(SyntaxToken token)
	{
		switch (token.code) {
			/*
			 * 如果当前Token是INCLUDE，那么我们当前就认定
			 * 正在下个生成的节点是在声明头信息。
			 *
			 * 头信息就比如说导包，导入声明文件等操作。
			 */
			case KEEP_INCLUDE: {
				tokens.add(token);
				// ## 因为还没读到下一个，所以我们暂且就认为用户声明一个
				// ## INCLUDE关键字，只是不知道这个关键字作用在导包还是作用在
				// ## 导入声明文件。
				updateNodekind(Nodekind.NK_INCLUDE);
			}

			/*
			 * 当前的token为小于号。小于号有多种用途，比如说单个小于号
			 * 可以用来比较大小，也可以用来作为导入声明文件的括号，以及
			 * 两个小于号可以表示左位移运算符等。
			 *
			 * 所以我们需要定位到当前小于号的作用的话就需要通过上一个#Nodekind来
			 * 进行对语法的推导。
			 */
			case OPT_LT: {
				// ##
				// ## 如果当前token是小于号，且当前{@code nodekind}是
				// ## {@code Nodekind#NK_INCLUDE}d的话，那么我们就可以确定
				// ## 当前是在导入声明文件了。
				// ##
				// ## 如果后续没扫描到声明文件，或者说没扫描到最后一个小于号那么我们都
				// ## 可以抛出声明异常，并且可以精确定位异常所在点。
				// ##
				if(nodekind == Nodekind.NK_INCLUDE) {
					updateNodekind(Nodekind.STMT_INCLUDE_STMT);
				}
			}
			case IDEN: {
				if (nodekind == Nodekind.STMT_INCLUDE_STMT) {
					String v = token.value;
					System.out.println(v);
				}
			}
		}
	}

	void updateNodekind(Nodekind kind) {
		previous = nodekind;
		nodekind = kind;
	}

}