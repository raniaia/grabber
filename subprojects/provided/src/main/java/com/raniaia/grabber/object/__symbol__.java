//package com.raniaia.grabber.object;/*
// * Copyright (C) 2020 the original author or authors.
// * Licensed under the GPL, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     https://b2evolution.net/about/gnu-gpl-license
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// * The source code of this program is only provided for learning and research.
// * If the program source code is used for criminal acts, such as illegal acts,
// * it is not related to the original author and need to be personally responsible.
// */
//
///*
// * Creates on 2020/4/8.
// */
//
//import org.raniaia.available.map.Maps;
//
//import java.lang.reflect.Field;
//import java.util.Map;
//
///**
// * 符号种别码
// * @author tiansheng
// */
//public interface Symbol {
//
//	Map<String,Integer> table = Maps.newHashMap(new SymbolTable().table);
//
//	int
//
//			//
//			// 标识符
//			//
//			IDEN      =       1,
//
//	//
//	// 常数
//	//
//	CONST     =       2,
//
//	//
//	// 保留字符
//	//
//	KEEP      =       3,
//
//	//
//	// 运算符
//	//
//	OP        =       4,
//
//	//
//	// 界限符
//	//
//	LIMIT     =       5;
//
//	/**
//	 * 符号Hash表
//	 */
//	class SymbolTable{
//		Map<String,Integer> table = Maps.newHashMap();
//
//		{
//			/*
//			 * 保留字符
//			 */
//			table.put(  "public",                 KEEP                              );
//			table.put(  "private",                KEEP                              );
//			table.put(  "set",                    KEEP                              );
//			table.put(  "const",                  KEEP                              );
//			table.put(  "if",                     KEEP                              );
//			table.put(  "else",                   KEEP                              );
//			table.put(  "switch",                 KEEP                              );
//			table.put(  "case",                   KEEP                              );
//			table.put(  "default",                KEEP                              );
//			table.put(  "define",                 KEEP                              );
//			table.put(  "def",                    KEEP                              );
//			table.put(  "return",                 KEEP                              );
//			table.put(  "class",                  KEEP                              );
//			table.put(  "@interface",             KEEP                              );
//			table.put(  "#include",               KEEP                              );
//			table.put(  "null",                   KEEP                              );
//			table.put(  "true",                   KEEP                              );
//			table.put(  "false",                  KEEP                              );
//			table.put(  "instanceof",             KEEP                              );
//			table.put(  "goto",                   KEEP                              );
//			table.put(  "break",                  KEEP                              );
//			table.put(  "for",                    KEEP                              );
//			table.put(  "while",                  KEEP                              );
//			table.put(  "this",                   KEEP                              );
//			table.put(  "func",                   KEEP                              );
//			table.put(  "+",                      OP                                );
//			table.put(  "-",                      OP                                );
//			table.put(  "*",                      OP                                );
//			table.put(  "/",                      OP                                );
//			table.put(  "%",                      OP                                );
//			table.put(  "^",                      OP                                );
//			table.put(  "=",                      OP                                );
//			table.put(  "<",                      OP                                );
//			table.put(  ">",                      OP                                );
//			table.put(  "++",                     OP                                );
//			table.put(  "--",                     OP                                );
//			table.put(  "==",                     OP                                );
//			table.put(  ">=",                     OP                                );
//			table.put(  "<=",                     OP                                );
//			table.put(  ">>",                     OP                                );
//			table.put(  "<<",                     OP                                );
//			table.put(  "===",                    OP                                );
//			table.put(  ";",                      LIMIT                             );
//			table.put(  "(",                      LIMIT                             );
//			table.put(  ")",                      LIMIT                             );
//			table.put(  "[",                      LIMIT                             );
//			table.put(  "]",                      LIMIT                             );
//			table.put(  "{",                      LIMIT                             );
//			table.put(  "}",                      LIMIT                             );
//			table.put(  ".",                      LIMIT                             );
//			table.put(  "\\",                     LIMIT                             );
//		}
//
//	}
//
//
//	/**
//	 * 每个符号对应的识别码
//	 */
//	int
//
//			//
//			// 操作符
//			//
//			ELSE                =             0xf1,           // value:       else
//			RETURN              =             0xf2,           // value:       return
//			FOR                 =             0xf3,           // value:       for
//			WHILE               =             0xf4,           // value:       while
//			EXPORT              =             0xf5,           // value:       export
//			DEF                 =             0xf6,           // value:       def
//			SET                 =             0xf7,           // value:       set
//			STATIC              =             0xf8,           // value:       static
//			NULL                =             0xf9,           // value:       null
//			NONE                =            0xf10,           // value:       none
//			TRUE                =            0xf11,           // value:       true
//			FALSE               =            0xf12,           // value:       false
//			INSTANCEOF          =            0xf13,           // value:       instance
//			ARRAYOF             =            0xf14,           // value:       arrayof
//			GOTO                =            0xf15,           // value:       goto
//			BREAK               =            0xf16,           // value:       break
//			CONTINUE            =            0xf17,           // value:       continue
//			PRE                 =            0xf18,           // value:       pre
//			THIS                =            0xf19,           // value:       this
//			SUPER               =            0xf20,           // value:       super
//			SWITCH              =            0xf21,           // value:       switch
//			CASE                =            0xf22,           // value:       case
//			PACKAGE             =            0xf23,           // value:       package
//			CLASS               =            0xf24,           // value:       class
//			INTERFACE           =            0xf25,           // value:       @interface       注解
//			INCLUDE             =            0xf26,
//
//	//
//	// 运算符
//	//
//	ADD                 =            0x26e,           // value:       +
//			SUB                 =            0x27e,           // value:       -
//			MUL                 =            0x28e,           // value:       *
//			DIV                 =            0x29e,           // value:       /
//			EQ                  =            0x30e,           // value:       ==
//			NE                  =            0x31e,           // value:       !=
//			GT                  =            0x32e,           // value:       >
//			LT                  =            0x33e,           // value:       <
//			GE                  =            0x34e,           // value:       >=
//			LE                  =            0x35e,           // value:       <=
//			DISL                =            0x36e,           // value:       <<
//			DISR                =            0x37e,           // value:       >>
//			POWER               =            0x38e,           // value:       ^               次方计算
//			SURPLUS             =            0x39e,           // value:       %               取余
//
//	//
//	// 比较特殊的符号，字符串以及数字等
//	//
//	CHAR                =             0x1fe,          // value:        单个字符
//			STRING              =             0x2fe,          // value:        字符串
//			S_INTEGER             =           0x3fe,          // value:        有符号整数
//			U_INTEGER           =             0x4fe,          // value:        无符号整数
//
//	//
//	// 其他符号
//	//
//	DOLLAR               =           0x39a,            // value:       $
//			LPBT                 =           0x40a,            // value:       (
//			RPBT                 =           0x40a,            // value:       )
//			LSBT                 =           0x41a,            // value:       [
//			RSBT                 =           0x42a,            // value:       ]
//			LCBT                 =           0x43a,            // value:       {
//			RCBT                 =           0x44a,            // value:       }
//
//	//
//	// 类的标识符，头信息
//	//
//	HEAD_INFO           =            0xF01,            // value: 0xF01
//
//	// 结束符
//	__END__             =            0xF02;            // value: 0xF02
//
//
//	static void main(String[] args) throws Throwable {
//		long s = System.currentTimeMillis();
//		Operator op = new Operator(){};
//		for(Field f : Operator.class.getDeclaredFields()){
//			System.out.println(f.getName() + " = " + f.get(op));
//		}
//		long e = System.currentTimeMillis();
//	}
//
//
//}
