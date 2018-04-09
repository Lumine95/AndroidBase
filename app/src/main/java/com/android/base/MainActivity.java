package com.android.base;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.library.utils.CleanCacheUtil;
import com.android.library.utils.PermissionUtils;
import com.android.library.utils.U;

public class MainActivity extends AppCompatActivity {
    private final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE};
    private Button btnCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn_test);
        btnCache = (Button) findViewById(R.id.btn_clean_cache);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });
        getCacheSize();
    }


    private void test() {
        U.showToast("test");
    }

    public void showLoadingDialog(View view) {
        U.showLoadingDialog(this, "正在加载");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    U.dismissLoadingDialog();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void permissionRequest(View view) {
        String tag = (String) view.getTag();
        if (tag.equals("single")) {
            PermissionUtils.checkAndRequestPermission(this, PERMISSION_CAMERA, 1, new PermissionUtils.PermissionRequestSuccessCallBack() {
                @Override
                public void onHasPermission() {
                    U.showToast(PERMISSION_CAMERA + "申请成功");
                }
            });
        } else if (tag.equals("multi")) {
            PermissionUtils.checkAndRequestMorePermissions(this, permissions, 1, new PermissionUtils.PermissionRequestSuccessCallBack() {
                @Override
                public void onHasPermission() {
                    U.showToast("多权限申请成功");
                }
            });
        }
    }

    private void getCacheSize() {
        try {
            String totalCacheSize = CleanCacheUtil.getTotalCacheSize(this);
            btnCache.setText("清除缓存  " + totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleanCache(View view) {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("是否清除缓存？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CleanCacheUtil.clearAllCache(MainActivity.this);
                        getCacheSize();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        dialog.show();
    }
}
