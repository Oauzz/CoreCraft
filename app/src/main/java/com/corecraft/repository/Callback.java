package com.corecraft.repository;

public interface Callback<T> {
    void onResult(T result);
}
