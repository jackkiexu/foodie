package com.lami.foodie.utils.classloader.test2;

/**
 * Created by xjk on 5/2/17.
 */
import java.lang.reflect.Method;

public class MonitorHotSwap implements Runnable {

    // Hot就是用于修改，用来测试热加载
    private String className = "com.lami.foodie.utils.classloader.test2.Hot";
    private Class<?> hotClazz = null;
    private HotSwapURLClassLoader hotSwapCL = null;

    //	@Override
    public void run() {
        try {
			while (true) {
                initLoad();
                Object hot = hotClazz.newInstance();
                Method m = hotClazz.getMethod("hot");
                m.invoke(hot); // 打印出相关信息
                Thread.sleep(1000);// 每隔10秒重新加载一次
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载class
     */
    public void initLoad() throws Exception {
        hotSwapCL = HotSwapURLClassLoader.getClassLoader();
        // 如果Hot类被修改了，那么会重新加载，hotClass也会返回新的
        hotClazz = hotSwapCL.loadClass(className);
    }
}