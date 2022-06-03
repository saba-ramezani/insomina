import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Panel 3 generates and design the left panel in insomnia window.
 *
 * @ author saba
 */
public class Panel3 extends JPanel {

    private JButton copy ;
    private JPanel receivedDataPanel ;
    private JPanel timePanel ;
    private JPanel statusPanel ;
    private JLabel status ;
    private JLabel time ;
    private JLabel receivedData ;
    //raw panel
    private JPanel rawPanel ;
    //json panel
    private JPanel jsonPanel ;
    //preview panel
    private JPanel previewPanel ;
    //header panel
    private JPanel headerTab ;
    private JTextArea rawTextArea ;
    private JTextArea jsonTextArea ;
    private JPanel namesJPanel ;
    private JPanel valuesJPanel ;
    private JPanel jPanel1 ;


    public Panel3(){
        this.setLayout(new BorderLayout(0,5));
        this.setBorder(BorderFactory.createMatteBorder(0,1,0,0,Color.BLACK));
        this.setBackground(Color.DARK_GRAY);
        updatePanel3();
        designHeaderTab();
    }

    public JButton getCopy() {
        return copy;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public JPanel getTimePanel() {
        return timePanel;
    }

    public JPanel getReceivedDataPanel() {
        return receivedDataPanel;
    }

    public JPanel getStatusPanel() {
        return statusPanel;
    }

    public JPanel getPreviewPanel() {
        return previewPanel;
    }

    public JLabel getReceivedData() {
        return receivedData;
    }

    public JLabel getStatus() {
        return status;
    }

    public JLabel getTime() {
        return time;
    }

    public JTextArea getRawTextArea() {
        return rawTextArea;
    }

    public JTextArea getJsonTextArea() {
        return jsonTextArea;
    }

    /**
     * add 3 top labels.
     */
    public void updatePanel3(){
        Color color = Color.DARK_GRAY ;
        jPanel1 = new JPanel(new BorderLayout()) ;
        jPanel1.setBorder(BorderFactory.createMatteBorder(10,10,10,10,color));
        JPanel jPanel2 = new JPanel(new BorderLayout()) ;
        jPanel2.setBorder(BorderFactory.createMatteBorder(10,10,10,10,color));
        JPanel jPanel3 = new JPanel(new BorderLayout()) ;
        jPanel3.setBorder(BorderFactory.createMatteBorder(10,10,10,10,color));
        JPanel topPanel = new JPanel() ;
        statusPanel = new JPanel() ;
        timePanel = new JPanel() ;
        receivedDataPanel = new JPanel() ;
        timePanel.setBackground(color);
        receivedDataPanel.setBackground(color);
        topPanel.setLayout(new BoxLayout(topPanel , BoxLayout.X_AXIS));

        status = new JLabel() ;
        status.setForeground(Color.BLACK);
        status.setVerticalTextPosition(SwingConstants.CENTER);
        status.setFont(new Font("Arial" , Font.BOLD , 10));
        time = new JLabel() ;
        time.setForeground(Color.BLACK);
        time.setVerticalTextPosition(SwingConstants.CENTER);
        time.setFont(new Font("Arial" , Font.BOLD , 10));
        receivedData = new JLabel() ;
        receivedData.setForeground(Color.BLACK);
        receivedData.setVerticalTextPosition(SwingConstants.CENTER);
        receivedData.setFont(new Font("Arial" , Font.BOLD , 10));
        statusPanel.add(status);
        timePanel.add(time);
        receivedDataPanel.add(receivedData);
        topPanel.setBackground(color);

        jPanel1.setBackground(color);
        statusPanel.setLocation(15,15);
        jPanel1.add(statusPanel) ;
        topPanel.add(jPanel1) ;
        jPanel2.setBackground(color);
        jPanel2.setPreferredSize(new Dimension(70 , 50));
        jPanel2.setMaximumSize(new Dimension(100 , 50));
        jPanel2.setMinimumSize(new Dimension(70 , 50));
        timePanel.setLocation(15,15);
        jPanel2.add(timePanel) ;
        topPanel.add(jPanel2) ;
        jPanel3.setBackground(color);
        jPanel3.setPreferredSize(new Dimension(70 , 50));
        jPanel3.setMaximumSize(new Dimension(100 , 50));
        jPanel3.setMinimumSize(new Dimension(70 , 50));
        receivedDataPanel.setLocation(15,15);
        jPanel3.add(receivedDataPanel) ;
        topPanel.add(jPanel3) ;
        statusPanel.setPreferredSize(new Dimension(50,25));
        statusPanel.setMinimumSize(new Dimension(50 , 25));
        statusPanel.setMaximumSize(new Dimension(100 , 25));
        timePanel.setPreferredSize(new Dimension(50,25));
        timePanel.setMinimumSize(new Dimension(50 , 25));
        timePanel.setMaximumSize(new Dimension(100 , 25));
        receivedDataPanel.setPreferredSize(new Dimension(50,25));
        receivedDataPanel.setMinimumSize(new Dimension(50 , 25));
        receivedDataPanel.setMaximumSize(new Dimension(100 , 25));
        topPanel.setPreferredSize(new Dimension(100 , 56));
        topPanel.setMaximumSize(new Dimension(100 , 56));
        topPanel.setMinimumSize(new Dimension(100 , 56));
        topPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        this.add(topPanel , BorderLayout.NORTH);
        designPanel3();
    }

    /**
     * Design panel 3.
     */
    public void designPanel3(){
        Color color = Color.DARK_GRAY ;
        Color color1 = Color.LIGHT_GRAY ;
        JTabbedPane jTabbedPane = new JTabbedPane() ;
        jTabbedPane.setBackground(color);
        jTabbedPane.setForeground(color1);
        JComboBox body = new JComboBox() ;
        body.addItem("Raw");
        body.addItem("JSON");
        body.addItem("Preview");
        body.setSelectedIndex(0);

        rawPanel = new JPanel(new BorderLayout()) ;
        rawPanel.setBackground(color);
        rawTextArea = new JTextArea() ;
        rawTextArea.setBackground(color);
        rawTextArea.setForeground(Color.WHITE);
        rawTextArea.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(rawTextArea) ;
        rawPanel.add(jScrollPane , BorderLayout.CENTER);
        jsonPanel = new JPanel(new BorderLayout()) ;
        jsonPanel.setBackground(color);
        jsonTextArea = new JTextArea() ;
        jsonTextArea.setBackground(color);
        jsonTextArea.setForeground(Color.WHITE);
        jsonTextArea.setLineWrap(true);
        JScrollPane jScrollPane1 = new JScrollPane(jsonTextArea) ;
        jsonPanel.add(jScrollPane1 , BorderLayout.CENTER);
        previewPanel = new JPanel(new BorderLayout()) ;
        previewPanel.setBackground(color);
        headerTab = new JPanel(new BorderLayout()) ;
        JScrollPane jScrollPane2 = new JScrollPane(headerTab) ;
        headerTab.setBackground(color);


        body.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (body.getSelectedIndex() == 0){
                    jTabbedPane.setSelectedIndex(0);
                    jTabbedPane.setComponentAt(0 , rawPanel);
                    updateUI();
                }
                else if (body.getSelectedIndex() == 1){
                    jTabbedPane.setSelectedIndex(0);
                    jTabbedPane.setComponentAt(0 , jsonPanel);
                    updateUI();
                }
                else if (body.getSelectedIndex() == 2){
                    jTabbedPane.setSelectedIndex(0);
                    jTabbedPane.setComponentAt(0 , previewPanel);
                    updateUI();
                }
            }
        });

        jTabbedPane.addTab("Body" , rawPanel);
        jTabbedPane.setTabComponentAt(0 , body);
        jTabbedPane.addTab("Header" , jScrollPane2);
        this.add(jTabbedPane , BorderLayout.CENTER);
    }

    public void addANewHeaderName(String name) {
        JTextArea jTextArea1 = new JTextArea(name) ;
        jTextArea1.setBackground(Color.LIGHT_GRAY);
        jTextArea1.setForeground(Color.BLACK);
        jTextArea1.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        jTextArea1.setPreferredSize(new Dimension(80,30));
        jTextArea1.setMinimumSize(new Dimension(20,30));
        jTextArea1.setMaximumSize(new Dimension(1500,30));
        jTextArea1.setFont(new Font("Arial" , Font.PLAIN , 10));
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(jTextArea1) ;
        jScrollPane.setPreferredSize(new Dimension(80,30));
        jScrollPane.setMinimumSize(new Dimension(20,30));
        jScrollPane.setMaximumSize(new Dimension(1500,30));
        namesJPanel.add(jTextArea1);
    }

    public void addANewHeaderValue(String value) {
        JTextArea jTextArea1 = new JTextArea(value) ;
        jTextArea1.setBackground(Color.DARK_GRAY);
        jTextArea1.setForeground(Color.WHITE);
        jTextArea1.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        jTextArea1.setFont(new Font("Arial" , Font.PLAIN , 10));
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(jTextArea1) ;
        jScrollPane.setPreferredSize(new Dimension(80,30));
        jScrollPane.setMinimumSize(new Dimension(20,30));
        jScrollPane.setMaximumSize(new Dimension(1500,30));
        valuesJPanel.add(jScrollPane);
    }

    /**
     * Design header tab and add the table to it.
     */
    public void designHeaderTab(){
        Color color = Color.DARK_GRAY ;
        JPanel jPanel = new JPanel() ;
        jPanel.setBackground(color);
        jPanel.setBorder(BorderFactory.createLineBorder(color , 5));
        jPanel.setLayout(new BoxLayout(jPanel , BoxLayout.X_AXIS));
        namesJPanel = new JPanel() ;
        namesJPanel.setBackground(color);
        namesJPanel.setLayout(new BoxLayout(namesJPanel , BoxLayout.Y_AXIS));
        valuesJPanel = new JPanel() ;
        valuesJPanel.setBackground(color);
        valuesJPanel.setLayout(new BoxLayout(valuesJPanel , BoxLayout.Y_AXIS));

        jPanel.add(namesJPanel);
        jPanel.add(valuesJPanel);

        namesJPanel.setPreferredSize(new Dimension(150 , 500));
        namesJPanel.setMinimumSize(new Dimension(120,500));
        namesJPanel.setMaximumSize(new Dimension(1500 , 500));
        valuesJPanel.setPreferredSize(new Dimension(150 , 500));
        valuesJPanel.setMinimumSize(new Dimension(120,500));
        valuesJPanel.setMaximumSize(new Dimension(1500 , 500));

        jPanel.setPreferredSize(new Dimension(300 , 500));
        jPanel.setMaximumSize(new Dimension(3000,500));
        jPanel.setMinimumSize(new Dimension(250 , 500));

        headerTab.add(jPanel , BorderLayout.NORTH);
    }

    public void addCopyToClipBoard() {
        copy = new JButton("Copy to clipboard") ;
        copy.setPreferredSize(new Dimension(50,30));
        copy.setMinimumSize(new Dimension(50,30));
        copy.setMaximumSize(new Dimension(50,30));
        JPanel jPanel3 = new JPanel(new BorderLayout()) ;
        jPanel3.setBackground(Color.DARK_GRAY);
        jPanel3.setPreferredSize(new Dimension(80,50));
        jPanel3.setMinimumSize(new Dimension(20 ,50));
        jPanel3.setMaximumSize(new Dimension(1500,50));
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY , 15));
        jPanel3.add(copy);
        valuesJPanel.add(jPanel3);
    }
}
