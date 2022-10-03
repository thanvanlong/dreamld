package com.example.dreamtale;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;

public class App extends Application {
    private static App sAppInstance;

    private static synchronized void setAppInstance(App instance) {
        sAppInstance = instance;
    }

    public static App getInstance() {
        return sAppInstance;
    }
}