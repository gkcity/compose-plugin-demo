package cn.geekcity.plugin.ui.common.assets;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Objects;


public final class Assets {

    private static final String TAG = "Assets";

    private Assets() {
    }

    /**
     * 从Assets中拷贝文件到app的数据目录中
     * release 版本: 如果没有缓存的资源文件，则从 asset 中复制(首次才会有这种情况)
     * debug 版本经常修改，每次都从 asset 复制出后使用
     * @param context Context
     * @param src src from Assets
     */
    public static void copyToDataIfNecessary(Context context, String src, String subFolder) {
        copyToIfNecessary(context, src, fileOf(context, subFolder, src));
    }

    public static void copyToIfNecessary(Context context, String src, String subFolder, String dst) {
        copyToIfNecessary(context, src, fileOf(context, subFolder, dst));
    }

    /**
     * 从应用的Data目录中取文件
     * @param subFolder 子目录
     * @param name 文件名
     */
    public static File fileOf(Context context, String subFolder, String name) {
        File folder = new File(context.getFilesDir(), subFolder);
        return new File(folder, name);
    }

    public static void copyToIfNecessary(Context context, String src, File dst) {
        if (!dst.exists() || debuggableApplication(context)) {
            Assets.copyTo(context, src, dst);
        }
    }

    private static void copyTo(Context context, String assetFileName, File destination) {
        Log.i(TAG, "copy " + assetFileName + " to " + destination.getPath());

        try (InputStream in = context.getAssets().open(assetFileName)) {
            if (!Objects.requireNonNull(destination.getParentFile()).exists()) {
                destination.getParentFile().mkdirs();
            }

            try (OutputStream out = Files.newOutputStream(destination.toPath())){
                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            destination.delete();
        }
    }

    private static boolean debuggableApplication(Context context) {
        return (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) > 0;
    }
}
