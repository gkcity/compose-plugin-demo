package cn.geekcity.home.manager;

import cn.geekcity.home.manager.impl.HomeManagerFakeImpl;

public final class HomeManagerInstance {

    private static HomeManager manager = null;
    private static final Object classLock = HomeManagerInstance.class;

    private HomeManagerInstance() {
    }

    public static HomeManager get() {
        synchronized (classLock) {
            if (manager == null) {
                manager = new HomeManagerFakeImpl();
            }

            return manager;
        }
    }
}
