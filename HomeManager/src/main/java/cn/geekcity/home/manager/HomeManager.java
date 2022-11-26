package cn.geekcity.home.manager;

import java.util.function.Consumer;

public interface HomeManager {

    <T> void setValue(String key, T value, Consumer<T> onSuccess, Consumer<String> onError);
}
