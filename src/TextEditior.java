import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.Buffer;

public class TextEditior implements ActionListener {
    // declaring properties of text Editor
    JFrame frame;
    JMenuBar menuBar;
    JTextArea textArea;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close ;


    TextEditior(){
        // initialize frame
        frame=new JFrame();

        // initialize menuBar
        menuBar =new JMenuBar();
        // initialize TextArea
        textArea=new JTextArea();

        //initialize menus
        file=new JMenu("File");
        edit=new JMenu("Edit");

        //initialize FILE Menu Items
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");

        // add action Listener to file menu
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // now adding above menu items to File Menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize EDIT Menu Items
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        //add action Listener to edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //now adding above menu items to Edit Menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        // set menubar to frame
        frame.setJMenuBar(menuBar);
        // creating content pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // adding textArea to panel
        panel.add(textArea, BorderLayout.CENTER);
        // create scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //ads scroll pane to Panel
        panel.add(scrollPane);
        // add panel to frame
        frame.add(panel);
        // set dimention of frame
        frame.setBounds(100, 100,550,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==cut){
            // perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            // perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            // perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            // perform selectAll operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            // perform close operation
           System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            //open a file chooser
            JFileChooser fileChooser =new JFileChooser("C:");
            int chooseOption= fileChooser.showOpenDialog(null);
            // if we clicked open button
             if(chooseOption==JFileChooser.APPROVE_OPTION){
                 // getting  selected file
                 File file=fileChooser.getSelectedFile();
                 // get path of selected file
                 String filePath=file.getPath();

                 try{
                     // initialize file reader
                     FileReader fileReader=new FileReader(filePath);
                     // initialize a buffer which work as a Scanner
                     BufferedReader bufferedReader=new BufferedReader(fileReader);
                     String intermediate="", output="";
                     // read content of file line by line

                     while ((intermediate =bufferedReader.readLine())!=null){
                         output+=intermediate+"\n";
                     }
                     // set output string to text as One
                     textArea.setText(output);
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }

             }

          }

        if(actionEvent.getSource()==saveFile){
            // initialise a file picker
            JFileChooser fileChooser=new JFileChooser("C:");
            // get choose option from fileChooser
            int chooseOption=fileChooser.showSaveDialog(null);
            // check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a new file with chosen directory path and file name
                File file =new File(fileChooser.getSelectedFile().getAbsolutePath()+".text");
                try{
                    // initialize file writer
                    FileWriter fileWriter=new FileWriter(file);
                    // initialize buffer Writer
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    // write content of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();


                }catch(IOException fileNotFountExeption){
                    fileNotFountExeption.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==newFile){
            TextEditior newTextEditor =new TextEditior();

        }

        }

    public static void main(String[] args) {
     TextEditior textEditior=new TextEditior();

    }

}