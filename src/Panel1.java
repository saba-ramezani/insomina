import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class Panel1 extends JPanel {


    static String[] options = {"GET", "DELETE", "POST", "PUT", "PATCH"};
    static Color[] colors1 = {new Color(208, 100, 203), Color.green, Color.orange, Color.cyan, new Color(227, 66, 70)};
    private DefaultListModel<Request> list;
    private JList<Request> requests;
    private JScrollPane jScrollPane;
    private JPanel pnl;
    private JButton createButton;
    private JTextArea nameJTextArea;
    private JComboBox methodJComboBox;

    public Panel1() {
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.DARK_GRAY);
        list = new DefaultListModel<>();
        pnl = new JPanel(new BorderLayout());
        pnl.add(addAddRequestButton(), BorderLayout.NORTH);
        this.add(pnl, BorderLayout.CENTER);
        designRequestsList();
        createButton = new JButton("Create");
        designPanel1();
    }

    public JList<Request> getRequests() {
        return requests;
    }

    public JComboBox getMethodJComboBox() {
        return methodJComboBox;
    }

    public JTextArea getNameJTextArea() {
        return nameJTextArea;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public void designPanel1() {
        JPanel jPanel = new JPanel(new BorderLayout(5, 5));
        jPanel.setBackground(new Color(130, 40, 121));
        JLabel insomnia = new JLabel();
        insomnia.setForeground(Color.WHITE);
        insomnia.setText("   Insomnia");
        insomnia.setVerticalTextPosition(SwingConstants.CENTER);
        insomnia.setHorizontalTextPosition(SwingConstants.LEFT);
        insomnia.setFont(new Font("Arial", 0, 25));
        jPanel.add(insomnia);
        jPanel.setPreferredSize(new Dimension((int) jPanel.getPreferredSize().getWidth(), (int) jPanel.getPreferredSize().getHeight() + 20));
        this.add(jPanel, BorderLayout.NORTH);
    }

    public void addRequest(Request request) {
        list.addElement(request);
        revalidate();
    }

    public JPanel addAddRequestButton() {
        Color color = Color.DARK_GRAY;
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(color);
        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
        p2.setBackground(color);
        p2.setBorder(BorderFactory.createLineBorder(color, 5));
        JPanel icon3 = new JPanel(new BorderLayout());
        icon3.setBorder(BorderFactory.createLineBorder(color, 10));
        icon3.setBackground(color);

        ImageIcon imageIcon = new ImageIcon("plus1.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);

        JButton b3 = new JButton("", imageIcon);
        b3.setBackground(color);
        b3.setBorder(BorderFactory.createLineBorder(color, 0));
        b3.setPreferredSize(new Dimension(20, 20));
        b3.setMaximumSize(new Dimension(20, 20));

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                designNewRequestFrame();
            }
        });

        icon3.add(b3, BorderLayout.CENTER);
        icon3.setPreferredSize(new Dimension(50, 50));
        icon3.setMinimumSize(new Dimension(50, 50));
        icon3.setMaximumSize(new Dimension(50, 50));
        p2.add(icon3);

        p.add(p2, BorderLayout.CENTER);

        p.setPreferredSize(new Dimension(70, 50));
        p.setMinimumSize(new Dimension(70, 50));
        p.setMaximumSize(new Dimension(1500, 50));

        return p;
    }

    public void designNewRequestFrame() {
        Color color = Color.DARK_GRAY;
        JFrame jFrame = new JFrame("New Request");
        jFrame.setBackground(color);
        jFrame.setVisible(true);
        jFrame.setSize(800, 300);
        jFrame.setLayout(new BorderLayout());


        JPanel jPanel = new JPanel();
        jPanel.setBackground(color);
        jPanel.setLayout(new BorderLayout(10, 20));
        jPanel.setBorder(BorderFactory.createLineBorder(color, 12));
        methodJComboBox = new JComboBox(options);
        methodJComboBox.setRenderer(new MyRenderer());
        methodJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                methodJComboBox.setForeground(colors1[methodJComboBox.getSelectedIndex()]);
            }
        });

        methodJComboBox.setBackground(new Color(60, 60, 60));
        methodJComboBox.setPreferredSize(new Dimension(115, (int) methodJComboBox.getPreferredSize().getHeight()));
        jPanel.add(methodJComboBox, BorderLayout.WEST);
        JPanel jPanel1 = new JPanel(new BorderLayout());
        jPanel1.setBackground(color);
        jPanel1.setBorder(BorderFactory.createLineBorder(color, 6));
        nameJTextArea = new JTextArea("Enter name of your request here");
        nameJTextArea.setForeground(new Color(140, 140, 140));
        nameJTextArea.setAlignmentY(SwingConstants.CENTER);
        nameJTextArea.setPreferredSize(new Dimension((int) nameJTextArea.getPreferredSize().getWidth() + 250, (int) nameJTextArea.getPreferredSize().getHeight() + 5));
        nameJTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameJTextArea.getText().equals("Enter name of your request here")) {
                    nameJTextArea.setText("");
                    nameJTextArea.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameJTextArea.getText().equals("")) {
                    nameJTextArea.setText("Enter name of your request here");
                    nameJTextArea.setForeground(new Color(140, 140, 140));
            }
            }
        });

        jPanel1.add(nameJTextArea, BorderLayout.CENTER);
        jPanel.add(jPanel1, BorderLayout.CENTER);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });
        createButton.setBackground(new Color(60, 60, 60));
        jPanel.add(createButton, BorderLayout.EAST);
        jPanel.setBorder(BorderFactory.createMatteBorder(110, 10, 110, 10, color));
        jFrame.add(jPanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void designRequestsList(){
        requests = new JList<>(list);
        requests.setBackground(Color.DARK_GRAY);
        requests.setCellRenderer(new RequestRenderer());
        jScrollPane = new JScrollPane(requests);
        pnl.add(jScrollPane, BorderLayout.CENTER);
    }

    /**
     * The type My renderer creates a combo box with items in different colors.
     */
    private class MyRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (index > -1) {
                lbl.setForeground(colors1[index]);
            }
            return lbl;
        }
    }

    /**
     * The type request rendered creates a j list with custom j panel items.
     */
    private class RequestRenderer extends JPanel implements ListCellRenderer<Request> {


        @Override
        public Component getListCellRendererComponent(JList<? extends Request> list, Request request, int index,
                                                      boolean isSelected, boolean cellHasFocus) {

            Color color = Color.DARK_GRAY;
            Color color1 = Color.LIGHT_GRAY;
            String method = request.getMethod();
            String name = request.getName();
            setLayout(new BorderLayout());
            setBackground(color);
            JPanel p2 = new JPanel();
            p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
            p2.setBackground(color);
            p2.setBorder(BorderFactory.createLineBorder(color, 5));

            JPanel text1 = new JPanel(new BorderLayout());
            text1.setBorder(BorderFactory.createLineBorder(color, 5));
            text1.setBackground(color);
            JPanel text2 = new JPanel(new BorderLayout());
            text2.setBorder(BorderFactory.createLineBorder(color, 5));
            text2.setBackground(color);


            JLabel t1 = new JLabel(method);
            if (method.equals("GET")) {
                t1.setForeground(new Color(208, 100, 203));
            } else if (method.equals("DELETE")) {
                t1.setForeground(Color.green);
            } else if (method.equals("POST")) {
                t1.setForeground(Color.orange);
            } else if (method.equals("PUT")) {
                t1.setForeground(Color.cyan);
            } else if (method.equals("PATCH")) {
                t1.setForeground(new Color(227, 66, 70));
            }

            t1.setBackground(color);

            t1.setPreferredSize(new Dimension(20, 25));
            t1.setMaximumSize(new Dimension(2000, 25));
            t1.setMinimumSize(new Dimension(30, 25));


            JLabel t2 = new JLabel(name);
            t2.setForeground(color1);
            t2.setBackground(color);

            t2.setPreferredSize(new Dimension(30, 25));
            t2.setMaximumSize(new Dimension(2000, 25));
            t2.setMinimumSize(new Dimension(30, 25));


            text1.add(t1, BorderLayout.CENTER);
            text1.setPreferredSize(new Dimension(20, 30));
            text1.setMaximumSize(new Dimension(2000, 30));
            text1.setMinimumSize(new Dimension(30, 30));
            p2.add(text1);


            text2.add(t2, BorderLayout.CENTER);
            text2.setPreferredSize(new Dimension(30, 30));
            text2.setMaximumSize(new Dimension(2000, 30));
            text2.setMinimumSize(new Dimension(30, 30));
            p2.add(text2);


            setPreferredSize(new Dimension(70, 30));
            setMinimumSize(new Dimension(70, 30));
            setMaximumSize(new Dimension(1500, 30));


            if (isSelected) {
                t1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                t2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            } else {
                setBackground(color);
                t1.setBackground(color);
                t2.setBackground(color);
                text1.setBackground(color);
                text2.setBackground(color);
                p2.setBackground(color);
                t2.setForeground(color1);
            }

            setOpaque(true);

            return p2;
        }
    }
}
