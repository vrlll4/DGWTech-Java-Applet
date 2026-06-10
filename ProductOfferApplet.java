import java.applet.Applet;
import java.awt.Graphics;

/*
 * Booking Applet
 * Displays an animated booking message.
 * Author: Rana
 */

public class BookingApplet extends Applet implements Runnable
{
    // Position of the moving text
    int x = 10;

    // Thread used for animation
    Thread t;

    // Initialize the applet
    public void init()
    {
        // Create and start animation thread
        t = new Thread(this);
        t.start();
    }

    // Animation loop
    public void run()
    {
        while(true)
        {
            // Move text to the right
            x += 5;

            // Return text to start when it reaches the end
            if(x > 300)
            {
                x = 10;
            }

            // Redraw applet
            repaint();

            try
            {
                // Pause animation for 100 milliseconds
                Thread.sleep(100);
            }
            catch(Exception e)
            {
                // Handle any errors
            }
        }
    }

    // Draw text on the screen
    public void paint(Graphics g)
    {
        g.drawString("Room Already Reserved", x, 50);
    }
}
