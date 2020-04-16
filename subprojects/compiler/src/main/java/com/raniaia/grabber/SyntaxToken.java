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
 * Creates on 2020/4/9.
 */

import com.raniaia.grabber.Constants;

/**
 * @author tiansheng
 */
public class SyntaxToken {

    /**
     * 符号对应编码，参考{@link Constants}
     */
    int          code;

    /**
     * 符号对应的值
     */
    String       value;

    /**
     * 符号对应的分类，参考{@link Constants}
     */
    int          classify;

    /**
     * Token对应的代码中的行号
     */
    int          lineNumber;

    public SyntaxToken(){}

    /**
     * 创建一个语法Token对象
     *
     * @param code          token对应编码，参考{@link Constants}
     * @param value         token的值
     * @param classify      token分类，参考{@link Constants}
     * @param lineNumber    token在代码中对应的行号
     */
    public SyntaxToken(int code, String value, int classify, int lineNumber) {
        this.code = code;
        this.value = value;
        this.classify = classify;
        this.lineNumber = lineNumber;
    }

}