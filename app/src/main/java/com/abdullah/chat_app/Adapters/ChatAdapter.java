package com.abdullah.chat_app.Adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdullah.chat_app.databinding.ItemContainerRecieveMessageBinding;
import com.abdullah.chat_app.databinding.ItemContainerSendMeessageBinding;
import com.abdullah.chat_app.databinding.ItemContainerUserBinding;
import com.abdullah.chat_app.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Bitmap receiveProfileImage;
    private final List<ChatMessage> chatMessages;
    private final String senderId;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;

    public void setReceiveProfileImage(Bitmap bitmap){
        receiveProfileImage=bitmap;
    }

    public ChatAdapter(Bitmap receiveProfileImage, List<ChatMessage> chatMessages, String senderId) {
        this.receiveProfileImage = receiveProfileImage;
        this.chatMessages = chatMessages;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_SENT){
            return new SendMessageViewHolder(
                    ItemContainerSendMeessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }else {
            return new ReceivedMessageViewHolder(
                    ItemContainerRecieveMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(getItemViewType(position) == VIEW_TYPE_SENT){
                ((SendMessageViewHolder)holder).setData(chatMessages.get(position));
            }else {
                ((ReceivedMessageViewHolder)holder).setData(chatMessages.get(position),receiveProfileImage);

            }

    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(chatMessages.get(position).senderId.equals(senderId)){
            return VIEW_TYPE_SENT;
        }else{
            return VIEW_TYPE_RECEIVED;
        }
    }

    static class SendMessageViewHolder extends RecyclerView.ViewHolder{
        private final ItemContainerSendMeessageBinding binding;
        SendMessageViewHolder(ItemContainerSendMeessageBinding itemContainerSendMeessageBinding){
            super(itemContainerSendMeessageBinding.getRoot());
            binding=itemContainerSendMeessageBinding;
        }
        void setData (ChatMessage chatMessage){
            binding.textMessageId.setText(chatMessage.message);
            binding.TextDateId.setText(chatMessage.dateTime);
        }
    }
    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder{
        private final ItemContainerRecieveMessageBinding binding;
        ReceivedMessageViewHolder(ItemContainerRecieveMessageBinding itemContainerRecieveMessageBinding){
            super(itemContainerRecieveMessageBinding.getRoot());
            binding = itemContainerRecieveMessageBinding;
        }
        void setData(ChatMessage chatMessage,Bitmap receiverProfileImage){
            binding.textMessageId.setText(chatMessage.message);
            binding.TextDateID.setText(chatMessage.dateTime);
            if(receiverProfileImage!=null){
                binding.imageProfileId.setImageBitmap(receiverProfileImage);
            }
        }
    }
}
