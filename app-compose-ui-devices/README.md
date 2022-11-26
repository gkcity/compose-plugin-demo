# 加载设备设备界面

注意:

```
设备界面必须是@Composable界面，纯kotlin代码，使用的图片来自网络（需要缓存到本地文件夹中）。
也就是说，不需要任何本地资源（图片，字符串等），所以用aar包编译就行，apk编译太大耗时间。
```

## 一、更新UI映射文件

文件位于：

```
app-compose-ui-devices/src/main/assets/ui/devices.json
```

此文件修改后，需要上传到服务器存储。


## 二、更新UI实现

### 1. 编译成aar

编译后，文件位于:

```
app-compose-ui-devices/build/outputs/aar
```

### 2. 将aar转成dex格式

* 先解压aar

```
unzip app-compose-ui-devices-release.aar
```

* 使用dx工具转换，dx位于android/sdk/build-tools/30.0.3/dx (高版本找不到dx工具了)
* 注意sdk版本问题，必须和project中的min-sdk-version匹配。

```
dx --dex --min-sdk-version=24 --output=translators.dex classes.jar 
```

### 3. 上传translators.dex到服务器存储
