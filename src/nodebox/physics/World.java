package nodebox.physics;

import net.phys2d.math.ROVector2f;
import net.phys2d.raw.strategies.QuadSpaceStrategy;
import nodebox.node.*;
import processing.core.PGraphics;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.Shape;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;

@Description("Populate a World.")
@Category("Physics")
public class World extends Node {

    public final FloatPort pGravityX = new FloatPort(this, "gravityX", Port.Direction.INPUT, 0);
    public final FloatPort pGravityY = new FloatPort(this, "gravityY", Port.Direction.INPUT, 10f);
    public final BooleanPort pReset = new BooleanPort(this, "reset", Port.Direction.INPUT, true);
    public final WorldPort pWorld = new WorldPort(this, "world", Port.Direction.OUTPUT);
    public final BodyListPort pBodies = new BodyListPort(this, "bodies", Port.Direction.OUTPUT);
    public final JointListPort pJoints = new JointListPort(this, "joints", Port.Direction.OUTPUT);
    public final BooleanPort pHasReset = new BooleanPort(this, "hasReset", Port.Direction.OUTPUT);

    private net.phys2d.raw.World world;
    private float time = 0;
    
    public void createTheWorld() {
        world = new net.phys2d.raw.World(new Vector2f(0.0f, 10.0f), 10, new QuadSpaceStrategy(20,5));
        world.clear();
    }

    @Override
    public void execute(Context context, float time) {
        if (pReset.get()) {
            createTheWorld();
            pWorld.set(world);
        }
        world.setGravity(pGravityX.get(), pGravityY.get());
        float dt = time - this.time;
        world.step(dt);
        this.time = time;
        pBodies.set(world.getBodies());
        pJoints.set(world.getJoints());
        pHasReset.set(pReset.get());
    }

    @Override
    public void draw(PGraphics g, Context context, float v) {
        BodyList bodies = world.getBodies();

        for (int i=0;i<bodies.size();i++) {
            Body body = bodies.get(i);
            Shape shape = body.getShape();
            if (shape instanceof Box)
                drawBoxBody(g, body, (Box) shape);
            else if (shape instanceof Circle)
                drawCircleBody(g, body, (Circle) shape);
        }
    }

    protected void drawBoxBody(PGraphics g, Body body, Box box) {
        Vector2f[] pts = box.getPoints(body.getPosition(), body.getRotation());

        Vector2f v1 = pts[0];
        Vector2f v2 = pts[1];
        Vector2f v3 = pts[2];
        Vector2f v4 = pts[3];

        g.quad(v1.x, v1.y, v2.x, v2.y, v3.x, v3.y, v4.x, v4.y);
    }

    protected void drawCircleBody(PGraphics g, Body body, Circle circle) {
        ROVector2f pos = body.getPosition();
        g.ellipseMode(PGraphics.CENTER);
        g.ellipse(pos.getX(), pos.getY(), circle.getRadius() * 2, circle.getRadius() * 2);
    }
}