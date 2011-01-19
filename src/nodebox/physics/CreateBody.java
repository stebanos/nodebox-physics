package nodebox.physics;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.DynamicShape;
import nodebox.node.*;

public abstract class CreateBody extends Node {
    public final BooleanPort pCreate = new BooleanPort(this, "create", Port.Direction.INPUT, false);
    public final WorldPort pWorld = new WorldPort(this, "world", Port.Direction.INPUT);
    public final FloatPort pX = new FloatPort(this, "x", Port.Direction.INPUT);
    public final FloatPort pY = new FloatPort(this, "y", Port.Direction.INPUT);
    public final FloatPort pMass = new FloatPort(this, "mass", Port.Direction.INPUT, 20f);
    public final FloatPort pFriction = new FloatPort(this, "friction", Port.Direction.INPUT, 0.3f);
    public final FloatPort pRestitution = new FloatPort(this, "restitution", Port.Direction.INPUT, 0.1f);
    public final FloatPort pRotation = new FloatPort(this, "rotation", Port.Direction.INPUT, 0.1f);
    public final FloatPort pVelocityX = new FloatPort(this, "velocityX", Port.Direction.INPUT);
    public final FloatPort pVelocityY = new FloatPort(this, "velocityY", Port.Direction.INPUT);
    public final FloatPort pAngularVelocity = new FloatPort(this, "angularVelocity", Port.Direction.INPUT);
    public final FloatPort pDamping = new FloatPort(this, "linearDamping", Port.Direction.INPUT);
    public final FloatPort pAngularDamping = new FloatPort(this, "angularDamping", Port.Direction.INPUT);
    public final ShapePort pShape = new ShapePort(this, "shape", Port.Direction.OUTPUT);

    @Override
    public void execute(Context context, float time) {
        if (pWorld.get() != null && pCreate.get()) {
            DynamicShape shape = createShape();
            Body body = new Body(shape, pMass.get());
            body.setPosition(pX.get(), pY.get());
            body.setFriction(pFriction.get());
            body.setRestitution(pRestitution.get());
            body.setRotation(pRotation.get());
            body.adjustVelocity(new Vector2f(pVelocityX.get(), pVelocityY.get()));
            body.adjustAngularVelocity(pAngularVelocity.get());
            body.setDamping(pDamping.get());
            body.setRotDamping(pAngularDamping.get());
            pWorld.get().add(body);
            pShape.set(shape);
        }
    }

    protected abstract DynamicShape createShape();
}
