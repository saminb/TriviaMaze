import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MazeTester {

    private Maze testMaze, testMaze2;


    @Before
    public void startup() {
        testMaze = new Maze(4, 5, 0, 0, 3, 4);
        testMaze2 = new Maze(6, 7);
    }

    @Test
    public void testConstructor1() {
        assertEquals(testMaze.getHeight(), 4);
        assertEquals(testMaze.getWidth(), 5);

        assertEquals(testMaze.getPlayerH(), 0);
        assertEquals(testMaze.getPlayerW(), 0);

        assertEquals(testMaze.getGoalH(), 3);
        assertEquals(testMaze.getGoalW(), 4);
    }

    @Test
    public void testConstructor2() {
        assertEquals(testMaze2.getHeight(), 6);
        assertEquals(testMaze2.getWidth(), 7);

        assertNotEquals(testMaze2.getPlayerH(), testMaze2.getGoalH());
        assertNotEquals(testMaze2.getPlayerW(), testMaze2.getGoalW());

        assertTrue(testMaze2.getPlayerH() >= 0);
        assertTrue(testMaze2.getPlayerW() >= 0);
        assertTrue(testMaze2.getPlayerH() < testMaze2.getHeight());
        assertTrue(testMaze2.getPlayerW() < testMaze2.getWidth());

        assertTrue(testMaze2.getGoalH() >= 0);
        assertTrue(testMaze2.getGoalW() >= 0);
        assertTrue(testMaze2.getGoalH() < testMaze2.getHeight());
        assertTrue(testMaze2.getGoalW() < testMaze2.getWidth());
    }

    @Test
    public void testSetPlayerPosition() {
        testMaze.setPlayerPosition(1, 2);
        assertEquals(testMaze.getPlayerH(), 1);
        assertEquals(testMaze.getPlayerW(), 2);

        testMaze.setPlayerPosition(2, 1);
        assertEquals(testMaze.getPlayerH(), 2);
        assertEquals(testMaze.getPlayerW(), 1);
    }

    @Test
    public void testCheckDoor() {
        assertEquals(testMaze.checkDoor(0, 3, 0), -1);
        assertEquals(testMaze.checkDoor(0, 3, 1), 0);

        assertEquals(testMaze2.checkDoor(0, 0, 0), -1);
        assertEquals(testMaze2.checkDoor(0, 0, 1), 0);
        assertEquals(testMaze2.checkDoor(0, 0, 2), -1);
        assertEquals(testMaze2.checkDoor(0, 0, 3), 0);
    }

    @Test
    public void testCheckAllDoors() {
        int[] testArr1 = {-1, 0, -1, 0};
        int[] testArr2 = {0, 0, 0, 0};
        assertArrayEquals(testMaze.checkAllDoors(0, 0), testArr1);
        assertArrayEquals(testMaze.checkAllDoors(1, 1), testArr2);
    }

    @Test
    public void testSetDoors() {
        int[] testArr1 = {-1, 0, 1, 2};
        int[] testArr2 = {-1, 0, 0, 1};
        testMaze.setDoor(0, 2, 2, true);
        testMaze.setDoor(0, 2, 3, false);
        assertArrayEquals(testMaze.checkAllDoors(0, 2), testArr1);
        assertArrayEquals(testMaze.checkAllDoors(0, 1), testArr2);
    }

    @Test
    public void testIsSolved() {
        assertFalse(testMaze.isSolved());
        assertFalse(testMaze2.isSolved());

        testMaze.setPlayerPosition(3, 4);
        testMaze2.setPlayerPosition(testMaze2.getGoalH(), testMaze2.getGoalW());

        assertTrue(testMaze.isSolved());
        assertTrue(testMaze2.isSolved());
    }

    @Test
    public void testSolvable() {
        assertTrue(testMaze.solvable(testMaze.getPlayerH(), testMaze.getPlayerW()));
        assertTrue(testMaze2.solvable(testMaze2.getPlayerH(), testMaze2.getPlayerW()));

        testMaze.setDoor(1, 1, 0, false);
        testMaze.setDoor(1, 1, 1, false);
        testMaze.setDoor(1, 1, 2, false);
        testMaze.setDoor(1, 1, 3, false);

        assertFalse(testMaze.solvable(1, 1));
        assertTrue(testMaze.solvable(0, 0));

        testMaze2.setDoor(testMaze2.getGoalH(), testMaze2.getGoalW(), 0, false);
        testMaze2.setDoor(testMaze2.getGoalH(), testMaze2.getGoalW(), 1, false);
        testMaze2.setDoor(testMaze2.getGoalH(), testMaze2.getGoalW(), 2, false);
        testMaze2.setDoor(testMaze2.getGoalH(), testMaze2.getGoalW(), 3, false);

        assertFalse(testMaze2.solvable(testMaze2.getPlayerH(), testMaze2.getPlayerW()));

        testMaze2.setDoor(testMaze2.getGoalH(), testMaze2.getGoalW(), 0, true);
        testMaze2.setDoor(testMaze2.getGoalH(), testMaze2.getGoalW(), 1, true);

        assertTrue(testMaze2.solvable(testMaze2.getPlayerH(), testMaze2.getPlayerW()));
    }
}
