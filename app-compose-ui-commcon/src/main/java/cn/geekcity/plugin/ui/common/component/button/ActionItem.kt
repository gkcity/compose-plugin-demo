package cn.geekcity.plugin.ui.common.component.button

import androidx.annotation.DrawableRes

class ActionItem(
    @DrawableRes var iconId: Int,
    var title: String,
    var onClick: () -> Unit,
)