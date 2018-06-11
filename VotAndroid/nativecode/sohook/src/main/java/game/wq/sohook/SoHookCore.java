package game.wq.sohook;

/**
 * Created by Administrator on 2018/6/10 0010.
 */

public class SoHookCore {

    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("so-hook");
    }

    static SoHookCore soHookCore;

    public static SoHookCore Instance() {
        if(soHookCore == null){
            soHookCore = new SoHookCore();
        }
        return soHookCore;
    }

    public static void staticHookJNIString(){
        SoHookCore.Instance().hookJNIString();
    }

    public native String hookJNIString();

}
