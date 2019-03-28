package DataTypes;

import java.net.Socket;

public abstract class Data {
    protected boolean isGameData, isReturned, isUpdate; //isReturned means that after the data is sent to the client,
            //the client is expected to return a new datatype of the same type (sometimes variables are changed)
            //isUpdate does not expect a response and will not accept a response, data of isUpdate type are only to replace old data

    protected Socket recipient; //the user this is sent to
    protected String message; //this is not the only data sent, but should be displayed to te user if it on not @null

    public Data(String message, Socket recipient, boolean isGameData, boolean isReturned, boolean isUpdate) {
        this.isGameData = isGameData;
        this.isReturned = isReturned;
        this.isUpdate = isUpdate;
        this.recipient = recipient;
        this.message = message;
    }

    public boolean isGameData() {
        return isGameData;
    }

    public void setGameData(boolean gameData) {
        isGameData = gameData;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public Socket getRecipient() {
        return recipient;
    }

    public void setRecipient(Socket recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Data{" +
                "isGameData=" + isGameData +
                ", isReturned=" + isReturned +
                ", isUpdate=" + isUpdate +
                ", recipient=" + recipient +
                ", message='" + message + '\'' +
                '}';
    }
}
