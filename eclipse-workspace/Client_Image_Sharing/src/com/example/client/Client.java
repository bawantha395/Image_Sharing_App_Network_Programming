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

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Client {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final File[] fileToSend = new File[1];

        JFrame jFrame = new JFrame("Client Image Sender");
        jFrame.setSize(450, 450);
        jFrame.setLayout(new BorderLayout(10, 10));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jlTitle = new JLabel("Image Sender", SwingConstants.CENTER);
        jlTitle.setFont(new Font("SansSerif", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20, 0, 10, 0));

        JLabel jlFileName = new JLabel("Choose an image to send.", SwingConstants.CENTER);
        jlFileName.setFont(new Font("SansSerif", Font.PLAIN, 18));

        JButton jbSendFile = new JButton("Send Image");
        jbSendFile.setFont(new Font("SansSerif", Font.BOLD, 15));

        JButton jbChooseFile = new JButton("Choose Image");
        jbChooseFile.setFont(new Font("SansSerif", Font.BOLD, 15));

        JPanel jpButtons = new JPanel(new FlowLayout());
        jpButtons.add(jbChooseFile);
        jpButtons.add(jbSendFile);

        jbChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Choose an image to send.");
                jFileChooser.setAcceptAllFileFilterUsed(false);
                jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));

                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileToSend[0] = jFileChooser.getSelectedFile();
                    jlFileName.setText("Selected: " + fileToSend[0].getName());
                }
            }
        });

        jbSendFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileToSend[0] == null) {
                    jlFileName.setText("Please choose an image to send first!");
                } else {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
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

                        jlFileName.setText("Image sent successfully: " + fileToSend[0].getName());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        jlFileName.setText("An error occurred: " + ex.getMessage());
                    }
                }
            }
        });

        jFrame.add(jlTitle, BorderLayout.NORTH);
        jFrame.add(jlFileName, BorderLayout.CENTER);
        jFrame.add(jpButtons, BorderLayout.SOUTH);
        jFrame.setVisible(true);
    }
}
