package src.test.java.pl.roadflow.core.mechanics.collision;

import org.junit.Before;
import org.junit.Test;
import pl.roadflow.core.mechanics.car.Car;
import pl.roadflow.core.mechanics.stats.CarParameters;
import pl.roadflow.core.mechanics.car.controller.impl.TopDownCarController;
import pl.roadflow.core.mechanics.car.physics.CollisionHandler;
import pl.roadflow.ui.screens.GameScreen;
import pl.roadflow.utils.Vector2;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CollisionHandlerTest {

    private Car testCar;
    private CollisionHandler collisionHandler;
    private static final int TEST_CAR_SIZE = 30;

    @Before
    public void setUp() throws Exception {
        // Create test car with known parameters
        ImageIcon carIcon = new ImageIcon(new byte[0]) {
            @Override
            public int getIconWidth() { return TEST_CAR_SIZE; }

            @Override
            public int getIconHeight() { return TEST_CAR_SIZE; }
        };

        CarParameters params = new CarParameters(
                0.8f, 1.5f, 0.6f, 50f, 0.1f, 8f
        );

        testCar = new Car(params, carIcon);
        collisionHandler = new CollisionHandler(testCar);

        // Reset GameScreen state
        setGameScreenState(new ArrayList<>(), new Rectangle(0, 0, 800, 600));
    }

    private void setGameScreenState(ArrayList<Rectangle> obstacles, Rectangle bounds)
            throws Exception {

        Field obstaclesField = GameScreen.class.getDeclaredField("mapObstacles");
        obstaclesField.setAccessible(true);
        obstaclesField.set(null, obstacles);

        Field boundsField = GameScreen.class.getDeclaredField("frameBounds");
        boundsField.setAccessible(true);
        boundsField.set(null, bounds);
    }

    @Test
    public void testNoCollisionDetection() throws Exception {
        TopDownCarController controller = new TopDownCarController(testCar.getCarParameters());
        controller.setVelocity(new Vector2(5, 5));

        Vector2 result = collisionHandler.handleCollisions(
                100, 100, 90, 90, controller
        );

        assertEquals(new Vector2(100, 100), result);
        assertEquals(0, collisionHandler.stuckCounter);
    }

    // Modified testObstacleCollisionResponse
    @Test
    public void testObstacleCollisionResponse() throws Exception {
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(new Rectangle(100, 100, 50, 50));
        setGameScreenState(obstacles, new Rectangle(0, 0, 800, 600));

        TopDownCarController controller = new TopDownCarController(testCar.getCarParameters());
        controller.setVelocity(new Vector2(10, 0));

        Vector2 result = collisionHandler.handleCollisions(
                120, 120, 100, 100, controller
        );

        // Verify position adjustment
        assertNotEquals(new Vector2(120, 120), result);
        assertEquals(1, collisionHandler.stuckCounter);

        // Verify velocity reduction (from 10 to 5)
        assertEquals(5.0f, controller.getVelocity().x, 0.1f);
    }

    @Test
    public void testFrameBoundaryCollision() throws Exception {
        setGameScreenState(new ArrayList<>(), new Rectangle(0, 0, 800, 600));
        TopDownCarController controller = new TopDownCarController(testCar.getCarParameters());
        controller.setVelocity(new Vector2(-10, 0));

        // Try to move 15 pixels left of boundary (car center at -15)
        Vector2 result = collisionHandler.handleCollisions(
                -15, 300, 0, 300, controller
        );

        // Should be clamped to minX (half car width = 15)
        assertEquals(15.0f, result.x, 0.1f);
        assertEquals(300.0f, result.y, 0.1f);
        assertEquals(1, collisionHandler.stuckCounter);
    }

    @Test
    public void testStuckCounterReset() throws Exception {
        // First collision
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(new Rectangle(100, 100, 50, 50));
        setGameScreenState(obstacles, new Rectangle(0, 0, 800, 600));

        TopDownCarController controller = new TopDownCarController(testCar.getCarParameters());
        collisionHandler.handleCollisions(110, 110, 100, 100, controller);
        assertEquals(1, collisionHandler.stuckCounter);

        // Clear obstacles and test again
        setGameScreenState(new ArrayList<>(), new Rectangle(0, 0, 800, 600));
        collisionHandler.handleCollisions(200, 200, 200, 200, controller);
        assertEquals(0, collisionHandler.stuckCounter);
    }

    @Test
    public void testRotationCollisionDetection() {
        Rectangle obstacle = new Rectangle(100, 100, 50, 50);

        // Test collision with 45 degree rotation
        boolean collision = collisionHandler.isCollidingWithObstacle(
                obstacle, 100, 100, testCar.getCarModelIcon(), 45f
        );

        assertTrue("Should detect collision with rotated car", collision);
    }

    @Test
    public void testComplexCollisionScenario() throws Exception {
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(new Rectangle(200, 200, 50, 50));
        obstacles.add(new Rectangle(300, 300, 50, 50));
        setGameScreenState(obstacles, new Rectangle(0, 0, 800, 600));

        TopDownCarController controller = new TopDownCarController(testCar.getCarParameters());
        controller.setVelocity(new Vector2(15, 15));

        // First collision
        Vector2 pos1 = collisionHandler.handleCollisions(220, 220, 200, 200, controller);
        assertNotEquals(new Vector2(220, 220), pos1);
        assertEquals(1, collisionHandler.stuckCounter);

        // Second collision
        Vector2 pos2 = collisionHandler.handleCollisions(320, 320, 300, 300, controller);
        assertNotEquals(new Vector2(320, 320), pos2);
        assertEquals(2, collisionHandler.stuckCounter);
    }
}