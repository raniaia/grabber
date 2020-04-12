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

import com.raniaia.grabber.error.syntax.GrabberStatementError;
import com.raniaia.grabber.error.syntax.GrabberSyntaxError;
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
import java.util.Scanner;

import com.raniaia.grabber.object.GrabberSymbol;
import com.raniaia.grabber.object.structure.GrabberSourceCode;
import com.raniaia.grabber.object.syntax.SyntaxToken;
import org.raniaia.available.list.Lists;
import org.raniaia.available.string.StringUtils;

/**
 * 词法解析器 (lexer)
 *
 * @author tiansheng
 */
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
	 * 词法解析器当前读取到小数
	 */
	static final int DECIMAL = 3;

	/**
	 * 词法解析器解析到一个定义变量或者是函数的
	 * 句子。
	 */
	static final int DEF = 4;

	/**
	 * 解析异常，语法声明等不正确情况
	 */
	static final int ERROR = 5;

	/**
	 * 当前读到int声明, 后面读取的只能是参数。
	 * 要么就是表达式或者整数，否则抛异常。
	 */
	static final int STMT_INT = 0xf5;

	/**
	 * 当前读到long声明, 后面读取的只能是参数。
	 * 要么就是表达式或者整数，否则抛异常。
	 */
	static final int STMT_LONG = 0xf6;

	/**
	 * 当前读到char声明, 后面读取到字符只能是char或者
	 * 是表达式（因为char可以做运算），否则抛异常。
	 */
	static final int STMT_CHAR = 0xf7;

	/**
	 * 当前读到double声明, 后面读取的只能是参数。
	 * 要么就是表达式或者小数，否则抛异常。
	 */
	static final int STMT_DOUBLE = 0xf8;

	/**
	 * 当前读到flaot声明, 后面读取的只能是参数。
	 * 要么就是表达式或者小数，否则抛异常。
	 */
	static final int STMT_FLOAT = 0xf9;

	/**
	 * 当前读到bool声明, 后面读取的只能是true或者是false
	 * ，否则抛异常。
	 */
	static final int STMT_BOOL = 0xf10;

	/**
	 * 当读到set声明的时候
	 */
	static final int STMT_SET = 0xf11;

	/**
	 * 对一个int类型的变量进行赋值
	 */
	static final int A_INT = 0xe11;

	/**
	 * 对一个long类型的变量进行赋值
	 */
	static final int A_LONG = 0xe12;

	/**
	 * 对一个char类型的变量进行赋值
	 */
	static final int A_CHAR = 0xe13;

	/**
	 * 对一个double类型的变量进行赋值
	 */
	static final int A_DOUBLE = 0xe14;

	/**
	 * 对一个float类型的变量进行赋值
	 */
	static final int A_FLOAT = 0xe15;

	/**
	 * 对一个bool类型的变量进行赋值
	 */
	static final int A_BOOL = 0xe16;

	/**
	 * 当前状态
	 */
	int status = INITIAL;

	/**
	 * 上一个状态
	 */
	int previous = INITIAL;


	/**
	 * 用于在扫描源码的时候保存源码的对象
	 */
	StringBuilder builder = new StringBuilder();

	List<SyntaxToken> tokens = Lists.newLinkedList();

	CharReader reader;

	public void setSourceCode(GrabberSourceCode code) {
		this.code = code;
	}

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

	/** 判断是不是一个结束符 **/
	boolean space(char ch) {
		return ch == ';' || ch == ' ' ||
				ch == '\n' || ch == '}' ||
				!reader.ready();
	}

	/**
	 * 判断是不是整数
	 */
	boolean number(char ch) {
		return (ch >= '0' && ch <= '9');
	}

	/**
	 * 判断是不是整数
	 */
	boolean number(String input) {
		for (char ch : input.toCharArray()) {
			if (!number(ch)) {
				return false;
			}
		}
		return !"".equals(input);
	}

	/**
	 * 判断是不是小数
	 */
	boolean decimal(String input) {
		int point = 0;
		for (char ch : input.toCharArray()) {
			if (number(ch)) continue;
			if ('.' == ch) {
				if (point > 1) {
					return false;
				}
				point++;
				continue;
			}
			return false;
		}
		return !"".equals(input);
	}

	/** 判断是不是结束符 **/
	boolean eof(char ch) {
		return ch == ';';
	}

	void tokenRecord() {
		tokenRecord(builderClear());
	}

	/**
	 * 手动添加token
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
						updateStatus(STMT_CHAR);
						return;
					case GrabberSymbol.TYPE_INT:
						updateStatus(STMT_INT);
						return;
					case GrabberSymbol.TYPE_LONG:
						updateStatus(STMT_LONG);
						return;
					case GrabberSymbol.TYPE_DOUBLE:
						updateStatus(STMT_DOUBLE);
						return;
					case GrabberSymbol.TYPE_FLOAT:
						updateStatus(STMT_FLOAT);
						return;
					case GrabberSymbol.TYPE_BOOL:
						updateStatus(STMT_BOOL);
						return;
				}
			}

			/* 操作符 */
			case GrabberSymbol.OP: {
				switch (species) {
					case GrabberSymbol.OP_ASSIGN: {
						if (previous == STMT_INT) {
							updateStatus(A_INT);
						}
						if (previous == STMT_DOUBLE) {
							updateStatus(A_DOUBLE);
						}
						return;
					}
				}
			}

			case GrabberSymbol.LIMIT: {
				switch (species) {
					case GrabberSymbol.LIMIT_EOF:
						updateStatus(INITIAL);
				}
			}

		}

	}

	/**
	 * 将源码拆分成Token
	 *
	 * @see #lexer
	 * @return Token集合
	 */
	public List<SyntaxToken> getSyntaxTokens() {
		while (reader.ready()) {
			char ch = reader.read();
			lexer(ch);
		}
		return this.tokens;
	}

	/**
	 * 具体解析的代码在这里
	 *
	 * @see #getSyntaxTokens
	 */
	private void lexer(char ch) {
		// 判断是不是空格等符号
		if (space(ch)) {

			/*
			 * 判断当前状态
			 */
			switch (status) {

				case INITIAL:
					break;

				/* 如果状态是一个声明的时候，那么该字符就表示是定义一个变量。 **/
				case STMT_INT:
				case STMT_DOUBLE: {
					if (!GrabberSymbol.isEmpty(value())) {
						throw new GrabberSyntaxError("非法定义，不能使用关键字作为成员名。" + value());
					}
					tokenRecord(IDEN, builderClear(), IDEN, reader.lineNumber, DEF);
					return;
				}

				/* 读取到数字 **/
				case NUMBER: {
					if (previous == A_INT) {
						if (number(value())) {
							tokenRecord(GrabberSymbol.NUMBER, builderClear(), GrabberSymbol.NUMBER,
									reader.lineNumber, NUMBER);
							break;
						} else {
							throw new GrabberSyntaxError("非法定义的字符：" + value());
						}
					}
				}

				/* 如果读取到一个小数  **/
				case DECIMAL: {
					if (decimal(value())) {
						tokenRecord(GrabberSymbol.DECIMAL, builderClear(), GrabberSymbol.DECIMAL,
								reader.lineNumber, DECIMAL);
						break;
					} else {
						throw new GrabberSyntaxError("非法定义的字符：" + value());
					}
				}

			}

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
				return;
			}

		}

		/*
		 * 可能是标识符
		 */
		if (letter(ch)) {
			append(ch);
			return;
		}

		/*
		 * 数字
		 */
		if (number(ch)) {
			append(ch);
			if (status != NUMBER && status != DECIMAL) {
				updateStatus(NUMBER);
			}
			return;
		}

		/*
		 * 判断是不是其他符号
		 */
		switch (ch) {

			case '.': {
				/*
				 * 当扫描到 '.' 的时候判断当前是不是正在读取
				 * 数字，如果是正在读取数字的话，那么状态就转换成当前
				 * 正在读小数。
				 */
				if (status == NUMBER) {
					append(ch);
					updateStatus(DECIMAL);
					return;
				}

				/*
				 * 如果当前状态正在读小数点，又扫描到点符号了。
				 * 那么就抛出声明错误。
				 */
				if (status == DECIMAL) {
					throw new GrabberStatementError("浮点数声明异常。小数点只能由一个。" + value());
				}

			}

			/*
			 * 赋值
			 */
			case '=': {
				tokenRecord(String.valueOf(ch));
				return;
			}

			/*
			 * 结束
			 */
			case ';': {
				tokenRecord(String.valueOf(ch));
			}
		}
	}

	class CharReader {

		char[] value;
		int position = -1; // 当前读取到的位置
		int lineNumber = 0; // 当前读取到的行

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
		System.out.println("请输入语法：");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String value = scanner.nextLine();
			if ("quit()".equals(value)) {
				System.exit(0);
			}
			Lexer lexer = new Lexer();
			GrabberSourceCode sourceCode = new GrabberSourceCode();
			sourceCode.read(value);
			lexer.setSourceCode(sourceCode);
			lexer.initReader();
			for (SyntaxToken token : lexer.getSyntaxTokens()) {
				System.out.println("<" + token.getCode() + ", " + token.getValue() + ">");
			}
		}
	}

}