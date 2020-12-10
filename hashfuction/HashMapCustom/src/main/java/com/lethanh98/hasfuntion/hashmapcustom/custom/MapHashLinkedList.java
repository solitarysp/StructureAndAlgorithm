package com.lethanh98.hasfuntion.hashmapcustom.custom;

import com.lethanh98.hasfuntion.hashmapcustom.model.HashObj;

import java.util.LinkedList;

public class MapHashLinkedList<K, V> implements MapCustom<K, V> {
    private int maxSize;
    private Object[] obj;

    public MapHashLinkedList(int maxSize) {
        this.maxSize = maxSize;
        init();
    }

    private void init() {
        obj = new Object[maxSize];
    }

    @Override
    public V get(K key) {
        int hash = getIndex(key);
        if (obj[hash] == null) {
            return null;
        }
        LinkedList<HashObj<K, V>> objLinkedList = (LinkedList<HashObj<K, V>>) obj[hash];
        if (objLinkedList.size() == 1) {
            return objLinkedList.getFirst().getValue();
        }
        for (HashObj<K, V> hashObjFor : objLinkedList) {
            if (hashObjFor.getKey().equals(key)) {
                return hashObjFor.getValue();
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        int hash = getIndex(key);

        HashObj<K, V> hashObj = new HashObj<>();
        hashObj.setHash(key.hashCode());
        hashObj.setValue(value);
        hashObj.setKey(key);

        if (obj[hash] == null) {
            LinkedList<HashObj<K, V>> objLinkedList = new LinkedList<>();
            objLinkedList.add(hashObj);
            obj[hash] = objLinkedList;
            return;
        }
        LinkedList<HashObj<K, V>> objLinkedList = (LinkedList<HashObj<K, V>>) obj[hash];
        for (HashObj<K, V> hashObjFor : objLinkedList) {
            if (hashObjFor.getKey().equals(key)) {
                hashObjFor.setValue(value);
                return;
            }
        }
        objLinkedList.add(hashObj);
        return;
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
}
