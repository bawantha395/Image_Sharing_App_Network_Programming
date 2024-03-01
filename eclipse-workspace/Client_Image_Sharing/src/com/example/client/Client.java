//package com.example.client;
//
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.net.Socket;
//
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//public class Client {
//
//	public static void main(String[] args) {
//		
//		final File[] fileToSend = new File[1];
//		
//		 // Set the frame to house everything.
//		JFrame jFrame = new JFrame("Client_4162_4035_4036");
//		
//		// Set the size of the frame.
//		jFrame.setSize(450, 450);
//		
//		// Make the layout to be box layout that places its children on top of each other.
//		jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
//		
//		// Make it so when the frame is closed the program exits successfully.
//		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
//		
//		
//		
//		// Title above panel.
//        JLabel jlTitle = new JLabel("WittCode's File Sender");
//        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
//        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
//        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//        
//        
//        // Label that has the file name.
//        JLabel jlFileName = new JLabel("Choose a file to send.");
//        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
//        jlFileName.setBorder(new EmptyBorder(50, 0, 0, 0));
//        jlFileName.setAlignmentX(Component.CENTER_ALIGNMENT);
//        
//        
//        
//        // Panel that contains the buttons.
//        JPanel jpButton = new JPanel();
//        jpButton.setBorder(new EmptyBorder(75, 0, 10, 0));
//        
//        
//        // Create send file button.
//        JButton jbSendFile = new JButton("Send File");
//        jbSendFile.setPreferredSize(new Dimension(150, 75));
//        jbSendFile.setFont(new Font("Arial", Font.BOLD, 20));
//        
//        
//        // Make the second button to choose a file.
//        JButton jbChooseFile = new JButton("Choose File");
//        jbChooseFile.setPreferredSize(new Dimension(150, 75));
//        jbChooseFile.setFont(new Font("Arial", Font.BOLD, 20));
//        
//        
//        // Add the buttons to the panel.
//        jpButton.add(jbSendFile);
//        jpButton.add(jbChooseFile);
//        
//        
//        // Button action for choosing the file.
//        jbChooseFile.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser jFileChooser = new JFileChooser();
//                jFileChooser.setDialogTitle("Choose a file to send.");
//                
//                // Show the dialog and if a file is chosen from the file chooser execute the following statements.
//                if (jFileChooser.showOpenDialog(null)  == JFileChooser.APPROVE_OPTION) {
//                    fileToSend[0] = jFileChooser.getSelectedFile();
//                    jlFileName.setText("The file you want to send is: " + fileToSend[0].getName());
//                }
//            }
//        });
//        
//        
//        
//
//        // Sends the file when the button is clicked.
//        jbSendFile.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (fileToSend[0] == null) {
//                    jlFileName.setText("Please choose a file to send first!");
//                    
//                } else {
//                    try {
//                        FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
//                        Socket socket = new Socket("localhost", 1234);
//                        
//                        // Create an output stream to write to write to the server over the socket connection.
//                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//                        
//                        // Get the name of the file you want to send and store it in filename.
//                        String fileName = fileToSend[0].getName();
//                        byte[] fileNameBytes = fileName.getBytes();
//                        
//                        byte[] fileBytes = new byte[(int)fileToSend[0].length()];
//                        fileInputStream.read(fileBytes);
//                        
//                        
//                        // Send the length of the name of the file so server knows when to stop reading.
//                        dataOutputStream.writeInt(fileNameBytes.length);
//                        dataOutputStream.write(fileNameBytes);
//                        
//                        // Send the length of the byte array so the server knows when to stop reading.
//                        dataOutputStream.writeInt(fileBytes.length);
//                        dataOutputStream.write(fileBytes);
//                        
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        });
//        
//        
//        // Add everything to the frame and make it visible.
//        jFrame.add(jlTitle);
//        jFrame.add(jlFileName);
//        jFrame.add(jpButton);
//        jFrame.setVisible(true);
//		
//		
//
//	}
//
//}


package com.example.client;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            // Setting Nimbus Look and Feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final File[] fileToSend = new File[1];

        SwingUtilities.invokeLater(() -> createAndShowGUI(fileToSend));
    }

    private static void createAndShowGUI(File[] fileToSend) {
        JFrame jFrame = new JFrame("Client Image Sender");
        jFrame.setSize(450, 450);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with background color
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(51, 51, 51)); // Dark gray background

        // Title label with custom font and color
        JLabel titleLabel = new JLabel("Image Sender", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);

        // File name label with custom font and color
        JLabel fileNameLabel = new JLabel("Choose an image to send.", SwingConstants.CENTER);
        fileNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        fileNameLabel.setForeground(Color.WHITE);

        // Buttons with custom styling
        JButton chooseButton = new JButton("Choose Image");
        chooseButton.setFont(new Font("Arial", Font.BOLD, 16));
        chooseButton.setBackground(new Color(100, 149, 237)); // Cornflower blue
        chooseButton.setForeground(Color.WHITE);
        JButton sendButton = new JButton("Send Image");
        sendButton.setFont(new Font("Arial", Font.BOLD, 16));
        sendButton.setBackground(new Color(60, 179, 113)); // Medium sea green
        sendButton.setForeground(Color.WHITE);

        // Button panel with custom layout and spacing
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBackground(new Color(51, 51, 51)); // Dark gray background
        buttonPanel.add(chooseButton);
        buttonPanel.add(sendButton);

        // Add action listeners to buttons
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseImage(fileToSend, fileNameLabel);
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendImage(fileToSend, fileNameLabel);
            }
        });

        // Add components to main panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(fileNameLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        jFrame.getContentPane().add(mainPanel);
        jFrame.setVisible(true);
    }

    private static void chooseImage(File[] fileToSend, JLabel fileNameLabel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose an image to send.");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            fileToSend[0] = fileChooser.getSelectedFile();
            fileNameLabel.setText("Selected: " + fileToSend[0].getName());
        }
    }

    private static void sendImage(File[] fileToSend, JLabel fileNameLabel) {
        if (fileToSend[0] == null) {
            fileNameLabel.setText("Please choose an image to send first!");
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(fileToSend[0]);
                Socket socket = new Socket("localhost", 1234);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                String fileName = fileToSend[0].getName();
                byte[] fileNameBytes = fileName.getBytes();

                byte[] fileBytes = new byte[(int) fileToSend[0].length()];
                fileInputStream.read(fileBytes);

                dataOutputStream.writeInt(fileNameBytes.length);
                dataOutputStream.write(fileNameBytes);

                dataOutputStream.writeInt(fileBytes.length);
                dataOutputStream.write(fileBytes);

                fileInputStream.close();
                dataOutputStream.close();
                socket.close();

                fileNameLabel.setText("Image sent successfully: " + fileToSend[0].getName());
            } catch (IOException ex) {
                ex.printStackTrace();
                fileNameLabel.setText("An error occurred: " + ex.getMessage());
            }
        }
    }
}
