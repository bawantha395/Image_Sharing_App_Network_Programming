//package com.example.server;
//
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//
//import javax.swing.BoxLayout;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.border.EmptyBorder;
//
//public class Server {
//	
//	// Array list to hold information about the files received.
//    static ArrayList<MyImage> myImages = new ArrayList<>();
//	
//	public static void main(String[] args) throws IOException  {
//		
//		// Used to track the file
//		int fileId = 0;
//		
//		// Main container, set the name.
//        JFrame jFrame = new JFrame("WittCode's Server");
//        jFrame.setSize(400, 400);
//        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        
//        
//        // Panel that will hold the title label and the other jpanels.
//        JPanel jPanel = new JPanel();
//        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
//        
//        
//        // Make it scrollable when the data gets in jpanel.
//        JScrollPane jScrollPane = new JScrollPane(jPanel);
//        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        
//        
//        // Title above panel.
//        JLabel jlTitle = new JLabel("WittCode's File Receiver");
//        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
//        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
//        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//        
//        
//        // Add everything to the main GUI.
//        jFrame.add(jlTitle);
//        jFrame.add(jScrollPane);
//        
//        
//        // Make the GUI show up.// Create a server socket that the server will be listening with.
//        ServerSocket serverSocket = new ServerSocket(1234);
//        jFrame.setVisible(true);
//        
//        
//        while (true) {
//
//            try {
//                // Wait for a client to connect and when they do create a socket to communicate with them.
//                Socket socket = serverSocket.accept();
//                
//
//                // Stream to receive data from the client through the socket.
//                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//
//                // Read the size of the file name so know when to stop reading.
//                int fileNameLength = dataInputStream.readInt();
//                
//                if (fileNameLength > 0) {
//                    byte[] fileNameBytes = new byte[fileNameLength];
//                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
//                    String fileName = new String(fileNameBytes);
//                    
//                    
//                    // Read how much data to expect for the actual content of the file.
//                    int fileContentLength = dataInputStream.readInt();
//                    
//
//                    if (fileContentLength > 0) {
//                        byte[] fileContentBytes = new byte[fileContentLength];
//                        dataInputStream.readFully(fileContentBytes, 0, fileContentBytes.length);
//                        
//                        
//                        // Panel to hold the picture and file name.
//                        JPanel jpFileRow = new JPanel();
//                        jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.X_AXIS));
//                        
//                        
//                        // Set the file name.
//                        JLabel jlFileName = new JLabel(fileName);
//                        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
//                        jlFileName.setBorder(new EmptyBorder(10,0, 10,0));
//                        
//                        
//                        if (getFileExtension(fileName).equalsIgnoreCase("txt")) {
//                            jpFileRow.setName((String.valueOf(fileId)));
//                            jpFileRow.addMouseListener(getMyMouseListener());
//                            
//                            jpFileRow.add(jlFileName);
//                            jPanel.add(jpFileRow);
//                            jFrame.validate();
//                            
//                        } else {
//                            jpFileRow.setName((String.valueOf(fileId)));
//                            jpFileRow.addMouseListener(getMyMouseListener());
//                            jpFileRow.add(jlFileName);
//                            jPanel.add(jpFileRow);
//                            jFrame.validate();
//                        }
//                        
//                        
//
//                        // Add the new file to the array list which holds all our data.
//                        myImages.add(new MyImage(fileId, fileName, fileContentBytes, getFileExtension(fileName)));
//                        fileId++;
//                    }
//                    
//                }
//                
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//		
//		
//	}
//	
//	
//	
//	
//	
//    public static String getFileExtension(String fileName) {
//       
//        // Will have issues with files like myFile.tar.gz.
//        int i = fileName.lastIndexOf('.');
//        
//        if (i > 0) {
//            return fileName.substring(i + 1);
//        } else {
//            return "No extension found.";
//        }
//    }
//    
//    
//    
//    
//    
//    public static MouseListener getMyMouseListener() {
//        return new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//            	
//                // Get the source of the click which is the JPanel.
//                JPanel jPanel = (JPanel) e.getSource();
//                int fileId = Integer.parseInt(jPanel.getName());
//                
//                for (MyImage myFile : myImages) {
//                    if (myFile.getId() == fileId) {
//                        JFrame jfPreview = createFrame(myFile.getName(), myFile.getData(), myFile.getFileExtension());
//                        jfPreview.setVisible(true);
//                    }
//                }
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        };
//    }
//    
//    
//    
//    
//    
//    public static JFrame createFrame(String fileName, byte[] fileData, String fileExtension) {
//
//        
//        JFrame jFrame = new JFrame("WittCode's File Downloader");
//        jFrame.setSize(400, 400);
//
//
//        JPanel jPanel = new JPanel();
//        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
//
//        
//        // Title above panel.
//        JLabel jlTitle = new JLabel("WittCode's File Downloader");
//        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
//        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
//        
//
//        // Label to prompt the user if they are sure they want to download the file.
//        JLabel jlPrompt = new JLabel("Are you sure you want to download " + fileName + "?");
//        jlPrompt.setFont(new Font("Arial", Font.BOLD, 20));
//        jlPrompt.setBorder(new EmptyBorder(20,0,10,0));
//        jlPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);
//        
//
//        // Create the yes for accepting the download.
//        JButton jbYes = new JButton("Yes");
//        jbYes.setPreferredSize(new Dimension(150, 75));
//        jbYes.setFont(new Font("Arial", Font.BOLD, 20));
//
//        
//        // No button for rejecting the download.
//        JButton jbNo = new JButton("No");
//        jbNo.setPreferredSize(new Dimension(150, 75));
//        jbNo.setFont(new Font("Arial", Font.BOLD, 20));
//
//        
//        // Label to hold the content of the file whether it be text of images.
//        JLabel jlFileContent = new JLabel();
//        jlFileContent.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        
//        // Panel to hold the yes and no buttons and make the next to each other left and right.
//        JPanel jpButtons = new JPanel();
//        jpButtons.setBorder(new EmptyBorder(20, 0, 10, 0));
//        
//        
//        // Add the yes and no buttons.
//        jpButtons.add(jbYes);
//        jpButtons.add(jbNo);
//
//        
//        // If the file is a text file then display the text.
//        if (fileExtension.equalsIgnoreCase("txt")) {
//            jlFileContent.setText("<html>" + new String(fileData) + "</html>");
//        } else {
//            jlFileContent.setIcon(new ImageIcon(fileData));
//        }
//        
//
//        // Yes so download file.
//        jbYes.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                File fileToDownload = new File(fileName);
//                
//                try {
//                    FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
//                    
//                    
//                    fileOutputStream.write(fileData);
//                    fileOutputStream.close();
//                    jFrame.dispose();
//                    
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//
//            }
//        });
//
//        // No , close window.
//        jbNo.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                jFrame.dispose();
//            }
//        });
//        
//        
//
//        // Add everything to the panel before adding to the frame.
//        jPanel.add(jlTitle);
//        jPanel.add(jlPrompt);
//        jPanel.add(jlFileContent);
//        jPanel.add(jpButtons);
//
//        // Add panel to the frame.
//        jFrame.add(jPanel);
//
//        // Return the jFrame so it can be passed the right data and then shown.
//        return jFrame;
//
//    }
//
//	
//	
//}






package com.example.server;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class Server {

    static ArrayList<MyImage> myImages = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int fileId = 0;

        JFrame jFrame = new JFrame("Server Image Receiver");
        jFrame.setSize(400, 400);
        jFrame.setLayout(new BorderLayout(10, 10));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jlTitle = new JLabel("Image Receiver", SwingConstants.CENTER);
        jlTitle.setFont(new Font("SansSerif", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20, 0, 10, 0));

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jFrame.add(jlTitle, BorderLayout.NORTH);
        jFrame.add(jScrollPane, BorderLayout.CENTER);
        jFrame.setVisible(true);

        ServerSocket serverSocket = new ServerSocket(1234);

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                int fileNameLength = dataInputStream.readInt();
                if (fileNameLength > 0) {
                    byte[] fileNameBytes = new byte[fileNameLength];
                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
                    String fileName = new String(fileNameBytes);

                    int fileContentLength = dataInputStream.readInt();
                    if (fileContentLength > 0) {
                        byte[] fileContentBytes = new byte[fileContentLength];
                        dataInputStream.readFully(fileContentBytes, 0, fileContentBytes.length);

                        JPanel jpFileRow = new JPanel();
                        jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.X_AXIS));
                        jpFileRow.setName((String.valueOf(fileId)));
                        jpFileRow.addMouseListener(getMyMouseListener());

                        JLabel jlFileName = new JLabel(fileName);
                        jlFileName.setFont(new Font("SansSerif", Font.BOLD, 20));
                        jlFileName.setBorder(new EmptyBorder(10, 0, 10, 0));
                        jpFileRow.add(jlFileName);
                        jPanel.add(jpFileRow);
                        jFrame.validate();

                        myImages.add(new MyImage(fileId, fileName, fileContentBytes, getFileExtension(fileName)));
                        fileId++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i + 1);
        } else {
            return "No extension found.";
        }
    }

    public static MouseListener getMyMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel jPanel = (JPanel) e.getSource();
                int fileId = Integer.parseInt(jPanel.getName());

                for (MyImage myImage : myImages) {
                    if (myImage.getId() == fileId) {
                        JFrame jfPreview = createFrame(myImage.getName(), myImage.getData(), myImage.getFileExtension());
                        jfPreview.setVisible(true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
    }

    public static JFrame createFrame(String fileName, byte[] fileData, String fileExtension) {
        JFrame jFrame = new JFrame("Image Downloader");
        jFrame.setSize(400, 400);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JLabel jlTitle = new JLabel("Image Downloader");
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20, 0, 10, 0));

        JLabel jlPrompt = new JLabel("Are you sure you want to download " + fileName + "?");
        jlPrompt.setFont(new Font("Arial", Font.BOLD, 20));
        jlPrompt.setBorder(new EmptyBorder(20, 0, 10, 0));
        jlPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jbYes = new JButton("Yes");
        jbYes.setPreferredSize(new Dimension(150, 75));
        jbYes.setFont(new Font("Arial", Font.BOLD, 20));

        JButton jbNo = new JButton("No");
        jbNo.setPreferredSize(new Dimension(150, 75));
        jbNo.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel jlFileContent = new JLabel();
        jlFileContent.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel jpButtons = new JPanel();
        jpButtons.setBorder(new EmptyBorder(20, 0, 10, 0));

        jpButtons.add(jbYes);
        jpButtons.add(jbNo);

        jlFileContent.setIcon(new ImageIcon(fileData));

        jbYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fileToDownload = new File(fileName);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                    fileOutputStream.write(fileData);
                    fileOutputStream.close();
                    jFrame.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        jbNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });

        jPanel.add(jlTitle);
        jPanel.add(jlPrompt);
        jPanel.add(jlFileContent);
        jPanel.add(jpButtons);

        jFrame.add(jPanel);

        return jFrame;
    }
}

