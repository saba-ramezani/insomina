import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;


/**
 * The type Insomnia display generates insomnia gui.
 *
 * @author saba
 */
public class InsomniaDisplay {

    //the main frame for insomnia
    private JFrame frame ;
    //the splitPane for 3 panels
    private JSplitPane splitPane1 ;
    //the splitPane for panel 2 and panel 3
    private JSplitPane splitPane2 ;
    //system tray checkbox
    private JCheckBox systemTray ;
    //the left panel
    private JPanel jPanel ;
    //the middle panel
    private Panel1 jPanel1 ;
    private Panel2 jPanel2 ;
    //the right panel
    private Panel3 jPanel3 ;
    //if its full screen
    private Boolean fullScreen ;
    //if the side bar is visible
    private Boolean sideBar ;
    //hide in system tray
    private boolean hideInSystemTray ;

    /**
     * Instantiates a new Insomnia display.
     */
    public InsomniaDisplay(){
        hideInSystemTray = false ;
        frame = new JFrame() ;
        frame.setSize(1250 , 550);
        frame.setTitle("Insomnia");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addMenuBarToFrame();
        jPanel1 = new Panel1() ;
        jPanel2 = new Panel2() ;
        jPanel3 = new Panel3() ;
        addPanels();
        frame.setVisible(true);
        fullScreen = false ;
        sideBar = true ;
        FlatLaf flatLaf = new FlatDarculaLaf() ;
        FlatLaf.install(flatLaf) ;
    }

    public Panel1 getjPanel1() {
        return jPanel1;
    }

    public Panel2 getjPanel2() {
        return jPanel2;
    }

    public Panel3 getjPanel3() {
        return jPanel3;
    }

    /**
     * Add menu bar to frame.
     */
    public void addMenuBarToFrame(){
        JMenuBar jMenuBar = new JMenuBar() ;

        JMenu application = new JMenu("Application") ;
        application.setMnemonic(KeyEvent.VK_A);

        JMenuItem options = new JMenuItem("Options") ;
        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == options){
                    designOptionFrame();
                }
            }
        });
        options.setMnemonic(KeyEvent.VK_O);
        options.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O , ActionEvent.CTRL_MASK));

        JMenuItem exit = new JMenuItem("Exit") ;
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E , ActionEvent.CTRL_MASK));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (systemTray.isSelected()) {
                    frame.setVisible(false);
                    systemTray();
                }
                else {
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            }
        });

        application.add(options) ;
        application.addSeparator();
        application.add(exit) ;


        JMenu view = new JMenu("View") ;
        view.setMnemonic(KeyEvent.VK_V);

        JMenuItem toggleFullScreen = new JMenuItem("Toggle Full Screen") ;
        toggleFullScreen.setMnemonic(KeyEvent.VK_F);
        toggleFullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F , ActionEvent.CTRL_MASK));
        toggleFullScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleFullScreen() ;
            }
        });

        JMenuItem toggleSidebar = new JMenuItem("Toggle Sidebar") ;
        toggleSidebar.setMnemonic(KeyEvent.VK_S);
        toggleSidebar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S , ActionEvent.CTRL_MASK));
        toggleSidebar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSideBar();
            }
        });

        view.add(toggleFullScreen) ;
        view.addSeparator();
        view.add(toggleSidebar) ;


        JMenu help = new JMenu("Help") ;
        help.setMnemonic(KeyEvent.VK_H);

        JMenuItem about = new JMenuItem("About") ;
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == about){
                    designAboutFrame();
                }
            }
        });
        about.setMnemonic(KeyEvent.VK_A);
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A , ActionEvent.CTRL_MASK));

        JMenuItem Help = new JMenuItem("Help") ;
        Help.setMnemonic(KeyEvent.VK_H);
        Help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H , ActionEvent.CTRL_MASK));

        help.add(about) ;
        help.addSeparator();
        help.add(Help) ;

        jMenuBar.add(application) ;
        jMenuBar.add(view) ;
        jMenuBar.add(help) ;
        frame.setJMenuBar(jMenuBar);
    }

    /**
     * Add panels.
     */
    public void addPanels(){
        frame.setLayout(new BorderLayout());
        jPanel = new JPanel() ;
        jPanel.setLayout(new BorderLayout());

        splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT) ;
        splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT) ;
        splitPane1.setLeftComponent(jPanel2);
        splitPane1.add(jPanel3);
        splitPane2.setLeftComponent(jPanel1);
        splitPane2.add(splitPane1);
        splitPane1.setDividerSize(2);
        splitPane2.setDividerSize(2);
        double width = jPanel1.getPreferredSize().getWidth() + 100 ;
        double height = jPanel1.getPreferredSize().getHeight() ;
        jPanel1.setPreferredSize(new Dimension((int)width , (int) height));
        width = jPanel2.getPreferredSize().getWidth() + 70 ;
        height = jPanel2.getPreferredSize().getHeight() ;
        jPanel2.setPreferredSize(new Dimension((int) width , (int) height));

        jPanel.add(splitPane2 , BorderLayout.CENTER) ;
        frame.add(jPanel , BorderLayout.CENTER) ;
    }

    /**
     * Toggle full screen.
     */
    public void toggleFullScreen(){
        if (!fullScreen) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            fullScreen = true ;
        }
        else {
            frame.setExtendedState(JFrame.NORMAL);
            fullScreen = false ;
        }
    }

    /**
     * Toggle side bar.
     */
    public void toggleSideBar(){
        if (!sideBar){
            jPanel1.setVisible(true);
            splitPane2.setDividerLocation(-2);
            sideBar = true ;
        }
        else {
            jPanel1.setVisible(false);
            sideBar = false ;
        }
    }

    /**
     * System tray.
     */
    public void systemTray(){
        if (!SystemTray.isSupported()){
            return;
        }
        SystemTray systemTray = SystemTray.getSystemTray() ;
        Image image = Toolkit.getDefaultToolkit().getImage("insomnia.png") ;
        PopupMenu trayPopupMenu = new PopupMenu() ;
        MenuItem show = new MenuItem("Show") ;
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
            }
        });
        trayPopupMenu.add(show) ;
        MenuItem close = new MenuItem("Close") ;
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        trayPopupMenu.add(close) ;
        TrayIcon trayIcon = new TrayIcon(image , "Insomnia" , trayPopupMenu) ;
        trayIcon.setImageAutoSize(true);
        try{
            systemTray.add(trayIcon);
        }
        catch (AWTException awtExceotioin){
            awtExceotioin.printStackTrace();
        }
    }

    /**
     * Design option frame.
     */
    public void designOptionFrame(){
        Color color = new Color(50,50,50) ;
        Color color1 = Color.LIGHT_GRAY ;
        JFrame optionFrame = new JFrame("Option") ;
        optionFrame.setBackground(color);
        JPanel optionPanel = new JPanel(new GridLayout(3 , 1)) ;
        optionPanel.setBackground(color);
        JPanel jPanel11 = new JPanel(new FlowLayout()) ;
        jPanel11.setBackground(color);
        jPanel11.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color1));
        JPanel jPanel22 = new JPanel(new FlowLayout()) ;
        jPanel22.setBackground(new Color(50,50,50));
        jPanel22.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color1));
        JPanel jPanel33 = new JPanel(new GridLayout(1 , 2)) ;
        jPanel33.setBackground(new Color(50,50,50));

        JCheckBox followRedirect = new JCheckBox("Follow Redirect") ;
        followRedirect.setForeground(color1);
        jPanel11.add(followRedirect);
        systemTray = new JCheckBox("Hide In System Tray") ;
        systemTray.setSelected(hideInSystemTray);
        systemTray.setForeground(color1);
        systemTray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideInSystemTray = systemTray.isSelected() ;
            }
        });
        jPanel22.add(systemTray);

        optionPanel.add(jPanel11);
        optionPanel.add(jPanel22);
        optionFrame.add(optionPanel) ;
        optionFrame.setSize(250,150);
        optionFrame.setLocationRelativeTo(null);
        optionFrame.setResizable(false);
        optionFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        optionFrame.setVisible(true);
    }

    /**
     * Design about frame.
     */
    public void designAboutFrame(){
        Color color = new Color(50,50,50) ;
        Color color1 = Color.LIGHT_GRAY ;
        JFrame aboutFrame = new JFrame("About") ;
        aboutFrame.setLayout(new BorderLayout());
        JPanel aboutPanel = new JPanel(new GridLayout(2,1)) ;
        aboutPanel.setBackground(color);

        JLabel jLabel1 = new JLabel("Email : saba.rad.sr@Gmail.com") ;
        jLabel1.setFont(new Font("Arial" , 0 , 12));
        jLabel1.setBorder(BorderFactory.createMatteBorder(0,0,1,0,color1));
        jLabel1.setBackground(color);
        jLabel1.setForeground(color1);

        JLabel jLabel2 = new JLabel("ID : 9831030") ;
        jLabel2.setFont(new Font("Arial" , 0 , 12));
        jLabel2.setBackground(color);
        jLabel2.setForeground(color1);


        aboutPanel.add(jLabel1) ;
        aboutPanel.add(jLabel2);
        aboutFrame.add(aboutPanel , BorderLayout.CENTER);
        aboutFrame.setSize(250,200);
        aboutFrame.setResizable(false);
        aboutFrame.setLocationRelativeTo(null);
        aboutFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        aboutFrame.setVisible(true);
    }


}

