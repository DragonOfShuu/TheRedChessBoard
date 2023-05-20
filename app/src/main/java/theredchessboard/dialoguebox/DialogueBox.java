package theredchessboard.dialoguebox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class DialogueBox extends JFrame implements ActionListener {
    private ArrayList<String> themes;
    private boolean playerOneChosen;
    private String playerOneSkin;
    private String playerTwoSkin;
    private JLabel themeQuestion;
    private IChosenThemes callback;
    // arrays are used to allow a filepath and the theme name in one position in the arraylist

    public DialogueBox(IChosenThemes callback){
        super("Choose Your Skin");
        int[] windowBounds = {700,700};

        this.callback = callback;

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int[] centerBounds = {(screenWidth - windowBounds[0]) / 2, (screenHeight - windowBounds[1]) / 2};
        // Gets the center of the screen

        this.setBounds(0, 0, windowBounds[0], windowBounds[1]);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(centerBounds[0], centerBounds[1]);
        this.setVisible(true);
        // JFrame window initilization


        themes = new ArrayList<String>();
        Font font = new Font("Monospace", Font.BOLD, 20);
        // Font and ArrayList initialization


        themeQuestion = new JLabel("Choose Your Theme for Player 1");
        themeQuestion.setBounds((windowBounds[0] - 367) / 2, 0, 370, 50);
        themeQuestion.setFont(font);
        themeQuestion.setForeground(Color.BLACK);
        this.add(themeQuestion);
        // Text JLabel


        // String path = "app/src/main/resources/themes/";
        // // path to the folder "themes"

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("themes");
        String path = url.getPath();
        
        File directory = new File(path);
        System.out.println(directory);
        File[] folders = directory.listFiles(); //unfortunately I have to use type File[]. No shortcuts here

        for (File x : folders) {
            System.out.println(x.toString());
        }
        if(folders != null){
            for(File x : folders){
                themes.add(x.getName());
            }
        }
        // adds the folder names from directory into arraylist "themes"

        
        buttonLayout(windowBounds);
        // creates the buttons and layout of the GUI


        playerOneChosen = false;
        // makes it so the second button you click saves as player two's skin
    }




    private void buttonLayout(int[] windowBounds){
        boolean[] rowCheck = {false, true, false};
        // used to align stuff to the center


        int lefty = 50;
        int centerx = (windowBounds[0] - ((windowBounds[0] - 20) / 3)) / 2;
        int centery = 50;
        int rightx = windowBounds[0] - ((windowBounds[0] - 20) / 3);
        int righty = 50;
        // coordinate stuff, calculates where each three columns of buttons need to go


        for(String x : themes){
            JButton button = new JButton(x);

            // middle column
            if(rowCheck[1]){
                button.setBounds(centerx, centery, (windowBounds[0] - 20) / 3, ((windowBounds[1] - 50) / 10) - 5);
                button.addActionListener(this);
                this.add(button);

                centery += (windowBounds[1] - 50) / 10;

                rowCheck[1] = false;
                rowCheck[0] = true;


            // left column
            } else if(rowCheck[0]){
                button.setBounds(0, lefty, (windowBounds[0] - 20) / 3, ((windowBounds[1] - 50) / 10) - 5);
                button.addActionListener(this);
                this.add(button);

                lefty += (windowBounds[1] - 50) / 10;

                rowCheck[0] = false;
                rowCheck[2] = true;


            // right column
            } else if(rowCheck[2]){
                button.setBounds(rightx, righty, (windowBounds[0] - 20) / 3, ((windowBounds[1] - 50) / 10) - 5);
                button.addActionListener(this);
                this.add(button);

                righty += (windowBounds[1] - 50) / 10;

                rowCheck[2] = false;
                rowCheck[1] = true;
            }
        }
    }




    @Override
    public void actionPerformed(ActionEvent event){
        // self explanatory method tbh

        if(!playerOneChosen){
            playerOneSkin = event.getActionCommand();
            playerOneChosen = true;
            themeQuestion.setText("Choose Your Theme for Player 2");
        } else {
            playerTwoSkin = event.getActionCommand();

            // CHANGE THIS TO WHATEVER YOU NEED IT TO BE
            System.out.println("PLAYER ONE SKIN: " + playerOneSkin + "\nPLAYER TWO SKIN: " + playerTwoSkin);
            // CHANGE THIS TO WHATEVER YOU NEED IT TO BE
            // idk if you also want to have the window close or what 
            // I haven't looked into that but it's up to you so I'll just let you do it
            this.setVisible(false);
            callback.startBoard(playerOneSkin, playerTwoSkin);
        }
    }
}