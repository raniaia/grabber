# Grabber字节码指令集

| 指令   | 解释  |
| ------------ | ------------ |
| #NEW |  创建一个对象 |
|#PI_NULL |将null压入栈|
|#PI_I | 将一个int类型的常量压入栈  |
|#PI_L|将一个long类型的常量压入栈|
|#PI_C|将一个char类型的常量压入栈|
|#PI_D|将一个double类型的常量压入栈|
|#PI_F|将一个float类型的常量压入栈|
|#PI_B|将一个boolean类型的常量压入栈|
|#POP1 | 从栈顶弹出一个常量|
|#POP2 | 从栈顶弹出两个常量|
|#DUP1|复制栈顶的一个常量|
|#DUP2|复制栈顶的两个常量|
|#IADD|执行int类型的加法|
|#ISUB|执行int类型的减法|
|#IMUL|执行int类型的乘法|
|#IDIV|执行int类型的除法
|#IREM|计算int类型取余|
|#ISHL|执行int类型向左位移操作|
|#ISHR|执行int类型向右位移操作|
|#LADD|执行long类型的加法|
|#LSUB|执行long类型的减法|
|#LMUL|执行long类型的乘法|
|#LDIV|执行long类型的除法|
|#LREM|计算long类型取余|
|#LSHL|执行long类型向左位移操作|
|#LSHR|执行long类型向右位移操作|
|#FADD|执行float类型的加法|
|#FSUB|执行float类型的减法|
|#FMUL|执行float类型的乘法|
|#FDIV|执行float类型的除法
|#FREM|计算float类型取余|
|#FSHL|执行float类型向左位移操作|
|#FSHR|执行float类型向右位移操作|
|#DADD|执行double类型的加法|
|#DSUB|执行double类型的减法|
|#DMUL|执行double类型的乘法|
|#DDIV|执行double类型的除法
|#DREM|计算double类型取余|
|#DSHL|执行double类型向左位移操作|
|#DSHR|执行double类型向右位移操作|
|#IFEQ|两个常量进行比较，如果等于则跳转到true|
|#IFNE|两个常量进行比较，如果不等于则跳转到true|
|#IFGT|两个常量进行比较，如果大于则跳转到true|
|#IFGE|两个常量进行比较，如果大于等于则跳转到true|
|#IFLT|两个常量进行比较，如果小于则跳转到true|
|#IFLE|两个常量进行比较，如果小于等于则跳转到true|
|#INSOF|两个对象进行比较，如果类型相同则跳转到true|
|#GOTO|跳转到指定位置|
|#TRY|异常捕获块|
|#CACHE|异常处理块|
|#FINALLY|finally块|
|#THROW|抛出一个异常|
|#INVOKE|执行一个方法|
|#RETURN|方法返回，如果没有返回值则返回null|
