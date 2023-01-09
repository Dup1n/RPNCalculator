package org.dupin;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class StackRPN {
    private Deque<Double> stack;
    private double offsideValue;

    public StackRPN() {
        stack = new ArrayDeque<>();
        stack.addAll(Arrays.asList(0.0, 0.0, 0.0));
        offsideValue = 0.0;
    }

    public void add(double value) {
        stack.push(value);
        offsideValue = stack.removeLast();
    }

    public double pop() {
        stack.addLast(offsideValue);
        return stack.pop();
    }

    public double swap(double value) {
        double valueInStack = stack.pop();
        stack.push(value);
        return valueInStack;
    }

    public void ac() {
        stack.clear();
        stack.addAll(Arrays.asList(0.0, 0.0, 0.0));
        offsideValue = 0.0;
    }

    @Override
    public String toString() {
        return "StackRPN{" +
                "stack=" + stack +
                ", offsideValue=" + offsideValue +
                '}';
    }
}