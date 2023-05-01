package com.simplicity;

public class UpgradeState<T,U,V>{
    private final T first;
    private final U second;
    private final V third;

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
