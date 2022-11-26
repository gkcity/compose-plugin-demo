package cn.geekcity.plugin.app

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cn.geekcity.home.devices.Lightbulb
import cn.geekcity.plugin.ui.common.R


@Composable
fun TestController(
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
                        Log.d("LightbulbController", "onSuccess")
                        on = it
                    },
                    {
                        Log.d("LightbulbController", "onFailure: $it")
                    }
                )
            }
    )
}