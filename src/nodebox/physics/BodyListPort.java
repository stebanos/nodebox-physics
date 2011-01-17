package nodebox.physics;

import net.phys2d.raw.BodyList;
import nodebox.node.Node;
import nodebox.node.Port;

public class BodyListPort extends Port {
    private BodyList value;

    public BodyListPort(Node node, String name, Direction direction) {
        super(node, name, direction);
    }

    @Override
    public Object getValue() {
        return value;
    }

    public BodyList get() {
        return value;
    }

    @Override
    public void setValue(Object value) throws IllegalArgumentException {
        if (value instanceof BodyList || value == null) {
            set((BodyList) value);
        } else {
            throw new IllegalArgumentException("Value is not a BodyList.");
        }
    }

    public void set(BodyList value) {
        this.value = value;
    }
}
