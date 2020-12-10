package com.lethanh98.hasfuntion.hashmapcustom;

import com.lethanh98.hasfuntion.hashmapcustom.custom.MapCustom;
import com.lethanh98.hasfuntion.hashmapcustom.custom.MapHashArrayCore;
import com.lethanh98.hasfuntion.hashmapcustom.custom.MapHashLinkedList;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainTest {
    public static void main(String[] args) {
        MapCustom<TestObj, String> mapHashArrayCore = new MapHashLinkedList<>(300000);
        for (int i = 0; i < 50000; i++) {
            mapHashArrayCore.put(new TestObj(i), "" + i);
        }
        for (int i = 50000-5000; i < 50000; i++) {
            long startTime = System.nanoTime();
            String s=  mapHashArrayCore.get(new TestObj(i));
            System.out.println("Get data in : "+i+" : Total nano Get: " + (System.nanoTime() - startTime)+" Data: "+s);
        }

    }

    static class TestObj {
        int number;

        public TestObj(int number) {
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestObj testObj = (TestObj) o;
            return number == testObj.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }
    }
}
