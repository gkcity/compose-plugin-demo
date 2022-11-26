package cn.geekcity.plugin.ui.common.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class DeviceViewModel(application: Application) : AndroidViewModel(application) {

    var online by mutableStateOf(true)
    var removable by mutableStateOf(false)
    var name by mutableStateOf("床头灯")
}