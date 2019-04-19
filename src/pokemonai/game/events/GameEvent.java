package pokemonai.game.events;

public abstract class GameEvent {
    public abstract EventType eventType();

    public enum EventType {
        MOVEUSED, DAMAGE, RECOIL, MISS
    }
}
