package com.gianlu.aria2app.ProfilesManager.Testers;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gianlu.aria2app.ProfilesManager.MultiProfile;

public abstract class BaseTester<T> implements Runnable {
    protected final Context context;
    protected final MultiProfile.UserProfile profile;
    private final IPublish<T> listener;

    BaseTester(Context context, MultiProfile.UserProfile profile, @Nullable IPublish<T> listener) {
        this.context = context;
        this.profile = profile;
        this.listener = listener;
    }

    @Override
    public void run() {
        start(null);
    }

    protected final void publishMessage(String message, @ColorRes int color) {
        if (listener != null) listener.publishGeneralMessage(message, color);
    }

    public final T start(@Nullable Object prevResult) {
        if (listener != null) listener.startedNewTest(this);
        T a = call(prevResult);
        if (listener != null) listener.endedTest(this, a);
        return a;
    }

    /**
     * @return true if the test was successful, false otherwise
     */
    @Nullable
    protected abstract T call(@Nullable Object prevResult);

    @NonNull
    public abstract String describe();

    public interface IPublish<T> {
        void startedNewTest(BaseTester tester);

        void publishGeneralMessage(String message, @ColorRes int color);

        void endedTest(BaseTester tester, @Nullable T result);
    }
}
