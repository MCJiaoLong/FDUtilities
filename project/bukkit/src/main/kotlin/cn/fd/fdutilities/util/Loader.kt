package cn.fd.fdutilities.util

import cn.fd.fdutilities.config.*
import cn.fd.fdutilities.module.*
import cn.fd.fdutilities.util.FileListener.unlisten
import taboolib.common.platform.function.console
import taboolib.module.lang.sendLang

object Loader {


    fun reloadModules() {
        arrayOf(
            ServerTeleportModule,
            PlaceholderAPIExtension
        ).forEach {
            it.reload()
            //检测是否启用，如果不启用，就不需要监听文件变化
            if (it.isEnabled) {
                listen(it)
            } else {
                unlisten(it)
            }
        }
    }


    fun reloadConfigs() {
        SettingsYaml.conf.reload()
        ConfigYaml.conf.reload()
    }

    fun reloadAll() {
        reloadConfigs()
        reloadModules()
    }

    /**
     * 监听模块
     */
    fun listen(module: Module) {
        /**
         * 当文件变化时，重新加载模块
         */
        val file = module.file
        if (!FileListener.isListening(module.file)) {
            //监听文件变化
            FileListener.listener(module.file) {
                val start = System.currentTimeMillis()
                //重新加载模块
                try {
                    module.reload()
                } catch (t: Throwable) {
                    //报告错误日志
                    console().sendLang("Menu-Loader-Failed", file.name, t.stackTraceToString())
                    return@listener
                }

                //输出日志
                console().sendLang("Module-Loader-Reloaded", file.name, System.currentTimeMillis() - start)
            }
        }
    }

    /**
     * 取消监听模块
     */
    fun unlisten(module: Module) {
        if (FileListener.isListening(module.file))
            unlisten(module.file)
    }


}