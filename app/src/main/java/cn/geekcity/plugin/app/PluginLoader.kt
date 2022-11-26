package cn.geekcity.plugin.app

import android.content.Context
import cn.geekcity.plugin.ui.common.assets.Assets
import dalvik.system.DexClassLoader

object PluginLoader {

    fun getClassLoader(context: Context): ClassLoader {
        val dexFile = Assets.fileOf(context, "ui", "compose_ui_devices.dex")
        return DexClassLoader(dexFile.absolutePath, null, null, javaClass.classLoader)
    }

    fun init(context: Context) {
        Assets.copyToIfNecessary(context, "compose_ui_devices.dex", "ui", "compose_ui_devices.dex")
    }
}
