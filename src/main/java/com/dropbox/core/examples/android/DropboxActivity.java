package com.dropbox.core.examples.android;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.dropbox.core.android.Auth;


/**
 * Base class for Activities that require auth tokens
 * Will redirect to auth flow if needed
 */
public abstract class DropboxActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();

        String accessToken = "GfOGCUWNMXAAAAAAAAAAIT9WOF1R8rUxP7aYMUVEpAkPeiBf9XDPQXyoM1CFVZ1m";
        if (accessToken == null) {
            accessToken = Auth.getOAuth2Token();

            if (accessToken != null) {

                initAndLoadData(accessToken);
            }
        } else {
            initAndLoadData(accessToken);
        }

    }

    private void initAndLoadData(String accessToken) {
        DropboxClientFactory.init(accessToken);
        PicassoClient.init(getApplicationContext(), DropboxClientFactory.getClient());
        loadData();
    }

    protected abstract void loadData();

    protected boolean hasToken() {
        SharedPreferences prefs = getSharedPreferences("dropbox-sample", MODE_PRIVATE);
        String accessToken = prefs.getString("access-token", null);
        return accessToken != null;
    }

}
