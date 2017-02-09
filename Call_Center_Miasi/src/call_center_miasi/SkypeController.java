package call_center_miasi;

import com.skype.*;

import javax.swing.*;

public class SkypeController {

    private static SkypeController skypeController;

    private String name;

    private MessageArea messageArea = MessageArea.getInstance();

    public SkypeController() {
        addCallListener();
        addMessageListener();
        Skype.setDaemon(false);

    }

    public void setName(String name) {
        this.name = name;
    }

    public void makeCall() {
        try {
            Call call = Skype.call(name);
            while (call.getStatus() != Call.Status.RINGING) {
                if (call.getStatus() != Call.Status.ROUTING) {
                    Skype.call(name);
                }
            }
        } catch (CommandFailedException exc) {

        } catch (SkypeException e) {
            e.printStackTrace();
        }
    }

    private void addCallListener() {
        try {
            Skype.addCallMonitorListener(new CallMonitorListener() {
                @Override
                public void callMonitor(Call call, Call.Status status) throws SkypeException {
                    if (status == Call.Status.RINGING) {
                        int answer = JOptionPane.showConfirmDialog(null, "Odbierz", "połączenie przychodzące", JOptionPane.YES_NO_OPTION);
                        if (answer == JOptionPane.YES_OPTION) {
                            call.answer();
                        } else {
                            call.cancel();
                        }
                    }
                }
            });
        } catch (SkypeException e) {
            e.printStackTrace();
        }
    }

    public static SkypeController getInstance() {
        if (skypeController == null) {
            skypeController = new SkypeController();
        }
        return skypeController;
    }

    public void sendMessage(String message) {
        try {
            Skype.chat(name).send(message);
        } catch (SkypeException e) {
            e.printStackTrace();
        }
    }

    private void addMessageListener() {
        try {
            Skype.addChatMessageListener(new ChatMessageAdapter() {
                public void chatMessageReceived(ChatMessage received) throws SkypeException {
                    if (received.getType().equals(ChatMessage.Type.SAID)) {
                        messageArea.addMessage(received.getSender() + ": " + received.getContent());
                    }
                }
            });
        } catch (SkypeException e) {
            e.printStackTrace();
        }
    }

}
