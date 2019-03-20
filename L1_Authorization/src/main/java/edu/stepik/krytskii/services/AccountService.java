package edu.stepik.krytskii.services;

import edu.stepik.krytskii.entities.UserProfile;

public interface AccountService {

    UserProfile getUserByLogin(String login);

    void saveUser(UserProfile userProfile);

    void updateUserProfile (String login, UserProfile userProfile);

    void deleteUser(String login);

    UserProfile getUserBySessionId (String sessionId);

    void addSession(String sessionId, UserProfile profile);

    void deleteSession(String sessionId);

    int getSize();

}
