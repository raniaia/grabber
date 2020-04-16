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
 * Creates on 2020/4/11.
 */

import com.raniaia.grabber.error.syntax.GrabberSyntaxError;
import org.raniaia.available.list.Lists;
import org.raniaia.available.string.StringUtils;

import java.util.List;

/**
 * 词法解析器 (lexer)
 *
 * @author tiansheng
 * @see    #lexer(char)
 * @see    #getSyntaxTokens()
 * @see    #getCharReader()
 * @see	   #tokenRecord(int, String, int, int, LexerSym)
 */
public class LexicalAnalyzer {

	private GrabberSource code;

	static final int IDEN = Constants.IDEN;

	enum LexerSym {

		/**
		 * 终结符
		 */
		EOF,

		/**
		 * 最初状态
		 */
		INITIAL,

		/**
		 * 词法解析器当前正读取到字符串
		 */
		READ_STRING,

		/**
		 * 词法解析器当前正在读数字
		 */
		READ_NUMBER,

		/**
		 * 词法解析器当前读取到小数
		 */
		READ_DECIMAL,

		/**
		 * 词法解析器读取到char声明
		 */
		READ_CHAR,

		/**
		 * 词法解析器解析到一个定义是函数的句子。
		 */
		FEAT,

		/**
		 * 当解析到是一个定义关键字
		 */
		DEFINE,

		/**
		 * 解析异常，语法声明等不正确情况
		 */
		ERROR,

		/**
		 * 当前读到int声明, 后面读取的只能是参数。
		 * 要么就是表达式或者整数，否则抛异常。
		 */
		STMT_INT,

		/**
		 * 当前读到long声明, 后面读取的只能是参数。
		 * 要么就是表达式或者整数，否则抛异常。
		 */
		STMT_LONG,

		/**
		 * 当前读到char声明, 后面读取到字符只能是char或者
		 * 是表达式（因为char可以做运算），否则抛异常。
		 */
		STMT_CHAR,

		/**
		 * 当前读到double声明, 后面读取的只能是参数。
		 * 要么就是表达式或者小数，否则抛异常。
		 */
		STMT_DOUBLE,

		/**
		 * 当前读到flaot声明, 后面读取的只能是参数。
		 * 要么就是表达式或者小数，否则抛异常。
		 */
		STMT_FLOAT,

		/**
		 * 当前读到bool声明, 后面读取的只能是true或者是false
		 * ，否则抛异常。
		 */
		STMT_BOOL,

		/**
		 * 当读到set声明的时候
		 */
		STMT_SET,

		/**
		 * 声明一个类
		 */
		STMT_CLASS,

		/**
		 * 声明一个String对象
		 */
		STMT_STR,

		/**
		 * 对一个int类型的变量进行赋值
		 */
		A_INT,

		/**
		 * 对一个long类型的变量进行赋值
		 */
		A_LONG,

		/**
		 * 对一个char类型的变量进行赋值
		 */
		A_CHAR,

		/**
		 * 对一个double类型的变量进行赋值
		 */
		A_DOUBLE,

		/**
		 * 对一个float类型的变量进行赋值
		 */
		A_FLOAT,

		/**
		 * 对一个bool类型的变量进行赋值
		 */
		A_BOOL,

		/**
		 * 对一个String类型的变量进行赋值
		 */
		A_STR,

		/**
		 * 当读取到大于
		 */
		GT,

		/**
		 * 当读取到小于号
		 */
		LT,

		/**
		 * 当读取到include
		 */
		INCLUDE,

		/**
		 * 单行注释
		 */
		SINGLE_LINE_COMMENT,

		/**
		 * 多行注释
		 */
		MULTI_LINE_COMMENT,

		;
	}

	/**
	 * 当前状态
	 */
	LexerSym status = LexerSym.INITIAL;

	/**
	 * 上一个状态
	 */
	LexerSym previous = LexerSym.INITIAL;

	/**
	 * 正在读取多行注释结束
	 */
	boolean E_MLC = false;

	/**
	 * 当前是否扫描到转义符
	 */
	boolean CONVERSION_CHAR = false;

	/**
	 * 当前读取到第几行了。
	 *
	 * 这个lineNumber不需要做到实时更新，因为这个是拿来做注释
	 * 判断的。当扫描到单行注释时忽略整行，所以我们需要记下当前行号并在每次读取下一个字符
	 * 的时候判断行号是否有变化。
	 *
	 * 如果存在变化就代表当前行结束了，那么就进行下一行的判断了。状态会回到initial。
	 */
	int lineNumber = 0;

	/**
	 * 用于在扫描源码的时候保存源码的对象
	 */
	StringBuilder builder = new StringBuilder();

	List<SyntaxToken> tokens = Lists.newLinkedList();

	CharReader reader;

	public void setSourceCode(GrabberSource code) {
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

	/** 获取{@link #builder}中的value **/
	String value(char ch) {
		builder.append(ch);
		return builder.toString();
	}

	/** 更新状态 **/
	void updateStatus(LexerSym x) {
		previous = status;
		status = x;
	}

	/** 获取源程序单个字符的读取器 **/
	CharReader getCharReader() {
		return new CharReader(code);
	}

	/** 判断是不是一个字符 **/
	boolean letter(char ch) {
		return  ('a' <= ch && 'z' >= ch) ||
				('A' <= ch && 'Z' >= ch) ||
				ch == '_'  || ch == '#'  ||
				ch == '$';
	}

	/** 判断是不是一个结束符 **/
	boolean space(char ch) {
		return  ch ==  ';' || ch == ' ' ||
				ch == '\n' || ch == '(' ||
				ch ==  ')' || ch == '{' ||
				ch ==  '}' || ch == ',' ||
				ch ==  '.' || ch == '<' ||
				ch ==  '>' ||
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

	void tokenRecord(char ch) {
		tokenRecord(String.valueOf(ch));
	}

	/**
	 * 手动添加token
	 *
	 * @param code        		token识别码
	 * @param value            	token值
	 * @param classify        	token分类
	 * @param lineNumber    	token所在行
	 * @param nextStatus    	下一个状态
	 */
	void tokenRecord(int code, String value, int classify, int lineNumber, LexerSym nextStatus) {
		tokens.add(new SyntaxToken(code, value, classify, lineNumber));
		if (nextStatus != null) {
			updateStatus(nextStatus);
		}
	}

	void tokenRecord(int code, String value, int classify, int lineNumber) {
		tokenRecord(code, value, classify, lineNumber,null);
	}

	/**
	 * 将当前{@link #builder}中的内容记录成token。
	 */
	@SuppressWarnings({"ConstantConditions"})
	void tokenRecord(String value) {
		int[] scode = Constants.getCode(value);
		final int classify = scode[1];
		final int species = scode[0];
		SyntaxToken token = new SyntaxToken();
		token.code = scode[0];
		token.classify = classify;
		token.value = value;
		token.lineNumber = reader.lineNumber;
		this.tokens.add(token);

		switch (classify) {

			/* 数据类型 */
			case Constants.TYPE_OF_DATA: {
				switch (species) {
					case Constants.TYPE_CHAR:
						if("char".equals(value)) {
							updateStatus(LexerSym.STMT_CHAR);
						} else {
							updateStatus(LexerSym.INITIAL);
						}
						return;
					case Constants.TYPE_INT:
						updateStatus(LexerSym.STMT_INT);
						return;
					case Constants.TYPE_LONG:
						updateStatus(LexerSym.STMT_LONG);
						return;
					case Constants.TYPE_DOUBLE:
						updateStatus(LexerSym.STMT_DOUBLE);
						return;
					case Constants.TYPE_FLOAT:
						updateStatus(LexerSym.STMT_FLOAT);
						return;
					case Constants.TYPE_BOOL:
						updateStatus(LexerSym.STMT_BOOL);
						return;
				}
			}

			/* 关键字 */
			case Constants.KEEP: {
				switch (species) {
					case Constants.KEEP_SET: {
						updateStatus(LexerSym.STMT_SET);
						return;
					}
					case Constants.KEEP_CLASS: {
						updateStatus(LexerSym.STMT_CLASS);
						return;
					}
					case Constants.KEEP_DEF: {
						updateStatus(LexerSym.FEAT);
						return;
					}
					case Constants.KEEP_INCLUDE: {
						updateStatus(LexerSym.INCLUDE);
						return;
					}
					case Constants.KEEP_DEFINE: {
						updateStatus(LexerSym.DEFINE);
						return;
					}
				}
			}

			/* 操作符 */
			case Constants.OP: {
				switch (species) {
					case Constants.OPT_ASSIGN: {
						if (previous == LexerSym.STMT_INT) {
							updateStatus(LexerSym.A_INT);
						}
						if (previous == LexerSym.STMT_DOUBLE) {
							updateStatus(LexerSym.A_DOUBLE);
						}
						return;
					}
					case Constants.OPT_GT:{
						updateStatus(LexerSym.GT);
						return;
					}
					case Constants.OPT_LT:{
						updateStatus(LexerSym.LT);
						return;
					}
				}
			}

			case Constants.LIMIT: {
				switch (species) {
					case Constants.LIMIT_EOF:
					case Constants.LIMIT_LPBT:
					case Constants.LIMIT_RPBT:
					case Constants.LIMIT_STR: {
						updateStatus(LexerSym.INITIAL);
						return;
					}
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
	public FinalToken getSyntaxTokens() {
		while (reader.ready()) {
			char ch = reader.read();
			/*
			 * 当前是正在读取单行注释
			 */
			if (status == LexerSym.SINGLE_LINE_COMMENT) {
				if(reader.lineNumber > this.lineNumber) {
					status = LexerSym.INITIAL;
				}
				continue;
			}

			/*
			 * 当前正在读取多行注释
			 */
			if(status == LexerSym.MULTI_LINE_COMMENT) {
				if (ch == '-') {
					append(ch);
					E_MLC = true;
					continue;
				}
				if(ch == '/' && E_MLC) {
					status = LexerSym.INITIAL;
					builderClear();
					E_MLC = false;
					continue;
				}
				builderClear();
				E_MLC = false;
				continue;
			}

			lexer(ch);
		}
		return new FinalToken(reader.name,this.tokens);
	}

	void error(String message) {
		throw new GrabberSyntaxError(message + " 错误符号：" + value() + ", 当前行号：" + reader.lineNumber);
	}

	/**
	 * 具体解析的代码在这里
	 *
	 * @see #getSyntaxTokens
	 */
	private void lexer(char ch) {

		// 如果当前字符是转义符
		if(ch == '\\') {
			CONVERSION_CHAR = true;
			append(ch);
			return;
		}

		/*
		 * 首先判断当前状态是否是正在读取字符串。
		 *
		 * 因为如果是正在读取字符串的话，字符串中可能会出现各种各式各样的符号，
		 * 这些符号可能在编译器的符号表中是不允许出现的。但是字符串可以出现，所以我们
		 * 需要先判断当前是不是正在读取字符串。不是的话再判断其他的。
		 */
		if(status == LexerSym.READ_STRING) {
			// 当又扫描到双引号的时候
			if(ch == '\"') {
				// 如果当前是转义符
				if(CONVERSION_CHAR){
					append(ch);
					CONVERSION_CHAR = false;
					return;
				}
				append(ch);
				tokenRecord();
				return;
			}

			append(ch);
			return;
		}

		/*
		 * 判断当前是不是读取到了单行注释
		 */
		if (ch == '/') {
			if(value().equals("/")) {
				status = LexerSym.SINGLE_LINE_COMMENT;
				// 记录下当前行号
				this.lineNumber = reader.lineNumber;
				// 清空当前builder中的内容
				builderClear();
				return;
			}
			append(ch);
			return;
		}

		/*
		 * 判断是不是读取到了多行注释。
		 */
		if(ch == '-') {
			if("/".equals(value())) {
				append(ch);
				return;
			}
			if("/-".equals(value())) {
				status = LexerSym.MULTI_LINE_COMMENT;
				builderClear();
				return;
			}
		}

		// 判断是不是空格等符号
		if (space(ch)) {

			/*
			 * 判断当前状态
			 */
			switch (status) {

				case INITIAL: {
					break;
				}

				/*
				 * 如果状态是一个声明的时候，那么该字符就表示是定义一个变量。
				 */
				case STMT_INT:
				case STMT_SET:
				case STMT_FLOAT:
				case STMT_BOOL:
				case STMT_CHAR:
				case STMT_CLASS:
				case STMT_DOUBLE: {
					if (!Constants.isEmpty(value())) {
						error("非法定义，不能使用关键字作为成员名。");
					}
					tokenRecord(IDEN, builderClear(), IDEN, reader.lineNumber, LexerSym.FEAT);
					return;
				}

				/* 读取到数字 **/
				case READ_NUMBER: {
					if (previous == LexerSym.A_INT) {
						if (number(value())) {
							tokenRecord(Constants.NUMBER, builderClear(), Constants.NUMBER,
									reader.lineNumber, LexerSym.READ_NUMBER);
							break;
						} else {
							error("非法定义的字符。");
						}
					}
					if(number(value())) {
						tokenRecord(Constants.NUMBER, builderClear(), Constants.NUMBER,
								reader.lineNumber, LexerSym.INITIAL);
						return;
					}
				}

				/* 如果读取到一个小数  **/
				case READ_DECIMAL: {
					if (decimal(value())) {
						tokenRecord(Constants.DECIMAL, builderClear(), Constants.DECIMAL,
								reader.lineNumber, LexerSym.READ_DECIMAL);
						break;
					}
					else {
						if(previous == LexerSym.A_DOUBLE || previous == LexerSym.A_FLOAT) {
							error("非法定义的字符。");
						}
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
			boolean _return = false;
			if (!Constants.isEmpty(value())) {
				tokenRecord();
				_return = true;
			}

			if(!StringUtils.isEmpty(value())) {
				tokenRecord(IDEN,builderClear(),IDEN,reader.lineNumber);
			}

			if(ch == ' ') return;


			if (!Constants.isEmpty(ch)) {
				tokenRecord(ch);
				return;
			}

			if(_return) return;

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
			if (status != LexerSym.READ_NUMBER && status != LexerSym.READ_DECIMAL) {
				updateStatus(LexerSym.READ_NUMBER);
			}
			return;
		}

		/*
		 * 判断是不是其他符号
		 */
		switch (ch) {

			case '\'': {

				/**
				 * 如果当前状态是读取到大于号，且上一个状态为include的话，那么就认定
				 * 这个char字符是头文件的声明。
				 */
				if(status == LexerSym.LT && previous == LexerSym.INCLUDE) {
					append(ch);
					updateStatus(LexerSym.READ_CHAR);
					return;
				}

				if(status == LexerSym.READ_CHAR && previous == LexerSym.LT) {
					append(ch);
					tokenRecord(IDEN,builderClear(),IDEN,reader.lineNumber,LexerSym.INITIAL);
					return;
				}

				/*
				 * 当当前状态不等于读取字符的时候且上一个状态也不等于读取字符，则将当前状态转变成
				 * 正在读取字符。
				 */
				if(status != LexerSym.READ_CHAR && previous != LexerSym.READ_CHAR) {
					append(ch);
					updateStatus(LexerSym.READ_CHAR);
					return;
				}

				if(status == LexerSym.READ_CHAR) {
					// 判断当前是不是读取到了转义符
					if(CONVERSION_CHAR) {
						append(ch);
						CONVERSION_CHAR = false;
						return;
					}
					append(ch);
					// 如果不是转义符则判断当前字符是否超出了char的范围
					String value = value();
					/*
					 * 正常情况下char的token只有三位，两个单引号加一个符号。
					 * 不正常的情况就是出现转义符的时候，因为多了一个反斜杠，所以value应该是4位。
					 *
					 * 所以如果没有反斜杠的情况下如果超出3位就报错提示，如果有反斜杠的情况下就是超出4位就报错提示。
					 */
					out:
					if(value.length() > 3) {
						if(value.contains("\\")) {
							if(value.length() <= 4) {
								break out;
							}
						}
						error("char字符中的符号过多。");
					}
					tokenRecord();
					return;
				}
				error("声明一个char字符时，单引号需要通过反斜杠进行转义<\\>。");
			}

			/*
			 * 读取到string
			 */
			case '"': {
				// 如果当前状态不等于String
				if(status != LexerSym.READ_STRING && previous != LexerSym.READ_STRING){
					append(ch);
					updateStatus(LexerSym.READ_STRING);
					return;
				} else {
					error("声明一个字符串时，如果有双引号需要通过反斜杠进行转义<\\>。");
				}
			}

			case '.': {
				/*
				 * 当扫描到 '.' 的时候判断当前是不是正在读取
				 * 数字，如果是正在读取数字的话，那么状态就转换成当前
				 * 正在读小数。
				 */
				if (status == LexerSym.READ_NUMBER) {
					append(ch);
					updateStatus(LexerSym.READ_DECIMAL);
					return;
				}

				/*
				 * 如果当前状态正在读小数点，又扫描到点符号了。
				 * 那么就抛出声明错误。
				 */
				if (status == LexerSym.READ_DECIMAL) {
					error("浮点数声明异常。小数点只能由一个。");
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

	/**
	 * 字符读取器
	 */
	static class CharReader {

		char[] value;
		int position   = -1; // 当前读取到的位置
		int lineNumber =  0; // 当前读取到的行
		String name;

		CharReader(GrabberSource source) {
			this.name = source.name;
			this.value = source.value.toCharArray();
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
			if (v == '\n') {
				lineNumber++;
			}
			return v;
		}
	}

}