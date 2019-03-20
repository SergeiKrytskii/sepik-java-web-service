package edu.stepik.krytskii.services.impl;

import edu.stepik.krytskii.entities.UserProfile;
import edu.stepik.krytskii.services.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private Map<String, UserProfile> registered ;

    private Map<String, UserProfile> sessions = new HashMap<String, UserProfile>();

    public AccountServiceImpl (){
        registered = new HashMap<String, UserProfile>();
        sessions = new HashMap<String, UserProfile>();
        registered.put("User1234", new UserProfile("User1234", "12345", "google@google.com"));
    }

    public UserProfile getUserByLogin(String login) {
        return registered.get(login);
    }

    public void saveUser(UserProfile userProfile) {
        if (getUserByLogin(userProfile.getLogin()) == null){
            registered.put(userProfile.getLogin(), userProfile);
        }
    }

    public void updateUserProfile(String login, UserProfile userProfile) {
        registered.replace(login, userProfile);
    }

    public void deleteUser(String login) {
        registered.remove(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessions.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile profile) {
        sessions.put(sessionId, profile);
    }

    public void deleteSession(String sessionId) {
        sessions.remove(sessionId);
    }
    public void logout(String login) {
        sessions.remove(login);
    }

    public int getSize() {
        return registered.size();
    }
}
