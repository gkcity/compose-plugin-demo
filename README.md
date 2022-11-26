# 动态加载设备界面

注意:

```
设备界面必须是@Composable界面，纯kotlin代码，使用的图片来自网络（需要缓存到本地文件夹中）。
也就是说，不需要任何本地资源（图片，字符串等），所以用aar包编译就行，apk编译太大耗时间。
```

### 1. 编译成aar


编译
```
./gradlew app-compose-ui-devices:build
```

编译后，文件位于:
```
app-compose-ui-devices/build/outputs/aar
```

### 2. 将aar转成dex格式

* 先解压aar

```
cd app-compose-ui-devices/build/outputs/aar
unzip app-compose-ui-devices-release.aar
```

* 使用dx工具转换，dx位于android/sdk/build-tools/30.0.3/dx (高版本找不到dx工具了)
* 注意sdk版本问题，必须和project中的min-sdk-version匹配。

```
~/Library/Android/sdk/build-tools/30.0.3/dx --dex --min-sdk-version=26 --output=compose_ui_devices.dex classes.jar
```

### 3. 拷贝compose_ui_devices.dex到app/src/main/assets中

```
cp compose_ui_devices.dex ../../../../app/src/main/assets
```

### 4. 编译app，运行