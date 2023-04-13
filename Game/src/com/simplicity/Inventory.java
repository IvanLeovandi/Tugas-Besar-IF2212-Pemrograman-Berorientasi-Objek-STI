package com.simplicity;

import java.util.List;

public class Inventory {
    private List slots;

    private class Slot<E> {
        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public void take() {

    }
}
