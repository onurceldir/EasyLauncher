package com.example.mylauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        /**we have an listview operator named:
         * userInstalledApps */
        ListView userInstalledApps = (ListView)findViewById(R.id.installed_app_list);

        /**installedApps is an application list for a listview. we take the values from getinstalledapps function.
        * this functions takes only nosystemapps*/
        final List<AppList> installedApps = getInstalledApps();

        /** we need an installedappadapter. basicly, default listview shows only names.
        * with appadapter we customize it.*/
        AppAdapter installedAppAdapter = new AppAdapter(SearchActivity.this, installedApps);

        /**InstalledAppAdapter entities shown.*/
        userInstalledApps.setAdapter(installedAppAdapter);

        /**buttonclick function that opens app from the listview.
        * takes an application path from the installedapp list. */
        userInstalledApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                /**this code takes package path from the installed apps. and then opens the application.*/
                String application_path = installedApps.get(position).getPackage();
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(application_path);
                startActivity(launchIntent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /** takes an appName, icon, packageName.  and adds this values into the applist that have an object the res.
     * it takes parameters from the packageInfo. */
    private List<AppList> getInstalledApps() {
        List<AppList> res = new ArrayList<AppList>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p) == false)) {
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String package_path = p.applicationInfo.packageName.toString();

                res.add(new AppList(appName, icon, package_path));
            }
        }
        return res;
    }

    /**returns the boolean. says is this app systemapp?
     * we dont want to see all system apps. because there is a lot of system apps in android.
     * standart users dont use them so don't want to see them*/
    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }
}
