package de.leoliebig.playground.patterns.mvp;

import android.os.Bundle;

import java.lang.ref.WeakReference;

public abstract class BasePresenter {

    public abstract void present();

    public abstract void onViewAttach(Bundle state);

    public abstract void onViewDetach(Bundle state);

    public static boolean isViewAttached(WeakReference viewReference) {

        return (viewReference != null && viewReference.get() != null);

    }

}