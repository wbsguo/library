package com.example.newreliclibrary;

import android.app.Application;

/**
 * Created by wangbs on 16/5/10.
 */
import com.newrelic.agent.android.FeatureFlag;
import com.newrelic.agent.android.NewRelic;
import com.newrelic.agent.android.logging.AgentLog;
public class NewRelicApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        newRelicInit();
    }
    private void newRelicInit(){
        NewRelic.withApplicationToken("AA7a621d7e9cbcda1c8db6beb683c49f989135283e").start(this);
        NewRelic.withApplicationToken("AA7a621d7e9cbcda1c8db6beb683c49f989135283e").withLogLevel(AgentLog.ERROR).start(this);
        NewRelic.enableFeature(FeatureFlag.CrashReporting);
    }
}
