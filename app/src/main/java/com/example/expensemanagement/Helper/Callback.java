package com.example.expensemanagement.Helper;

public interface Callback<T> {

    void invoke(T obj);
}
