package pokemonai.game.events;

import pokemonai.game.BattlePokemon;
import pokemonai.game.EventPokemon;

public class MissEvent extends GameEvent {
    EventPokemon target;

    public MissEvent(BattlePokemon target) {
        this.target = new EventPokemon(target);
    }

    @Override
    public EventType eventType() {
        return EventType.MISS;
    }
}
