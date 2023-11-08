package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class ChatHelper {
    private Set<String> userName = new HashSet<>();
    private Set<UserInstance> UserInstance = new HashSet<>();
    private String ctM = String.valueOf(System.currentTimeMillis());
    private File logFile = new File("." + "\\" + "ChatApp_logFile" + ctM + ".txt");

    public void broadcast(String message, UserInstance excludeUser) {
        for (UserInstance user : UserInstance) {
            if (user != excludeUser) {
                user.sendMessage(message);
            }
        }
        logData(message);
    }


    public void addUserName(String userToAdd) {
        userName.add(userToAdd);
    }


    public void removeUser(String userToRemove, UserInstance userLocation) {
        if (userName.remove(userToRemove)) {
            System.out.println("User: " + userToRemove + " has left the conversation.");
            UserInstance.remove(userLocation);
        }
    }


    public Set<String> getuserName() {
        return this.userName;
    }


    public boolean hasUsers() {
        boolean hasUsers;
        if(this.userName.isEmpty()) {
            hasUsers = false;
        } else {
            hasUsers = true;
        }
        return hasUsers;
    }


    public void UserThreadAdd(UserInstance newUser) {
        this.UserInstance.add(newUser);
    }

    public void logData(String stringToWrite) {
        try {
            FileWriter fr = new FileWriter(logFile, true);
            fr.write(stringToWrite + "\n");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}