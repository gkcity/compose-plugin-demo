package cn.geekcity.plugin.ui.devices.lightbulb

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.geekcity.home.devices.Lightbulb
import cn.geekcity.plugin.ui.common.theme.HomeApplicationTheme
import cn.geekcity.plugin.ui.common.theme.HomeComposeTheme
import cn.geekcity.plugin.ui.common.R


/**
 * 注意，Composable中的callback修改on的状态，会崩溃
 */
@Composable
fun LightbulbController(
    device: Lightbulb,
    modifier: Modifier
) {
    var on by remember { mutableStateOf(device.on()) }
    val iconId by remember(on) {
        mutableStateOf(
            if (on) R.drawable.ouyang_lightbulb_demo_on_24 else R.drawable.ouyang_lightbulb_demo_off_24
        )
    }

    Image(
        painter = painterResource(id = iconId),
        contentDescription = null,
        modifier = modifier
            .padding(32.dp)
            .fillMaxSize()
            .clickable {
                device.on(
                    ! on,
                    {
                        // TODO: 这里会崩溃
                        Log.d("LightbulbController", "onSuccess")
                        on = it
                    },
                    {
                        // TODO: 这里会崩溃
                        Log.d("LightbulbController", "onFailure")
                    }
                )
            }
    )
}

@Preview(showBackground = true)
@Composable
fun DemoControllerPreview() {
    HomeApplicationTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(HomeComposeTheme.colors.background)
                .fillMaxWidth(),
        ) {
            LightbulbController(
                device = Lightbulb(),
                modifier = Modifier
                    .width(400.dp)
                    .height(200.dp),
            )
        }
    }
}