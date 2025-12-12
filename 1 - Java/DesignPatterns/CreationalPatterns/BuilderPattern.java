package DesignPatterns.CreationalPatterns;

class User {
    private String userId;
    private String userName;
    private String userEmail;
    private String userLegalName;
    private int userAge;

    private User(UserBuilder userBuilder) {
        this.userId = userBuilder.userId;
        this.userName = userBuilder.userName;
        this.userEmail = userBuilder.userEmail;
        this.userLegalName = userBuilder.userLegalName;
        this.userAge = userBuilder.userAge;
    }

    static class UserBuilder {
        private String userId;
        private String userName;
        private String userEmail;
        private String userLegalName;
        private int userAge;

        public UserBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder setUserEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public UserBuilder setUserLegalName(String userLegalName) {
            this.userLegalName = userLegalName;
            return this;
        }

        public UserBuilder setUserAge(int userAge) {
            this.userAge = userAge;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userLegalName='" + userLegalName + '\'' +
                ", userAge=" + userAge +
                '}';
    }
}
// Builder pattern is helpful when dealing with object creation of complex objects
// where we have to take a look at the constructor params to know at which params goes where
public class BuilderPattern {
    public static void main(String[] args) {
        User user = new User.
                UserBuilder().
                setUserName("userName").
                setUserId("userId").
                setUserAge(23).
                setUserEmail("userEmail").
                setUserLegalName("userLegalName").
                build();
        System.out.println(user);
    }
}
