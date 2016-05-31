package com.roselism.base.util.convert;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.format.Formatter;

import com.roselism.base.content.pm.Application;

import java.io.File;

/**
 * Created by simon on 16-5-23.
 */
public class ApplicationInfo2AppInfo implements Converter<ApplicationInfo, Application> {

    private PackageManager packageManager;
    private Context context;

    public ApplicationInfo2AppInfo(PackageManager packageManager, Context context) {
        this.packageManager = packageManager;
        this.context = context;
    }

    @Override
    public Application convert(ApplicationInfo application) {
        Application appInfo = Application.createApplication();
        appInfo.setPackageName(application.packageName);
        appInfo.setIcon(application.loadIcon(packageManager));
        appInfo.setAppName(application.loadLabel(packageManager).toString());

        File dataDir = new File(application.sourceDir);
        long usedSize = dataDir.length();
        appInfo.setSize(Formatter.formatFileSize(context, usedSize));
        appInfo.setFlag(application.flags);

        return appInfo;
    }
}
