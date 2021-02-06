import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by MegaSerg01 on 22.12.2017.
 */
public class GUI {
    public ImageIcon  empty = new ImageIcon(ImageIO.read(new File("Empty_Field.gif")).getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    public static int count_turns = 1;
    public static long time;
    public static int good = 0;
    public  static  int score = 1;
    public static Card currCard1;
    public static Card currCard2;
    public static JFrame frame;
    public static int height = 2;
    public static int width = 3;
    public  static  boolean isused = false;
    public GUI() throws IOException {
    }


    public static void addComponentsToPane(Container pane) throws IOException {
        pane.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        ArrayList<Card> arr = new ArrayList<Card>();
        JLabel sc = new JLabel("Level: " + Integer.toString(score));
        sc.setFont(new Font("", 10, 25));
        c.gridx = width / 2;
        c.gridy = height;
        pane.add(sc, c);
        for(int i = 1; i <=height * width / 2;i++){
            arr.add(new Card(i, "Picture"+Integer.toString(i) +".jpg"));
            arr.add(new Card(i, "Picture"+Integer.toString(i) +".jpg"));
        }
        JButton nextLevel = new JButton("NextLvL");
        long timestart = System.currentTimeMillis();
        long ans = 0;
        nextLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(good == height * width / 2){
                    try {
                        if (height < 3){
                            height++;
                            width++;

                        good = 0;
                        count_turns = 0;
                        GUI.score += 1;

                        GUI.frame.setContentPane(new Container());
                        GUI.addComponentsToPane(GUI.frame.getContentPane());
                        GUI.frame.pack();
                        GUI.frame.setVisible(true);
                        }else{
                            JTextField name = new JTextField("Enter Name");

                            GUI.frame.setContentPane(new Container());
                            GUI.frame.getContentPane().setLayout(new GridBagLayout());
                            GridBagConstraints c = new GridBagConstraints();
                            JLabel win = new JLabel("YOU WIN!!!");
                            win.setFont(new Font("", 10, 30));
                            Container pane = GUI.frame.getContentPane();
                            frame.setSize(500, 700);
                            pane.add(win, c);
                            c.gridy = 1;
                            c.ipadx = 70;

                            pane.add(name, c);
                            int ans = (int)(System.currentTimeMillis() - timestart)/ 1000;
                            final JButton enter = new JButton("enter");
                            enter.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(name.getText().equals("") || isused){
                                        return;
                                    }System.out.println(e.getActionCommand());
                                    isused = true;
                                    User winner = new User(ans, name.getText());
                                    Scanner sc = null;
                                    try {
                                        sc = new Scanner(new File("winners.win"));
                                    } catch (FileNotFoundException e1) {
                                        e1.printStackTrace();
                                    }
                                    ArrayList<User> winners = new ArrayList<>();
                                    for (int i = 0; i < 10 && sc.hasNextLine();i++){
                                        String name = sc.nextLine();
                                        int sco = Integer.parseInt(sc.nextLine());
                                        winners.add(new User(sco, name));

                                    }
                                    winners.add(winner);
                                    winners.sort(new Comparator<User>() {
                                        @Override
                                        public int compare(User o1, User o2) {
                                            return o1.result - o2.result;
                                        }
                                    });
                                    JLabel scor;
                                    for(int i = 0; i < Math.min(winners.size(), 10);i++){
                                        scor = new JLabel((winners.get(i).name +" ") +Integer.toString(winners.get(i).result) +"sec");
                                        c.gridy = GridBagConstraints.RELATIVE;
                                        pane.add(scor, c);
                                    }
                                    GUI.frame.pack();
                                    sc.close();
                                    try {
                                        FileWriter w = new FileWriter("winners.win");
                                        w.flush();
                                        for(int i = 0; i < Math.min(winners.size(), 10);i++){
                                            w.write((winners.get(i).name ));
                                            w.write("\n");
                                            w.write( Integer.toString(winners.get(i).result));
                                            w.write("\n");
                                        }
                                        w.close();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }

                                }
                            });
                            c.gridx = 0;
                            c.gridy = 3;
                            pane.add(enter, c);
                            GUI.frame.pack();

                            GUI.frame.setVisible(true);

                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        c.gridx = 0;
        c.gridy = height;
        pane.add(nextLevel, c);
        Collections.shuffle(arr);
        int totalcount = 0;
        for(int y = 0; y < height;y++){
            for(int x = 0; x < width; x++){
                c.gridx = x;
                c.gridy = y;
                pane.add(arr.get(totalcount), c);
                totalcount++;
            }
        }

    }

    static void createAndShowGUI() throws IOException {
        // РЎРѕР·РґР°РЅРёРµ РѕРєРЅР°
        frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // РЈСЃС‚Р°РЅРѕРІРёС‚СЊ РїР°РЅРµР»СЊ СЃРѕРґРµСЂР¶Р°РЅРёСЏ
        addComponentsToPane(frame.getContentPane());

        // РџРѕРєР°Р·Р°С‚СЊ РѕРєРЅРѕ
        frame.pack();
        frame.setVisible(true);

    }
}