package net.sanukin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.unity3d.player.UnityPlayer;

public class PermissionManager {

    public static void requestPermission(String permissionStr) {

        if(!hasPermission(permissionStr)) {

			Intent intent = new Intent(UnityPlayer.currentActivity, RequestPermissionActivity.class);
			intent.putExtra(RequestPermissionActivity.PERMISSION, permissionStr);
			UnityPlayer.currentActivity.startActivity(intent);
        }
    }

    public static boolean hasPermission(String permissionStr) {
        if(Build.VERSION.SDK_INT < 23) {
            return true;
        }
        Context context = UnityPlayer.currentActivity.getApplicationContext();
        return context.checkCallingOrSelfPermission(permissionStr) == PackageManager.PERMISSION_GRANTED;
    }
}
