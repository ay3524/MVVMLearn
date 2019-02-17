package com.ay3524.mvvmlearn;

import androidx.lifecycle.MutableLiveData;

/**
 * This class is a wrapper over MutableLiveData
 * To make custom load, error, success etc.
 * Here 'T' is the type of data we get from the server
 */
public class StateLiveData<T> extends MutableLiveData<StateData<T>> {

    /**
     * Use this to put the Data on a LOADING Status
     */
    public void postLoading() {
        postValue(new StateData<T>().loading());
    }

    /**
     * Use this to put the Data on a ERROR DataStatus
     *
     * @param throwable the error to be handled
     */
    public void postError(Throwable throwable) {
        postValue(new StateData<T>().error(throwable));
    }

    /**
     * Use this to put the Data on a SUCCESS DataStatus
     *
     * @param data to get the data
     */
    public void postSuccess(T data) {
        postValue(new StateData<T>().success(data));
    }
}
