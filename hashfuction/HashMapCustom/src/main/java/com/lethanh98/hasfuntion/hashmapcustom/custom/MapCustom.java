package com.lethanh98.hasfuntion.hashmapcustom.custom;

public interface MapCustom<K, V> {
    V get(K key);
    void put(K key, V value);
}
