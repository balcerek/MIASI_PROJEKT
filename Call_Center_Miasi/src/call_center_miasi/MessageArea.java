package call_center_miasi;

import javax.swing.*;

public class MessageArea {

    private static MessageArea messageArea;

    private JScrollPane scroll;

    private JTextArea textArea = new JTextArea(21, 18);

    public MessageArea() {
        scroll = new JScrollPane(textArea);
        scroll = new JScrollPane(textArea);
        textArea.setEditable(false);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void addMessage(String msg) {
        textArea.append(msg + System.getProperty("line.separator"));
    }

    public JScrollPane getScrollPane() {
        return this.scroll;
    }

    public static MessageArea getInstance() {
        if (messageArea == null) {
            messageArea = new MessageArea();
        }
        return messageArea;
    }

}
