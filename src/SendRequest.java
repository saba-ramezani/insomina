import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SendRequest extends SwingWorker {

    private InsomniaDisplay view ;
    private RequestsManager model ;
    private Request currentRequest ;

    public SendRequest(InsomniaDisplay view , RequestsManager model , Request currentRequest) {
        this.view = view ;
        this.model = model ;
        this.currentRequest = currentRequest ;
    }

    @Override
    protected Object doInBackground() throws Exception {
        currentRequest = view.getjPanel1().getRequests().getSelectedValue();
        if (!view.getjPanel2().getUrlTextArea().getText().equals("") && !view.getjPanel2().getUrlTextArea().getText().equals("Enter URL here")) {
            currentRequest.setAddress(view.getjPanel2().getUrlTextArea().getText());
        }
        currentRequest.setMethod((String) view.getjPanel2().getjComboBox().getSelectedItem());
        for (JTextArea t : view.getjPanel2().getQueryTexts().keySet()) {
            if (!t.getText().equals("") && !t.getText().equals("Name") && !view.getjPanel2().getQueryTexts().get(t).getText().equals("") && !view.getjPanel2().getQueryTexts().get(t).getText().equals("Value")) {
                currentRequest.addQuery(t.getText(), view.getjPanel2().getQueryTexts().get(t).getText());
            }
        }
        currentRequest.addQueriesToRequest();
        view.getjPanel2().getUrlTextField().setText(currentRequest.getUrlWithQueries());
        for (JTextArea t : view.getjPanel2().getHeaderText().keySet()) {
            if (!t.getText().equals("") && !t.getText().equals("Header") && !view.getjPanel2().getHeaderText().get(t).equals("") && !view.getjPanel2().getHeaderText().get(t).equals("Value")) {
                currentRequest.addHeader(t.getText(), view.getjPanel2().getHeaderText().get(t).getText());
            }
        }
        for (JTextArea t : view.getjPanel2().getFormDataText().keySet()) {
            if (!t.getText().equals("") && !t.getText().equals("Name") && !view.getjPanel2().getFormDataText().get(t).equals("") && !view.getjPanel2().getFormDataText().get(t).equals("Value")) {
                currentRequest.addFormData(t.getText(), view.getjPanel2().getFormDataText().get(t).getText());
            }
        }
        if (!view.getjPanel2().getBinaryFilePath().getText().equals("No File Selected")) {
            currentRequest.setBinaryFilePath(view.getjPanel2().getBinaryFilePath().getText());
        }
        if (!view.getjPanel2().getJsonContent().getText().equals("...")) {
            currentRequest.addJson(view.getjPanel2().getJsonContent().getText());
        }

        currentRequest.setTypeOfMessageBody(view.getjPanel2().getBody().getSelectedIndex());
        currentRequest.createRequest();

        if (currentRequest.getResponseHeaders().size() != 0) {
            view.getjPanel3().addANewHeaderName(" NAME");
            view.getjPanel3().addANewHeaderValue(" VALUE");
            for (String name : currentRequest.getResponseHeaders().keySet()) {
                view.getjPanel3().addANewHeaderName(" " + name);
                view.getjPanel3().addANewHeaderValue(" " + currentRequest.getResponseHeaders().get(name));
            }
            view.getjPanel3().addCopyToClipBoard();
            view.getjPanel3().getCopy().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String myString;
                    myString = currentRequest.getResponseHeaders().toString();
                    StringSelection stringSelection = new StringSelection(myString);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                }
            });
        }
        view.getjPanel3().getStatus().setText(currentRequest.getCode() + " " + currentRequest.getReasonPhrase());
        if ((Integer.parseInt(currentRequest.getCode()) >= 200) && (Integer.parseInt(currentRequest.getCode()) < 300)) {
            view.getjPanel3().getStatusPanel().setBackground(new Color(36, 168, 60));
            view.getjPanel3().getjPanel1().setPreferredSize(new Dimension(70, 50));
            view.getjPanel3().getjPanel1().setMaximumSize(new Dimension(70, 50));
            view.getjPanel3().getjPanel1().setMinimumSize(new Dimension(70, 50));
        } else if ((Integer.parseInt(currentRequest.getCode()) >= 300) && (Integer.parseInt(currentRequest.getCode()) < 400)) {
            view.getjPanel3().getStatusPanel().setBackground(new Color(143, 89, 193));
            view.getjPanel3().getjPanel1().setPreferredSize(new Dimension(70, 50));
            view.getjPanel3().getjPanel1().setMaximumSize(new Dimension(130, 50));
            view.getjPanel3().getjPanel1().setMinimumSize(new Dimension(70, 50));
        } else if ((Integer.parseInt(currentRequest.getCode()) >= 400) && (Integer.parseInt(currentRequest.getCode()) < 500)) {
            view.getjPanel3().getStatusPanel().setBackground(new Color(251, 125, 0));
            view.getjPanel3().getjPanel1().setPreferredSize(new Dimension(70, 50));
            view.getjPanel3().getjPanel1().setMaximumSize(new Dimension(120, 50));
            view.getjPanel3().getjPanel1().setMinimumSize(new Dimension(70, 50));
        } else if ((Integer.parseInt(currentRequest.getCode()) >= 500) && (Integer.parseInt(currentRequest.getCode()) < 600)) {
            view.getjPanel3().getStatusPanel().setBackground(Color.RED);
            view.getjPanel3().getjPanel1().setPreferredSize(new Dimension(70, 50));
            view.getjPanel3().getjPanel1().setMaximumSize(new Dimension(150, 50));
            view.getjPanel3().getjPanel1().setMinimumSize(new Dimension(70, 50));
        } else {
            view.getjPanel3().getStatusPanel().setBackground(Color.BLUE);
            view.getjPanel3().getjPanel1().setPreferredSize(new Dimension(70, 50));
            view.getjPanel3().getjPanel1().setMaximumSize(new Dimension(100, 50));
            view.getjPanel3().getjPanel1().setMinimumSize(new Dimension(70, 50));
        }
        view.getjPanel3().getTime().setText("TIME " + currentRequest.getResponseTime() + " ms");
        view.getjPanel3().getTimePanel().setBackground(Color.LIGHT_GRAY);
        view.getjPanel3().getReceivedData().setText("SIZE " + currentRequest.getResponseSize() + " KB");
        view.getjPanel3().getReceivedDataPanel().setBackground(Color.LIGHT_GRAY);
        if (currentRequest.getResult().equals("")) {
            view.getjPanel3().getRawTextArea().setText("No Body");
        } else {
            view.getjPanel3().getRawTextArea().setText(currentRequest.getResult());
            try {
                JEditorPane jEditorPane = new JEditorPane();
                jEditorPane.setPage(currentRequest.getAddress());
                jEditorPane.setPreferredSize(new Dimension(500, 500));
                view.getjPanel3().getPreviewPanel().add(jEditorPane);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (currentRequest.getResponseHeaders().containsKey("Content-Type")) {
            if (currentRequest.getResponseHeaders().get("Content-Type").contains("application/json")) {
                try {
                    view.getjPanel3().getJsonTextArea().setText(new JSONObject(currentRequest.getResult()).toString(2));
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
                view.getjPanel3().getJsonTextArea().setText("No JSON Body");
            }
        } else {
            view.getjPanel3().getJsonTextArea().setText("No JSON Body");
        }
        try {
            model.saveRequests();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null ;
    }
}
