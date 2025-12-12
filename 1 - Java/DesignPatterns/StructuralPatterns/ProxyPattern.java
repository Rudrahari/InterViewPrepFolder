package DesignPatterns.StructuralPatterns;

import java.util.ArrayList;
import java.util.List;

// as the name suggests, it's a substitute for another
interface Internet {
    void connectToHost(String hostName);
}

class PublicNetwork implements Internet {

    @Override
    public void connectToHost(String hostName) {
        System.out.println("Connecting to Host: " + hostName);
    }
}

class PrivateNetwork implements Internet {

    private PublicNetwork publicNetwork;
    private static List<String> restrictedHostNames = new ArrayList<>();

    public PrivateNetwork() {
        publicNetwork = new PublicNetwork();
    }

    static {
        restrictedHostNames.add("host.cvc");
        restrictedHostNames.add("hos.sas");
    }

    @Override
    public void connectToHost(String hostName) {
        if (restrictedHostNames.contains(hostName.toLowerCase())) {
            System.out.println("Access Denied -> Restricted Host, contact admin");
            return;
        }
        publicNetwork.connectToHost(hostName);
    }
}

public class ProxyPattern {
    public static void main(String[] args) {
        Internet companyNetwork = new PrivateNetwork();
        companyNetwork.connectToHost("host.cvc");
        companyNetwork.connectToHost("hos.tye");
    }
}
