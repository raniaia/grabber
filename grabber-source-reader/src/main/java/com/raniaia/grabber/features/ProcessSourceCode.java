package com.raniaia.grabber.features;

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
 * Creates on 2020/4/5.
 */

import com.raniaia.grabber.features.save.SourceCode;

/**
 * 处理源代码，擦除注释等操作。
 * 可能会有一些疑问，就是擦除注释后我们怎么进行异常的精准定位呢？
 * 其实在擦除注释的过程中会对源码进行一个标记。源码如下：
 * <code>
 *     // 这是main方法
 *     def main(args)
 *     {
 *         // 打印hello，world
 *         print("hello,world!")
 *     }
 * </code>
 *
 * 上述源码一共有6行,在擦除的过程中会进行标记，最终结果如下
 * <code>
 *     1:def main(args)
 *     2:{
 *     4:print("hello,world!")
 *     5:}
 * </code>
 *
 * @author tiansheng
 */
public class ProcessSourceCode {

    //
    // 获取擦除器
    //
    public static Erase eraser = new Erase();

    public static class Erase
    {
        // 擦除注释
        public SourceCode comment(SourceCode sourceCode)
        {
            return SourceCode.NONE;
        }
    }

}