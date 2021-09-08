import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class encrypter {
    public static void main(String[] args) {
        
        // creating frame for the application
        JFrame frame = new JFrame();
        frame.setTitle("Image Encrypter and Decrypter");
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //creating font
        Font font = new Font("Roboto",Font.BOLD, 20);

        //creating button
        JButton button = new JButton();
        button.setText(("Browse Images"));
        button.setFont(font);

        //creating textField
        JTextField textField = new JTextField(10);
        textField.setFont(font);

        //creating actionListener that will trigger on button click
        //adding a lambda function inside listener to implement the functional interface addActionListener which has only one method actionPerformed()
        button.addActionListener(e->{
            String text = textField.getText();
            int key = Integer.parseInt(text);
            operate(key);
        });

        //setting layout of our frame
        frame.setLayout(new FlowLayout());

        //adding button and textField to out frame
        frame.add(button);
        frame.add(textField);
        
        //setting frame visibility to true so its visible
        frame.setVisible(true);
    }

    //this function will be called when the user clicks on the button in the frame
    static void operate(int key) {

        //creating a file chooser so the user can select a img fie from the dialogue box
        JFileChooser chooseFile = new JFileChooser();
        chooseFile.showOpenDialog(null);
        File file = chooseFile.getSelectedFile();

        try {
            //to take the file that user selected
            FileInputStream fis = new FileInputStream(file);
            //creating a byte array to store the bytes of image for further encryption and passing avail so it takes approx no. of bytes that can be read from the InputStream
            byte[] data = new byte[fis.available()];
            fis.read(data);
            int i = 0;
            for(byte b:data){
                System.out.println(b);
                 //XOR operation with the value and key and storing it at respective intex
                /**
                * we are using XOR encryption cuz when we do value^key then the byte value of
                * the img are mismatched so the img is no longer visible but when we XOR the new value with the *key then we get back the earlier byte value of the img so its visible again
                * eg - int defValue = 70 , key = 50
                * now to encrypt int newValue = a^key = 116 
                * to decrypt r^key = 70 = defValue
                */
                data[i] = (byte)(b^key);
                i++;
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            //closing both the files
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Successfull");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
