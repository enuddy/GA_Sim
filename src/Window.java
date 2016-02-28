import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Evan_Nudd on 2/24/16.
 */
public class Window {
    private JButton runButton;
    private JLabel STRONGEST_CHROME_Label;
    private JPanel panel;
    private JTextArea valueTextArea;
    private JTextArea keyTextArea;
    private JLabel genValue;
    private JLabel GEN_LABEL;

    private World world;

    public Window() {

        runButton.addActionListener(new ActionListener() { // Action listener to start a new experiment.
            @Override
            public void actionPerformed(ActionEvent e) {
               new Thread() {

                  @Override
                   public void run() {
                      EventQueue.invokeLater(new Runnable() {
                          @Override
                          public void run() {
                              char target = valueTextArea.getText().charAt(0);
                              System.out.println("Target is: " + target);
                              world.runExperiment(target);
                          }
                      });
                  } // end of run method

               }.start(); // start the thread.
            }
        });

    }

    public void setWorld(World world) {
        this.world = world;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame("Window");
                    frame.setSize(800, 800);
                    frame.setPreferredSize(new Dimension(800, 800));

                    // create our window and world objects so they can communicate.
                    Window window = new Window();
                    World world = new World(window);
                    window.setWorld(world);

                    // create the things that the java needs to do its thing to create a physical thing.
                    frame.setContentPane(window.panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }

    public void changeLabelTo(String newLabel) {
        STRONGEST_CHROME_Label.setText(newLabel);

       // STRONGEST_CHROME_Label.paintImmediately(STRONGEST_CHROME_Label.getVisibleRect());
        panel.paintImmediately(panel.getVisibleRect());
    }

    public void setGenerationTo(int gen) {
        this.genValue.setText(Integer.toString(gen));
//        this.genValue.paintImmediately(genValue.getVisibleRect());
    }

}
