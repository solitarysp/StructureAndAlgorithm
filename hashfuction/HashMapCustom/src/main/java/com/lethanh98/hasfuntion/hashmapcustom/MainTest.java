package com.lethanh98.hasfuntion.hashmapcustom;

import com.lethanh98.hasfuntion.hashmapcustom.custom.MapHashArrayCore;

import java.util.Objects;

public class MainTest {
    public static void main(String[] args) {
        MapHashArrayCore<TestObj, String> mapHashArrayCore = new MapHashArrayCore<>(5000000);
        for (int i = 0; i < 5000000; i++) {
            mapHashArrayCore.put(new TestObj(i), "" + i);
        }
        for (int i = 500000-5000; i < 500000; i++) {
            long startTime = System.nanoTime();
            mapHashArrayCore.get(new TestObj(i));
            System.out.println("Total nano Get: " + (System.nanoTime() - startTime));
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
