package de.hhn.shogi.BasicGameManager;

public abstract class TypeSerializer {
    protected abstract String serialize(Object object);
    protected abstract Object deserialize(String serialized);
    protected abstract Class getType();
}
