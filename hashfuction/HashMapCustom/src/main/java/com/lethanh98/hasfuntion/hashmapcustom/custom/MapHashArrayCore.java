package com.lethanh98.hasfuntion.hashmapcustom.custom;


import com.lethanh98.hasfuntion.hashmapcustom.model.HashObjArrayCore;
import lombok.Data;

@Data
public class MapHashArrayCore<K, V> implements MapCustom<K, V> {
    private int maxSize;
    private Object[] obj;

    public MapHashArrayCore(int maxSize) {
        this.maxSize = maxSize;
        init();
    }

    private void init() {
        obj = new Object[maxSize];
    }

    public V get(K key) {
        int hash = getIndex(key);
        if (obj[hash] == null) {
            return null;
        }
        HashObjArrayCore<K, V> kvHashObjArrayCore = (HashObjArrayCore<K, V>) obj[hash];

        if (kvHashObjArrayCore.getKey().equals(key)) {
            return kvHashObjArrayCore.getValue();
        }

        while (true) {
            if (kvHashObjArrayCore.getNext() == null) {
                return null;
            }
            kvHashObjArrayCore = kvHashObjArrayCore.getNext();
            if (kvHashObjArrayCore.getKey().equals(key)) {
                return kvHashObjArrayCore.getValue();
            }
        }
    }

    private int getIndex(K key) {
        int hash = key.hashCode();
        if (hash < obj.length) {
            return hash;
        }
        int number = 1;
        do {
            hash = (hash % ++number);
        } while (hash >= obj.length);
        return hash;
    }

    public void put(K key, V value) {
        int hash = getIndex(key);
        HashObjArrayCore<K, V> objHash = new HashObjArrayCore<>();
        objHash.setHash(hash);
        objHash.setKey(key);
        objHash.setValue(value);
        if (obj[hash] == null) {
            obj[hash] = objHash;
            return;
        }
        HashObjArrayCore<K, V> kvHashObjArrayCore = (HashObjArrayCore<K, V>) obj[hash];
        if (kvHashObjArrayCore.getKey().equals(key)) {
            kvHashObjArrayCore.setValue(value);
            return;
        }
        while (true) {
            if (kvHashObjArrayCore.getNext() == null) {
                kvHashObjArrayCore.setNext(objHash);
                break;
            }
            kvHashObjArrayCore = kvHashObjArrayCore.getNext();

            if (kvHashObjArrayCore.getKey().equals(key)) {
                kvHashObjArrayCore.setValue(value);
                return;
            }
        }
        kvHashObjArrayCore.setNext(objHash);
    }
}
