package com.example.xposedtest;

import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.*;

public class HookToast implements IXposedHookLoadPackage{
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable
    {
        if (loadPackageParam.packageName.equals("com.example.myapplication")) {
            XposedBridge.log("we are now in myapplication");
            Class clazz = loadPackageParam.classLoader.loadClass("com.example.myapplication.MainActivity");
            XposedHelpers.findAndHookMethod(clazz, "getString", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("debug@27");
                    param.setResult("你已被劫持");
                }
            });
        }
    }
}
