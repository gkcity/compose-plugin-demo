package cn.geekcity.plugin.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cn.geekcity.home.devices.Lightbulb
import cn.geekcity.plugin.ui.common.R
import cn.geekcity.plugin.ui.common.component.button.ActionItem
import cn.geekcity.plugin.ui.common.theme.HomeComposeTheme

@Composable
fun MainScreen(
    device: Lightbulb,
    onClose: () -> Unit,
) {
    val actions = mutableListOf(
        ActionItem(R.drawable.outline_info_24, "设备信息") { },
        ActionItem(R.drawable.outline_delete_outline_24, "删除设备") { }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(HomeComposeTheme.colors.background)
            .fillMaxWidth(),
    ) {
        MainScreenTopBar(actions, onClose)
        MainScreenController(device, Modifier.weight(1f))
    }
}


