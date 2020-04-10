package lexer.analyzer;

import java.util.List;

import object.structure.SourceCode;
import object.syntax.SyntaxToken;
import org.raniaia.available.list.Lists;
import org.raniaia.available.string.StringUtils;

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
 * Creates on 2020/4/8.
 */

/**
 * 词法解析器 (lexer)
 * @author tiansheng
 */
public class LexicalAnalyzer {

	private SourceCode code;

	/**
	 * 用于在扫描源码的时候保存源码的对象
	 */
	private StringBuilder builder = new StringBuilder();

	public LexicalAnalyzer(SourceCode code) {
		this.code = code;
	}

	/**
	 * 将源码内容读取到对象中
	 * @param codes 处理过后的源码对象集合
	 */
	public static LexicalAnalyzer getInstance(SourceCode codes) {
		return new LexicalAnalyzer(codes);
	}

	/**
	 * 获取当前源码中的所有token。
	 * 需要注意的是数组必须是按照读取顺序进行存放token。
	 *
	 * @return SyntaxToken数组。
	 */
	public SyntaxToken[] getSyntaxTokens() {
		List<SyntaxToken> tokens = Lists.newLinkedList();
		CharReader reader = getCharReader();
		while(reader.ready())
		{
			char ch = reader.read();
			// 忽略字符
			if(ignore(ch)) continue;

			// 普通的A-z a-z _等字符
			if(isLetter(ch)) {
				append(ch);
				continue;
			}

			// 界限符
			if(isLimit(ch)) {

			}
		}
		return null;
	}

	/**
	 * 清空{@link #builder}并返回{@link #builder}中的值。
	 *
	 * @return {@link #builder} the value.
	 */
	String clear() {
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
	 * 判断是否为一个a-z\A-z以及下划线的字符
	 */
	boolean isLetter(char ch) {
		return  ('a' <= ch && ch <= 'z') ||
				('A' <= ch && ch <= 'Z') ||
				(ch == '_');
	}

	/**
	 * 判断字符是否为一个界限符。
	 * 比如：空格，换行、Tab等符号
	 */
	boolean isLimit(char ch) {
		return '('  == ch || ')'  == ch ||
			   '['  == ch || ']'  == ch ||
			   '{'  == ch || '}'  == ch ||
			   '<'  == ch || '>'  == ch ||
			   '+'  == ch || '-'  == ch ||
			   '*'  == ch || '/'  == ch ||
			   '^'  == ch || '='  == ch ||
			   '&'  == ch || '|'  == ch ||
			   '%'  == ch || '$'  == ch ||
			   '"'  == ch ||  39  == ch || // 39是单引号: '
			   ';'  == ch || '@'  == ch ||
			   '!'  == ch || '#'  == ch  ;
	}

	boolean ignore(char ch) {
		return ' ' == ch || '\n' == ch || '\t' == ch;
	}

	/**
	 * 字符读取器
	 */
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