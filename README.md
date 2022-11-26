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

* 注意，使用工具转换，用dx会导致插件中的callback调用崩溃，应该使用d8
* 注意sdk版本问题，必须和project中的min-sdk-version匹配。
* 感谢 https://github.com/linwoain

```
~/Library/Android/sdk/build-tools/30.0.3/d8 classes.jar
```

### 3. classes.dex到app/src/main/assets中

```
cp classes.dex ../../../../app/src/main/assets/compose_ui_devices.dex
```

### 4. 编译app，运行