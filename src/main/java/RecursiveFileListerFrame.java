import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;


public class RecursiveFileListerFrame extends JFrame {
    JFrame frame = new JFrame();
    JPanel mainPanel;
    JPanel topPanel;
    JPanel midPanel;
    JPanel botPanel;

    JLabel titleLbl;


    JLabel fileLabel;
    JTextArea fileDisplay;

    JButton quitButton;
    JButton addButton;


    public RecursiveFileListerFrame() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        createTopPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        createMiddlePanel();
        mainPanel.add(midPanel, BorderLayout.CENTER);
        createBottomPanel();
        mainPanel.add(botPanel, BorderLayout.SOUTH);

        add(mainPanel);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenWidth / 2, screenHeight / 2);
        setLocation(screenWidth / 4, screenHeight / 4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTopPanel() {
        topPanel = new JPanel();
        titleLbl = new JLabel("Recursive File Lister", JLabel.CENTER);
        titleLbl.setFont(new Font("Times New Roman MS", Font.BOLD, 42));


        topPanel.add(titleLbl);
    }

    private void createMiddlePanel() {
        midPanel = new JPanel();

        fileDisplay = new JTextArea(60, 60);

        midPanel.add(fileDisplay);

    }

    private void createBottomPanel()
    {
        botPanel = new JPanel();

        addButton = new JButton("Search File");
        quitButton = new JButton("Quit");

        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));

        addButton.addActionListener((ActionEvent ae) ->
        {
            JFileChooser chooser = new JFileChooser();
            File chosenFile;
            String rec = "";

            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                chosenFile = chooser.getSelectedFile();
                Search(chosenFile);

            }


        });

        botPanel.add(addButton);
        botPanel.add(quitButton);
    }

    private void Search(File toSearch)
    {
        File[] files = toSearch.listFiles();
        if (toSearch.isDirectory())
        {
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    fileDisplay.append((" \ndirectory: " + file.toPath()));
                    Search(file);
                } else {
                    fileDisplay.append("\nfile: " + file.toPath());
                }
            }
        } else
        {
            fileDisplay.append("\nfile: " + toSearch.toPath());
        }
    }
}
