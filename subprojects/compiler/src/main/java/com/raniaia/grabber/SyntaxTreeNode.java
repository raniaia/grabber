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
 * 抽象语法树（abstract syntax tree）
 *
 * 当前类才是整课语法树，而{@link SyntaxTree}是对{@code SyntaxTreeNode}做了
 * 一层基本的封装，以及对树的遍历等操作。
 *
 * 封装了一颗语法树的基本属性。所以真正的抽象语法树是{@code SyntaxTreeNode}
 * 而不是{@link SyntaxTree}。
 *
 * @author tiansheng
 * @see SyntaxTree
 */
public class SyntaxTreeNode extends AbstractTreeNode {

}