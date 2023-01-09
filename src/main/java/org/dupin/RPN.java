package org.dupin;

public class RPN {
    private StackRPN storage;

    public RPN() {
        storage = new StackRPN();
    }

    public void add(double value) {
        storage.add(value);
    }

    public double sum(double value) {
        return storage.pop() + value;
    }

    public double sub(double value) {
        return storage.pop() - value;
    }

    public double mul(double value) {
        return storage.pop() * value;
    }

    public double div(double value) {
        return storage.pop() / value;
    }

    public double inverse(double value) {
        return 1 / value;
    }

    public double sqrt(double value) {
        return Math.sqrt(value);
    }

    public double power(double value) {
        return Math.pow(value, storage.pop());
    }

    public void ac() {
        storage.ac();
    }

    public double swap(double value) {
        return storage.swap(value);
    }

    public String toString() {
        return storage.toString();
    }
}
