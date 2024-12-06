package com.example.demo;

public class DestructionState {
    private boolean isDestroyed;

    public DestructionState() {
        this.isDestroyed = false;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }
}
