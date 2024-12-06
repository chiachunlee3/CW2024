package com.example.demo;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {
    private final DestructionState destructionState;

    public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.destructionState = new DestructionState();
    }

    @Override
    public abstract void updatePosition();

    public abstract void updateActor();

    @Override
    public abstract void takeDamage();

    @Override
    public void destroy() {
        destructionState.setDestroyed(true);
    }

    public boolean isDestroyed() {
        return destructionState.isDestroyed();
    }
}
