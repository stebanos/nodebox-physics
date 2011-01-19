package nodebox.physics;

import net.phys2d.raw.shapes.DynamicShape;
import nodebox.node.*;

@Description("Create a Box shape.")
@Category("Physics")
@EvaluatedNode
public class Box extends CreateBody {
    public final FloatPort pWidth = new FloatPort(this, "width", Port.Direction.INPUT, 10f);
    public final FloatPort pHeight = new FloatPort(this, "height", Port.Direction.INPUT, 10f);

    protected DynamicShape createShape() {
        return new net.phys2d.raw.shapes.Box(pWidth.get(), pHeight.get());
    }
}
