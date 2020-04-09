//
// 宏定义
//
#include 'base: base_of_grabber'
#define 'file'
    set {
        // 公开
        public {
            path,               // 文件路径
            size,               // 文件大小
            name,               // 全名
            simpleName          // 简单名称
        },
        // 私有且不公开
        private {
            data                // 文件数据
        }
    },

    fun {

       File(),
       File(path),
       read(path),              // 读取文件
       push(content),           // 放入内容
       append(object),          // 追加内容
       move(to),                // 移动文件
       copy(to),                // 复制文件
       delete()
    }
#end