package com.ay3524.mvvmlearn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * This class is simply like a pojo
 * With this class we can set data, error status
 * Here 'T' is the type of data we get from the server
 */
public class StateData<T> {

    /**
     * Simply an enum which exposes the state of the network request
     */
    @NonNull
    private DataStatus status;

    /**
     * Simply the type of data received from network
     */
    @Nullable
    private T data;

    /**
     * Simply the type of error while making request
     */
    @Nullable
    private Throwable error;

    /**
     * Constructor initialization.
     * For DataStatus Enum,T data and Throwable error
     * It will set the enum as CREATED
     */
    public StateData() {
        this.status = DataStatus.CREATED;
        this.data = null;
        this.error = null;
    }

    /**
     * Function for showing the status as loading
     * It will set the enum as LOADING
     * Careful that data and error are null
     */
    public StateData<T> loading() {
        this.status = DataStatus.LOADING;
        this.data = null;
        this.error = null;
        return this;
    }

    /**
     * Function for showing the status as success
     * It will set the enum as SUCCESS
     * You will get the data if it is in this state
     * Careful error is null
     */
    public StateData<T> success(@NonNull T data) {
        this.status = DataStatus.SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    /**
     * Function for showing the status as error
     * It will set the enum as ERROR
     * You won't get the data if it is in this state
     * Careful data is null
     */
    public StateData<T> error(@NonNull Throwable error) {
        this.status = DataStatus.ERROR;
        this.data = null;
        this.error = error;
        return this;
    }

    /**
     * Function will return the enum status
     */
    @NonNull
    public DataStatus getStatus() {
        return status;
    }

    /**
     * Function will return the data
     * Don't use when it is null!
     */
    @Nullable
    public T getData() {
        return data;
    }

    /**
     * Function will return the error
     * Don't use when it is null!
     */
    @Nullable
    public Throwable getError() {
        return error;
    }

    /**
     * This is an enum to expose the type network status
     * CREATED - constructor initialized
     * SUCCESS - API request successful
     * ERROR   - API request came back with error
     * LOADING - API request is still taking time
     */
    public enum DataStatus {
        CREATED,
        SUCCESS,
        ERROR,
        LOADING
    }
}
