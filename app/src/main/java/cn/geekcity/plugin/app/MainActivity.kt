package cn.geekcity.plugin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import cn.geekcity.home.devices.Lightbulb
import cn.geekcity.plugin.ui.common.theme.HomeApplicationTheme
import cn.geekcity.plugin.ui.common.theme.HomeComposeTheme
import cn.geekcity.plugin.ui.common.viewmodel.DeviceViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: DeviceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化，拷贝compose_ui_devices.dex到app的data目录中
        PluginLoader.init(this)

        viewModel.online = true
        viewModel.removable = false
        viewModel.name = "床头灯"

        setContent {
            HomeApplicationTheme(theme = HomeComposeTheme.Theme.Dark) {
                CompositionLocalProvider(
                    LocalDeviceUILoader provides PluginLoader.getClassLoader(this),
                ) {
                    MainScreen(
                        device = Lightbulb(),
                        onClose = this::onClose,
                    )
                }
            }
        }
    }

    private fun onClose() {
        finish()
    }
}