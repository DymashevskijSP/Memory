import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
public class Card extends JButton {
    int id;
    boolean isAlive = true;
    String filePath;

    public Card(int x, String path) throws IOException {
        this.id = x;
        this.filePath = path;
        Card me = this;
        me.setIcon("Empty_Field.gif");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isAlive){if(GUI.count_turns %3 == 0){
                    if(GUI.currCard1 != null){try {
                        GUI.currCard1.setIcon("Empty_Field.gif");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }}
                    GUI.currCard1 = me;
                    if(GUI.currCard2 != null){try {
                        GUI.currCard2.setIcon("Empty_Field.gif");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    }
                    try {
                        GUI.currCard1.setIcon(me.filePath);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    GUI.currCard2 = null;
                }


                    if(GUI.currCard1 != null){
                        GUI.currCard2 = me;

                        if (me.id == GUI.currCard1.id && !me.equals(GUI.currCard1) ){
                            GUI.count_turns++;
                            GUI.good++;
                            try {
                                me.setIcon(me.filePath);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            if(GUI.currCard1 != null){
                                try {
                                    GUI.currCard1.isAlive = false;
                                    //GUI.currCard1.setIcon("Good.jpg");
                                    GUI.currCard1 = null;
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                            try {
                                me.setIcon(me.filePath);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            if(GUI.currCard2 != null){
                                try {
                                    GUI.currCard2.isAlive = false;
                                    GUI.currCard2.setIcon(GUI.currCard2.filePath);
                                    GUI.currCard2 = null;
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }


                        }else{
                            if(!me.equals(GUI.currCard1)){
                                GUI.currCard2 = me;
                                try {
                                    GUI.currCard2.setIcon(me.filePath);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }else{
                                GUI.count_turns = 1;
                            }
                        }
                    }else{

                        try {
                            me.setIcon(me.filePath);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        GUI.currCard1 = me;
                    }
                if(false){
                    GUI.count_turns = 1;
                    if(GUI.currCard1 != null){
                        try {
                            GUI.currCard1.setIcon("Empty_Field.gif");

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                    GUI.currCard1 = me;
                    try {
                        GUI.currCard1.setIcon(me.filePath);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    if(GUI.currCard2 != null){
                        try {
                            GUI.currCard2.setIcon("Empty_Field.gif");
                            GUI.currCard2 = null;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }


                }
                    GUI.count_turns++;}
                GUI.frame.repaint();
            }

        });
    }

    public void setIcon(String path) throws IOException {
        this.setIcon(new ImageIcon(ImageIO.read(new File(path)).getScaledInstance(100, 100, Image.SCALE_DEFAULT)));


    }
}