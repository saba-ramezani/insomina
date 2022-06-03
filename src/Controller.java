import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

public class Controller {

    private InsomniaDisplay view ;
    private RequestsManager model ;
    private Request currentRequest ;

    public Controller() throws Exception {
        FlatDarkLaf.install() ;
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatDraculaIJtheme");
        }
        catch (Exception e){
        }
        view = new InsomniaDisplay() ;
        model = new RequestsManager() ;
        view.getjPanel1().getRequests().addListSelectionListener(new RequestsListSelectionHandler());
        view.getjPanel2().getjComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    view.getjPanel1().getRequests().getSelectedValue().setMethod(((String) view.getjPanel2().getjComboBox().getSelectedItem()));
                } catch (NullPointerException ex){ }
                view.getjPanel1().updateUI();
                view.getjPanel1().revalidate();
                try {
                    model.saveRequests();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        view.getjPanel1().getCreateButton().addActionListener(new CreateNewRequestHandler());
        view.getjPanel2().getSave().addActionListener(new saveButton());
        view.getjPanel2().getSend().addActionListener(new sendButton());
        for (Request request: model.getRequests())
            view.getjPanel1().addRequest(request);
        try {
            model.saveRequests();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class saveButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!view.getjPanel2().getUrlTextArea().getText().equals("") && !view.getjPanel2().getUrlTextArea().getText().equals("Enter URL here")) {
                currentRequest.setAddress(view.getjPanel2().getUrlTextArea().getText());
            }
            currentRequest.setMethod((String) view.getjPanel2().getjComboBox().getSelectedItem());
            for (JTextArea t : view.getjPanel2().getQueryTexts().keySet()) {
                if (!t.getText().equals("") && !t.getText().equals("Name") && !view.getjPanel2().getQueryTexts().get(t).getText().equals("") && !view.getjPanel2().getQueryTexts().get(t).getText().equals("Value")) {
                    currentRequest.addQuery(t.getText() , view.getjPanel2().getQueryTexts().get(t).getText());
                }
            }
            for (JTextArea t : view.getjPanel2().getHeaderText().keySet()) {
                if (!t.getText().equals("") && !t.getText().equals("Header") && !view.getjPanel2().getHeaderText().get(t).equals("") && !view.getjPanel2().getHeaderText().get(t).equals("Value")) {
                    currentRequest.addHeader(t.getText() , view.getjPanel2().getHeaderText().get(t).getText());
                }
            }
            for (JTextArea t : view.getjPanel2().getFormDataText().keySet()) {
                if (!t.getText().equals("") && !t.getText().equals("Name") && !view.getjPanel2().getFormDataText().get(t).equals("") && !view.getjPanel2().getFormDataText().get(t).equals("Value")) {
                    currentRequest.addFormData(t.getText() , view.getjPanel2().getFormDataText().get(t).getText());
                }
            }
            if (!view.getjPanel2().getBinaryFilePath().getText().equals("No File Selected")) {
                currentRequest.setBinaryFilePath(view.getjPanel2().getBinaryFilePath().getText());
            }
            if (!view.getjPanel2().getJsonContent().getText().equals("...")) {
                currentRequest.addJson(view.getjPanel2().getJsonContent().getText());
            }
            currentRequest.addQueriesToRequest();
            view.getjPanel2().getUrlTextField().setText(currentRequest.getUrlWithQueries());
            currentRequest.addQueriesToRequest();
            try {
                model.saveRequests();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class sendButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SendRequest sendRequest = new SendRequest(view , model , currentRequest) ;
            try {
                sendRequest.doInBackground();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private class RequestsListSelectionHandler implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            view.getjPanel2().getHeaderText().clear();
            view.getjPanel2().getFormDataText().clear();
            view.getjPanel2().getQueryTexts().clear();
            JList<Request> requestJList = (JList<Request>) e.getSource();
            Request selectedRequest = requestJList.getSelectedValue();
            currentRequest = selectedRequest ;
            if (currentRequest.getFormData().containsKey("file")) {
                view.getjPanel2().getBinaryFilePath().setText(currentRequest.getFormData().get("file"));
                view.getjPanel2().revalidate();
            }
            if (currentRequest.getJson().equals("")) {
                view.getjPanel2().getJsonContent().setText("...");
                view.getjPanel2().revalidate();
            }
            else  {
                view.getjPanel2().getJsonContent().setText(currentRequest.getJson());
                view.getjPanel2().revalidate();
            }
            if (currentRequest.getBinaryFilePath().equals("")) {
                view.getjPanel2().getBinaryFilePath().setText("No File Selected");
                view.getjPanel2().revalidate();
            }
            view.getjPanel2().addAHeader(selectedRequest.getHeaders());
            view.getjPanel2().addAFormData(selectedRequest.getFormData());
            view.getjPanel2().addAQuery(selectedRequest.getQueries());
            if (!selectedRequest.getAddress().equals("")) {
                view.getjPanel2().getUrlTextArea().setForeground(Color.WHITE);
                view.getjPanel2().getUrlTextArea().setText(selectedRequest.getAddress());
            } else {
                view.getjPanel2().getUrlTextArea().setText("Enter URL here");
                view.getjPanel2().getUrlTextArea().setForeground(new Color(140, 140, 140));
                view.getjPanel2().getUrlTextArea().addFocusListener(new urlTextAreaFocusListener());
            }
            currentRequest.addQueriesToRequest();
            view.getjPanel2().getUrlTextField().setText(currentRequest.getUrlWithQueries());
            try {
                model.saveRequests();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            view.getjPanel2().getjComboBox().setSelectedItem(selectedRequest.getMethod());
            view.getjPanel2().revalidate();
        }
    }

    private class urlTextAreaFocusListener implements FocusListener{

        public void focusGained(FocusEvent e) {
                if (view.getjPanel2().getUrlTextArea().getText().equals("Enter URL here")) {
                    view.getjPanel2().getUrlTextArea().setText("");
                    view.getjPanel2().getUrlTextArea().setForeground(Color.WHITE);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (!view.getjPanel2().getUrlTextArea().getText().equals("") && !view.getjPanel2().getUrlTextArea().getText().equals("Enter URL here")) {
                currentRequest.setAddress(view.getjPanel2().getUrlTextArea().getText());
            }
        }
    }

    private class CreateNewRequestHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Request newRequest = new Request((String)view.getjPanel1().getMethodJComboBox().getSelectedItem(),
                    view.getjPanel1().getNameJTextArea().getText());
            model.add(newRequest);
            try {
                model.saveRequests();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            view.getjPanel1().addRequest(newRequest);
        }
    }

}