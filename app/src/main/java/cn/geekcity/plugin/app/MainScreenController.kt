package cn.geekcity.plugin.app

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.reflect.getDeclaredComposableMethod
import androidx.compose.ui.Modifier
import cn.geekcity.home.devices.Lightbulb

@Composable
fun MainScreenController(
    device: Lightbulb,
    modifier: Modifier,
) {
    Box {
        // 1. 直接加载Compose界面，可以正常工作
//        TestController(device, modifier)

        // 2. 动态加载在本应用中的Compose界面，可以正常工作！
//        DynamicDeviceControllerOnApp(device, modifier)

        // 3. 动态加载在dex中的Compose界面，可以正常工作!
        DynamicDeviceControllerOnDex(device, modifier)
    }
}

/**
 * 动态加载在本应用中的@Composable，没有问题
 */
@Composable
fun DynamicDeviceControllerOnApp(
    device: Lightbulb,
    modifier: Modifier,
) {
    val className = "cn.geekcity.plugin.app.TestControllerKt"
    val clazz = Class.forName(className)
    val method = clazz.getDeclaredComposableMethod(
        methodName = "TestController",
        Lightbulb::class.java,
        Modifier::class.java,
    )

    method.invoke(
        currentComposer,
        null,
        device,
        modifier)
}

/**
 * 动态加载在插件中的@Composable
 */
@Composable
fun DynamicDeviceControllerOnDex(
    device: Lightbulb,
    modifier: Modifier
) {
    val className = "cn.geekcity.plugin.ui.devices.lightbulb.LightbulbControllerKt"
    val clazz = LocalDeviceUILoader.current.loadClass(className) ?: return
    val method = clazz.getDeclaredComposableMethod(
        methodName = "LightbulbController",
        Lightbulb::class.java,
        Modifier::class.java,
    )

    method.invoke(
        currentComposer,
        null,
        device,
        modifier)
}
