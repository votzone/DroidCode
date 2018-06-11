package game.wq.sohook;

import android.app.Application;

/**
 * Created by Administrator on 2018/6/10 0010.
 */

public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SoHookCore.staticHookJNIString();
    }
}
