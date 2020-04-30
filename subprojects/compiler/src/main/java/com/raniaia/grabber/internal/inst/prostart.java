package com.raniaia.grabber.internal.inst;

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
 * Creates on 2020/4/30.
 */

import com.raniaia.grabber.internal.inst_base;
import com.raniaia.grabber.internal.instprocess;
import com.raniaia.grabber.internal.instop;

/**
 * 指定程序的入口函数
 *
 * @author tiansheng
 */
public class prostart extends instprocess implements instop {

    inst_base kind = inst_base.prostart;

    @Override
    public void accept(Object... consts) {
        build("prostart");
    }

}