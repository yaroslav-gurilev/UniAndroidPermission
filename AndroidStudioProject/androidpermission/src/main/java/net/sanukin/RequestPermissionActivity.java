package net.sanukin;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.util.Log;

import com.unity3d.player.UnityPlayer;

import static javax.xml.ws.Service.Mode.PAYLOAD;


public class RequestPermissionActivity extends Activity {

	public static final String PERMISSION = "permission";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();

		String permissionStr = intent.getStringExtra(PERMISSION);
		Log.d("DTI", "Requesting permission permissionStr " + permissionStr);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d("DTI", "Requesting permission Build.VERSION.SDK_INT" + Build.VERSION.SDK_INT);
			requestPermissions(new String[]{permissionStr}, 0);
		}
	}

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    UnitySendMessage("OnPermit");
                } else {
                    if (permissions.length > 0) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0]);
                        if (!showRationale) {
                            UnitySendMessage("NotPermitAlways");
                        } else {
                            UnitySendMessage("NotPermit");
                        }
                    }
                }
                break;
            }
        }
    }

    private void UnitySendMessage(String method) {
        UnityPlayer.UnitySendMessage("UniAndroidPermission", method, "");
        finish();
    }
}
