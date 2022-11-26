package cn.geekcity.plugin.app

import androidx.compose.runtime.compositionLocalOf

val LocalDeviceUILoader = compositionLocalOf<ClassLoader> { error("DeviceUILoader: not provide") }