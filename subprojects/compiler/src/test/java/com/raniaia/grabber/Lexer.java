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
 * it is not related to the odwadwariginal author and need to be personally responsible.
 */

/*
 * Creates on 2020/4/16.
 */

import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author tiansheng
 */
public class Lexer {

	static String srcdir = "/home/adminroot/IdeaProjects/grabber/grabber-example/test/";

	// ===========================================================
	//
	// 词法解析器测试
	//
	// ===========================================================

	@Test
	public void lexerShowToken() {
		SourcesReader reader = new SourcesReader(srcdir);
		reader.init();
		List<GrabberSource> scs = reader.toSourceCodeList();
		for (GrabberSource sc : scs) {
			LexicalAnalyzer lexer = new LexicalAnalyzer();
			lexer.setSourceCode(sc);
			lexer.initReader();
			FinalToken tokens = lexer.getSyntaxTokens();
			System.out.println("###############################################################");
			System.out.println(tokens.name);
			for (SyntaxToken token : tokens.tokens) {
				System.out.println("<" + token.code + ", " + token.value + ">");
			}
			System.out.println("###############################################################");
			System.out.println();
		}
	}

	/**
	 * 获取项目根目录
	 */
	@Test
	public void userDirPropertyForLinux() {
		/*
		 * 测试linux下获取user.dir目录。
		 * 这特么是个坑，如果是windows下的话获取到的目录应该是当前
		 * 项目的根路径，但是如果是在linux的话获取到的user.dir就是当前项目
		 * 模块的根路径。
		 *
		 * 所以这是值得注意的一个地方.
		 */
		System.out.println(System.getProperty("user.dir"));
	}

}