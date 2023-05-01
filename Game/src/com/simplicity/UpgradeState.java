package com.simplicity;

public class UpgradeState<T,U,V>{
    private T first;
    private U second;
    private V third;

    public UpgradeState(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public V getThird() {
        return third;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public void setThird(V third) {
        this.third = third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpgradeState<?, ?, ?> upgradeState = (UpgradeState<?, ?, ?>) o;
        return first.equals(upgradeState.first) && second.equals(upgradeState.second) && third.equals(upgradeState.third);
    }

    @Override
    public int hashCode() {
        return 31 * first.hashCode() + second.hashCode() + third.hashCode();
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
