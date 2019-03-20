package edu.stepik.authorization.services;

import edu.stepik.authorization.entities.UserProfile;

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
