package nodebox.physics;

import net.phys2d.raw.World;
import nodebox.node.Node;
import nodebox.node.Port;

public class WorldPort extends Port {
    private World value;

    public WorldPort(Node node, String name, Direction direction) {
        super(node, name, direction);
    }

    @Override
    public Object getValue() {
        return value;
    }

    public World get() {
        return value;
    }

    @Override
    public void setValue(Object value) throws IllegalArgumentException {
        if (value instanceof World || value == null) {
            set((World) value);
        } else {
            throw new IllegalArgumentException("Value is not a World.");
        }
    }

    public void set(World value) {
        this.value = value;
    }
}
