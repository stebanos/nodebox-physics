package nodebox.physics;

import net.phys2d.raw.shapes.Shape;
import nodebox.node.Node;
import nodebox.node.Port;

public class ShapePort extends Port {
    private Shape value;

    public ShapePort(Node node, String name, Direction direction) {
        super(node, name, direction);
    }

    @Override
    public Object getValue() {
        return value;
    }

    public Shape get() {
        return value;
    }

    @Override
    public void setValue(Object value) throws IllegalArgumentException {
        if (value instanceof Shape || value == null) {
            set((Shape) value);
        } else {
            throw new IllegalArgumentException("Value is not a Shape.");
        }
    }

    public void set(Shape value) {
        this.value = value;
    }
}
