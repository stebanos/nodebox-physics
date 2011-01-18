package nodebox.physics;

import net.phys2d.raw.JointList;
import nodebox.node.Node;
import nodebox.node.Port;

public class JointListPort extends Port {
    private JointList value;

    public JointListPort(Node node, String name, Direction direction) {
        super(node, name, direction);
    }

    @Override
    public Object getValue() {
        return value;
    }

    public JointList get() {
        return value;
    }

    @Override
    public void setValue(Object value) throws IllegalArgumentException {
        if (value instanceof JointList || value == null) {
            set((JointList) value);
        } else {
            throw new IllegalArgumentException("Value is not a BodyList.");
        }
    }

    public void set(JointList value) {
        this.value = value;
    }
}
