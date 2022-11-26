package cn.geekcity.plugin.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import cn.geekcity.plugin.ui.common.R
import cn.geekcity.plugin.ui.common.component.button.ActionButton
import cn.geekcity.plugin.ui.common.component.button.ActionItem
import cn.geekcity.plugin.ui.common.theme.HomeApplicationTheme
import cn.geekcity.plugin.ui.common.theme.HomeComposeTheme
import cn.geekcity.plugin.ui.common.viewmodel.DeviceViewModel

@Composable
fun MainScreenTopBar(
    actions: List<ActionItem>,
    onClose: () -> Unit,
) {
    val viewModel: DeviceViewModel = viewModel()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(HomeComposeTheme.colors.background)
            .height(60.dp),
    ) {
        BackButton(
            modifier = Modifier
                .height(60.dp)
                .width(80.dp)
                .background(HomeComposeTheme.colors.background)
                .clickable { onClose() },
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = viewModel.name,
            color = HomeComposeTheme.colors.textPrimary,
            fontSize = 24.sp,
        )
        Spacer(modifier = Modifier.width(20.dp))
        DeviceStatus(viewModel.online)
        Spacer(modifier = Modifier.weight(1f))
        ActionButton(
            modifier = Modifier
                .size(60.dp)
                .background(HomeComposeTheme.colors.background),
            actions = actions,
        )
    }
}

@Composable
private fun BackButton(
    modifier: Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back_ios_new_24),
            contentDescription = null,
            tint = HomeComposeTheme.colors.icon
        )
    }
}

@Composable
private fun DeviceStatus(online: Boolean) {
    Text(
        text = if (online) "设备在线" else "设备已离线",
        color = HomeComposeTheme.colors.textPrimary,
        fontSize = 24.sp,
    )
}

@Preview(showBackground = true)
@Composable
fun DeviceScreenTopBarPreview() {
    HomeApplicationTheme {
        MainScreenTopBar(
            actions = listOf(
                ActionItem(R.drawable.baseline_add_24, "设备信息") {},
                ActionItem(R.drawable.baseline_devices_other_24, "删除设备") {},
            ),
        ) {
        }
    }
}