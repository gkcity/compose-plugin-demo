package cn.geekcity.plugin.ui.common.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import cn.geekcity.plugin.ui.common.R
import cn.geekcity.plugin.ui.common.theme.HomeApplicationTheme
import cn.geekcity.plugin.ui.common.theme.HomeComposeTheme

@Composable
fun ActionButton(
    modifier: Modifier,
    actions: List<ActionItem>
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { expanded = true }
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_more_vert_24),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center),
                tint = HomeComposeTheme.colors.icon
            )
        }
        ActionMenu(
            actions = actions,
            expanded = expanded
        ) {
            expanded = false
        }
    }
}

@Composable
private fun ActionMenu(
    actions: List<ActionItem>,
    expanded: Boolean,
    onDismiss: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDismiss() },
            modifier = Modifier
                .background(HomeComposeTheme.colors.actionDialogBackground),
        ) {
            actions.forEachIndexed { index, item ->
                ActionMenuItem(
                    item.iconId,
                    item.title
                ) {
                    onDismiss()
                    item.onClick()
                }
                if (index < actions.lastIndex) {
                    TabRowDefaults.Divider(
                        color = HomeComposeTheme.colors.textSecondary,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ActionMenuItem(
    @DrawableRes iconId: Int,
    title: String,
    onClick: () -> Unit,
) {
    DropdownMenuItem(onClick = onClick) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(80.dp),
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = title,
                modifier = Modifier
                    .height(24.dp)
                    .padding(start = 16.dp)
                    .weight(2f),
                tint = HomeComposeTheme.colors.icon
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 24.sp,
                color = HomeComposeTheme.colors.textPrimary,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(6f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActionButtonPreview() {
    HomeApplicationTheme(theme = HomeComposeTheme.Theme.Dark) {
        ActionButton(
            actions = listOf(
                ActionItem(R.drawable.baseline_add_24, "添加设备") {},
                ActionItem(R.drawable.baseline_meeting_room_24, "房间管理") {},
                ActionItem(R.drawable.baseline_devices_other_24, "设备管理") {}
            ),
            modifier = Modifier
                .size(60.dp)
                .background(HomeComposeTheme.colors.more)
        )
    }
}