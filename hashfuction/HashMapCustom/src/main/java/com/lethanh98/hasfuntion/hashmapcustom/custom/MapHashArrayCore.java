package com.lethanh98.hasfuntion.hashmapcustom.custom;


import com.lethanh98.hasfuntion.hashmapcustom.model.HashObj;
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
        HashObj<K, V> kvHashObj = (HashObj<K, V>) obj[hash];

        if (kvHashObj.getKey().equals(key)) {
            return kvHashObj.getValue();
        }

        while (true) {
            if (kvHashObj.getNext() == null) {
                return null;
            }
            kvHashObj = kvHashObj.getNext();
            if (kvHashObj.getKey().equals(key)) {
                return kvHashObj.getValue();
            }
        }
    }

    private int getIndex(K key) {
        int hash = key.hashCode();
        if (hash < obj.length) {
            return hash;
        }
        do {
            hash = hash % 5;
        } while (hash > obj.length);
        return hash;
    }

    public void put(K key, V value) {
        int hash = getIndex(key);
        HashObj<K, V> objHash = new HashObj<>();
        objHash.setHash(hash);
        objHash.setKey(key);
        objHash.setValue(value);
        if (obj[hash] == null) {
            obj[hash] = objHash;
            return;
        }
        HashObj<K, V> kvHashObj = (HashObj<K, V>) obj[hash];
        if (kvHashObj.getKey().equals(key)) {
            kvHashObj.setValue(value);
            return;
        }
        while (true) {
            if (kvHashObj.getNext() == null) {
                kvHashObj.setNext(objHash);
                break;
            }
            kvHashObj = kvHashObj.getNext();

            if (kvHashObj.getKey().equals(key)) {
                kvHashObj.setValue(value);
                return;
            }
        }
        kvHashObj.setNext(objHash);
    }
}
