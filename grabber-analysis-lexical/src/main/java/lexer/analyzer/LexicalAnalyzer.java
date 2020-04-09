package lexer.analyzer;

import java.util.List;

import object.structure.SourceCode;
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

    private List<SourceCode> 	codes;

	/**
	 * 用于在扫描源码的时候保存源码的对象
	 */
	private StringBuilder 		builder 		= 	new StringBuilder();

    /**
     * 将源码内容读取到对象中
     * @param codes 处理过后的源码对象集合
     */
    public void setSourceCode(List<SourceCode> codes) {
        this.codes = codes;
    }

	/**
	 * 判断是否为一个字符
	 * @param ch char
	 */
	boolean isLetter(char ch) {
        return  ('a' <= ch && ch <= 'z') ||
                ('A' <= ch && ch <= 'Z') ||
                (ch == '_');
    }

    void append(char ch){
		builder.append(ch);
	}

	String clear() {
		return StringUtils.clear(builder);
	}

}