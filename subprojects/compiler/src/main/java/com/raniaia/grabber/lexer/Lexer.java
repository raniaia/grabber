package com.raniaia.grabber.lexer;


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

import com.raniaia.grabber.object.GrabberSymbol;
import com.raniaia.grabber.object.GrabberSymbol.*;
import com.raniaia.grabber.object.structure.GrabberSourceCode;
import com.raniaia.grabber.object.syntax.SyntaxToken;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.messaging.saaj.util.CharReader;
import jdk.nashorn.internal.runtime.regexp.joni.Syntax;
import org.raniaia.available.list.Lists;
import org.raniaia.available.string.StringUtils;

import java.util.List;

/**
 * 词法解析器 (lexer)
 *
 * @author tiansheng
 */
@SuppressWarnings("ALL")
public class Lexer {

	private GrabberSourceCode code;

	static final int IDEN = GrabberSymbol.IDEN;

	/**
	 * 终结符
	 */
	static final int EOF = -1;

	/**
	 * 最初状态
	 */
	static final int INITIAL = 0;

	/**
	 * 词法解析器当前正读取到字符串
	 */
	static final int STRING = 1;

	/**
	 * 词法解析器当前正在读数字
	 */
	static final int NUMBER = 2;

	/**
	 * 词法解析器解析到一个定义变量或者是函数的
	 * 句子。
	 */
	static final int DEF = 3;

	/**
	 * 解析异常，语法声明等不正确情况
	 */
	static final int ERROR = 4;

	/**
	 * 当前读到int声明, 后面读取的只能是参数。
	 * 要么就是表达式或者整数，否则抛异常。
	 */
	static final int INT = 5;

	static final int LONG = 6;

	static final int CHAR = 7;

	static final int DOUBLE = 8;

	static final int FLOAT = 9;

	static final int BOOL = 10;

	static final int A_INT = 11;
	static final int A_LONG = 12;
	static final int A_CHAR = 13;
	static final int A_DOUBLE = 14;
	static final int A_FLOAT = 15;
	static final int A_BOOL = 16;

	/**
	 * 当前状态
	 */
	static int status = INITIAL;

	/**
	 * 上一个状态
	 */
	static int previous = INITIAL;

	/**
	 * 用于在扫描源码的时候保存源码的对象
	 */
	StringBuilder builder = new StringBuilder();

	List<SyntaxToken> tokens = Lists.newLinkedList();

	public void setSourceCode(GrabberSourceCode code) {
		this.code = code;
	}

	CharReader reader;

	/**
	 * 初始化源码读取器
	 */
	public void initReader() {
		this.reader = getCharReader();
	}

	/**
	 * 清空{@link #builder}并返回{@link #builder}中的值。
	 *
	 * @return {@link #builder} the value.
	 */
	String builderClear() {
		return StringUtils.clear(builder);
	}

	/**
	 * 在{@link #builder}中追加一个char字符，然后清空并返回。
	 */
	String builderClear(char ch) {
		return StringUtils.clear(builder.append(ch));
	}

	/**
	 * 判断当前{@link #builder}是否为空
	 */
	boolean isEmpty() {
		return StringUtils.isEmpty(builder.toString());
	}

	/**
	 * 追加一个字符到{@link #builder}
	 */
	void append(char ch) {
		builder.append(ch);
	}

	/**
	 * 判断当前{@link #builder}中的值是否等于{@param input}输入
	 * 的值。
	 *
	 * @param input 输入
	 * @return 如果等于返回true。
	 */
	boolean eq(String input) {
		return input.equals(builder.toString());
	}

	/** 获取{@link #builder}中的value **/
	String value() {
		return builder.toString();
	}

	void tokenRecord() {
		tokenRecord(builderClear());
	}

	/**
	 * 将当前{@link #builder}中的内容记录成token。
	 */
	@SuppressWarnings({"ConstantConditions"})
	void tokenRecord(String value) {
		int[] scode = GrabberSymbol.getCode(value);
		final int classify = scode[1];
		final int species = scode[0];
		SyntaxToken token = new SyntaxToken();
		token.setCode(scode[0]);
		token.setClassify(classify);
		;
		token.setValue(value);
		token.setLineNumber(reader.lineNumber);
		this.tokens.add(token);

		switch (classify) {

			/* 数据类型 */
			case GrabberSymbol.TYPE_OF_DATA: {
				switch (species) {
					case GrabberSymbol.TYPE_CHAR:
						updateStatus(CHAR);
						return;
					case GrabberSymbol.TYPE_INT:
						updateStatus(INT);
						return;
					case GrabberSymbol.TYPE_LONG:
						updateStatus(LONG);
						return;
					case GrabberSymbol.TYPE_DOUBLE:
						updateStatus(DOUBLE);
						return;
					case GrabberSymbol.TYPE_FLOAT:
						updateStatus(FLOAT);
						return;
					case GrabberSymbol.TYPE_BOOL:
						updateStatus(BOOL);
						return;
				}
			}

			/* 操作符 */
			case GrabberSymbol.OP: {
				switch (species) {
					case GrabberSymbol.OP_ASSIGN:
						updateStatus(A_INT);
						return;
				}
			}

		}

	}

	/**
	 * 手动识别token
	 *
	 * @param code        token识别码
	 * @param value            token值
	 * @param classify        token分类
	 * @param lineNumber    token所在行
	 * @param nextStatus    下一个状态
	 */
	void tokenRecord(int code, String value, int classify, int lineNumber, int nextStatus) {
		tokens.add(new SyntaxToken(code, value, classify, lineNumber));
		updateStatus(nextStatus);
	}

	/** 更新状态 **/
	void updateStatus(int x) {
		previous = status;
		status = x;
	}

	/** 获取源程序单个字符的读取器 **/
	CharReader getCharReader() {
		return new CharReader(code.getValue());
	}

	/** 判断是不是一个字符 **/
	boolean letter(char ch) {
		return ('a' <= ch && 'z' >= ch) ||
				('A' <= ch && 'Z' >= ch) ||
				ch == '_' || ch == '#';
	}

	/** 结束符 **/
	boolean space(char ch) {
		return ch == ';' || ch == ' ' ||
				ch == '\n' || ch == '}';
	}

	boolean number(char ch) {
		return (ch >= '0' && ch <= '9');
	}

	boolean number(String input) {
		for (char ch : input.toCharArray()) {
			if (!number(ch)) {
				return false;
			}
		}
		return !"".equals(input);
	}

	/**
	 * 将源码拆分成Token
	 *
	 * @return Token集合
	 */
	public List<SyntaxToken> getSyntaxTokens() {
		while (reader.ready()) {
			char ch = reader.read();

			// 判断是不是空格等符号
			if (space(ch)) {
				/*
				 * 将已有的字符和定义的标识符做比较
				 * 如果不等于空的话那么我们这边肯定是扫描到了一个标识符（保留关键字）。
				 *
				 * 因为当前方法是判断是否为字母的，如果是字母则进来。
				 *
				 * 当然了，由于一些特殊的保留关键字。比如说#include、#define等
				 * 也会被识别成字母。
				 *
				 */
				if (!GrabberSymbol.isEmpty(value())) {
					tokenRecord();
					continue;
				}

				/*
				 * 判断当前状态
				 */
				switch (status) {

					/* 如果状态是int时，那么该字符就表示是定义一个变量。 **/
					case INT: {
						tokenRecord(IDEN, builderClear(), IDEN, reader.lineNumber, DEF);
						continue;
					}

					/* 给int变量赋值 **/
					case A_INT: {
						if (number(value())) {
							tokenRecord(GrabberSymbol.NUMBER, builderClear(), GrabberSymbol.NUMBER,
									reader.lineNumber, NUMBER);
						}
					}

				}
			}

			// 可能是标识符
			if (letter(ch)) {
				append(ch);
				continue;
			}

			// 数字
			if (number(ch)) {
				append(ch);
				continue;
			}

			switch (ch) {
				case '=': {
					if (previous == INT) {
						tokenRecord(String.valueOf(ch));
					}
					continue;
				}
				case ';': {
					tokenRecord(String.valueOf(ch));
				}
			}

		}

		return this.tokens;
	}

	static class CharReader {

		char[] value;
		int position = -1;
		int lineNumber = 0;

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
			if (ready()) {
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
			if (v == '\n') lineNumber++;
			return v;
		}
	}

	public static void main(String[] args) {
		Lexer lexer = new Lexer();
		GrabberSourceCode sourceCode = new GrabberSourceCode();
		sourceCode.read("int c = 10;");
		lexer.setSourceCode(sourceCode);
		lexer.initReader();
		for (SyntaxToken token : lexer.getSyntaxTokens()) {
			System.out.println("<" + token.getCode() + ", " + token.getValue() + ">");
		}
	}

}