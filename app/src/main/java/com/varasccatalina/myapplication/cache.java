package com.varasccatalina.myapplication;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class cache extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Configuración de la caché de Glide
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.init(this, new GlideBuilder().setDefaultRequestOptions(requestOptions));
    }
}
