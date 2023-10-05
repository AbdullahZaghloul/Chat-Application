package com.abdullah.chat_app.listeners;

import com.abdullah.chat_app.models.User;

public interface ConversationListener {
    void onConversationClicked(User user);
}
