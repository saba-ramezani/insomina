import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Panel 2 generates and design the middle panel and its tabs in insomnia window.
 *
 * @ author saba
 */
public class Panel2 extends JPanel {

    private JTextField urlTextField ;
    private JTextArea jsonContent ;
    private JButton save ;
    private JTextField binaryFilePath ;
    private JLabel urlPreview ;
    static String[] options = {"GET" , "DELETE" , "POST" , "PUT" , "PATCH"} ;
    static Color[] colors1 = {new Color(208,100,203) , Color.green , Color.orange , Color.cyan, new Color(227,66,70)} ;
    //the header panel
    private JPanel headerTab ;
    //the body tab panel
    private JPanel bodyTab ;
    //the query tab panel
    private JPanel QueryTab ;
    //the form data tab panel
    private JPanel formDataTab ;
    //the json tab panel
    private JPanel jsonTab ;
    //the binary file tab panel
    private JPanel binaryFileTab ;
    //the main header panel
    private JPanel hp ;
    //the main form data panel
    private JPanel fp ;
    //the main query panel
    private JPanel qp ;
    //the new header and value panel in header tab
    private JPanel newHeaderAndValue ;
    //the new name and value panel in form data tab
    private JPanel newNameAndValueForFormData ;
    //the main new name and value panel in query tab
    private JPanel newNameAndValueForQuery ;
    //the list of names and values in form data tab
    private ArrayList<JPanel> formDataNames ;
    private HashMap<JTextArea , JTextArea> formDataText ;
    private HashMap<JTextArea , JTextArea> queryTexts ;
    private HashMap<JTextArea , JTextArea> headerText ;
    //the list of names and values in query tab
    private ArrayList<JPanel> queryNames ;
    //the name of headers and values in headers tab
    private ArrayList<JPanel> headers ;
    private JComboBox jComboBox ;
    private JTextArea urlTextArea ;
    private JButton send ;
    private JComboBox body ;


    public Panel2(){
        formDataText = new HashMap<>() ;
        queryTexts = new HashMap<>() ;
        headerText = new HashMap<>() ;
        this.setBackground(Color.DARK_GRAY);
        newHeaderAndValue = getANewHeaderAndValue() ;
        newNameAndValueForFormData = getANewNameAndValueForFormData() ;
        newNameAndValueForQuery = getANewNameAndValueForQuery() ;
        designPanel2();

    }

    public JComboBox getBody() {
        return body;
    }

    public JButton getSend() {
        return send;
    }

    public JTextField getUrlTextField() {
        return urlTextField;
    }

    public HashMap<JTextArea, JTextArea> getQueryTexts() {
        return queryTexts;
    }

    public JTextArea getJsonContent() {
        return jsonContent;
    }

    public JTextField getBinaryFilePath() {
        return binaryFilePath;
    }

    public HashMap<JTextArea, JTextArea> getHeaderText() {
        return headerText;
    }

    public HashMap<JTextArea, JTextArea> getFormDataText() {
        return formDataText;
    }

    public JButton getSave() {
        return save;
    }

    public JComboBox getjComboBox() {
        return jComboBox;
    }

    public JTextArea getUrlTextArea() {
        return urlTextArea;
    }

    /**
     * Design panel 2.
     */
    public void designPanel2(){
        Color color = Color.DARK_GRAY ;
        this.setLayout(new BorderLayout(5, 5));
        JPanel jPanel = new JPanel() ;
        jPanel.setBackground(color);
        jPanel.setLayout(new BorderLayout(10 , 20));
        jPanel.setBorder(BorderFactory.createLineBorder(color , 4));
        jComboBox = new JComboBox(options) ;
        jComboBox.setRenderer(new MyRenderer());
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jComboBox.getSelectedIndex() == 0){
                    jComboBox.setForeground(colors1[0]);
                }
                else if (jComboBox.getSelectedIndex() == 1){
                    jComboBox.setForeground(colors1[1]);
                }
                else if (jComboBox.getSelectedIndex() == 2){
                    jComboBox.setForeground(colors1[2]);
                }
                else if (jComboBox.getSelectedIndex() == 3){
                    jComboBox.setForeground(colors1[3]);
                }
                else if (jComboBox.getSelectedIndex() == 4){
                    jComboBox.setForeground(colors1[4]);
                }
            }
        });

        jComboBox.setBackground(new Color(60,60,60));
        jComboBox.setPreferredSize(new Dimension(95 , (int) jComboBox.getPreferredSize().getHeight()));
        jPanel.add(jComboBox , BorderLayout.WEST) ;
        JPanel jPanel1 = new JPanel(new BorderLayout()) ;
        jPanel1.setBackground(color);
        jPanel1.setBorder(BorderFactory.createLineBorder(color, 6));
        urlTextArea = new JTextArea("Enter URL here") ;
        urlTextArea.setForeground(new Color(140, 140, 140));
        urlTextArea.setAlignmentY(SwingConstants.CENTER);
        urlTextArea.setPreferredSize(new Dimension((int)urlTextArea.getPreferredSize().getWidth()+280 , (int)urlTextArea.getPreferredSize().getHeight() + 5));

        jPanel1.add(urlTextArea, BorderLayout.CENTER) ;

        jPanel.add(jPanel1, BorderLayout.CENTER) ;
        JPanel pp = new JPanel() ;
        pp.setLayout(new GridLayout(2,1));
        save = new JButton("Save") ;
        send = new JButton("Send") ;
        save.setBackground(new Color(60, 60, 60));
        send.setBackground(new Color(60, 60, 60));
        pp.add(send);
        pp.add(save);
        jPanel.add(pp , BorderLayout.EAST) ;
        this.add(jPanel , BorderLayout.NORTH) ;
        JTabbedPane jTabbedPane = new JTabbedPane() ;
        jTabbedPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        jTabbedPane.setBackground(Color.DARK_GRAY);
        jTabbedPane.setForeground(Color.LIGHT_GRAY);

        body = new JComboBox() ;
        body.addItem("Form Data");
        body.addItem("JSON");
        body.addItem("Binary File");
        body.addItem("No Body");
        body.setSelectedIndex(3);
        designBody();

        QueryTab = new JPanel(new BorderLayout()) ;
        QueryTab.setBackground(color);
        headerTab = new JPanel() ;
        headerTab.setBackground(color);
        designBody();
        designHeader();
        designQuery();

        jTabbedPane.addTab("Body" ,bodyTab );
        jTabbedPane.setTabComponentAt(0 , body);
        jTabbedPane.addTab("Query" , QueryTab);

        designFormData();
        designJason();
        designBinaryFile();

        body.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (body.getSelectedIndex() == 0){
                    jTabbedPane.setSelectedIndex(0);
                    jTabbedPane.setComponentAt(0 , formDataTab);
                    updateUI();
                }
                else if (body.getSelectedIndex() == 1){
                    jTabbedPane.setSelectedIndex(0);
                    jTabbedPane.setComponentAt(0 , jsonTab);
                    updateUI();
                }
                else if (body.getSelectedIndex() == 2){
                    jTabbedPane.setSelectedIndex(0);
                    jTabbedPane.setComponentAt(0 , binaryFileTab);
                    updateUI();
                }
                else if (body.getSelectedIndex() == 3){
                    jTabbedPane.setSelectedIndex(0);
                    jTabbedPane.setComponentAt(0 , bodyTab);
                    updateUI();
                }
            }
        });


        headerTab = new JPanel(new BorderLayout()) ;
        headerTab.setBackground(color);
        designHeader();
        jTabbedPane.addTab("Header" , headerTab);
        this.add(jTabbedPane , BorderLayout.CENTER) ;
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    /**
     * Design the panel of header tab.
     */
    public void designHeader(){
        Color color = Color.DARK_GRAY ;
        hp = new JPanel() ;
        hp.setBackground(color);
        hp.setLayout(new BoxLayout(hp , BoxLayout.Y_AXIS)); ;
        hp.setAlignmentY(50);
        hp.setAlignmentX(50);
        JScrollPane jScrollPane = new JScrollPane(hp) ;

        headers = new ArrayList<>() ;
        headers.add(newHeaderAndValue) ;
        for (JPanel tmp : headers){
            hp.add(tmp);
        }
        headerTab.add(jScrollPane , BorderLayout.CENTER);
    }

    /**
     * Get a header and value j panel.
     *
     * @return the j panel
     */
    public JPanel getAHeaderAndValue(String key , String value){
        Color color = Color.DARK_GRAY ;
        JPanel p = new JPanel(new BorderLayout()) ;
        p.setBackground(color);
        JPanel p2 = new JPanel() ;
        p2.setLayout(new BoxLayout(p2 , BoxLayout.X_AXIS));
        p2.setBackground(color);
        p2.setBorder(BorderFactory.createLineBorder(color , 5));
        JPanel icon1 = new JPanel(new BorderLayout()) ;
        icon1.setBorder(BorderFactory.createLineBorder(color, 10));
        icon1.setBackground(color);
        JPanel icon2 = new JPanel(new BorderLayout()) ;
        icon2.setBorder(BorderFactory.createLineBorder(color , 10));
        icon2.setBackground(color);
        JPanel icon3 = new JPanel(new BorderLayout()) ;
        icon3.setBorder(BorderFactory.createLineBorder(color , 10));
        icon3.setBackground(color);
        JPanel text1 = new JPanel(new BorderLayout()) ;
        text1.setBorder(BorderFactory.createLineBorder(color , 10));
        text1.setBackground(color);
        JPanel text2 = new JPanel(new BorderLayout()) ;
        text2.setBorder(BorderFactory.createLineBorder(color , 10));
        text2.setBackground(color);

        ImageIcon imageIcon = new ImageIcon("delete.png") ;
        Image image = imageIcon.getImage() ;
        Image newImage = image.getScaledInstance(15,15,Image.SCALE_SMOOTH) ;
        imageIcon = new ImageIcon(newImage) ;

        ImageIcon imageIcon1 = new ImageIcon("menu.png") ;
        Image image1 = imageIcon1.getImage() ;
        Image newImage1 = image1.getScaledInstance(10,10,Image.SCALE_SMOOTH) ;
        imageIcon1 = new ImageIcon(newImage1) ;

        JTextArea ht1 = new JTextArea(key) ;
        if (key.equals("")) {
            ht1.setForeground(new Color(100, 100, 100));
        } else {
            ht1.setForeground(Color.WHITE);
        }
        ht1.setLineWrap(true);
        ht1.setBackground(color);
        ht1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        ht1.setPreferredSize(new Dimension(200,20));
        ht1.setMaximumSize(new Dimension(2000,20));
        ht1.setMinimumSize(new Dimension(50,20));
        ht1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ht1.getText().equals("Header")){
                    ht1.setText("");
                    ht1.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ht1.getText().equals("")){
                    ht1.setText("Header");
                    ht1.setForeground(new Color(100, 100, 100));
                }
            }
        });


        JTextArea ht2 = new JTextArea(value) ;
        if (value.equals("")) {
            ht2.setForeground(new Color(100, 100, 100));
        } else {
            ht2.setForeground(Color.WHITE);
        }
        ht2.setBackground(color);
        ht2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        ht2.setPreferredSize(new Dimension(200,20));
        ht2.setMaximumSize(new Dimension(2000,20));
        ht2.setMinimumSize(new Dimension(50,20));
        ht2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ht2.getText().equals("Value")){
                    ht2.setText("");
                    ht2.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ht2.getText().equals("")){
                    ht2.setText("Value");
                    ht2.setForeground(new Color(100, 100, 100));
                }
            }
        });



        JLabel b1 = new JLabel(imageIcon1 , JLabel.CENTER) ;
        b1.setBackground(color);
        b1.setBorder(BorderFactory.createLineBorder(color,0));
        b1.setPreferredSize(new Dimension(10 , 30));
        b1.setMaximumSize(new Dimension(10 , 10));
        b1.setMinimumSize(new Dimension(10,10));


        JCheckBox b2 = new JCheckBox("") ;
        b2.setSelected(true);
        b2.setBackground(color);
        b2.setPreferredSize(new Dimension(10 , 10));
        b2.setMaximumSize(new Dimension(10 , 10));
        b2.setMinimumSize(new Dimension(10,10));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (b2.isSelected()){
                    ht1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
                    ht2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
                    if (ht1.getText().equals("Header")){
                        ht1.setForeground(new Color(100, 100, 100));
                    }
                    else {
                        ht1.setForeground(Color.WHITE);
                    }
                    if (ht2.getText().equals("Value")){
                        ht2.setForeground(new Color(100, 100, 100));
                    }
                    else{
                        ht2.setForeground(Color.WHITE);
                    }
                }
                else {
                    ht1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(100, 100, 100)));
                    ht2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(100, 100, 100)));
                    ht1.setForeground(new Color(100 , 100 , 100));
                    ht2.setForeground(new Color(100 , 100 , 100));
                }
            }
        });


        JButton b3 = new JButton("" , imageIcon) ;
        b3.setBackground(color);
        b3.setBorder(BorderFactory.createLineBorder(color,0));
        b3.setPreferredSize(new Dimension(20 , 20));
        b3.setMaximumSize(new Dimension(20 , 20));

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b3){
                    p.setVisible(false);
                    headers.remove(p) ;
                }
            }
        });


        icon1.add(b1 , BorderLayout.CENTER) ;
        icon1.setPreferredSize(new Dimension(50,40));
        icon1.setMinimumSize(new Dimension(50,40));
        icon1.setMaximumSize(new Dimension(50,40));
        p2.add(icon1);

        text1.add(ht1, BorderLayout.CENTER);
        text1.setPreferredSize(new Dimension(200,40));
        text1.setMaximumSize(new Dimension(2000 , 40));
        text1.setMinimumSize(new Dimension(50 , 40));
        p2.add(text1);


        text2.add(ht2 , BorderLayout.CENTER);
        text2.setPreferredSize(new Dimension(200,40));
        text2.setMaximumSize(new Dimension(2000 , 40));
        text2.setMinimumSize(new Dimension(50 , 40));
        p2.add(text2);


        icon2.add(b2 , BorderLayout.CENTER) ;
        icon2.setPreferredSize(new Dimension(50,40));
        icon2.setMinimumSize(new Dimension(50,40));
        icon2.setMaximumSize(new Dimension(50,40));
        p2.add(icon2);


        icon3.add(b3 , BorderLayout.CENTER) ;
        icon3.setPreferredSize(new Dimension(50,40));
        icon3.setMinimumSize(new Dimension(50,40));
        icon3.setMaximumSize(new Dimension(50,40));
        p2.add(icon3);

        p.add(p2 , BorderLayout.CENTER);

        p.setPreferredSize(new Dimension(500 , 50));
        p.setMinimumSize(new Dimension(100 , 50));
        p.setMaximumSize(new Dimension(1500 , 50));

        headerText.put(ht1 , ht2) ;

        return p ;
    }


    /**
     * Get a new header and value j panel.
     *
     * @return the j panel
     */
    public JPanel getANewHeaderAndValue(){
        Color color = Color.DARK_GRAY ;
        Color color1 = new Color(100,100,100) ;
        Color color2 = Color.WHITE ;
        JPanel p = new JPanel(new BorderLayout()) ;
        p.setBackground(color);
        JPanel p2 = new JPanel() ;
        p2.setLayout(new BoxLayout(p2 , BoxLayout.X_AXIS));
        p2.setBackground(color);
        p2.setBorder(BorderFactory.createLineBorder(color , 5));
        JPanel icon1 = new JPanel(new BorderLayout()) ;
        icon1.setBorder(BorderFactory.createLineBorder(color , 10));
        icon1.setBackground(color);
        JPanel icon2 = new JPanel(new BorderLayout()) ;
        icon2.setBorder(BorderFactory.createLineBorder(color , 10));
        icon2.setBackground(color);
        JPanel icon3 = new JPanel(new BorderLayout()) ;
        icon3.setBorder(BorderFactory.createLineBorder(color , 10));
        icon3.setBackground(color);
        JPanel text1 = new JPanel(new BorderLayout()) ;
        text1.setBorder(BorderFactory.createLineBorder(color , 10));
        text1.setBackground(color);
        JPanel text2 = new JPanel(new BorderLayout()) ;
        text2.setBorder(BorderFactory.createLineBorder(color , 10));
        text2.setBackground(color);

        ImageIcon imageIcon = new ImageIcon("setting.png") ;
        Image image = imageIcon.getImage() ;
        Image newImage = image.getScaledInstance(15,15,Image.SCALE_SMOOTH) ;
        imageIcon = new ImageIcon(newImage) ;


        JTextArea t1 = new JTextArea("New Header") ;
        //t1.setEditable(false);
        t1.setForeground(color1);
        t1.setBackground(color);
        t1.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
        t1.setPreferredSize(new Dimension(200,20));
        t1.setMaximumSize(new Dimension(2000,20));
        t1.setMinimumSize(new Dimension(50,20));
        t1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                headers.remove(newHeaderAndValue) ;
                headers.add(getAHeaderAndValue("Header" , "Value")) ;
                headers.add(newHeaderAndValue) ;
                hp.removeAll();
                for (JPanel tmp : headers){
                    hp.add(tmp) ;
                }

                updateUI();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



        JTextArea t2 = new JTextArea("New Value") ;
        //t2.setEditable(false);
        t2.setForeground(color1);
        t2.setBackground(color);
        t2.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
        t2.setPreferredSize(new Dimension(200,20));
        t2.setMaximumSize(new Dimension(2000,20));
        t2.setMinimumSize(new Dimension(50,20));
        t2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                headers.remove(newHeaderAndValue) ;
                headers.add(getAHeaderAndValue("Header" , "Value")) ;
                headers.add(newHeaderAndValue) ;
                hp.removeAll();
                for (JPanel tmp : headers){
                    hp.add(tmp) ;
                }
                updateUI();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



        JLabel b1 = new JLabel(imageIcon , JLabel.CENTER) ;
        b1.setBackground(color);
        b1.setBorder(BorderFactory.createLineBorder(color,0));
        //b1.setEnabled(false);
        b1.setPreferredSize(new Dimension(10 , 30));
        b1.setMaximumSize(new Dimension(10 , 10));
        b1.setMinimumSize(new Dimension(10,10));


        JPopupMenu popupMenu = new JPopupMenu() ;
        JMenuItem menuItem = new JMenuItem("Delete all") ;
        popupMenu.add(menuItem) ;
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == menuItem){
                    headers = new ArrayList<JPanel>() ;
                    headers.add(newHeaderAndValue) ;
                    hp.removeAll();
                    hp.add(headers.get(0));
                    updateUI();
                }
            }
        });
        //b1.add(popupMenu);
        b1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getComponent() == b1) {
                    popupMenu.show(e.getComponent() , e.getX() , e.getY());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



        icon1.add(b1 , BorderLayout.CENTER) ;
        icon1.setPreferredSize(new Dimension(50,40));
        icon1.setMinimumSize(new Dimension(50,40));
        icon1.setMaximumSize(new Dimension(50,40));
        p2.add(icon1);

        text1.add(t1, BorderLayout.CENTER);
        text1.setPreferredSize(new Dimension(200,40));
        text1.setMaximumSize(new Dimension(2000 , 40));
        text1.setMinimumSize(new Dimension(50 , 40));
        p2.add(text1);


        text2.add(t2 , BorderLayout.CENTER);
        text2.setPreferredSize(new Dimension(200,40));
        text2.setMaximumSize(new Dimension(2000 , 40));
        text2.setMinimumSize(new Dimension(50 , 40));
        p2.add(text2);



        p2.add(icon3);
        //icon3.add(b3 , BorderLayout.CENTER) ;
        icon3.setPreferredSize(new Dimension(100,40));
        icon3.setMinimumSize(new Dimension(50,40));
        icon3.setMaximumSize(new Dimension(50,40));

        p.add(p2 , BorderLayout.CENTER);

        p.setPreferredSize(new Dimension(500 , 50));
        p.setMinimumSize(new Dimension(100 , 50));
        p.setMaximumSize(new Dimension(1500 , 50));

        return p ;
    }

    public void addAHeader(HashMap<String , String> hashMap) {
        headers.clear();
        for (String temp : hashMap.keySet()) {
            headers.add(getAHeaderAndValue(temp , hashMap.get(temp))) ;
        }
        headers.add(newHeaderAndValue) ;
        hp.removeAll();
        for (JPanel tmp : headers){
            hp.add(tmp) ;
        }
        updateUI();
    }

    public void addAFormData(HashMap<String , String> hashMap) {
        formDataNames.clear();
        for (String temp : hashMap.keySet()) {
            if (temp.equals("file")) {
                continue;
            }
            formDataNames.add(getANameAndValueForFormData(temp , hashMap.get(temp))) ;
        }
        formDataNames.add(newNameAndValueForFormData) ;
        fp.removeAll();
        for (JPanel tmp : formDataNames){
            fp.add(tmp) ;
        }
        updateUI();
    }

    public void addAQuery(HashMap<String , String> hashMap) {
        queryNames.clear();
        for (String temp : hashMap.keySet()) {
            queryNames.add(getANameAndValueForQuery(temp , hashMap.get(temp))) ;
        }
        queryNames.add(newNameAndValueForQuery) ;
        qp.removeAll();
        for (JPanel tmp : queryNames){
            qp.add(tmp) ;
        }
        updateUI();
    }

    /**
     * Get a name and value for form data j panel.
     *
     * @return the j panel
     */
    public JPanel getANameAndValueForFormData(String key , String value){
        Color color = Color.DARK_GRAY ;
        Color color1 = new Color(100,100,100) ;
        Color color2 = Color.WHITE ;
        JPanel p = new JPanel(new BorderLayout()) ;
        //p.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY , 5));
        p.setBackground(color);
        JPanel p2 = new JPanel() ;
        p2.setLayout(new BoxLayout(p2 , BoxLayout.X_AXIS));
        p2.setBackground(color);
        p2.setBorder(BorderFactory.createLineBorder(color , 5));
        JPanel icon1 = new JPanel(new BorderLayout()) ;
        icon1.setBorder(BorderFactory.createLineBorder(color, 10));
        icon1.setBackground(color);
        JPanel icon2 = new JPanel(new BorderLayout()) ;
        icon2.setBorder(BorderFactory.createLineBorder(color , 10));
        icon2.setBackground(color);
        JPanel icon3 = new JPanel(new BorderLayout()) ;
        icon3.setBorder(BorderFactory.createLineBorder(color , 10));
        icon3.setBackground(color);
        JPanel text1 = new JPanel(new BorderLayout()) ;
        text1.setBorder(BorderFactory.createLineBorder(color, 10));
        text1.setBackground(color);
        JPanel text2 = new JPanel(new BorderLayout()) ;
        text2.setBorder(BorderFactory.createLineBorder(color , 10));
        text2.setBackground(color);

        ImageIcon imageIcon = new ImageIcon("delete.png") ;
        Image image = imageIcon.getImage() ;
        Image newImage = image.getScaledInstance(15,15,Image.SCALE_SMOOTH) ;
        imageIcon = new ImageIcon(newImage) ;

        ImageIcon imageIcon1 = new ImageIcon("menu.png") ;
        Image image1 = imageIcon1.getImage() ;
        Image newImage1 = image1.getScaledInstance(10,10,Image.SCALE_SMOOTH) ;
        imageIcon1 = new ImageIcon(newImage1) ;

        JTextArea t1 = new JTextArea(key) ;
        t1.setForeground(color1);
        t1.setBackground(color);
        t1.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
        t1.setPreferredSize(new Dimension(200,20));
        t1.setMaximumSize(new Dimension(2000,20));
        t1.setMinimumSize(new Dimension(50,20));
        t1.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                Color color2 = Color.WHITE ;
                if (t1.getText().equals("Name")){
                    t1.setText("");
                    t1.setForeground(color2);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                Color color1 = new Color(100,100,100) ;
                if (t1.getText().equals("")){
                    t1.setText("Name");
                    t1.setForeground(color1);
                }
            }
        });


        JTextArea t2 = new JTextArea(value) ;
        t2.setForeground(color1);
        t2.setBackground(color);
        t2.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
        t2.setPreferredSize(new Dimension(200,20));
        t2.setMaximumSize(new Dimension(2000,20));
        t2.setMinimumSize(new Dimension(50,20));
        t2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Color color2 = Color.WHITE ;
                if (t2.getText().equals("Value")){
                    t2.setText("");
                    t2.setForeground(color2);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                Color color1 = new Color(100,100,100) ;
                if (t2.getText().equals("")){
                    t2.setText("Value");
                    t2.setForeground(color1);
                }
            }
        });



        JLabel b1 = new JLabel(imageIcon1 , JLabel.CENTER) ;
        b1.setBackground(color);
        b1.setBorder(BorderFactory.createLineBorder(color,0));
        b1.setPreferredSize(new Dimension(10 , 30));
        b1.setMaximumSize(new Dimension(10 , 10));
        b1.setMinimumSize(new Dimension(10,10));



        JCheckBox b2 = new JCheckBox("") ;
        b2.setSelected(true);
        b2.setBackground(color);
        b2.setPreferredSize(new Dimension(10 , 10));
        b2.setMaximumSize(new Dimension(10 , 10));
        b2.setMinimumSize(new Dimension(10,10));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color1 = new Color(100,100,100) ;
                Color color2 = Color.WHITE ;
                if (b2.isSelected()){
                    t1.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
                    t2.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
                    if (t1.getText().equals("Name")){
                        t1.setForeground(color1);
                    }
                    else {
                        t1.setForeground(color2);
                    }
                    if (t2.getText().equals("Value")){
                        t2.setForeground(color1);
                    }
                    else{
                        t2.setForeground(color2);
                    }
                }
                else {
                    t1.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color1));
                    t2.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color1));
                    t1.setForeground(color1);
                    t2.setForeground(color1);
                }
            }
        });


        JButton b3 = new JButton("" , imageIcon) ;
        b3.setBackground(color);
        b3.setBorder(BorderFactory.createLineBorder(color,0));
        b3.setPreferredSize(new Dimension(20 , 20));
        b3.setMaximumSize(new Dimension(20 , 20));

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b3){
                    p.setVisible(false);
                    formDataNames.remove(p) ;
                }
            }
        });


        icon1.add(b1 , BorderLayout.CENTER) ;
        icon1.setPreferredSize(new Dimension(50,40));
        icon1.setMinimumSize(new Dimension(50,40));
        icon1.setMaximumSize(new Dimension(50,40));
        p2.add(icon1);

        text1.add(t1, BorderLayout.CENTER);
        text1.setPreferredSize(new Dimension(200,40));
        text1.setMaximumSize(new Dimension(2000 , 40));
        text1.setMinimumSize(new Dimension(50 , 40));
        p2.add(text1);


        text2.add(t2 , BorderLayout.CENTER);
        text2.setPreferredSize(new Dimension(200,40));
        text2.setMaximumSize(new Dimension(2000 , 40));
        text2.setMinimumSize(new Dimension(50 , 40));
        p2.add(text2);


        icon2.add(b2 , BorderLayout.CENTER) ;
        icon2.setPreferredSize(new Dimension(50,40));
        icon2.setMinimumSize(new Dimension(50,40));
        icon2.setMaximumSize(new Dimension(50,40));
        p2.add(icon2);


        icon3.add(b3 , BorderLayout.CENTER) ;
        icon3.setPreferredSize(new Dimension(50,40));
        icon3.setMinimumSize(new Dimension(50,40));
        icon3.setMaximumSize(new Dimension(50,40));
        p2.add(icon3);

        p.add(p2 , BorderLayout.CENTER);

        p.setPreferredSize(new Dimension(500 , 50));
        p.setMinimumSize(new Dimension(100 , 50));
        p.setMaximumSize(new Dimension(1500 , 50));

        formDataText.put(t1 , t2) ;

        return p ;
    }

    /**
     * Get a new name and value for form data j panel.
     *
     * @return the j panel
     */
    public JPanel getANewNameAndValueForFormData(){
        Color color = Color.DARK_GRAY ;
        Color color1 = new Color(100,100,100) ;
        Color color2 = Color.WHITE ;
        JPanel p = new JPanel(new BorderLayout()) ;
        p.setBackground(color);
        JPanel p2 = new JPanel() ;
        p2.setLayout(new BoxLayout(p2 , BoxLayout.X_AXIS));
        p2.setBackground(color);
        p2.setBorder(BorderFactory.createLineBorder(color , 5));
        JPanel icon1 = new JPanel(new BorderLayout()) ;
        icon1.setBorder(BorderFactory.createLineBorder(color, 10));
        icon1.setBackground(color);
        JPanel icon2 = new JPanel(new BorderLayout()) ;
        icon2.setBorder(BorderFactory.createLineBorder(color , 10));
        icon2.setBackground(color);
        JPanel icon3 = new JPanel(new BorderLayout()) ;
        icon3.setBorder(BorderFactory.createLineBorder(color , 10));
        icon3.setBackground(color);
        JPanel text1 = new JPanel(new BorderLayout()) ;
        text1.setBorder(BorderFactory.createLineBorder(color , 10));
        text1.setBackground(color);
        JPanel text2 = new JPanel(new BorderLayout()) ;
        text2.setBorder(BorderFactory.createLineBorder(color , 10));
        text2.setBackground(color);

        ImageIcon imageIcon = new ImageIcon("setting.png") ;
        Image image = imageIcon.getImage() ;
        Image newImage = image.getScaledInstance(15,15,Image.SCALE_SMOOTH) ;
        imageIcon = new ImageIcon(newImage) ;


        JTextArea t1 = new JTextArea("New Name") ;
        //t1.setEditable(false);
        t1.setForeground(color1);
        t1.setBackground(color);
        t1.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
        t1.setPreferredSize(new Dimension(200,20));
        t1.setMaximumSize(new Dimension(2000,20));
        t1.setMinimumSize(new Dimension(50,20));
        t1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                formDataNames.remove(newNameAndValueForFormData) ;
                formDataNames.add(getANameAndValueForFormData("Name" , "Value")) ;
                formDataNames.add(newNameAndValueForFormData) ;
                fp.removeAll();
                for (JPanel tmp : formDataNames){
                    fp.add(tmp) ;
                }

                updateUI();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



        JTextArea t2 = new JTextArea("New Value") ;
        t2.setForeground(color1);
        t2.setBackground(color);
        t2.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
        t2.setPreferredSize(new Dimension(200,20));
        t2.setMaximumSize(new Dimension(2000,20));
        t2.setMinimumSize(new Dimension(50,20));
        t2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                formDataNames.remove(newNameAndValueForFormData) ;
                formDataNames.add(getANameAndValueForFormData("Name" , "Value")) ;
                formDataNames.add(newNameAndValueForFormData) ;
                fp.removeAll();
                for (JPanel tmp : formDataNames){
                    fp.add(tmp) ;
                }

                updateUI();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



        JLabel b1 = new JLabel(imageIcon , JLabel.CENTER) ;
        b1.setBackground(color);
        b1.setBorder(BorderFactory.createLineBorder(color,0));
        b1.setPreferredSize(new Dimension(10 , 30));
        b1.setMaximumSize(new Dimension(10 , 10));
        b1.setMinimumSize(new Dimension(10,10));


        JPopupMenu popupMenu = new JPopupMenu() ;
        JMenuItem menuItem = new JMenuItem("Delete all") ;
        popupMenu.add(menuItem) ;
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == menuItem){
                    formDataNames = new ArrayList<JPanel>() ;
                    formDataNames.add(newNameAndValueForFormData) ;
                    fp.removeAll();
                    fp.add(formDataNames.get(0));
                    updateUI();
                }
            }
        });
        b1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getComponent() == b1) {
                    popupMenu.show(e.getComponent() , e.getX() , e.getY());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



        icon1.add(b1 , BorderLayout.CENTER) ;
        icon1.setPreferredSize(new Dimension(50,40));
        icon1.setMinimumSize(new Dimension(50,40));
        icon1.setMaximumSize(new Dimension(50,40));
        p2.add(icon1);

        text1.add(t1, BorderLayout.CENTER);
        text1.setPreferredSize(new Dimension(200,40));
        text1.setMaximumSize(new Dimension(2000 , 40));
        text1.setMinimumSize(new Dimension(50 , 40));
        p2.add(text1);


        text2.add(t2 , BorderLayout.CENTER);
        text2.setPreferredSize(new Dimension(200,40));
        text2.setMaximumSize(new Dimension(2000 , 40));
        text2.setMinimumSize(new Dimension(50 , 40));
        p2.add(text2);



        p2.add(icon3);
        icon3.setPreferredSize(new Dimension(100,40));
        icon3.setMinimumSize(new Dimension(50,40));
        icon3.setMaximumSize(new Dimension(50,40));

        p.add(p2 , BorderLayout.CENTER);

        p.setPreferredSize(new Dimension(500 , 50));
        p.setMinimumSize(new Dimension(100 , 50));
        p.setMaximumSize(new Dimension(1500 , 50));

        return p ;
    }

    /**
     * Get a name and value for query j panel.
     *
     * @return the j panel
     */
    public JPanel getANameAndValueForQuery(String key , String value){
        Color color = Color.DARK_GRAY ;
        Color color1 = new Color(100,100,100) ;
        Color color2 = Color.WHITE ;
        JPanel p = new JPanel(new BorderLayout()) ;
        p.setBackground(color);
        JPanel p2 = new JPanel() ;
        p2.setLayout(new BoxLayout(p2 , BoxLayout.X_AXIS));
        p2.setBackground(color);
        p2.setBorder(BorderFactory.createLineBorder(color , 5));
        JPanel icon1 = new JPanel(new BorderLayout()) ;
        icon1.setBorder(BorderFactory.createLineBorder(color , 10));
        icon1.setBackground(color);
        JPanel icon2 = new JPanel(new BorderLayout()) ;
        icon2.setBorder(BorderFactory.createLineBorder(color , 10));
        icon2.setBackground(color);
        JPanel icon3 = new JPanel(new BorderLayout()) ;
        icon3.setBorder(BorderFactory.createLineBorder(color, 10));
        icon3.setBackground(color);
        JPanel text1 = new JPanel(new BorderLayout()) ;
        text1.setBorder(BorderFactory.createLineBorder(color , 10));
        text1.setBackground(color);
        JPanel text2 = new JPanel(new BorderLayout()) ;
        text2.setBorder(BorderFactory.createLineBorder(color , 10));
        text2.setBackground(color);

        ImageIcon imageIcon = new ImageIcon("delete.png") ;
        Image image = imageIcon.getImage() ;
        Image newImage = image.getScaledInstance(15,15,Image.SCALE_SMOOTH) ;
        imageIcon = new ImageIcon(newImage) ;

        ImageIcon imageIcon1 = new ImageIcon("menu.png") ;
        Image image1 = imageIcon1.getImage() ;
        Image newImage1 = image1.getScaledInstance(10,10,Image.SCALE_SMOOTH) ;
        imageIcon1 = new ImageIcon(newImage1) ;

        JTextArea t1 = new JTextArea(key) ;
        t1.setForeground(color1);
        t1.setBackground(color);
        t1.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
        t1.setPreferredSize(new Dimension(200,20));
        t1.setMaximumSize(new Dimension(2000,20));
        t1.setMinimumSize(new Dimension(50,20));
        t1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Color color2 = Color.WHITE ;
                if (t1.getText().equals("Name")){
                    t1.setText("");
                    t1.setForeground(color2);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                Color color1 = new Color(100,100,100) ;
                if (t1.getText().equals("")){
                    t1.setText("Name");
                    t1.setForeground(color1);
                }
            }
        });


        JTextArea t2 = new JTextArea(value) ;
        t2.setForeground(color1);
        t2.setBackground(color);
        t2.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
        t2.setPreferredSize(new Dimension(200,20));
        t2.setMaximumSize(new Dimension(2000,20));
        t2.setMinimumSize(new Dimension(50,20));
        t2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Color color2 = Color.WHITE ;
                if (t2.getText().equals("Value")){
                    t2.setText("");
                    t2.setForeground(color2);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                Color color1 = new Color(100,100,100) ;
                if (t2.getText().equals("")){
                    t2.setText("Value");
                    t2.setForeground(color1);
                }
            }
        });



        JLabel b1 = new JLabel(imageIcon1 , JLabel.CENTER) ;
        b1.setBackground(color);
        b1.setBorder(BorderFactory.createLineBorder(color,0));
        b1.setPreferredSize(new Dimension(10 , 30));
        b1.setMaximumSize(new Dimension(10 , 10));
        b1.setMinimumSize(new Dimension(10,10));



        JCheckBox b2 = new JCheckBox("") ;
        b2.setSelected(true);
        b2.setBackground(color);
        b2.setPreferredSize(new Dimension(10 , 10));
        b2.setMaximumSize(new Dimension(10 , 10));
        b2.setMinimumSize(new Dimension(10,10));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color1 = new Color(100,100,100) ;
                Color color2 = Color.WHITE ;
                if (b2.isSelected()){
                    t1.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
                    t2.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
                    if (t1.getText().equals("Name")){
                        t1.setForeground(color1);
                    }
                    else {
                        t1.setForeground(color2);
                    }
                    if (t2.getText().equals("Value")){
                        t2.setForeground(color1);
                    }
                    else{
                        t2.setForeground(color2);
                    }

                }
                else {
                    t1.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color1));
                    t2.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color1));
                    t1.setForeground(color1);
                    t2.setForeground(color1);
                }
            }
        });


        JButton b3 = new JButton("" , imageIcon) ;
        b3.setBackground(color);
        b3.setBorder(BorderFactory.createLineBorder(color,0));
        b3.setPreferredSize(new Dimension(20 , 20));
        b3.setMaximumSize(new Dimension(20 , 20));

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b3){
                    p.setVisible(false);
                    queryNames.remove(p) ;
                }
            }
        });


        icon1.add(b1 , BorderLayout.CENTER) ;
        icon1.setPreferredSize(new Dimension(50,40));
        icon1.setMinimumSize(new Dimension(50,40));
        icon1.setMaximumSize(new Dimension(50,40));
        p2.add(icon1);

        text1.add(t1, BorderLayout.CENTER);
        text1.setPreferredSize(new Dimension(200,40));
        text1.setMaximumSize(new Dimension(2000 , 40));
        text1.setMinimumSize(new Dimension(50 , 40));
        p2.add(text1);


        text2.add(t2 , BorderLayout.CENTER);
        text2.setPreferredSize(new Dimension(200,40));
        text2.setMaximumSize(new Dimension(2000 , 40));
        text2.setMinimumSize(new Dimension(50 , 40));
        p2.add(text2);


        icon2.add(b2 , BorderLayout.CENTER) ;
        icon2.setPreferredSize(new Dimension(50,40));
        icon2.setMinimumSize(new Dimension(50,40));
        icon2.setMaximumSize(new Dimension(50,40));
        p2.add(icon2);


        icon3.add(b3 , BorderLayout.CENTER) ;
        icon3.setPreferredSize(new Dimension(50,40));
        icon3.setMinimumSize(new Dimension(50,40));
        icon3.setMaximumSize(new Dimension(50,40));
        p2.add(icon3);

        p.add(p2 , BorderLayout.CENTER);

        p.setPreferredSize(new Dimension(500 , 50));
        p.setMinimumSize(new Dimension(100 , 50));
        p.setMaximumSize(new Dimension(1500 , 50));

        queryTexts.put(t1 , t2) ;
        return p ;
    }

    /**
     * Get a new name and value for query j panel.
     *
     * @return the j panel
     */
    public JPanel getANewNameAndValueForQuery(){
        Color color = Color.DARK_GRAY ;
        Color color1 = new Color(100,100,100) ;
        Color color2 = Color.WHITE ;
        JPanel p = new JPanel(new BorderLayout()) ;
        p.setBackground(color);
        JPanel p2 = new JPanel() ;
        p2.setLayout(new BoxLayout(p2 , BoxLayout.X_AXIS));
        p2.setBackground(color);
        p2.setBorder(BorderFactory.createLineBorder(color , 5));
        JPanel icon1 = new JPanel(new BorderLayout()) ;
        icon1.setBorder(BorderFactory.createLineBorder(color , 10));
        icon1.setBackground(color);
        JPanel icon2 = new JPanel(new BorderLayout()) ;
        icon2.setBorder(BorderFactory.createLineBorder(color , 10));
        icon2.setBackground(color);
        JPanel icon3 = new JPanel(new BorderLayout()) ;
        icon3.setBorder(BorderFactory.createLineBorder(color , 10));
        icon3.setBackground(color);
        JPanel text1 = new JPanel(new BorderLayout()) ;
        text1.setBorder(BorderFactory.createLineBorder(color , 10));
        text1.setBackground(color);
        JPanel text2 = new JPanel(new BorderLayout()) ;
        text2.setBorder(BorderFactory.createLineBorder(color , 10));
        text2.setBackground(color);

        ImageIcon imageIcon = new ImageIcon("setting.png") ;
        Image image = imageIcon.getImage() ;
        Image newImage = image.getScaledInstance(15,15,Image.SCALE_SMOOTH) ;
        imageIcon = new ImageIcon(newImage) ;


        JTextArea t1 = new JTextArea("New Name") ;
        t1.setForeground(color1);
        t1.setBackground(color);
        t1.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
        t1.setPreferredSize(new Dimension(200,20));
        t1.setMaximumSize(new Dimension(2000,20));
        t1.setMinimumSize(new Dimension(50,20));
        t1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                queryNames.remove(newNameAndValueForQuery) ;
                queryNames.add(getANameAndValueForQuery("Name" , "Value")) ;
                queryNames.add(newNameAndValueForQuery) ;
                qp.removeAll();
                for (JPanel tmp : queryNames){
                    qp.add(tmp) ;
                }

                updateUI();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



        JTextArea t2 = new JTextArea("New Value") ;
        t2.setForeground(color1);
        t2.setBackground(color);
        t2.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color2));
        t2.setPreferredSize(new Dimension(200,20));
        t2.setMaximumSize(new Dimension(2000,20));
        t2.setMinimumSize(new Dimension(50,20));
        t2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                queryNames.remove(newNameAndValueForQuery) ;
                queryNames.add(getANameAndValueForQuery("Name" , "Value")) ;
                queryNames.add(newNameAndValueForQuery) ;
                qp.removeAll();
                for (JPanel tmp : queryNames){
                    qp.add(tmp) ;
                }

                updateUI();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



        JLabel b1 = new JLabel(imageIcon , JLabel.CENTER) ;
        b1.setBackground(color);
        b1.setBorder(BorderFactory.createLineBorder(color,0));
        b1.setPreferredSize(new Dimension(10 , 30));
        b1.setMaximumSize(new Dimension(10 , 10));
        b1.setMinimumSize(new Dimension(10,10));


        JPopupMenu popupMenu = new JPopupMenu() ;
        JMenuItem menuItem = new JMenuItem("Delete all") ;
        popupMenu.add(menuItem) ;
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == menuItem){
                    queryNames = new ArrayList<JPanel>() ;
                    queryNames.add(newNameAndValueForQuery) ;
                    qp.removeAll();
                    qp.add(queryNames.get(0));
                    updateUI();
                }
            }
        });
        b1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getComponent() == b1) {
                    popupMenu.show(e.getComponent() , e.getX() , e.getY());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



        icon1.add(b1 , BorderLayout.CENTER) ;
        icon1.setPreferredSize(new Dimension(50,40));
        icon1.setMinimumSize(new Dimension(50,40));
        icon1.setMaximumSize(new Dimension(50,40));
        p2.add(icon1);

        text1.add(t1, BorderLayout.CENTER);
        text1.setPreferredSize(new Dimension(200,40));
        text1.setMaximumSize(new Dimension(2000 , 40));
        text1.setMinimumSize(new Dimension(50 , 40));
        p2.add(text1);


        text2.add(t2 , BorderLayout.CENTER);
        text2.setPreferredSize(new Dimension(200,40));
        text2.setMaximumSize(new Dimension(2000 , 40));
        text2.setMinimumSize(new Dimension(50 , 40));
        p2.add(text2);



        p2.add(icon3);
        icon3.setPreferredSize(new Dimension(100,40));
        icon3.setMinimumSize(new Dimension(50,40));
        icon3.setMaximumSize(new Dimension(50,40));

        p.add(p2 , BorderLayout.CENTER);

        p.setPreferredSize(new Dimension(500 , 50));
        p.setMinimumSize(new Dimension(100 , 50));
        p.setMaximumSize(new Dimension(1500 , 50));

        return p ;
    }

    /**
     * Design the panel of body tab.
     */
    public void designBody(){
        Color color = Color.DARK_GRAY ;
        Color color1 = Color.LIGHT_GRAY ;
        bodyTab = new JPanel(new BorderLayout()) ;
        bodyTab.setBackground(color) ;
        formDataTab = new JPanel(new BorderLayout()) ;
        formDataTab.setBackground(color);
        designFormData();
        jsonTab = new JPanel(new BorderLayout()) ;
        jsonTab.setBackground(color);
        binaryFileTab = new JPanel(new BorderLayout()) ;
        binaryFileTab.setBackground(color);
        JLabel noBody = new JLabel("Select A Body Type From Above") ;


        noBody.setForeground(color1);
        noBody.setBackground(color);
        noBody.setFont(new Font("Arial" , 0 , 20));
        noBody.setHorizontalTextPosition(SwingConstants.CENTER);
        noBody.setVerticalTextPosition(SwingConstants.CENTER);
        bodyTab.add(noBody, BorderLayout.CENTER) ;


    }

    /**
     * Design the panel of form data tab.
     */
    public void designFormData(){
        Color color = Color.DARK_GRAY ;
        fp = new JPanel() ;
        fp.setBackground(color);
        fp.setLayout(new BoxLayout(fp , BoxLayout.Y_AXIS)); ;
        fp.setAlignmentY(50);
        fp.setAlignmentX(50);
        JScrollPane jScrollPane = new JScrollPane(fp) ;

        formDataNames = new ArrayList<>() ;
        formDataNames.add(newNameAndValueForFormData) ;
        for (JPanel tmp : formDataNames){
            fp.add(tmp);
        }
        formDataTab.add(jScrollPane , BorderLayout.CENTER);
    }

    /**
     * Design the panel of jason tab.
     */
    public void designJason(){
        Color color = Color.DARK_GRAY ;
        Color color1 = Color.LIGHT_GRAY ;
        JPanel jPanel = new JPanel(new BorderLayout()) ;
        jPanel.setBackground(color);
        JScrollPane jScrollPane = new JScrollPane(jPanel) ;
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsonContent = new JTextArea("...") ;
        jsonContent.setBackground(color);
        jsonContent.setForeground(color1);
        jsonContent.setBorder(BorderFactory.createLineBorder(Color.BLACK , 1));
        jsonContent.setLineWrap(true);
        jsonContent.setFont(new Font("Arial" , 0 , 15));
        jsonContent.setFocusable(true);
        jPanel.add(jsonContent) ;
        jsonTab.add(jScrollPane);
    }

    /**
     * Design the panel of binary file tab.
     */
    public void designBinaryFile(){

        Color color = Color.DARK_GRAY ;
        Color color1 = Color.LIGHT_GRAY ;
        Color color2 = Color.WHITE ;
        JPanel jPanel = new JPanel(new FlowLayout()) ;
        jPanel.setBackground(color);

        JPanel jPanelLabel = new JPanel(new FlowLayout()) ;
        jPanelLabel.setBackground(color);
        JLabel jLabel = new JLabel("SELECTED FILE") ;
        jLabel.setFont(new Font("Arial" , 0 , 10));
        jLabel.setBackground(color);
        jLabel.setForeground(color1);
        JPanel jp = new JPanel(new BorderLayout()) ;
        jp.add(jLabel);
        jp.setPreferredSize(new Dimension(50,30));
        jp.setMinimumSize(new Dimension(50,30));
        jp.setMaximumSize(new Dimension(50,30));

        jPanelLabel.setPreferredSize(new Dimension(100,30));
        jPanelLabel.setMinimumSize(new Dimension(100,30));
        jPanelLabel.setMaximumSize(new Dimension(100,30));
        jPanelLabel.add(jLabel , FlowLayout.LEFT);
        jPanel.add(jPanelLabel  ,FlowLayout.LEFT);

        JPanel jPanelText = new JPanel(new BorderLayout()) ;
        jPanelText.setBackground(color);
        binaryFilePath = new JTextField("No File Selected") ;
        binaryFilePath.setFont(new Font("Arial" , 0 , 15));
        binaryFilePath.setBackground(color);
        binaryFilePath.setForeground(color1);
        binaryFilePath.setEditable(false);

        jPanelText.setPreferredSize(new Dimension(600,35));
        jPanelText.setMinimumSize(new Dimension(80,35));
        jPanelText.setMaximumSize(new Dimension(2000,35));
        jPanelText.add(binaryFilePath , BorderLayout.CENTER);
        jPanel.add(jPanelText);

        JPanel jPanelButtons = new JPanel(new FlowLayout()) ;
        jPanelButtons.setBackground(color);
        JButton choose = new JButton("Choose File") ;
        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                    binaryFilePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
        JButton reset = new JButton("Reset File") ;
        choose.setBackground(color);
        choose.setForeground(color2);
        reset.setBackground(color);
        reset.setForeground(Color.BLACK);
        reset.setEnabled(false);
        jPanelButtons.add(reset) ;
        jPanelButtons.add(choose);
        jPanelButtons.setPreferredSize(new Dimension(500,100));
        jPanelButtons.setMinimumSize(new Dimension(80 , 100));
        jPanelButtons.setMaximumSize(new Dimension(2000 , 100));
        jPanel.add(jPanelButtons);

        binaryFileTab.setLayout(new BorderLayout());
        binaryFileTab.add(jPanel , BorderLayout.CENTER);
    }

    /**
     * Design the panel of query tab.
     */
    public void designQuery(){
        Color color = Color.DARK_GRAY ;
        Color color1 = Color.LIGHT_GRAY ;
        JPanel jPanel = new JPanel(new BorderLayout(0 , 10)) ;
        jPanel.setBackground(color);
        JPanel urlPanel = new JPanel(new BorderLayout(0 , 5)) ;
        urlPanel.setBackground(color);
        qp = new JPanel() ;
        qp.setLayout(new BoxLayout(qp , BoxLayout.Y_AXIS));
        qp.setBackground(color);

        urlPreview = new JLabel("URL PREVIEW") ;
        urlPreview.setBackground(color);
        urlPreview.setForeground(color1);
        urlTextField = new JTextField() ;
        urlTextField.setBackground(color);
        urlTextField.setForeground(color1);
        urlTextField.setEditable(false);

        queryNames = new ArrayList<>() ;
        queryNames.add(newNameAndValueForQuery) ;
        for (JPanel tmp : queryNames){
            qp.add(tmp) ;
        }

        JScrollPane jScrollPane = new JScrollPane(qp) ;
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        urlPanel.setBorder(BorderFactory.createLineBorder(color,8));
        urlPanel.add(urlPreview , BorderLayout.NORTH);
        urlPanel.add(urlTextField , BorderLayout.CENTER);

        jPanel.add(urlPanel , BorderLayout.NORTH);
        jPanel.add(jScrollPane , BorderLayout.CENTER);

        QueryTab.add(jPanel , BorderLayout.CENTER);

    }

    /**
     * The type My renderer creates a combo box with items in different colors.
     */
    private class MyRenderer extends DefaultListCellRenderer
    {
        public Component getListCellRendererComponent(JList list,Object value,
                                                      int index,boolean isSelected,boolean cellHasFocus)
        {
            JLabel lbl = (JLabel)super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
            if(index > -1) {
                lbl.setForeground(colors1[index]);
            }
            return lbl;
        }
    }

}
