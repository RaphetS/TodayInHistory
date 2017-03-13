package org.raphets.todayinhistory.base;

import java.lang.ref.WeakReference;

/**
 * Created by RaphetS on 2016/10/16.
 */

public class BasePresenter<M extends IModel, V extends IView> implements IPresenter {

    protected M mModel;
    protected V mView;
    private WeakReference<V> weakReferenceOfView;
    private WeakReference<M> weakReferenceOfModel;

    public BasePresenter(M model, V view) {
        this.mModel = model;
        this.mView = view;
        this.weakReferenceOfView = new WeakReference<V>(view);
        this.weakReferenceOfModel = new WeakReference<M>(model);
    }
    public BasePresenter(V view) {

        this.mView = view;
    }

    public M getmModel() {
        return mModel;
    }

    public V getmView() {
        return mView;
    }


    @Override
    public void detachView() {
        weakReferenceOfModel.clear();
        weakReferenceOfView.clear();

    }
}
