package cn.geekcity.home.devices;


import java.util.function.Consumer;

import cn.geekcity.home.manager.HomeManagerInstance;

public class Lightbulb {

    private boolean value = false;

    public void on(boolean value, Consumer<Boolean> onSuccess, Consumer<String> onError) {
        HomeManagerInstance.get().setValue("on", value, onSuccess, onError);
    }

    public boolean on() {
        return value;
    }
}
