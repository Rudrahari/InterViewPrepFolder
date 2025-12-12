package DesignPatterns.CreationalPatterns;

interface Configuration extends Cloneable {
    Object clone() throws CloneNotSupportedException;
}

class WindowsConfiguration implements Configuration {

    String property;

    WindowsConfiguration(String property) {
        this.property = property;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class LinuxConfiguration implements Configuration {

    String property;

    LinuxConfiguration(String property) {
        this.property = property;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class MacConfiguration implements Configuration {

    String property;

    MacConfiguration(String property) {
        this.property = property;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

// prototype pattern where we can reuse one instance
public class PrototypePattern {
    public static void main(String[] args) throws CloneNotSupportedException {

        Configuration windowsConfiguration =
                new WindowsConfiguration("windows");
        WindowsConfiguration anotherWindows =
                (WindowsConfiguration) windowsConfiguration.clone();

        Configuration linuxConfiguration =
                new LinuxConfiguration("linux");
        LinuxConfiguration anotherLinux =
                (LinuxConfiguration) linuxConfiguration.clone();

        Configuration macConfiguration =
                new MacConfiguration("mac");
        MacConfiguration anotherMac =
                (MacConfiguration) macConfiguration.clone();
    }
}
