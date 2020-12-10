package com.lethanh98.hasfuntion.hashmapcustom.model;

import lombok.Data;

@Data
public class HashObjArrayCore<K, V> {
    int hash;
    K key;
    V value;
    HashObjArrayCore<K, V> next;
}
