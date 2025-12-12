package DesignPatterns.BehavioralPatterns;

import java.util.ArrayList;
import java.util.List;

// mediator interface
interface GroupChatMediator {
    void addGroupMember(GroupMember groupMember);

    void removeGroupMember(GroupMember groupMember);

    void sendMessage(String message, GroupMember sender);
}

// concrete mediator class
class GroupChat implements GroupChatMediator {

    List<GroupMember> groupChatMembers;

    GroupChat() {
        groupChatMembers = new ArrayList<>();
    }

    @Override
    public void addGroupMember(GroupMember groupMember) {
        groupChatMembers.add(groupMember);
    }

    @Override
    public void removeGroupMember(GroupMember groupMember) {
        groupChatMembers.remove(groupMember);
    }

    @Override
    public void sendMessage(String message, GroupMember sender) {
        if(!groupChatMembers.contains(sender)){
            System.out.println(sender.groupMemberName+ " removed from group cannot send message");
        }
        for (GroupMember receiver : groupChatMembers) {
            if (receiver == sender) continue;
            receiver.receiveGroupMessage(message);
        }
    }
}

// colleague abstraction
abstract class GroupMember {
    GroupChatMediator groupChatMediator;
    String groupMemberName;

    public GroupMember(GroupChatMediator groupChatMediator, String groupMemberName) {
        this.groupChatMediator = groupChatMediator;
        this.groupMemberName = groupMemberName;
    }

    abstract void sendGroupMessage(String message);
    abstract void receiveGroupMessage(String message);
}

// concrete colleague class
class GroupChatMember extends GroupMember{

    public GroupChatMember(GroupChatMediator groupChatMediator,
                           String groupMemberName) {
        super(groupChatMediator,groupMemberName);
    }

    @Override
    void sendGroupMessage(String message) {
        groupChatMediator.sendMessage(message,this);
    }

    @Override
    void receiveGroupMessage(String message) {
        System.out.println(groupMemberName+" Received Message: "+message);
    }
}

// Mediator design patterns is helpful when multiple objects/colleagues are interlinked
// but instead of direct communication between them, a mediator will decouple the dependency among them
public class MediatorPattern {
    public static void main(String[] args) {
        GroupChatMediator groupChat=new GroupChat();
        GroupMember userA=new GroupChatMember(groupChat,"userA");
        GroupMember userB=new GroupChatMember(groupChat,"userB");
        GroupMember userC=new GroupChatMember(groupChat,"userC");
        groupChat.addGroupMember(userA);
        groupChat.addGroupMember(userB);
        groupChat.addGroupMember(userC);

        userA.sendGroupMessage("Hello");

        // just run this you will be surprised with the output ordering, until you saw the method call
        groupChat.removeGroupMember(userC);
        userB.sendGroupMessage("Hello All!");
        userC.sendGroupMessage("Hello All!");
    }
}
