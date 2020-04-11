package com.raniaia.grabber.lexer;

import com.raniaia.grabber.object.structure.GrabberSourceCode;
import com.raniaia.grabber.object.syntax.SyntaxToken;
import com.sun.xml.internal.messaging.saaj.util.CharReader;
import org.raniaia.available.list.Lists;
import org.raniaia.available.string.StringUtils;

import java.util.List;

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
 * 词法解析器 (lexer)
 * @author tiansheng
 */
public class Lexer {

	private GrabberSourceCode code;

	/**
	 * 用于在扫描源码的时候保存源码的对象
	 */
	StringBuilder 		builder = new StringBuilder();

	List<SyntaxToken> 	tokens = Lists.newLinkedList();

	/**
	 * 清空{@link #builder}并返回{@link #builder}中的值。
	 *
	 * @return {@link #builder} the value.
	 */
	String builderClear() {
		return StringUtils.clear(builder);
	}

	boolean isEmpty() {
		return StringUtils.isEmpty(builder.toString());
	}

	void append(char ch) {
		builder.append(ch);
	}

	CharReader getCharReader() {
		return new CharReader(code.getValue());
	}

	/**
	 * 将源码拆分成Token
	 *
	 * @return Token集合
	 */
	public List<SyntaxToken> getSyntaxTokens() {

		CharReader reader = getCharReader();
		while(reader.ready()) {
			char ch = reader.read();
			switch (ch) {
				// a-z
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':

				// A-Z
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':

				// 0-9
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':

				// 符号
				case '!':
				case '@':
				case '#':
				case '$':
				case '%':
				case '^':
				case '&':
				case '*':
				case '(':
				case ')':
				case '-':
				case '_':
				case '+':
				case '=':
				case '[':
				case ']':
				case '{':
				case '}':
				case '\\':
				case '|':
				case ';':
				case ':':
				case '"':
				case '\'':
				case ',':
				case '.':
				case '<':
				case '>':
				case '?':
				case '/':
				case '`':
				case '~':
			}
		}

		return null;
	}

	static class CharReader {

		char[] 			value;
		int 			position	   = 		-1;
		int 			lineNumber 	   = 		 0;

		CharReader(String input) {
			this.value = input.toCharArray();
		}

		boolean ready() {
			return position < value.length;
		}

		/**
		 * 获取下一个字符
		 */
		char next() {
			position++;
			if(ready()) {
				return value[position];
			}
			return 0;
		}

		/**
		 * 读取一个字符，如果读取的字符是空格、回车或者换行那么就自动忽略掉。
		 * 如果忽略的字符是换行，那么{@link #lineNumber}就加一。
		 *
		 * @return 未被忽略掉的字符。
		 */
		char read() {
			char v = next();
			if ( v == '\n' ) lineNumber++;
			return v;
		}
	}

}