package com.china.permissiondemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wangjinya on 2016/12/28 0028.
 */

public class BaseActivity extends AppCompatActivity {

//    private PermissionListener mListener;
//
//    @SuppressLint("NewApi")
//    public void requestAppPermission(String[] permissions, PermissionListener listener) {
//        mListener = listener;
//        List<String> permissionsList = new ArrayList<>();
//        for (String permission : permissions) {
//            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
//                permissionsList.add(permission);
//            } else {
//                if (permission != null)
//                    mListener.grant(permission);
//            }
//        }
//        requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), 1);
//    }

    @SuppressLint("NewApi")
    public void requestAppPermission(String[] permissions) {
        List<String> permissionsList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
            } else {
                Toast.makeText(this, permission + "权限已通过", Toast.LENGTH_SHORT).show();
            }
        }
        if (!permissionsList.isEmpty())
            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), 2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
//                List<String> permissionsList = new ArrayList<>();
//                List<String> refusedPermissionsList = new ArrayList<>();
//                if (grantResults.length > 0) {
//                    for (int i = 0; i < grantResults.length; i++) {
//                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                            permissionsList.add(permissions[i]);
//                        } else {
//                            refusedPermissionsList.add(permissions[i]);
//                        }
//                    }
//                }
//                mListener.refused(refusedPermissionsList);
//                if (grantResults.length == permissionsList.size()) {
//                    mListener.allGrant();
//                }
                break;
            case 2:
                List<String> permissionsLists = new ArrayList<>();
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            permissionsLists.add(permissions[i]);
                        } else {
                            Toast.makeText(this, permissions[i] + "权限被拒绝", Toast.LENGTH_SHORT).show();
                        }
                        // 拒绝授权
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                            // 勾选了不再提示
                            if (! ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                                Toast.makeText(this, permissions[i]+"勾选了不再询问", Toast.LENGTH_SHORT).show();
                                /**
                                 * 跳转到设置权限页面
                                 */
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivityForResult(intent, 1);
                            }
                        }
                    }
                }

                if (grantResults.length == permissionsLists.size()) {
                    Toast.makeText(this, "权限全部通过！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
