package com.raniaia.grabber.compiler;

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
 * Creates on 2020/4/10.
 */

import com.raniaia.grabber.lexer.Lexer;
import com.raniaia.grabber.object.structure.GrabberSourceCode;
import com.raniaia.grabber.object.syntax.SyntaxToken;
import com.raniaia.grabber.tools.SourceReader;
import org.raniaia.available.list.Lists;

import java.util.List;

/**
 * @author tiansheng
 */
public class ReaderTest {

	static String srcdir = System.getProperty("user.dir") + "/grabber-example/test/";

	public static void main(String[] args) {
		SourceReader reader = new SourceReader(srcdir);
		reader.init();
		List<GrabberSourceCode> scs = reader.toSourceCodeList();
		for (GrabberSourceCode sc : scs) {
			Lexer lexer = new Lexer();
			lexer.setSourceCode(sc);
			lexer.initReader();
			List<SyntaxToken> tokens = lexer.getSyntaxTokens();
			for (SyntaxToken token : tokens) {
				System.out.println("<" + token.getCode() + ", " + token.getValue() + ">");
			}
		}
	}

}