package nodebox.physics;

import net.phys2d.raw.shapes.DynamicShape;
import nodebox.node.*;

@Description("Create a Circle shape.")
@Category("Physics")
@EvaluatedNode
public class Circle extends CreateBody {
    public final FloatPort pRadius = new FloatPort(this, "radius", Port.Direction.INPUT, 5f);

    protected DynamicShape createShape() {
        return new net.phys2d.raw.shapes.Circle(pRadius.get());
    }
}
