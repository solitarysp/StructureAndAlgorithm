package com.lethanh98.hasfuntion.hashmapcustom.model;

import lombok.Data;

@Data
public class HashObj<K, V> {
    int hash;
    K key;
    V value;
    HashObj<K, V> next;
}
