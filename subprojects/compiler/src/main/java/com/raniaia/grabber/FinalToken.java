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
 * Creates on 2020/4/15.
 */

import java.util.List;

/**
 * @author tiansheng
 */
public class FinalToken {

	/**
	 * 表示tokens属于哪个文件
	 */
	private String name;

	private List<SyntaxToken> tokens;

	public FinalToken(){}

	public FinalToken(String name, List<SyntaxToken> tokens) {
		this.name = name;
		this.tokens = tokens;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SyntaxToken> getTokens() {
		return tokens;
	}

	public void setTokens(List<SyntaxToken> tokens) {
		this.tokens = tokens;
	}
}