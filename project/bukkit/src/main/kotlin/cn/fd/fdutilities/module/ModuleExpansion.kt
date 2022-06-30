package cn.fd.fdutilities.module

import java.util.*

abstract class ModuleExpansion {

    //模块名称,也就是模块标识符
    abstract val name: String

    //模块作者
    open val author: String = if (Locale.getDefault().language == "zh_CN") "未知" else "UNKNOWN"

    //模块版本
    open val version: String = if (Locale.getDefault().language == "zh_CN") "未知" else "UNKNOWN"

    //是否启用模块
    open val enable: Boolean = true

    open fun printMyself() {
        println("Hello World")
        println("作者: $author \n名称: $name \n版本: $version")
        println("Test successful")
    }

    /**
     * 重载模块
     */
    fun reload() {
        //卸载模块
        this.unregister()
        //如果模块启用,就重新注册该模块
        if (enable) {
            this.register()
        }
    }

    //注册此模块
    fun register(): Boolean {
        return ExpansionManager.register(this)
    }

    //取消注册此模块
    fun unregister(): Boolean {
        return ExpansionManager.unregister(this)
    }


}