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
 * Creates on 2020/4/15.
 */

/**
 * @author tiansheng
 */
public class SyntaxTree implements AbstractSyntaxTree {

	String name;

	/**
	 * SyntaxTreeNode才是真正的树。
	 * SyntaxTree只是用来对TreeNode进行遍历的工具。
	 */
	SyntaxTreeNode tree = new SyntaxTreeNode();

	@Override
	public boolean top() {
		return false;
	}

	@Override
	public boolean bottom() {
		return false;
	}

	@Override
	public AbstractTreeNode next() {
		return null;
	}

	@Override
	public void setLeftChildren(SyntaxTreeNode node) {
		tree.leftChildren = node;
	}

	@Override
	public void setRightChildren(SyntaxTreeNode node) {
		tree.rightChildren = node;
	}
}