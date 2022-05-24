package com.learning.browser;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication implements Runnable {
  /*  @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }
            */
//    public static void main(String[] args) {
////        launch();
//    }

    WebEngine webEngine;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new JavaFxBrowser());
    }

    public void loadURL(final String url) {
        Platform.runLater(() -> {
            webEngine.load(url);
        });
    }

    @Override
    public void run() {
        // setup UI
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(1024, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFXPanel jfxPanel = new JFXPanel();
        frame.getContentPane().add(jfxPanel);
        frame.pack();

        Platform.runLater(() -> {
            WebView view = new WebView();
            webEngine = view.getEngine();

            jfxPanel.setScene(new Scene(view));
        });

        loadURL("http://www.google.com");
    }
}





/*

package learning;

import java.awt.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

public class Browser implements HyperlinkListener {

    private JButton btnBack, btnForward;
    private JTextField txtURL;
    private JEditorPane displayEditorPane;
    private ArrayList pageList = new ArrayList();

    public Browser() {
        initializeForm();
    }

    private void initializeForm(){
        JFrame frame = new JFrame("Browser");
        frame.setSize(1280, 960);



        JPanel buttonPanel = new JPanel();
        btnBack = new JButton("<-");
        btnBack.addActionListener(e -> directBack());
        btnBack.setEnabled(false);
        buttonPanel.add(btnBack);

        btnForward = new JButton("->");
        btnForward.addActionListener(e -> directForward());
        btnForward.setEnabled(false);
        buttonPanel.add(btnForward);

        txtURL = new JTextField(35);

        buttonPanel.add(txtURL);

        JButton btnGo = new JButton("Go");
        btnGo.addActionListener(e -> redirect());
        buttonPanel.add(btnGo);

        displayEditorPane = new JEditorPane();
        displayEditorPane.setContentType("text/html");
        displayEditorPane.setEditable(false);
        displayEditorPane.addHyperlinkListener(this);

         panel = new XHTMLPanel();

                 frame.setLayout(new BorderLayout());
                 frame.add(buttonPanel, BorderLayout.NORTH);
                 frame.add(new JScrollPane(displayEditorPane), BorderLayout.CENTER);

                 frame.setVisible(true);
                 }


private void directBack() {
        URL currentUrl = displayEditorPane.getPage();
        int pageIndex = pageList.indexOf(currentUrl.toString());
        try {
        displayPage(new URL((String) pageList.get(pageIndex - 1)), false);
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }

private void directForward() {
        URL currentUrl = displayEditorPane.getPage();
        int pageIndex = pageList.indexOf(currentUrl.toString());
        try {
        displayPage(new URL((String) pageList.get(pageIndex + 1)), false);
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }

private void redirect()  {
        try {

        URL verifiedUrl = new URL(txtURL.getText());
        displayPage(verifiedUrl, true);

        } catch (Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
        }

        }

private void displayPage(URL pageUrl, boolean addToList) {

        try {
        URL currentUrl = displayEditorPane.getPage();
        displayEditorPane.setPage(pageUrl);
        URL newUrl = displayEditorPane.getPage();

        if (addToList) {
        int listSize = pageList.size();
        if (listSize > 0) {
        int pageIndex =
        pageList.indexOf(currentUrl.toString());
        if (pageIndex < listSize - 1) {
        for (int i = listSize - 1; i > pageIndex; i--) {
        pageList.remove(i);
        }
        }
        }
        pageList.add(newUrl.toString());
        }

        txtURL.setText(newUrl.toString());
        updateButtons();

        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }

private void updateButtons() {
        if (pageList.size() < 2) {
        btnBack.setEnabled(false);
        btnForward.setEnabled(false);
        } else {
        URL currentUrl = displayEditorPane.getPage();
        int pageIndex = pageList.indexOf(currentUrl.toString());
        btnBack.setEnabled(pageIndex > 0);
        btnForward.setEnabled(pageIndex < (pageList.size() - 1));
        }
        }

public void hyperlinkUpdate(HyperlinkEvent event) {

        HyperlinkEvent.EventType eventType = event.getEventType();

        if (eventType == HyperlinkEvent.EventType.ACTIVATED) {

        if (event instanceof HTMLFrameHyperlinkEvent) {
        HTMLFrameHyperlinkEvent linkEvent = (HTMLFrameHyperlinkEvent) event;
        HTMLDocument document = (HTMLDocument) displayEditorPane.getDocument();
        document.processHTMLFrameHyperlinkEvent(linkEvent);
        } else {
        displayPage(event.getURL(), true);
        }
        }
        }

public static void main(String[] args) {
        Browser browser = new Browser();
        }
        }

        */