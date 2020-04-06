# Grabber字节码指令集

> 指令编码只是用于比较好识别所以定义了这么一个编码。0x*代表操作指令、0x*a代表运算、0x*b代表逻辑操作、0x*c代表其他指令

## 操作指令

| 编码 | 指令 | 解释 |
| ------------ | ------------ | ------------ |
|0x0|  #new       |  创建一个对象 |
|0x1|  #sput_null |  将null压入栈|
|0x2|  #sput_i    |  将一个int类型的常量压入栈  |
|0x3|  #sput_l    |  将一个long类型的常量压入栈|
|0x4|  #sput_c    |  将一个char类型的常量压入栈|
|0x5|  #sput_d    |  将一个double类型的常量压入栈|
|0x6|  #sput_f    |  将一个float类型的常量压入栈|
|0x7|  #sput_b    |  将一个boolean类型的常量压入栈|
|0x8|  #spop1     |  从栈顶弹出一个常量|
|0x9|  #spop2     |  从栈顶弹出两个常量|
|0x10| #sdup1     |  复制栈顶的一个常量|
|0x11| #sdup2     |  复制栈顶的两个常量|
|0x10| #hput_null |  将null压入堆|
|0x11| #hput_i    |  将一个int类型的常量压入堆  |
|0x12| #hput_l    |  将一个long类型的常量压入堆|
|0x13| #hput_c    |  将一个char类型的常量压入堆|
|0x14| #hput_d    |  将一个double类型的常量压入堆|
|0x15| #hput_f    |  将一个float类型的常量压入堆|
|0x16| #hput_b    |  将一个boolean类型的常量压入堆|
|0x17| #hpop1     |  从堆顶弹出一个常量|
|0x18| #hpop2     |  从堆顶弹出两个常量|
|0x19| #hdup1     |  复制堆顶的一个常量|
|0x20| #hdup2     |  复制堆顶的两个常量|

-----

## 运算指令

> i开头表示int、l开头表示long、f开头表示float、d开头表示double

| 编码 | 指令 | 解释 |
| ------------ | ------------ | ------------ |
|0x0a|#iadd|执行int类型的加法|
|0x1a|#isub|执行int类型的减法|
|0x2a|#imul|执行int类型的乘法|
|0x3a|#idiv|执行int类型的除法
|0x4a|#irem|计算int类型取余|
|0x5a|#ishl|执行int类型向左位移操作|
|0x6a|#ishr|执行int类型向右位移操作|
|0x7a|#ladd|执行long类型的加法|
|0x8a|#lsub|执行long类型的减法|
|0x9a|#lmul|执行long类型的乘法|
|0x10a|#ldiv|执行long类型的除法|
|0x11a|#lrem|计算long类型取余|
|0x12a|#lshl|执行long类型向左位移操作|
|0x13a|#lshr|执行long类型向右位移操作|
|0x14a|#fadd|执行float类型的加法|
|0x15a|#fsub|执行float类型的减法|
|0x16a|#fmul|执行float类型的乘法|
|0x17a|#fdiv|执行float类型的除法
|0x18a|#frem|计算float类型取余|
|0x19a|#fshl|执行float类型向左位移操作|
|0x20a|#fshr|执行float类型向右位移操作|
|0x21a|#dadd|执行double类型的加法|
|0x22a|#dsub|执行double类型的减法|
|0x23a|#dmul|执行double类型的乘法|
|0x24a|#ddiv|执行double类型的除法
|0x25a|#drem|计算double类型取余|
|0x26a|#dshl|执行double类型向左位移操作|
|0x27a|#dshr|执行double类型向右位移操作|

----

## 逻辑操作

| 编码 | 指令 | 解释 |
| ------------ | ------------ | ------------ |
|0x0b|#ifeq|两个常量进行比较，如果等于则跳转到true|
|0x1b|#ifne|两个常量进行比较，如果不等于则跳转到true|
|0x2b|#ifgt|两个常量进行比较，如果大于则跳转到true|
|0x3b|#ifge|两个常量进行比较，如果大于等于则跳转到true|
|0x4b|#iflt|两个常量进行比较，如果小于则跳转到true|
|0x5b|#ifle|两个常量进行比较，如果小于等于则跳转到true|
|0x6b|#insof|两个对象进行比较，如果类型相同则跳转到true|
|0x7b|#arrof|判断对象是否为数组，如果是则跳转到true|
|0x8b|#goto|跳转到指定位置|
|0x9b|#switch|生成一张索引表|

-----

## 其他指令
| 编码 | 指令 | 解释 |
| ------------ | ------------ | ------------ |
|0x0c|#try|异常捕获块|
|0x1c|#cache|异常处理块|
|0x2c|#finally|finally块|
|0x3c|#throw|抛出一个异常|
|0x4c|#invoke|执行一个方法|
|0x5c|#return|方法返回，如果没有返回值则返回null|
|0x6c|#mov|移动一个对象或者变量到另一个地址中|
|0x7c|#op|操作符|
|0x8c|#end|结束符|
