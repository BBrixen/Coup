package DataTypes;

import java.net.Socket;

public class CommunicationData extends Data {

    public CommunicationData(String message, Socket recipient, boolean isGameData, boolean isReturned, boolean isUpdate) {
        super(message, recipient, isGameData, isReturned, isUpdate);
    }
}
