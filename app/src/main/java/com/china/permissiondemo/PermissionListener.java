package com.china.permissiondemo;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public interface PermissionListener {

    void grant(String permission);

    void refused(List<String> refusedPermissions);

    void allGrant();
}
