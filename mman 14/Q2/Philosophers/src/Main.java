import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    static final int NUM_PHILOSOPHERS = 5;
    static Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
    static Stick[] sticks = new Stick[NUM_PHILOSOPHERS];
    static VisualizerPanel visualizer = new VisualizerPanel();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dining Philosophers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(visualizer);
        frame.setVisible(true);

        // Create sticks
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            sticks[i] = new Stick(i);
        }

        // Create and start philosophers
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            Stick left = sticks[i];
            Stick right = sticks[(i + 1) % NUM_PHILOSOPHERS];

            // Avoid deadlock by reversing order for last philosopher
            if (i == NUM_PHILOSOPHERS - 1) {
                philosophers[i] = new Philosopher(i, right, left);
            } else {
                philosophers[i] = new Philosopher(i, left, right);
            }

            Thread t = new Thread(philosophers[i]);
            t.start();
        }
    }
//stick constructor
    static class Stick {
        private final int stickId;
        private boolean isHeld = false;

        public Stick(int id) {

            this.stickId = id;
        }

        public int getId() {

            return stickId;
        }

        public synchronized void pickUp() throws InterruptedException {
            while (isHeld) {
                wait(); // Waits on THIS stick
            }
            isHeld = true;
            SwingUtilities.invokeLater(visualizer::repaint);
        }

        public synchronized void putDown() {
            isHeld = false;
            notifyAll(); // Wakes up threads waiting for this stick
        }

        public int getStickId() {

            return stickId;
        }

        public boolean isHeld() {
            return isHeld;
        }
    }

    static class Philosopher implements Runnable {
        private final int id;
        private final int second = 1000;
        private final Stick leftStick, rightStick;
        private String state = "rest"; //Starting state
    //Philosopher constructor
        public Philosopher(int id, Stick left, Stick right) {
            this.id = id;
            this.leftStick = left;
            this.rightStick = right;
        }
    // Getters for philosopher
        public int getId() {

            return id;
        }

        public String getState() {

            return state;
        }

        @Override
        public void run() {
            try {
                while (true) {

                    leftStick.pickUp();
                    setState("Picked left stick");
                    System.out.println("Philosopher " + (id + 1) + " Picked up left stick " + (leftStick.getStickId() + 1));
                    Thread.sleep(second); //delay for a second in order to see the change in the visual representation

                    rightStick.pickUp();
                    setState("Picked right stick");
                    System.out.println("Philosopher " + (id + 1) + " Picked up right stick " + (rightStick.getStickId() + 1));
                    Thread.sleep(second); //delay for a second in order to see the change in the visual representation

                    setState("Eating");
                    eat();

                    leftStick.putDown();
                    setState("Put down left stick");
                    System.out.println("Philosopher " + (id + 1) + " put down left stick " + (leftStick.getStickId() + 1));
                    Thread.sleep(second); //delay for a second in order to see the change in the visual representation

                    rightStick.putDown();
                    setState("Put down right stick");
                    System.out.println("Philosopher " + (id + 1) + " put down right stick " + (rightStick.getStickId() + 1));
                    Thread.sleep(second); //delay for a second in order to see the change in the visual representation

                    setState("Thinking");
                    think();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void think() throws InterruptedException {
            System.out.println("Philosopher " + (id + 1) + " is thinking");
            // Random thinking time between 0.5-2.5 seconds
            int delay = 500 + (int) (Math.random() * 2000); // 500 to 2500 ms
            Thread.sleep(delay);
        }

        private void eat() throws InterruptedException {
            System.out.println("Philosopher " + (id + 1) + " is eating");
            // Random eating time between 0.5-2.5 seconds
            int delay = 500 + (int) (Math.random() * 2000); // 500 to 2500 ms
            Thread.sleep(delay);
        }

        private void setState(String newState) {
            this.state = newState;
            SwingUtilities.invokeLater(visualizer::repaint);
        }
    }

    static class VisualizerPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = 200;

            for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
                double angle = 2 * Math.PI / NUM_PHILOSOPHERS * i;
                int x = (int) (centerX + radius * Math.cos(angle));
                int y = (int) (centerY + radius * Math.sin(angle));

                // Color based on philosopher state
                String state = philosophers[i].getState();
                if (state.contains("Eating")) {
                    g.setColor(Color.GREEN); // Eating → Green
                } else if (state.contains("Thinking")) {
                    g.setColor(Color.BLUE); // Thinking → Blue
                } else {
                    g.setColor(Color.LIGHT_GRAY); // Default/Idle
                }

                // Draw philosopher body (circle)
                g.fillOval(x - 20, y - 20, 40, 40);

                // Draw label
                g.setColor(Color.BLACK);
                g.drawString("P" + (i + 1) + ": " + state, x - 30, y - 30);
            }
        }
    }
}
